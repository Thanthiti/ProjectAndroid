package com.example.projectandroid;

import android.adservices.common.AdData;
import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class ManageFile implements Serializable {
    private Context ctx;
    private String name = "";
    private String email = "";
    private String password = "";
    private String progress = "";
    private String image = "";
    private String fileName = "";
    String PicProfile [] = {"black","pink","red","brown","green","orange","yellow","cyan","purple"};

    public  ManageFile(Context ctx,String name, String email, String password,String progress,String image,String fileName){
        this.ctx = ctx;
        this.name = name;
        this.email = email;
        this.password = password;
        this.progress = progress;
        this.image = image;
        this.fileName = fileName;
    }

    public  ManageFile(Context ctx,String fileName){
        this.ctx = ctx;
        this.fileName = fileName;
    }

    public boolean UpdateData(String Newname, String Newemail, String Newpass, String NewProfile) {
        ArrayList<String> Alldata = new ArrayList<>();
        boolean status = true;

        try {
            FileInputStream fin = ctx.openFileInput(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fin));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] part = line.split("\\s+", 5);

                if (part[0].equals(Newname) && !part[0].equals(name)) {
                    status = false;
                    return false;
                }

                if ( part[0].equals(name) && part[1].equals(email)) {
                    Alldata.add(Newname + " " + Newemail + " " + Newpass + " " + part[3] + " " + NewProfile);
                } else {
                    Alldata.add(line);
                }
            }
            System.out.println(Alldata);
            reader.close();
            fin.close();

            if (!status) {
                return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fout = ctx.openFileOutput(fileName, Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fout);

            for (String User : Alldata) {
                writer.write(User + "\n");
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return status;
    }

    public void saveFile(String Name,String Email, String Pass){
        try {
            FileOutputStream fout = ctx.openFileOutput(fileName,Context.MODE_APPEND);
            OutputStreamWriter writer = new OutputStreamWriter(fout);
            Random rand = new Random();
            writer.write(Name + " " + Email + " " + Pass + " 0 "+PicProfile[rand.nextInt(PicProfile.length-1)] + "\n");

            writer.flush();
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public  ArrayList<String> readFile() {
        ArrayList<String> Alldata = new ArrayList<>();
        try {
            FileInputStream fin = ctx.openFileInput(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fin));
            String line;

            while ((line = reader.readLine()) != null) {
                Alldata.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    return Alldata;
    }
}
