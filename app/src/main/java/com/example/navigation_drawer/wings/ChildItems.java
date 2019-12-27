package com.example.navigation_drawer.wings;

import java.util.ArrayList;

public class ChildItems {

    String groupname;
    ArrayList<String> groupdata;
    ArrayList<String> group2wheeldata=null;
    ArrayList<String> group4wheeldata=null;
    ArrayList<String> groupHouseType=null;

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public ArrayList<String> getGroupdata() {
        return groupdata;
    }

    public void setGroupdata(ArrayList<String> groupdata) {
        this.groupdata = groupdata;
    }

    public ArrayList<String> getGroup2wheeldata() {
        return group2wheeldata;
    }

    public void setGroup2wheeldata(ArrayList<String> group2wheeldata) {
        this.group2wheeldata = group2wheeldata;
    }

    public ArrayList<String> getGroup4wheeldata() {
        return group4wheeldata;
    }

    public void setGroup4wheeldata(ArrayList<String> group4wheeldata) {
        this.group4wheeldata = group4wheeldata;
    }

    public ArrayList<String> getGroupHouseType() {
        return groupHouseType;
    }

    public void setGroupHouseType(ArrayList<String> groupHouseType) {
        this.groupHouseType = groupHouseType;
    }
}
