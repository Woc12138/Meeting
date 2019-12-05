package org.sang.bean;

public class OptionInfo {
    private int employeeid;
    private String IDnumber;
    private String gender;
    private String location;
    private String roomrequired;

    public OptionInfo(int employeeid, String IDnumber, String gender, String location, String roomrequired) {
        this.employeeid = employeeid;
        this.IDnumber = IDnumber;
        this.gender = gender;
        this.location = location;
        this.roomrequired = roomrequired;
    }

    public OptionInfo(int employeeid, String gender) {
        this.employeeid = employeeid;
        this.gender = gender;
    }

    public OptionInfo(int employeeid, String gender, String location) {
        this.employeeid = employeeid;
        this.gender = gender;
        this.location = location;
    }

    public OptionInfo(String roomrequired) {
        this.roomrequired = roomrequired;
    }

    public OptionInfo(String location, String roomrequired) {
        this.location = location;
        this.roomrequired = roomrequired;
    }

    public OptionInfo(String gender, String location, String roomrequired) {
        this.gender = gender;
        this.location = location;
        this.roomrequired = roomrequired;
    }

    public OptionInfo(String IDnumber, String gender, String location, String roomrequired) {
        this.IDnumber = IDnumber;
        this.gender = gender;
        this.location = location;
        this.roomrequired = roomrequired;
    }

    @Override
    public String toString() {
        return "OptionInfo{" +
                "IDnumber='" + IDnumber + '\'' +
                ", gender='" + gender + '\'' +
                ", location='" + location + '\'' +
                ", roomrequired='" + roomrequired + '\'' +
                '}';
    }

    public OptionInfo(int employeeid) {
        this.employeeid = employeeid;
    }

    public int getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(int employeeid) {
        this.employeeid = employeeid;
    }

    public String getIDnumber() {
        return IDnumber;
    }

    public void setIDnumber(String IDnumber) {
        this.IDnumber = IDnumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRoomrequired() {
        return roomrequired;
    }

    public void setRoomrequired(String roomrequired) {
        this.roomrequired = roomrequired;
    }
}
