package ru.vyatsu.medical_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vyatsu.medical_backend.store.entities.MedicationNotification;
import ru.vyatsu.medical_backend.store.entities.MedicationRestock;
import ru.vyatsu.medical_backend.store.repositories.MedicationNotificationRepository;
import ru.vyatsu.medical_backend.store.repositories.MedicationScheduleRepository;
import ru.vyatsu.medical_backend.store.repositories.MedicationRestockRepository;
import ru.vyatsu.medical_backend.store.repositories.UserRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

@Service
public class MedicationService {

    @Autowired
    private MedicationNotificationRepository notificationRepository;

    @Autowired
    private MedicationScheduleRepository scheduleRepository;

    @Autowired
    private MedicationRestockRepository restockRepository;

    @Autowired
    private UserRepository userRepository;

    public List<MedicationNotification> findMedicationNotificationByStatusAndUser(String status, String email) {
        return notificationRepository.findByStatusAndUser(status, userRepository.findByEmail(email).orElseThrow());
    }

    public List<List<String>> getCalendar(String userEmail) {
        List<MedicationNotification> notifications = notificationRepository.findByUser(userRepository.findByEmail(userEmail).orElseThrow());

        LocalDate today = LocalDate.now();
        int daysInMonth = today.lengthOfMonth();
        Map<LocalDate, Integer> calendar = new TreeMap<>();

        for (MedicationNotification notification : notifications) {
            LocalDate date = notification.getSentAt().toLocalDate();
            calendar.put(date, calendar.getOrDefault(date, 0) + 1);
        }

        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate date = today.withDayOfMonth(day);
            calendar.putIfAbsent(date, 0);  // если дня нет в уведомлениях, ставим 0
        }

        LocalDate firstDayOfMonth = today.withDayOfMonth(1);
        DayOfWeek firstDayOfWeek = firstDayOfMonth.getDayOfWeek();
        int shift = firstDayOfWeek.getValue() % 7;

        List<List<String>> calendarList = new ArrayList<>();
        for (int i = 0; i < shift; i++) {
            calendarList.add(List.of("", "white"));
        }

        for (Map.Entry<LocalDate, Integer> entry : calendar.entrySet()) {
            String color = switch (entry.getValue()) {
                case 0 -> "white";
                case 1 -> "blue";
                case 2 -> "green";
                case 3 -> "yellow";
                case 4 -> "orange";
                default -> "red";
            };
            calendarList.add(List.of(String.valueOf(entry.getKey().getDayOfMonth()), color));
        }

        return calendarList;
    }

    public List<MedicationRestock> getUserStock(String email) {
        return restockRepository.findByUser(userRepository.findByEmail(email).orElseThrow());
    }

    public String getUserStatistics(String email) {
        var user = userRepository.findByEmail(email).orElseThrow(() ->
                new IllegalArgumentException("Пользователь с email " + email + " не найден"));

        int upcomingCount = notificationRepository.findByStatusAndUser("not sent", user).size();
        int totalCount = notificationRepository.findByUser(user).size();
        int confirmedCount = notificationRepository.findByStatusAndUser("confirmed", user).size();
        int overdueCount = notificationRepository.findByStatusAndUser("overdue", user).size();

        return String.format(
                "Предстоящих приёмов: %d\nВсего приёмов: %d\nПодтвержденных приёмов: %d\nПросроченных приёмов: %d",
                upcomingCount, totalCount, confirmedCount, overdueCount
        );
    }
}
