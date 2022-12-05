package com.app.teamproject;

public class BookmarkStation {

    String stationName;
    int resourceId;
    int b_resourceId;

    public BookmarkStation(int resourceId, String name,int b_resourceId){
        this.stationName=name;
        this.resourceId=resourceId;
        this.b_resourceId=b_resourceId;
    }
    public int getResourceId() {
        return resourceId;
    }

    public String getName(){
        return stationName;
    }

    public int getB_ResourceId(){ return b_resourceId; }


    public void setStationName(String name){
        this.stationName=name;
    }
    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
    public void setB_resourceId(int b_resourceId){this.b_resourceId=b_resourceId;}
}
