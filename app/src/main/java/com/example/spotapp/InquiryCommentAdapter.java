package com.example.spotapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotapp.dto.InquiryCommentData;
import com.example.spotapp.dto.InquiryData;

import java.util.List;

public class InquiryCommentAdapter extends RecyclerView.Adapter<InquiryCommentAdapter.InquiryCommentViewHolder> {


    private List<InquiryCommentData> commentDataList;

    public InquiryCommentAdapter(List<InquiryCommentData> commentDataList) {
        this.commentDataList = commentDataList;
    }


    @NonNull
    @Override
    public InquiryCommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inquiry_comment, parent, false);
        return new InquiryCommentAdapter.InquiryCommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InquiryCommentViewHolder holder, int position) {
        InquiryCommentData commentData = commentDataList.get(position);
        holder.name.setText(commentData.getName());
        holder.regDate.setText(commentData.getRegDate());
        holder.content.setText(commentData.getContent());
    }

    @Override
    public int getItemCount() {
        return commentDataList.size();
    }

    class InquiryCommentViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView content;
        private TextView regDate;


        public InquiryCommentViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.rv_inquiryCommentName);
            content = itemView.findViewById(R.id.rv_inquiryCommentContent);
            regDate = itemView.findViewById(R.id.rv_inquiryCommentRegdate);



        }
    }
}
