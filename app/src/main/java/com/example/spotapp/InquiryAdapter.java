package com.example.spotapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        }


    }
}
