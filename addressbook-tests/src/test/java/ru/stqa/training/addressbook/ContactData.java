package ru.stqa.training.addressbook;

public class ContactData {
    private final String fname;
    private final String lname;
    private final String title;
    private final String address;
    private final String day;
    private final String month;
    private final String year;

    public ContactData(String fname, String lname, String title, String address, String day, String month, String year) {
        this.fname = fname;
        this.lname = lname;
        this.title = title;
        this.address = address;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }
}
