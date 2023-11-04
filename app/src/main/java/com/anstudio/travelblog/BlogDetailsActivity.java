package com.anstudio.travelblog;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.Nullable;

public class BlogDetailsActivity extends AppCompatActivity {

    public static final String IMAGE_URL =
            "https://bitbucket.org/dmytrodanylyk/travel-blog-resources/raw/"+
                    "3436e16367c8ec2312a0644bebd2694d484eb047/images/sydney_image.jpg";
    public static final String AVATAR_URL =
            "https://bitbucket.org/dmytrodanylyk/travel-blog-resources/raw/"+
                    "3436e16367c8ec2312a0644bebd2694d484eb047/avatars/avatar1.jpg";

    @Override
    protected void onCreate(@Nullable Bundle saveInstanceState){

        // Bind Java Class with Xml class
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_blog_details);

        // Bind Elements inside the XML Page with Java Fields
        ImageView imageMain = findViewById(R.id.imageMain);
        Glide.with(this)
                .load(IMAGE_URL)
                .into(imageMain);
//        imageMain.setImageResource(R.drawable.sydney_image);

        ImageView imageAvatar = findViewById(R.id.imageAvatar);
        Glide.with(this)
                .load(AVATAR_URL)
                .into(imageAvatar);
//        imageAvatar.setImageResource(R.drawable.avatar);

        TextView textTitle = findViewById(R.id.textTitle);
        textTitle.setText("G'day from Sydney");

        TextView textDate = findViewById(R.id.textDate);
        textDate.setText("November 3, 2023");

        TextView textAuthor = findViewById(R.id.textAuthor);
        textAuthor.setText("Anas El");

        TextView textRating = findViewById(R.id.textRating);
        textRating.setText("4.4");

        TextView textViews = findViewById(R.id.textViews);
        textViews.setText("2521 views");

        TextView textDescription = findViewById(R.id.textDescription);
        textDescription.setText("Australia is one of the most popular travel destinations in the world.");

        RatingBar ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setRating(4.4f);

        ImageView imageBack = findViewById(R.id.imageBack);
        imageBack.setOnClickListener(v -> finish());
    }
}
