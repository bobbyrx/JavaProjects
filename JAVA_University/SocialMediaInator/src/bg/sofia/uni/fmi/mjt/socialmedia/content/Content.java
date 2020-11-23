package bg.sofia.uni.fmi.mjt.socialmedia.content;

import java.util.Collection;

public interface Content {

    int getNumberOfLikes();

    int getNumberOfComments();

    String getId();

    Collection<String> getTags();

    Collection<String> getMentions();
}
