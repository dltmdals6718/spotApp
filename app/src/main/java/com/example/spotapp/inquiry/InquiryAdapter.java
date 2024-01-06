package com.example.spotapp.inquiry;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotapp.R;
import com.example.spotapp.dto.ImageFile;
import com.example.spotapp.dto.InquiryData;

import java.util.ArrayList;
import java.util.List;

public class InquiryAdapter extends RecyclerView.Adapter<InquiryAdapter.InquiryViewHolder> {

    private List<InquiryData> inquiryList;

    public InquiryAdapter(List<InquiryData> inquiryList) {
        this.inquiryList = inquiryList;
    }

    @NonNull
    @Override
    public InquiryAdapter.InquiryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inquiry_item, parent, false);
        return new InquiryAdapter.InquiryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InquiryAdapter.InquiryViewHolder holder, int position) {
        InquiryData inquiryData = inquiryList.get(position);
        holder.title.setText(inquiryData.getTitle());
        holder.name.setText(inquiryData.getName());
    }

    @Override
    public int getItemCount() {
        return inquiryList.size();
    }

    public void setData(List<InquiryData> data) {
        inquiryList = data;
    }


    class InquiryViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView name;

        public InquiryViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.rv_inquiryTitle);
            name = itemView.findViewById(R.id.rv_inquiryName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    InquiryData inquiryData = inquiryList.get(pos);

                    Context context = view.getContext();
                    Intent inquiryDetailActivity = new Intent(context, InquiryDetailActivity.class);
                    inquiryDetailActivity.putExtra("id", inquiryData.getId());
                    inquiryDetailActivity.putExtra("title", inquiryData.getTitle());
                    inquiryDetailActivity.putExtra("name", inquiryData.getName());
                    inquiryDetailActivity.putExtra("content", inquiryData.getContent());
                    inquiryDetailActivity.putExtra("regDate", inquiryData.getRegDate());

                    ArrayList<String> storeFileNameList = new ArrayList<>();
                    for (ImageFile image : inquiryData.getImages()) {
                        storeFileNameList.add(image.getStoreFileName());
                    }
                    inquiryDetailActivity.putStringArrayListExtra("images", storeFileNameList);

                    context.startActivity(inquiryDetailActivity);

                }
            });
        }


    }
}
