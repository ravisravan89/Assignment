
package com.ravisravan.infyassignment.models;

import java.util.List;
import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class FactsResponseModel {

    @Expose
    private List<Row> rows;
    @Expose
    private String title;

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
