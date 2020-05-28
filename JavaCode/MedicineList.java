package com.example.cse.ayush;

public class MedicineList {
    private String MedicineName;
    private String Quantity;
    private String time;



    public MedicineList(String medicineName, String quantity, String time) {
        MedicineName = medicineName;
        Quantity = quantity;
        this.time = time;
    }

    public MedicineList() {

    }

    public String getMedicineName() {
        return MedicineName;

    }

    public void setMedicineName(String medicineName) {
        MedicineName = medicineName;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
