package ru.vyatsu.medical_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vyatsu.medical_backend.services.MedicationService;
import ru.vyatsu.medical_backend.services.UserService;

@Controller
@RequestMapping
public class HomeController {

    @Autowired
    private MedicationService medicationService;

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String home(Model model) {
        String userEmail = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            userEmail = userDetails.getUsername();
        }
        model.addAttribute("notifications", medicationService.findMedicationNotificationByStatusAndUser("not sent", userEmail));
        model.addAttribute("calendar", medicationService.getCalendar(userEmail));
        model.addAttribute("statistics", medicationService.getUserStatistics(userEmail));
        model.addAttribute("stock", medicationService.getUserStock(userEmail));
        return "home";
    }
}
