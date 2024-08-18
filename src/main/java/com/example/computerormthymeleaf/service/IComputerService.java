package com.example.computerormthymeleaf.service;

import com.example.computerormthymeleaf.model.Computer;

import java.util.List;

public interface IComputerService {
    List<Computer> findAll();

    void save(Computer computer);

    Computer findById(int id);

    void update(int id, Computer computer);

    void remove(int id);

    Computer findByName(String name);

    List<Computer> findByPriceRange(double minPrice, double maxPrice);
}
