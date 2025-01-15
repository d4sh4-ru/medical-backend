package ru.vyatsu.medical_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vyatsu.medical_backend.services.UserService;
import ru.vyatsu.medical_backend.store.entities.AdministrationMethod;
import ru.vyatsu.medical_backend.store.entities.MedicationSchedule;
import ru.vyatsu.medical_backend.services.ScheduleService;

@Controller
@RequestMapping
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private UserService userService;

    @GetMapping("/create-schedule")
    public String createScheduleForm(Model model) {
        model.addAttribute("medications", scheduleService.getAllMedications());
        MedicationSchedule medicationSchedule = new MedicationSchedule();
        medicationSchedule.setAdministrationMethod(new AdministrationMethod());
        model.addAttribute("medicationSchedule", medicationSchedule); // Добавляем новый объект MedicationSchedule в модель
        return "create-schedule";
    }

    @PostMapping("/create-schedule")
    public String createSchedule(MedicationSchedule schedule) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            String userEmail = userDetails.getUsername(); // username - это email
            schedule.setUser(userService.findByEmail(userEmail));
        }

        scheduleService.saveSchedule(schedule);
        return "redirect:/home";
    }
}
