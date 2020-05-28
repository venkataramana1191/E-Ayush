package com.example.cse.ayush;

public class DoctorAppointListStatus {
    private String Time,Date,DoctorName,Status,PName,ContactNo;

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String contactNo) {
        ContactNo = contactNo;
    }

    public DoctorAppointListStatus(String time, String date, String doctorName, String status, String PName, String contactNo) {
        Time = time;
        Date = date;
        DoctorName = doctorName;
        Status = status;
        this.PName = PName;
        ContactNo = contactNo;
    }

    public DoctorAppointListStatus() {
    }

    public String getPName() {
        return PName;
    }

    public void setPName(String PName) {
        this.PName = PName;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
