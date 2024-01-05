package com.example.spotapp.inquiry;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.spotapp.R;

import java.util.List;

public class MultiImageAdapter extends RecyclerView.Adapter<MultiImageAdapter.MultiImageViewHolder> {

    private List<Uri> images;
    private Context context;

    public MultiImageAdapter(List<Uri> images, Context context) {
        this.images = images;
        this.context = context;
    }

    class MultiImageViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public MultiImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.inquiry_image);
        }
    }

    @NonNull
    @Override
    public MultiImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;    // context에서 LayoutInflater 객체를 얻는다.
        View view = inflater.inflate(R.layout.inquiry_image_item, parent, false) ;	// 리사이클러뷰에 들어갈 아이템뷰의 레이아웃을 inflate.
        MultiImageAdapter.MultiImageViewHolder vh = new MultiImageAdapter.MultiImageViewHolder(view);
        return vh ;
    }

    @Override
    public void onBindViewHolder(@NonNull MultiImageViewHolder holder, int position) {
        Uri image_uri = images.get(position) ;

        System.out.println(position +"번째 uri Glide");

        Glide.with(context)
                .load(image_uri)
                .into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return images.size();
    }
}
