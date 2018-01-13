package com.shortbrodemo.pch.shortbrodemo.model;

public class Hashtag {
    String name;
    boolean is_top_media_only;
    String content_advisory;
    EdgeHashtagToMedia edge_hashtag_to_media;
    // TopPostsJsonObjectInstagram top_posts;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIs_top_media_only() {
        return is_top_media_only;
    }

    public void setIs_top_media_only(boolean is_top_media_only) {
        this.is_top_media_only = is_top_media_only;
    }

    public String getContent_advisory() {
        return content_advisory;
    }

    public void setContent_advisory(String content_advisory) {
        this.content_advisory = content_advisory;
    }

    public EdgeHashtagToMedia getEdge_hashtag_to_media() {
        return edge_hashtag_to_media;
    }

    public void setEdge_hashtag_to_media(EdgeHashtagToMedia edge_hashtag_to_media) {
        this.edge_hashtag_to_media = edge_hashtag_to_media;
    }
}
