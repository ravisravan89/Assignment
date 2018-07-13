package com.ravisravan.infyassignment.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ravisravan.infyassignment.GlideApp;
import com.ravisravan.infyassignment.R;
import com.ravisravan.infyassignment.models.Row;

/**
 * Created by ravisravankumar on 13/07/18.
 */

public class FactItemViewModel extends BaseObservable {

    public Row row;
    public String imageUrl;

    public FactItemViewModel(Row row) {
        this.row = row;
        this.imageUrl = row.getImageHref();
    }

    public String getImageUrl() {
        // The URL will usually come from a model (i.e Row)
        return imageUrl;
    }

    public void setRow(Row row){
        this.row = row;
        this.imageUrl = row.getImageHref();
        notifyChange();
    }

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String imageUrl) {
        GlideApp.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .fitCenter()
                .into(view);
    }
}
