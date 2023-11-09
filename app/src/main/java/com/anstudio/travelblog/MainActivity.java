package com.anstudio.travelblog;

import static android.app.ProgressDialog.show;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.anstudio.travelblog.adapter.MainAdapter;
import com.anstudio.travelblog.http.Blog;
import com.anstudio.travelblog.http.BlogArticlesCallback;
import com.anstudio.travelblog.http.BlogHttpClient;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainAdapter mainAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private static final int SORT_TITLE = 0;
    private static final int SORT_DATE = 1;
    private int currentSort = SORT_DATE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.sort) {
                onSortClicked();
            } else if (item.getItemId() == R.id.about){
                Log.d("myTask", "About Clicked");
                onAboutClicked();
            }
            return false;
        });

        mainAdapter = new MainAdapter(blog -> BlogDetailsActivity.startBlogDetailsActivity(this, blog));

        // Bind the search input with the search field
        MenuItem searchItem = toolbar.getMenu().findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query){
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText){
                Log.d("Search", "search text: " + newText);
                mainAdapter.filter(newText);
                return true;
            }
        });


        RecyclerView recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mainAdapter);

        swipeRefreshLayout = findViewById(R.id.refresh);
        swipeRefreshLayout.setOnRefreshListener(this::loadData);

        loadData();
    }

    private void onSortClicked(){
        String[] items = {"Title", "Date"};
        new MaterialAlertDialogBuilder(this)
                .setTitle("Sort Elements")
                .setSingleChoiceItems(items, currentSort, (dialog, which) -> {
                    dialog.dismiss();
                    currentSort = which;
                    sortData();
                })
                .show();
    }

    private void sortData(){
        if(currentSort == SORT_TITLE){
            mainAdapter.sortByTitle();
        } else if (currentSort == SORT_DATE){
            mainAdapter.sortByDate();
        }
    }

    private void onAboutClicked(){
        new MaterialAlertDialogBuilder(this)
        .setMessage("This is the message of the string :)")
        .show();
    }

    private void loadData() {
        swipeRefreshLayout.setRefreshing(true);
        BlogHttpClient.INSTANCE.loadBlogArticles(new BlogArticlesCallback() {
            @Override
            public void onSuccess(List<Blog> blogList) {
                runOnUiThread(() -> {
                    swipeRefreshLayout.setRefreshing(false);
                    mainAdapter.setData(blogList);
                    sortData();
                });
            }

            @Override
            public void onError() {
                runOnUiThread(() -> {
                    swipeRefreshLayout.setRefreshing(false);
                    showErrorSnackbar();
                });
            }
        });
    }

    private void showErrorSnackbar() {
        View rootView = findViewById(android.R.id.content);
        Snackbar snackbar = Snackbar.make(rootView, "Error during loading blog articles", Snackbar.LENGTH_INDEFINITE);
        snackbar.setActionTextColor(getResources().getColor(R.color.orange500));
        snackbar.setAction("Retry", v -> {
            loadData();
            snackbar.dismiss();
        });
        snackbar.show();
    }

}