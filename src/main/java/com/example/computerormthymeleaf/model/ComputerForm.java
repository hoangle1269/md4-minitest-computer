package com.example.computerormthymeleaf.model;

import org.springframework.web.multipart.MultipartFile;

public class ComputerForm {
    private int id;
    private String computerCode;
    private String computerName;
    private String manufacturer;
    private double price;
    private MultipartFile img;

    public ComputerForm() {
    }

    public ComputerForm(String computerCode, String computerName, String manufacturer, double price, MultipartFile img) {
        this.computerCode = computerCode;
        this.computerName = computerName;
        this.manufacturer = manufacturer;
        this.price = price;
        this.img = img;
    }

    public ComputerForm(int id, String computerCode, String computerName, String manufacturer, double price, MultipartFile img) {
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

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }



}
