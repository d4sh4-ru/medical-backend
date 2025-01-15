package ru.vyatsu.medical_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vyatsu.medical_backend.store.entities.MedicationRestock;
import ru.vyatsu.medical_backend.services.StockService;

@Controller
@RequestMapping
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/edit-stock")
    public String editStockForm(Model model) {
        model.addAttribute("stocks", stockService.getUserStock());
        return "edit-stock";
    }

    @PostMapping("/edit-stock")
    public String editStock(MedicationRestock restock) {
        stockService.updateStock(restock);
        return "redirect:/home";
    }
    //пусть js ходит по маршруту post /edit-stock/{stock_id}/{remainingQuantity}
}
