package bg.sofia.uni.fmi.mjt.socialmedia.content.enums;

public enum TypeContent {

    POST("Post"),
    STORY("Story");

    private String type;

    TypeContent(String type) {
        this.type = type;
    }

    public String getTypeValue() {
        return type;
    }
}
