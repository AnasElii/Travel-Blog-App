package com.anstudio.travelblog.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.anstudio.travelblog.Blog;
import com.anstudio.travelblog.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeTransition;

public class MainAdapter extends ListAdapter<Blog, MainAdapter.MainViewHolder> {

    private static final DiffUtil.ItemCallback<Blog> DIFF_CALLBACK = new DiffUtil.ItemCallback<Blog>() {
        @Override
        public boolean areItemsTheSame(@NonNull Blog oldItem, @NonNull Blog newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Blog oldItem, @NonNull Blog newItem) {
            return oldItem.equals(newItem);
        }
    };

    public MainAdapter(){
        super(DIFF_CALLBACK);
    }

    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_main, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position){
        holder.bindTo(getItem(position));
    }

    class MainViewHolder extends RecyclerView.ViewHolder{

        TextView textTitle;
        TextView textDate;
        ImageView imageView;

        MainViewHolder(@NonNull View itemView){
            super(itemView);

            textTitle = itemView.findViewById(R.id.textTitle);
            textDate = itemView.findViewById(R.id.textDate);
            imageView = itemView.findViewById(R.id.imageAvatar);
        }

        void bindTo(Blog blog){
            textTitle.setText(blog.getTitle());
            textDate.setText(blog.getDate());

            Glide.with(itemView)
                    .load(blog.getAuthor().getAvatarURL())
                    .transform(new CircleCrop())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView);
        }

    }

}
