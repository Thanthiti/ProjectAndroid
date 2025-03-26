package com.example.projectandroid;

import java.io.Serializable;

public class userData implements Serializable {
    private String name = "";
    private String email = "";
    private String password = "";
    private int progress = 0;

    public userData(String name, String email, String password,int progress){
        this.name = name;
        this.email = email;
        this.password = password;
        this.progress = progress;
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
    public String toString(){
        return (getName() + " " + getEmail() + " " + getPassword() + " " + getProgress());
    }
}
