package com.example.computerormthymeleaf.controller;

import com.example.computerormthymeleaf.model.Computer;
import com.example.computerormthymeleaf.model.ComputerForm;
import com.example.computerormthymeleaf.service.IComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("computers")
@PropertySource("classpath:upload_file.properties")
public class ComputerController {

    @Autowired
    private final IComputerService computerService;

    public ComputerController(IComputerService computerService) {
        this.computerService = computerService;
    }

    @GetMapping("")
    public String index(Model model) {
        List<Computer> computers = computerService.findAll();
        model.addAttribute("computers", computers);
        return "/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("computer", new Computer());
        return "/create";
    }

    @Value("${file-upload}")
    private String upload;

    @PostMapping("/save")
    public String save(ComputerForm computerForm) {
        MultipartFile file = computerForm.getImg();
        String fileName = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(computerForm.getImg().getBytes(), new File(upload + fileName));
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        Computer computer = new Computer();
        computer.setComputerCode(computerForm.getComputerCode());
        computer.setComputerName(computerForm.getComputerName());
        computer.setManufacturer(computerForm.getManufacturer());
        computer.setPrice(computerForm.getPrice());
        computer.setImg(fileName);
        computerService.save(computer);
        return "redirect:/computers";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable int id, Model model) {
        model.addAttribute("computer", computerService.findById(id));
        return "/update";
    }

    @PostMapping("/update")
    public String update(ComputerForm computerForm) {
        MultipartFile file = computerForm.getImg();
        String fileName = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(upload+fileName));
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        Computer computer = new Computer();
        computer.setComputerCode(computerForm.getComputerCode());
        computer.setComputerName(computerForm.getComputerName());
        computer.setManufacturer(computerForm.getManufacturer());
        computer.setPrice(computerForm.getPrice());
        computer.setImg(fileName);
        computerService.update(computer.getId(), computer);
        return "redirect:/posts";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, Model model) {
        model.addAttribute("computer", computerService.findById(id));
        return "/delete";
    }

    @PostMapping("/delete")
    public String delete(Computer computer, RedirectAttributes redirect) {
        computerService.remove(computer.getId());
        redirect.addFlashAttribute("success", "Removed computer successfully!");
        return "redirect:/posts";
    }

    @GetMapping("/{id}/view")
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("computer", computerService.findById(id));
        return "/view";
    }

}
