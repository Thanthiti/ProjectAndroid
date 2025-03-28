package com.example.projectandroid;

import android.content.Context;

import java.io.Serializable;

public class userData implements Serializable {
    private String name = "";
    private String email = "";
    private String password = "";
    private int progress = 0;
    private String image = "";
    private Context ctx;

    public userData(String name, String email, String password,int progress,String image){
        this.name = name;
        this.email = email;
        this.password = password;
        this.progress = progress;
        this.image = image;
    }
    public  userData(Context ctx){
        this.ctx = ctx;
    }
    public String getName(){
        return this.name;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPassword(){
        return this.password;
    }
    public int getProgress() {
        return this.progress;
    }
    public String getImage() {
        return this.image;
    }

    public String toString(){
        return (getName() + " " + getEmail() + " " + getPassword() + " " + getProgress() + " " + getImage());
    }
}
