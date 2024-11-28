package com.example.assignment_ph38936.model;

public class Car {
    private String id;
    private String maXe;
    private String name;
    private String manufacturer;
    private int year;
    private double price;
    private String description;

    public Car(String id, String maXe, String name, String manufacturer, int year, double price, String description) {
        this.id = id;
        this.maXe = maXe;
        this.name = name;
        this.manufacturer = manufacturer;
        this.year = year;
        this.price = price;
        this.description = description;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getMaXe() { return maXe; }
    public void setMaXe(String maXe) { this.maXe = maXe; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getManufacturer() { return manufacturer; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}

