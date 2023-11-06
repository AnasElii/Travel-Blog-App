package com.anstudio.travelblog;

public class Blog {
    private String id;
    private Author author;
    private String title;
    private String date;
    private String description;
    private int views;
    private float rating;
    private String image;

    public String getId() {
        return id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getImageURL() {

        return BlogHttpClient.BASE_URL + BlogHttpClient.PATH + image;

    }

    public String getDescription() {
        return description;
    }

    public int getViews() {
        return views;
    }

    public float getRating() {
        return rating;
    }
}
