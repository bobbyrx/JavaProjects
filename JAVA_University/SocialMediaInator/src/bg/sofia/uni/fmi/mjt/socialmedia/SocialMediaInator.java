package bg.sofia.uni.fmi.mjt.socialmedia;


import bg.sofia.uni.fmi.mjt.socialmedia.content.Content;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface SocialMediaInator {
    void register(String username);

    String publishPost(String username, LocalDateTime publishedOn, String description);

    String publishStory(String username, LocalDateTime publishedOn, String description);

    void like(String username, String id);

    void comment(String username, String text, String id);

    Collection<Content> getNMostPopularContent(int n);

    Collection<Content> getNMostRecentContent(String username, int n);

    String getMostPopularUser();

    Collection<Content> findContentByTag(String tag);

    List<String> getActivityLog(String username);
}
