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

import com.anstudio.travelblog.http.Blog;
import com.anstudio.travelblog.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class MainAdapter extends ListAdapter<Blog, MainAdapter.MainViewHolder> {

    public interface OnItemClickListener{
        void onItemClicked(Blog blog);
    }

    private OnItemClickListener clickListener;

    public MainAdapter(OnItemClickListener clickListener){
        super(DIFF_CALLBACK);
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_main, parent, false);
        return new MainViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position){
        holder.bindTo(getItem(position));
    }

    class MainViewHolder extends RecyclerView.ViewHolder{

        private TextView textTitle;
        private TextView textDate;
        private ImageView imageView;
        private Blog blog;


        MainViewHolder(@NonNull View itemView, OnItemClickListener listener){
            super(itemView);

            itemView.setOnClickListener(v -> listener.onItemClicked(blog));
            textTitle = itemView.findViewById(R.id.textTitle);
            textDate = itemView.findViewById(R.id.textDate);
            imageView = itemView.findViewById(R.id.imageAvatar);
        }

        void bindTo(Blog blog){
            this.blog = blog;
            textTitle.setText(blog.getTitle());
            textDate.setText(blog.getDate());

            Glide.with(itemView)
                    .load(blog.getAuthor().getAvatarURL())
                    .transform(new CircleCrop())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView);
        }

    }

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

}
