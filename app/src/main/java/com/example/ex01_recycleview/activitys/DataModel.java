package com.example.ex01_recycleview.activitys;

public class DataModel {
    private String name;
    private int image;
    private String desc;
    private int id_;

    public DataModel(String name, int image, String desc, int id_) {
        this.name = name;
        this.image = image;
        this.desc = desc;
        this.id_ = id_;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId_() {
        return id_;
    }

    public void setId_(int id_) {
        this.id_ = id_;
    }
}
