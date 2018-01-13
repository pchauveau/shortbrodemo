package com.shortbrodemo.pch.shortbrodemo.model;

import java.util.List;


public class Node {
    boolean comments_disabled;
    long id;
    EdgeMediaToCaption edge_media_to_caption;
    String shortcode;
    BasicIntObject edge_media_to_comment;
    String taken_at_timestamp;
    Dimension dimensions;
    String display_url;
    BasicIntObject edge_liked_by;
    Owner owner;
    String thumbnail_src;
    List<Thumbnails> thumbnail_resources;
    boolean is_video;
    String text;

    public boolean isComments_disabled() {
        return comments_disabled;
    }

    public void setComments_disabled(boolean comments_disabled) {
        this.comments_disabled = comments_disabled;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public EdgeMediaToCaption getEdge_media_to_caption() {
        return edge_media_to_caption;
    }

    public void setEdge_media_to_caption(EdgeMediaToCaption edge_media_to_caption) {
        this.edge_media_to_caption = edge_media_to_caption;
    }

    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }

    public BasicIntObject getEdge_media_to_comment() {
        return edge_media_to_comment;
    }

    public void setEdge_media_to_comment(BasicIntObject edge_media_to_comment) {
        this.edge_media_to_comment = edge_media_to_comment;
    }

    public String getTaken_at_timestamp() {
        return taken_at_timestamp;
    }

    public void setTaken_at_timestamp(String taken_at_timestamp) {
        this.taken_at_timestamp = taken_at_timestamp;
    }

    public Dimension getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimension dimensions) {
        this.dimensions = dimensions;
    }

    public String getDisplay_url() {
        return display_url;
    }

    public void setDisplay_url(String display_url) {
        this.display_url = display_url;
    }

    public BasicIntObject getEdge_liked_by() {
        return edge_liked_by;
    }

    public void setEdge_liked_by(BasicIntObject edge_liked_by) {
        this.edge_liked_by = edge_liked_by;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getThumbnail_src() {
        return thumbnail_src;
    }

    public void setThumbnail_src(String thumbnail_src) {
        this.thumbnail_src = thumbnail_src;
    }

    public List<Thumbnails> getThumbnail_resources() {
        return thumbnail_resources;
    }

    public void setThumbnail_resources(List<Thumbnails> thumbnail_resources) {
        this.thumbnail_resources = thumbnail_resources;
    }

    public boolean isIs_video() {
        return is_video;
    }

    public void setIs_video(boolean is_video) {
        this.is_video = is_video;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
