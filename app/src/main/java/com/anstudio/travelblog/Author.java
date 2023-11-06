package com.anstudio.travelblog;

public class Author {
    private String name;
    private String avatar;


    public String getName() {
        return name;
    }

    public String getAvatarURL() {

        return BlogHttpClient.BASE_URL + BlogHttpClient.PATH + avatar;
    }
}
