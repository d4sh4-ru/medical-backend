package ru.vyatsu.medical_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vyatsu.medical_backend.store.entities.MedicationRestock;
import ru.vyatsu.medical_backend.store.repositories.MedicationRestockRepository;

import java.util.List;

@Service
public class StockService {

    @Autowired
    private MedicationRestockRepository restockRepository;

    public List<MedicationRestock> getUserStock() {
        return restockRepository.findAll();
    }

    public void updateStock(MedicationRestock restock) {
        restockRepository.save(restock);
    }
}
