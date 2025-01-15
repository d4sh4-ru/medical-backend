package ru.vyatsu.medical_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vyatsu.medical_backend.services.NotificationService;

@Controller
@RequestMapping
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @GetMapping("/submit/{id}")
    public String submit(Model model, @PathVariable int id) {
        notificationService.submit(id);
        return "redirect:/home";
    }
}
