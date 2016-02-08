package com.example.soham.doodle;

/**
 * Created by Soham on 2/7/2016.
 */
public class DoodleManager {

    private String name;
    private String comment;

    public DoodleManager() {}
    
    public DoodleManager(String s){
        name = s;
        comment = "";
    }

    public DoodleManager(String n, String c){
        name = n;
        comment =c;
    }

    public String getName() { return  name; }
    public String getComment(){return comment; }

    public void setName(String s){ this.name = s; }
    public void setComment(String s){ this.comment = s; }


}
