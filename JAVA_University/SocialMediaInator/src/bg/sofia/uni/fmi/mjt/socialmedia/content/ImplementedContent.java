package bg.sofia.uni.fmi.mjt.socialmedia.content;

import bg.sofia.uni.fmi.mjt.socialmedia.content.enums.TypeContent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ImplementedContent implements Content {

    private int numberOfLikes;
    private int numberOfComments;
    private String uniqueId;
    private LocalDateTime localDateTime;
    private List<String> tagsInContent;
    private List<String> mentionsInContent;
    private TypeContent type;

    public ImplementedContent(String uniqueId, LocalDateTime localDateTime, TypeContent type) {
        this.uniqueId = uniqueId;
        this.localDateTime = localDateTime;
        this.type = type;
        this.mentionsInContent = new ArrayList<>();
        this.tagsInContent = new ArrayList<>();
    }

    public TypeContent getType() {
        return type;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void addTagsInContent(String tag) {
        tagsInContent.add(tag);
    }

    public void addMentionsInContent(String mention) {
        mentionsInContent.add(mention);
    }

    public void increaseLikeCount() {
        this.numberOfLikes++;
    }

    public void increaseCommentCount() {
        this.numberOfComments++;
    }

    @Override
    public int getNumberOfLikes() {
        return this.numberOfLikes;
    }

    @Override
    public int getNumberOfComments() {
        return this.numberOfComments;
    }

    @Override
    public String getId() {
        return this.uniqueId;
    }

    @Override
    public Collection<String> getTags() {
        return tagsInContent;
    }

    @Override
    public Collection<String> getMentions() {
        return mentionsInContent;
    }
}
