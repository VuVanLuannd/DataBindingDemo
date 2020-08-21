package org.wysaid.databindingdeomo.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.wysaid.databindingdeomo.databinding.SupportDataBinding;
import org.wysaid.databindingdeomo.model.ImageModel;

import java.util.ArrayList;
import java.util.List;

public class StudioAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private OnClickListener onClickListener;
    private List<ImageModel> imageModels = new ArrayList<>();
    private int positionTemp = 0;

    public StudioAdapter() {
    }

    public void setImageModels(List<ImageModel> imageModels) {
        if (this.imageModels == null) {
            this.imageModels = new ArrayList<>();
        }
        this.imageModels.clear();
        this.imageModels.addAll(imageModels);
        notifyDataSetChanged();

    }

    public void setListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SupportDataBinding dataBinding = SupportDataBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PhotoViewHolder(dataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PhotoViewHolder photoViewHolder = (PhotoViewHolder) holder;
        ImageModel imageModel = imageModels.get(position);
        photoViewHolder.bind(imageModel, position);
    }

    @Override
    public int getItemCount() {
        return imageModels == null ? 0 : imageModels.size();
    }

    public interface OnClickListener {
        void onClick(int position);
    }

    class PhotoViewHolder extends RecyclerView.ViewHolder {

        private SupportDataBinding supportDataBinding;

        PhotoViewHolder(@NonNull SupportDataBinding itemView) {
            super(itemView.getRoot());
            this.supportDataBinding = itemView;
        }

        void bind(ImageModel imageModel, final int position) {
            supportDataBinding.ivCheck.setVisibility(positionTemp == position && imageModel.isShow ? View.VISIBLE : View.GONE);
            supportDataBinding.setStudioModel(imageModel);
            supportDataBinding.getRoot().setOnClickListener(v -> {
                if (onClickListener != null) {
                    onClickListener.onClick(position);
                    positionTemp = position;
                }
                imageModel.setShow(!imageModel.isShow);
                notifyItemChanged(position);
            });
        }

    }

}
