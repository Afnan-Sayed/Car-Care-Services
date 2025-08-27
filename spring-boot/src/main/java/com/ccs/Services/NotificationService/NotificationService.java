package com.ccs.Services.NotificationService;

import com.ccs.Models.Notification;
import com.ccs.Repository.NotificationsRepo;
import java.util.List;

/*
Kareem
Epic 9: Notifications APIs
•	GET /notifications/{user_id}
•	POST /notifications
•	PUT /notifications/{id}/read
 */
public class NotificationService {
    private final NotificationsRepo notificationsRepo = new NotificationsRepo();

    public List<Notification> getNotificationsByUserId(int userId) {
        return notificationsRepo.findByUserId(userId);
    }

    public Notification createNotification(Notification notification) {
        return notificationsRepo.save(notification);
    }

    public Notification markAsRead(int id) {
        return notificationsRepo.markAsRead(id);
    }
}
