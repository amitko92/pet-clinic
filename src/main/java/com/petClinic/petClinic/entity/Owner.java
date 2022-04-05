package com.petClinic.petClinic.entity;

public class Owner {

    private int id = -1;
    private String fName = "";
    private String lName = "";
    private String dateOfBirth = "";
    private String registrationDate = "";
    private String city = "";
    private String street = "";
    private int house = -1;
    private int apartment = -1;
    private int projectSerialNumber = -1;

    public Owner(String fName, String lName, String dateOfBirth,
                 String registrationDate, String city,
                 String street, int house, int apartment,
                 int projectSerialNumber) {
        this.fName = fName;
        this.lName = lName;
        this.dateOfBirth = dateOfBirth;
        this.registrationDate = registrationDate;
        this.city = city;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
        this.projectSerialNumber = projectSerialNumber;
    }

    public Owner(int id, String fName, String lName, String dateOfBirth,
                 String registrationDate, String city,
                 String street, int house, int apartment,
                 int projectSerialNumber) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.dateOfBirth = dateOfBirth;
        this.registrationDate = registrationDate;
        this.city = city;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
        this.projectSerialNumber = projectSerialNumber;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", registrationDate='" + registrationDate + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", house=" + house +
                ", apartment=" + apartment +
                ", projectSerialNumber=" + projectSerialNumber +
                '}';
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public int getApartment() {
        return apartment;
    }

    public void setApartment(int apartment) {
        this.apartment = apartment;
    }

    public int getProjectSerialNumber() {
        return projectSerialNumber;
    }

    public void setProjectSerialNumber(int projectSerialNumber) {
        this.projectSerialNumber = projectSerialNumber;
    }
}
