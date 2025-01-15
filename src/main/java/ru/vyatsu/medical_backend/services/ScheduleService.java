package ru.vyatsu.medical_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vyatsu.medical_backend.store.entities.AdministrationMethod;
import ru.vyatsu.medical_backend.store.entities.Medication;
import ru.vyatsu.medical_backend.store.entities.MedicationNotification;
import ru.vyatsu.medical_backend.store.entities.MedicationSchedule;
import ru.vyatsu.medical_backend.store.repositories.AdministrationMethodRepository;
import ru.vyatsu.medical_backend.store.repositories.MedicationNotificationRepository;
import ru.vyatsu.medical_backend.store.repositories.MedicationRepository;
import ru.vyatsu.medical_backend.store.repositories.MedicationScheduleRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private MedicationRepository medicationRepository;

    @Autowired
    private MedicationScheduleRepository scheduleRepository;

    @Autowired
    private AdministrationMethodRepository administrationMethodRepository;

    @Autowired
    private MedicationNotificationRepository medicationNotificationRepository;

    public List<Medication> getAllMedications() {
        return medicationRepository.findAll();
    }

    public void saveSchedule(MedicationSchedule schedule) {
        schedule.getAdministrationMethod().setMedication(schedule.getMedication());
        administrationMethodRepository.save(schedule.getAdministrationMethod());
        scheduleRepository.save(schedule);
        generateNotification(schedule);
    }

    public void generateNotification(MedicationSchedule schedule) {
        // Получаем начальную дату расписания
        LocalDate startDate = schedule.getStartDate();
        // Получаем метод приема
        AdministrationMethod method = schedule.getAdministrationMethod();
        // Интервал между приемами
        Integer interval = method.getInterval();
        // Времена приема в строке, разделенные запятой
        String[] times = method.getAdministrationTimes().split(",");

        // Перебираем дни от начала до конца расписания
        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(schedule.getEndDate())) {
            for (String time : times) {
                // Парсим время в формате HH:mm и добавляем к текущей дате
                LocalDateTime sentAt = LocalDateTime.of(currentDate, LocalTime.parse(time.trim()));

                // Определяем статус (прошло время или нет)
                String status = sentAt.isBefore(LocalDateTime.now()) ? "pass" : "not sent";

                // Создаем уведомление
                MedicationNotification notification = MedicationNotification.builder()
                        .medicationSchedule(schedule)
                        .user(schedule.getUser())
                        .relatedUser(null)
                        .sentAt(sentAt)
                        .status(status)
                        .actualTakenAt(null)
                        .build();

                // Сохраняем уведомление в базе
                medicationNotificationRepository.save(notification);
            }

            // Увеличиваем дату на `interval` дней
            currentDate = currentDate.plusDays(interval);
        }
    }
}
