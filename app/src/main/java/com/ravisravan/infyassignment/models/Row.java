
package com.ravisravan.infyassignment.models;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class Row {

    @Expose
    private String description;
    @Expose
    private String imageHref;
    @Expose
    private String title;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
