package com.example.cse.ayush;

public class PatientListIcons {
    private  String Title;
    private  int IconID;

    public PatientListIcons(String title) {
        Title = title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setIconID(int iconID) {
        IconID = iconID;
    }

    public PatientListIcons(String title, int iconID) {

        Title = title;
        IconID = iconID;

    }

    public String getTitle() {
        return Title;
    }


    public int getIconID() {
        return IconID;
    }

}
