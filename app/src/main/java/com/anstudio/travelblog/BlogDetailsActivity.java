package com.anstudio.travelblog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.anstudio.travelblog.http.Blog;
import com.anstudio.travelblog.http.BlogArticlesCallback;
import com.anstudio.travelblog.http.BlogHttpClient;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlogDetailsActivity extends AppCompatActivity {

    private static final String EXTRAS_BLOG = "EXTRAS_BLOG";

    private TextView textTitle;
    private TextView textDate;
    private TextView textAuthor;
    private TextView textRating;
    private TextView textViews;
    private TextView textDescription;
    private RatingBar ratingBar;
    private ImageView imageMain;
    private ImageView imageAvatar;
    private ImageView imageBack;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle saveInstanceState){

        // Bind Java Class with Xml class
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_blog_details);

        // Bind Elements inside the XML Page with Java Fields
        imageMain = findViewById(R.id.imageMain);
        imageAvatar = findViewById(R.id.imageAvatar);

        textTitle = findViewById(R.id.textTitle);

        textDate = findViewById(R.id.textDate);

        textAuthor = findViewById(R.id.textAuthor);

        textRating = findViewById(R.id.textRating);

        textViews = findViewById(R.id.textViews);

        textDescription = findViewById(R.id.textDescription);

        ratingBar = findViewById(R.id.ratingBar);

        // Back Image
        imageBack = findViewById(R.id.imageBack);
        imageBack.setOnClickListener(v -> finish());

        // Progress Bar Binding
        progressBar = findViewById(R.id.progressBar);

        Blog blog = getIntent()
                .getExtras()
                .getParcelable(EXTRAS_BLOG);

        //  Show Data
        showData(getIntent()
                .getExtras()
                .getParcelable(EXTRAS_BLOG));
    }

//    private void loadData(){
//        BlogHttpClient.INSTANCE.loadBlogArticles(new BlogArticlesCallback() {
//            @Override
//            public void onSuccess(List<Blog> blogList){
//                runOnUiThread(() -> showData(blogList.get(0)));
//            }
//
//            @Override
//            public void onError(){
//                runOnUiThread(() -> showErrorSnackbar());
//            }
//        });
//    }

    private void showData(Blog blog){
        progressBar.setVisibility(View.GONE);
        textTitle.setText(blog.getTitle());
        textDate.setText(blog.getDate());
        textAuthor.setText(blog.getAuthor().getName());
        textViews.setText(String.format("(%d views)", blog.getViews()));
        textRating.setText(String.valueOf(blog.getRating()));
        textDescription.setText(Html.fromHtml(blog.getDescription(), 0));

        // Set Rating
        ratingBar.setRating(blog.getRating());
        ratingBar.setVisibility(View.VISIBLE);

        // Set Images
        Glide.with(this)
                .load(blog.getImageURL())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageMain);

        Glide.with(this)
                .load(blog.getAuthor().getAvatarURL())
                .transform(new CircleCrop())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageAvatar);
    }

//    private void showErrorSnackbar(){
//        View rootView = findViewById(android.R.id.content);
//        Snackbar snackbar = Snackbar.make(rootView, "Error during loading blog articles", Snackbar.LENGTH_INDEFINITE);
//        snackbar.setActionTextColor(getResources().getColor(R.color.grey500));
//        snackbar.setAction("Retry", v -> {
//           loadData();
//           snackbar.dismiss();
//        });
//        snackbar.show();
//    }

    public static void startBlogDetailsActivity(Activity activity, Blog blog) {
        Intent intent = new Intent(activity, BlogDetailsActivity.class);
        intent.putExtra(EXTRAS_BLOG, blog);
        activity.startActivity(intent);
    }
}
