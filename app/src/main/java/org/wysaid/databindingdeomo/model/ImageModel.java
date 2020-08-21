package org.wysaid.databindingdeomo.model;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.wysaid.databindingdeomo.BR;

public class ImageModel extends BaseObservable {

    public String name;
    private String drawable;
    public boolean isShow;

    public ImageModel() {
    }
    public ImageModel(String name) {
        this.name = name;
    }

    @BindingAdapter("drawable")
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .into(view);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getPath() {
        return drawable;
    }

    public void setPath(String path) {
        this.drawable = path;
        notifyPropertyChanged(BR.path);
    }

    @Bindable
    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
        notifyPropertyChanged(BR.show);
    }
}
