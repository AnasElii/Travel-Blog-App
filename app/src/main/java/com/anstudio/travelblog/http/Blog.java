package com.anstudio.travelblog.http;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import androidx.room.*;

@Entity
public class Blog implements Parcelable {

    @PrimaryKey
    private int id;

    @Embedded
    private Author author;

    private String title;
    private String date;
    private static final SimpleDateFormat dateForma = new SimpleDateFormat("MMMM dd, yyyy");
    private String description;
    private int views;
    private float rating;
    private String image;

    public Blog(int id, Author author, String title, String date, String image,
                String description, int views, float rating) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.date = date;
        this.image = image;
        this.description = description;
        this.views = views;
        this.rating = rating;
    }

    protected Blog(Parcel in) {
        id = in.readInt();
        title = in.readString();
        date = in.readString();
        image = in.readString();
        description = in.readString();
        views = in.readInt();
        rating = in.readFloat();
        author = in.readParcelable(Author.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(date);
        dest.writeString(image);
        dest.writeString(description);
        dest.writeInt(views);
        dest.writeFloat(rating);
        dest.writeParcelable(author, 0);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Blog> CREATOR = new Creator<Blog>() {
        @Override
        public Blog createFromParcel(Parcel in) {
            return new Blog(in);
        }

        @Override
        public Blog[] newArray(int size) {
            return new Blog[size];
        }
    };

    public Integer getId() {
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

    public Long getDateMillis(){
        try{
            Date date = dateForma.parse(getDate());
            return date != null ? date.getTime() : null;
        } catch (ParseException e){
            e.printStackTrace();
        }

        return null;
    }

    public String getImage() { return image; }

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



    // Override the equals method
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Blog blog = (Blog) o;

        return views == blog.views &&
                Float.compare(blog.rating, rating) == 0 &&
                Objects.equals(id, blog.id) &&
                Objects.equals(author, blog.author) &&
                Objects.equals(title, blog.title) &&
                Objects.equals(date, blog.date) &&
                Objects.equals(image, blog.image) &&
                Objects.equals(description, blog.description);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, author, title, date, image, description, views, rating);
    }
}
