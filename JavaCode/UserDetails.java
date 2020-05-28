package com.example.cse.ayush;

public class UserDetails {
    private String StringAadher,StringType,StringName,StringMobilNo,StringEmail,StringVillage,StringMandal,StringDistrict,StringState,StringPincode;
    private String Description,DocotorName,HospitalName,CurrentIssue,MustReport,LastReport,StringPassword;

    public String getStringPassword() {
        return StringPassword;
    }

    public void setStringPassword(String stringPassword) {
        StringPassword = stringPassword;
    }

    public UserDetails(String stringAadher, String stringType, String stringName, String stringMobilNo, String stringEmail, String stringVillage, String stringMandal, String stringDistrict, String stringState, String stringPincode, String description, String docotorName, String hospitalName, String currentIssue, String mustReport, String lastReport, String stringPassword) {
        StringAadher = stringAadher;
        StringType = stringType;
        StringName = stringName;
        StringMobilNo = stringMobilNo;
        StringEmail = stringEmail;
        StringVillage = stringVillage;
        StringMandal = stringMandal;
        StringDistrict = stringDistrict;
        StringState = stringState;
        StringPincode = stringPincode;
        Description = description;
        DocotorName = docotorName;
        HospitalName = hospitalName;
        CurrentIssue = currentIssue;
        MustReport = mustReport;
        LastReport = lastReport;
        StringPassword = stringPassword;
    }

    public UserDetails(){

    }
    public String getStringType() {
        return StringType;
    }

    public void setStringType(String stringType) {
        StringType = stringType;
    }

    public String getStringAadher() {
        return StringAadher;
    }

    public void setStringAadher(String stringAadher) {
        StringAadher = stringAadher;
    }

    public String getStringName() {
        return StringName;
    }

    public void setStringName(String stringName) {
        StringName = stringName;
    }

    public String getStringMobilNo() {
        return StringMobilNo;
    }

    public void setStringMobilNo(String stringMobilNo) {
        StringMobilNo = stringMobilNo;
    }

    public String getStringEmail() {
        return StringEmail;
    }

    public void setStringEmail(String stringEmail) {
        StringEmail = stringEmail;
    }

    public String getStringVillage() {
        return StringVillage;
    }

    public void setStringVillage(String stringVillage) {
        StringVillage = stringVillage;
    }

    public String getStringMandal() {
        return StringMandal;
    }

    public void setStringMandal(String stringMandal) {
        StringMandal = stringMandal;
    }

    public String getStringDistrict() {
        return StringDistrict;
    }

    public void setStringDistrict(String stringDistrict) {
        StringDistrict = stringDistrict;
    }

    public String getStringState() {
        return StringState;
    }

    public void setStringState(String stringState) {
        StringState = stringState;
    }

    public String getStringPincode() {
        return StringPincode;
    }

    public void setStringPincode(String stringPincode) {
        StringPincode = stringPincode;
    }
    // Medical Worker Work

    public UserDetails(String description, String docotorName, String hospitalName, String currentIssue, String mustReport, String lastReport) {
        Description = description;
        DocotorName = docotorName;
        HospitalName = hospitalName;
        CurrentIssue = currentIssue;
        MustReport = mustReport;
        LastReport = lastReport;
    }


    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDocotorName() {
        return DocotorName;
    }

    public void setDocotorName(String docotorName) {
        DocotorName = docotorName;
    }

    public String getHospitalName() {
        return HospitalName;
    }

    public void setHospitalName(String hospitalName) {
        HospitalName = hospitalName;
    }

    public String getCurrentIssue() {
        return CurrentIssue;
    }

    public void setCurrentIssue(String currentIssue) {
        CurrentIssue = currentIssue;
    }

    public String getMustReport() {
        return MustReport;
    }

    public void setMustReport(String mustReport) {
        MustReport = mustReport;
    }

    public String getLastReport() {
        return LastReport;
    }

    public void setLastReport(String lastReport) {
        LastReport = lastReport;
    }
}
