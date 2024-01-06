package com.example.spotapp.Image;

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

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private List<String> images;
    private Context context;
    private String path = "http://10.0.2.2:8080/inquirys/imagefile/";

    public ImageAdapter(List<String> images, Context context) {

        List<String> nameToPath = new ArrayList<>();
        for (String imageName : images) {
            nameToPath.add(path + imageName);
        }
        this.images = nameToPath;
        this.context = context;
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.inquiry_image);
        }
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;    // context에서 LayoutInflater 객체를 얻는다.
        View view = inflater.inflate(R.layout.inquiry_image_item, parent, false) ;	// 리사이클러뷰에 들어갈 아이템뷰의 레이아웃을 inflate.
        ImageViewHolder vh = new ImageViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        String image_uri = images.get(position) ;

        System.out.println(position +"번째 uri Glide");

        Glide.with(holder.itemView.getContext())
                .load(image_uri)
                .into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return images.size();
    }
}
