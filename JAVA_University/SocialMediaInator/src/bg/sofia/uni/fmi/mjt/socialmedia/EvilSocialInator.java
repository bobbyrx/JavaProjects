package bg.sofia.uni.fmi.mjt.socialmedia;

import bg.sofia.uni.fmi.mjt.socialmedia.content.Content;
import bg.sofia.uni.fmi.mjt.socialmedia.content.ImplementedContent;
import bg.sofia.uni.fmi.mjt.socialmedia.content.enums.TypeContent;
import bg.sofia.uni.fmi.mjt.socialmedia.exceptions.ContentNotFoundException;
import bg.sofia.uni.fmi.mjt.socialmedia.exceptions.NoUsersException;
import bg.sofia.uni.fmi.mjt.socialmedia.exceptions.UsernameAlreadyExistsException;
import bg.sofia.uni.fmi.mjt.socialmedia.exceptions.UsernameNotFoundException;
import bg.sofia.uni.fmi.mjt.socialmedia.multielements.Pair;
import bg.sofia.uni.fmi.mjt.socialmedia.multielements.Triple;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EvilSocialInator implements SocialMediaInator {

    private Map<String, List<ImplementedContent>> userMadeContentLog;
    private Map<String, Integer> userMentionCount;
    private Map<String, ImplementedContent> madeContentLog;
    private Map<String, List<Pair<String, LocalDateTime>>> likedContentLog;
    private Map<String, List<Triple<String, String, LocalDateTime>>> commentedContentLog;
    private int integerID;

    private int autoIncrementIntegerID() {
        return ++integerID;
    }

    private void increaseUserMentionCount(String username) {

        int value = userMentionCount.get(username);
        userMentionCount.put(username, value + 1);
    }

    /**
     * Making a general function to create a content (depending on the type).
     * Also counting user mentions when he is being mention (@username) and saving it
     * in the content's list of mentions. When finding a tag (#keyword) put it in content's list of tags.
     * Saving the content in two places - userMadeContentLog and madeContentLog.
     * In the first one we save all the content made by the user.
     * In the second one we save all the content made on the platform.
     *
     * @param username    String
     * @param publishedOn LocalDateTime
     * @param description String
     * @param type        TypeContent
     * @return the id of the newly created content.
     */
    private String publishContent(String username, LocalDateTime publishedOn, String description, TypeContent type) {

        if (username == null) {
            throw new IllegalArgumentException("Username is null!");
        }
        if (publishedOn == null) {
            throw new IllegalArgumentException("PublishedOn date is null!");
        }
        if (description == null) {
            throw new IllegalArgumentException("Description is null!");
        }
        if (!userMadeContentLog.containsKey(username)) {
            throw new UsernameNotFoundException("Username is not found!");
        }

        String newId = username + "-" + this.autoIncrementIntegerID();
        ImplementedContent newContent = new ImplementedContent(newId, publishedOn, type);

        String[] listOfWords =
                description.replaceAll("\\s{2,}", " ").trim().split(" ");
        for (String word : listOfWords) {

            if (word.startsWith("@")
                    && userMadeContentLog.containsKey(word.substring(1))) {

                this.increaseUserMentionCount(word.substring(1));
                newContent.addMentionsInContent(word.substring(1));

            }
            if (word.startsWith("#")) {
                newContent.addTagsInContent(word);
            }
        }

        this.userMadeContentLog.get(username).add(newContent);
        this.madeContentLog.put(newId, newContent);

        return newId;
    }

    public EvilSocialInator() {
        this.userMadeContentLog = new LinkedHashMap<>();
        this.userMentionCount = new LinkedHashMap<>();
        this.madeContentLog = new LinkedHashMap<>();
        this.likedContentLog = new LinkedHashMap<>();
        this.commentedContentLog = new LinkedHashMap<>();
    }

    /**
     * Saving the user in the platform.
     *
     * @param username String
     */
    @Override
    public void register(String username) {

        if (username == null) {
            throw new IllegalArgumentException("Username is null!");
        }
        if (userMadeContentLog.containsKey(username)) {
            throw new UsernameAlreadyExistsException("Username already exist!");
        }

        userMadeContentLog.put(username, new ArrayList<>());
        likedContentLog.put(username, new ArrayList<>());
        commentedContentLog.put(username, new ArrayList<>());
        userMentionCount.put(username, 0);
    }

    /**
     * Creates a post.
     *
     * @param username    String
     * @param publishedOn LocalDateTime
     * @param description String
     * @return the id of the newly created post.
     */
    @Override
    public String publishPost(String username, LocalDateTime publishedOn, String description) {
        return this.publishContent(username, publishedOn, description, TypeContent.POST);
    }

    /**
     * Created a story.
     *
     * @param username    String
     * @param publishedOn LocalDateTime
     * @param description String
     * @return the id of the newly created story.
     */
    @Override
    public String publishStory(String username, LocalDateTime publishedOn, String description) {
        return this.publishContent(username, publishedOn, description, TypeContent.STORY);
    }

    /**
     * Increases the count of likes on the content.
     * Also saves the id and the date of the given like in likedContentLog.
     *
     * @param username String
     * @param id       String
     */
    @Override
    public void like(String username, String id) {

        if (username == null) {
            throw new IllegalArgumentException("Username is null!");
        }
        if (id == null) {
            throw new IllegalArgumentException("Id is null!");
        }
        if (!userMadeContentLog.containsKey(username)) {
            throw new UsernameNotFoundException("Username is not found!");
        }
        if (!madeContentLog.containsKey(id)) {
            throw new ContentNotFoundException("Content is not found!");
        }

        madeContentLog.get(id).increaseLikeCount();
        likedContentLog.get(username).add(new Pair<>(id, LocalDateTime.now()));
    }

    /**
     * Increases the count of comments on the content.
     * Also saves the id , text (the real comment)
     * and the date of the posted comment in commentedContentLog.
     *
     * @param username String
     * @param text     String
     * @param id       String
     */
    @Override
    public void comment(String username, String text, String id) {

        if (username == null) {
            throw new IllegalArgumentException("Username is null!");
        }
        if (id == null) {
            throw new IllegalArgumentException("Id is null!");
        }
        if (text == null) {
            throw new IllegalArgumentException("text is null!");
        }
        if (!userMadeContentLog.containsKey(username)) {
            throw new UsernameNotFoundException("Username is not found!");
        }
        if (!madeContentLog.containsKey(id)) {
            throw new ContentNotFoundException("Content is not found!");
        }

        madeContentLog.get(id).increaseCommentCount();
        commentedContentLog.get(username).add(new Triple<>(id, text, LocalDateTime.now()));
    }

    /**
     * First check and puts in a new List only non-expired content.
     * Then sorts them by popularity (number of likes and comments).
     *
     * @param n int
     * @return the top n most popular content.
     */
    @Override
    public Collection<Content> getNMostPopularContent(int n) {

        if (n < 0) {
            throw new IllegalArgumentException("N number is null!");
        }

        List<Content> newCollection = new LinkedList<>();
        for (ImplementedContent newContent : madeContentLog.values()) {
            if (newContent.getLocalDateTime().isBefore(LocalDateTime.now())) {
                if (newContent.getType().getTypeValue().equals("Story")
                        && Math.abs(Duration.between(LocalDateTime.now(),
                        newContent.getLocalDateTime()).toHours()) < 24) {
                    newCollection.add(newContent);
                } else if (newContent.getType().getTypeValue().equals("Post")
                        && Math.abs(Duration.between(LocalDateTime.now(),
                        newContent.getLocalDateTime()).toDays()) < 30) {
                    newCollection.add(newContent);
                }
            }
        }

        newCollection.sort(new Comparator<Content>() {
            @Override
            public int compare(Content o1, Content o2) {
                int likesAndComments1 = o1.getNumberOfComments() + o1.getNumberOfLikes();
                int likesAndComments2 = o2.getNumberOfComments() + o2.getNumberOfLikes();
                return Integer.compare(likesAndComments1, likesAndComments2);
            }
        });

        if (newCollection.isEmpty()) {
            return newCollection;
        }
        if (n >= newCollection.size()) {
            return Collections.unmodifiableCollection(newCollection);
        }

        return Collections.unmodifiableCollection(newCollection.subList(0, n));
    }

    /**
     * First check and puts in a new List only non-expired content.
     * Firstly sorts them by date (the more resent).
     * Only takes the top n contents.
     * Then sorts them by popularity (number of likes and comments).
     *
     * @param username String
     * @param n        int
     * @return the n most recent content from a certain user sorted by popularity.
     */
    @Override
    public Collection<Content> getNMostRecentContent(String username, int n) {

        if (n < 0) {
            throw new IllegalArgumentException("N number is null!");
        }
        if (username == null) {
            throw new IllegalArgumentException("Username is null!");
        }
        if (!userMadeContentLog.containsKey(username)) {
            throw new UsernameNotFoundException("Username is not found!");
        }

        List<ImplementedContent> newCollection = new LinkedList<>();
        for (ImplementedContent newContent : userMadeContentLog.get(username)) {
            if (newContent.getLocalDateTime().isBefore(LocalDateTime.now())) {
                if (newContent.getType().getTypeValue().equals("Story")
                        && Math.abs(Duration.between(LocalDateTime.now(),
                        newContent.getLocalDateTime()).toHours()) < 24) {
                    newCollection.add(newContent);
                } else if (newContent.getType().getTypeValue().equals("Post")
                        && Math.abs(Duration.between(LocalDateTime.now(),
                        newContent.getLocalDateTime()).toDays()) < 30) {
                    newCollection.add(newContent);
                }
            }
        }

        newCollection.sort(new Comparator<ImplementedContent>() {
            @Override
            public int compare(ImplementedContent o1, ImplementedContent o2) {
                return o1.getLocalDateTime().compareTo(o2.getLocalDateTime());
            }
        });

        List<Content> contentCollector = new LinkedList<>(newCollection);

        if (contentCollector.isEmpty()) {
            return contentCollector;
        }

        if (n >= contentCollector.size()) {
            contentCollector.sort(new Comparator<Content>() {
                @Override
                public int compare(Content o1, Content o2) {
                    int likesAndComments1 = o1.getNumberOfComments() + o1.getNumberOfLikes();
                    int likesAndComments2 = o2.getNumberOfComments() + o2.getNumberOfLikes();
                    return Integer.compare(likesAndComments1, likesAndComments2);
                }
            });
            return Collections.unmodifiableCollection(contentCollector);
        }

        List<Content> subList = new LinkedList<>(contentCollector.subList(0, n));

        subList.sort(new Comparator<Content>() {
            @Override
            public int compare(Content o1, Content o2) {
                int likesAndComments1 = o1.getNumberOfComments() + o1.getNumberOfLikes();
                int likesAndComments2 = o2.getNumberOfComments() + o2.getNumberOfLikes();
                return Integer.compare(likesAndComments1, likesAndComments2);
            }
        });

        return Collections.unmodifiableCollection(subList);
    }

    /**
     * @return the most mentioned user on the platform.
     */
    @Override
    public String getMostPopularUser() {

        int max = Integer.MIN_VALUE;
        String username = "";
        for (String name : userMentionCount.keySet()) {
            if (Integer.max(max, userMentionCount.get(name)) != max) {
                username = name;
                max = userMentionCount.get(name);

            }
        }

        if (username.isEmpty()) {
            throw new NoUsersException("No users!");
        }

        return username;
    }

    /**
     * First checks and puts in a new List only non-expired content.
     * Then checks if each of these contents has this tag.
     *
     * @param tag String
     * @return collection of all contents that have a certain tag on them.
     */
    @Override
    public Collection<Content> findContentByTag(String tag) {

        if (tag == null) {
            throw new IllegalArgumentException("Tag is null!");
        }

        List<ImplementedContent> newCollection = new ArrayList<>();
        for (ImplementedContent newContent : madeContentLog.values()) {
            if (newContent.getLocalDateTime().isBefore(LocalDateTime.now())) {
                if (newContent.getType().getTypeValue().equals("Story")
                        && Math.abs(Duration.between(LocalDateTime.now(),
                        newContent.getLocalDateTime()).toHours()) < 24) {
                    newCollection.add(newContent);
                } else if (newContent.getType().getTypeValue().equals("Post")
                        && Math.abs(Duration.between(LocalDateTime.now(),
                        newContent.getLocalDateTime()).toDays()) < 30) {
                    newCollection.add(newContent);
                }
            }
        }

        List<Content> tagCollection = new ArrayList<>();
        for (int i = 0; i < newCollection.size(); i++) {
            if (newCollection.get(i).getTags().contains(tag)) {
                tagCollection.add(newCollection.get(i));
            }
        }

        return Collections.unmodifiableCollection(tagCollection);
    }

    /**
     * This includes likes, comments, post and stories.
     * For every one of them should be written the date
     * when they were made and the id of the content.
     * Also if it is a comment it should be written the actual text of the comment.
     *
     * @param username String
     * @return list of a certain user activity log.
     */
    @Override
    public List<String> getActivityLog(String username) {

        if (username == null) {
            throw new IllegalArgumentException("Username is null!");
        }
        if (!userMadeContentLog.containsKey(username)) {
            throw new UsernameNotFoundException("Username is not found!");
        }

        List<ImplementedContent> newList = new ArrayList<>(userMadeContentLog.get(username));
        List<Pair<String, LocalDateTime>> newLikeList = new ArrayList<>(likedContentLog.get(username));
        List<Triple<String, String, LocalDateTime>> newCommentList = new ArrayList<>(commentedContentLog.get(username));
        List<Pair<LocalDateTime, String>> newLogList = new ArrayList<>();

        for (ImplementedContent implementedContent : newList) {
            String line = " Created a ";
            if (implementedContent.getType().getTypeValue().equals("Post")) {
                line += "post";
            } else if (implementedContent.getType().getTypeValue().equals("Story")) {
                line += "story";
            }
            line += " with id " + implementedContent.getId();
            newLogList.add(new Pair<>(implementedContent.getLocalDateTime(), line));
        }

        for (int i = 0; i < newLikeList.size(); i++) {
            String line = " Liked a content with id " + newLikeList.get(i).getFirst();
            newLogList.add(new Pair<>(newLikeList.get(i).getSecond(), line));
        }

        for (int i = 0; i < newCommentList.size(); i++) {
            String line = " Commented " + newCommentList.get(i).getSecond()
                    + " on a content with id " + newLikeList.get(i).getFirst();
            newLogList.add(new Pair<>(newCommentList.get(i).getThird(), line));
        }

        newLogList.sort(new Comparator<Pair<LocalDateTime, String>>() {
            @Override
            public int compare(Pair<LocalDateTime, String> o1, Pair<LocalDateTime, String> o2) {
                return o1.getFirst().compareTo(o2.getFirst());
            }
        });

        List<String> returnLog = new ArrayList<>();
        for (int i = 0; i < newLogList.size(); i++) {
            String line = newLogList.get(i).getFirst().format(DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yy"))
                    + ":" + newLogList.get(i).getSecond();
            returnLog.add(line);
        }

        return returnLog;
    }
}

