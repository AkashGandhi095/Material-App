package com.example.teamwork.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teamwork.Modal.ImageData;
import com.example.teamwork.R;
import com.google.android.material.textview.MaterialTextView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private Context context;
    private List<ImageData> imageDataList;
    public ImageAdapter(Context context)
    {
        this.context = context;
    }

    public void setImageList(List<ImageData> imageDataList)
    {
        this.imageDataList = imageDataList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row , parent , false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ViewHolder holder, int position) {
        ImageData data = imageDataList.get(position);
        Picasso.get().load(data.getImageUrl()).into(holder.imageView);
        holder.idText.setText("ID : " +data.getImgId());
        holder.titleText.setText("Title : " +data.getImageTitle());
    }

    @Override
    public int getItemCount() {
        if(imageDataList != null)
        {
            return imageDataList.size();
        }
        return 0;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        MaterialTextView idText , titleText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
            idText = itemView.findViewById(R.id.img_id);
            titleText = itemView.findViewById(R.id.img_title);
        }
    }
}
