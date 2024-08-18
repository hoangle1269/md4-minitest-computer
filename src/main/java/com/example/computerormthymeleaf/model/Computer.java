package com.example.computerormthymeleaf.model;

import javax.persistence.*;

@Entity
@Table(name = "computer")
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String computerCode;
    private String computerName;
    private String manufacturer;
    private double price;
    private String img;

    public Computer() {
    }

    public Computer(String computerCode, String computerName, String manufacturer, double price, String img) {
        this.computerCode = computerCode;
        this.computerName = computerName;
        this.manufacturer = manufacturer;
        this.price = price;
        this.img = img;
    }

    public Computer(int id, String computerCode, String computerName, String manufacturer, double price, String img) {
        this.id = id;
        this.computerCode = computerCode;
        this.computerName = computerName;
        this.manufacturer = manufacturer;
        this.price = price;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComputerCode() {
        return computerCode;
    }

    public void setComputerCode(String computerCode) {
        this.computerCode = computerCode;
    }

    public String getComputerName() {
        return computerName;
    }

    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
