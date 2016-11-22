package ru.stqa.training.addressbook.model;

import java.io.File;

public class ContactData {

    private int id = Integer.MAX_VALUE;
    private String fname;
    private String lname;
    private String title;
    private String address;
    private String homephone;
    private String mobilephone;
    private String workphone;
    private String allPhones;
    private String email;
    private String email2;
    private String email3;
    private String day;
    private String month;
    private String year;
    private String group;
    private File photo;

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

    public String getHomephone() {

        return homephone;
    }

    public String getMobilephone() {

        return mobilephone;
    }

    public String getWorkphone() {

        return workphone;
    }


    public String getAllPhones() {

        return allPhones;
    }


    public String getEmail() {

        return email;
    }

    public String getEmail2() {

        return email2;
    }

    public String getEmail3() {

        return email3;
    }


    public String getAllEmails() {
        return allEmails;
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

    public File getPhoto() {
        return photo;
    }
    public ContactData withId(int id) {

        this.id = id;
        return this;
    }

    public ContactData withFname(String fname) {
        this.fname = fname;
        return this;
    }

    public ContactData withLname(String lname) {
        this.lname = lname;
        return this;
    }

    public ContactData withTitle(String title) {
        this.title = title;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withHomephone(String homephone) {
        this.homephone = homephone;
        return this;
    }

    public ContactData withMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
        return this;
    }

    public ContactData withWorkphone(String workphone) {
        this.workphone = workphone;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    private String allEmails;

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withDay(String day) {
        this.day = day;
        return this;
    }

    public ContactData withMonth(String month) {
        this.month = month;
        return this;
    }

    public ContactData withYear(String year) {
        this.year = year;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }


    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
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
                ", homephone='" + homephone + '\'' +
                ", mobilephone='" + mobilephone + '\'' +
                ", workphone='" + workphone + '\'' +
                ", email='" + email + '\'' +
                ", email2='" + email2 + '\'' +
                ", email3='" + email3 + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (fname != null ? !fname.equals(that.fname) : that.fname != null) return false;
        if (lname != null ? !lname.equals(that.lname) : that.lname != null) return false;
        if (homephone != null ? !homephone.equals(that.homephone) : that.homephone != null) return false;
        if (mobilephone != null ? !mobilephone.equals(that.mobilephone) : that.mobilephone != null) return false;
        if (workphone != null ? !workphone.equals(that.workphone) : that.workphone != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (email2 != null ? !email2.equals(that.email2) : that.email2 != null) return false;
        if (email3 != null ? !email3.equals(that.email3) : that.email3 != null) return false;
        return address != null ? address.equals(that.address) : that.address == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fname != null ? fname.hashCode() : 0);
        result = 31 * result + (lname != null ? lname.hashCode() : 0);
        result = 31 * result + (homephone != null ? homephone.hashCode() : 0);
        result = 31 * result + (mobilephone != null ? mobilephone.hashCode() : 0);
        result = 31 * result + (workphone != null ? workphone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (email2 != null ? email2.hashCode() : 0);
        result = 31 * result + (email3 != null ? email3.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}