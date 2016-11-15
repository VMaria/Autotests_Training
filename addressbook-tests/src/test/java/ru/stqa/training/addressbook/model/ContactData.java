package ru.stqa.training.addressbook.model;

public class ContactData {

    private int id;
    private final String fname;
    private final String lname;
    private final String title;
    private final String address;
    private final String day;
    private final String month;
    private final String year;
    private String group;


    public ContactData(String fname, String lname, String title, String address, String day, String month, String year, String group) {

        this.id = Integer.MAX_VALUE;
        //this.id = 0;
        this.fname = fname;
        this.lname = lname;
        this.title = title;
        this.address = address;
        this.day = day;
        this.month = month;
        this.year = year;
        this.group = group;
    }

    public ContactData(int id, String fname, String lname, String title, String address, String day, String month, String year, String group) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.title = title;
        this.address = address;
        this.day = day;
        this.month = month;
        this.year = year;
        this.group = group;
    }

    public int getId() {

        return id;
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

    public String getGroup() {

        return group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (fname != null ? !fname.equals(that.fname) : that.fname != null) return false;
        return lname != null ? lname.equals(that.lname) : that.lname == null;

    }

    @Override
    public int hashCode() {
        int result = fname != null ? fname.hashCode() : 0;
        result = 31 * result + (lname != null ? lname.hashCode() : 0);
        return result;
    }

    public void setId(int id) {

        this.id = id;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                '}';
    }
}