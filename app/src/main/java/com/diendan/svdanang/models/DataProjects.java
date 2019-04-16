package com.diendan.svdanang.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 4/13/2017.
 */

public class DataProjects implements Serializable {
    @SerializedName("content")
    private List<ContentProject> content = null;
    @SerializedName("page")
    private Integer page;
    @SerializedName("size")
    private Integer size;
    @SerializedName("totalElements")
    private Integer totalElements;
    @SerializedName("totalPages")
    private Integer totalPages;
    @SerializedName("last")
    private Boolean last;

    public List<ContentProject> getContent() {
        return content;
    }

    public void setContent(List<ContentProject> content) {
        this.content = content;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Boolean getLast() {
        return last;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }

}

