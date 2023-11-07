package com.anstudio.travelblog;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Author implements Parcelable {
    private String name;
    private String avatar;

    protected Author(Parcel parcel){
        name = parcel.readString();
        avatar = parcel.readString();
    }

    @Override
    public void writeToParcel(Parcel dese, int flag){
        dese.writeString(name);
        dese.writeString(avatar);
    }

    @Override
    public int describeContents(){
        return 0;
    }

    public static final Creator<Author> CREATOR = new Creator<Author>() {
        @Override
        public Author createFromParcel(Parcel in){
            return new Author(in);
        }

        @Override
        public Author[] newArray(int size){
            return new Author[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getAvatarURL() {

        return BlogHttpClient.BASE_URL + BlogHttpClient.PATH + avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(name, author.name) &&
                Objects.equals(avatar, author.avatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, avatar);
    }
}
