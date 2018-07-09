package com.example.shanna.orbital2;

public class Projects {

    private String Title;
    private String About;
    private String thumb_image;
    private String Owner;

    public Projects(){

    }

    public Projects(String title, String about, String Thumb_image, String owner){
        this.Title = title;
        this.About = about;
        this.thumb_image=Thumb_image;
        this.Owner=owner;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAbout() {
        return About;
    }

    public void setAbout(String about) {
        About = about;
    }

    public String getThumb_image() {
        return thumb_image;
    }

    public void setThumb_image(String Thumb_image) {
        this.thumb_image = Thumb_image;
    }
    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }
}

