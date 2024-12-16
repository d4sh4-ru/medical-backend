package ru.vyatsu.medical_backend.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.vyatsu.medical_backend.DTO.CoordinatesDto;
import ru.vyatsu.medical_backend.store.entities.PharmacySearchTemplate;
import ru.vyatsu.medical_backend.store.repositories.PharmacySearchTemplateRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PharmacySearchTemplateService {
    @Autowired
    private PharmacySearchTemplateRepository pharmacySearchTemplateRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<String> findNearest(CoordinatesDto coordinatesDto) {
        // Формируем URL с параметрами широты и долготы
        String url = String.format("http://127.0.0.1:5000/nearest_pharmacies?lat=%f&lon=%f",
                coordinatesDto.getLatitude(), coordinatesDto.getLongitude()).replace(",", ".");

        // Выполняем HTTP-запрос и получаем ответ
        String response = restTemplate.getForObject(url, String.class);

        // Список для хранения названий аптек
        List<String> pharmacyUrls = new ArrayList<>();

        try {
            // Преобразуем ответ в JSON
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode pharmaciesNode = objectMapper.readTree(response);

            // Перебираем полученный JSON и извлекаем названия аптек
            for (JsonNode pharmacyNode : pharmaciesNode) {
                PharmacySearchTemplate pharmacySearchTemplate = pharmacySearchTemplateRepository.findByPharmacyName(pharmacyNode.get("name").asText()).orElse(null);
                String urlPharmacy;
                if (pharmacySearchTemplate != null) {
                    urlPharmacy = pharmacySearchTemplate.getSearchUrlTemplate();
                }
                else{
                    urlPharmacy = "";
                }
                pharmacyUrls.add(urlPharmacy);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Обрабатываем ошибки парсинга JSON
        }

        // Возвращаем список названий аптек
        return pharmacyUrls;
    }

    public List<PharmacySearchTemplate> findAll() {
        return pharmacySearchTemplateRepository.findAll();
    }

    public Optional<PharmacySearchTemplate> findById(Long id) {
        return pharmacySearchTemplateRepository.findById(id);
    }

    public PharmacySearchTemplate save(PharmacySearchTemplate pharmacySearchTemplate) {
        return pharmacySearchTemplateRepository.save(pharmacySearchTemplate);
    }

    public PharmacySearchTemplate update(PharmacySearchTemplate pharmacySearchTemplate) {
        return pharmacySearchTemplateRepository.save(pharmacySearchTemplate);
    }

    public void deleteById(Long id) {
        pharmacySearchTemplateRepository.deleteById(id);
    }
}
