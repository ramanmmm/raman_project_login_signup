package com.example.ramanmishraproject;

import java.util.List;

public class RedditPost {
    private String postLink;
    private String subreddit;
    private String title;
    private String url;
    private boolean nsfw;
    private boolean spoiler;
    private String author;
    private int ups;
    private List<String> preview;

    // Constructor
    public RedditPost(String postLink, String subreddit) {
        this.postLink = postLink;
        this.subreddit = subreddit;
        this.title = title;
        this.url = url;
        this.nsfw = nsfw;
        this.spoiler = spoiler;
        this.author = author;
        this.ups = ups;
        this.preview = preview;
    }

    // Getters and Setters
    public String getPostLink() {
        return postLink;
    }

    public void setPostLink(String postLink) {
        this.postLink = postLink;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isNsfw() {
        return nsfw;
    }

    public void setNsfw(boolean nsfw) {
        this.nsfw = nsfw;
    }

    public boolean isSpoiler() {
        return spoiler;
    }

    public void setSpoiler(boolean spoiler) {
        this.spoiler = spoiler;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getUps() {
        return ups;
    }

    public void setUps(int ups) {
        this.ups = ups;
    }

    public List<String> getPreview() {
        return preview;
    }

    public void setPreview(List<String> preview) {
        this.preview = preview;
    }
}
