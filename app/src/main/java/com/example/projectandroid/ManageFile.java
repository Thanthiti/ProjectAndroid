package com.example.projectandroid;

import android.adservices.common.AdData;
import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;

public class ManageFile implements Serializable {
    private Context ctx;
    private String name = "";
    private String email = "";
    private String password = "";
    private int progress = 0;
    private String image = "";
    private String fileName = "";
    public  ManageFile(Context ctx,String name, String email, String password,int progress,String image,String fileName){
        this.ctx = ctx;
        this.name = name;
        this.email = email;
        this.password = password;
        this.progress = progress;
        this.image = image;
        this.fileName = fileName;
    }

    public void UpdateData(String Newname, String Newemail, String Newpass){
        ArrayList<String> Alldata = new ArrayList<>();
        try {
            FileInputStream fin = ctx.openFileInput(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fin));
            String line;
            while((line = reader.readLine())!= null){
                String [] part = line.split("\\s+",5);
                if(part.length >=2 && part[0].equals(name) && part[1].equals(email)){
                    Alldata.add(Newname + " " + Newemail + " " + Newpass + " " + part[3] + " " + part[4] );
                }else {
                    Alldata.add(line);
                }
            }
            System.out.println(Alldata);
            reader.close();
            fin.close();

        }catch (IOException e){
            e.printStackTrace();
        }
        try {
            FileOutputStream fout = ctx.openFileOutput(fileName,Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fout);
            for(String User : Alldata){
                writer.write(User + "\n");
            }
            writer.flush();
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
