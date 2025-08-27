package com.ccs.Controllers.NotificationsControllers;

import com.ccs.Models.Notification;
import com.ccs.Services.NotificationService.NotificationService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationsController {

    private final NotificationService notificationService = new NotificationService();

    // GET /notifications/{user_id}
    @GetMapping("/{user_id}")
    public List<Notification> getNotificationsByUserId(@PathVariable int user_id) {
        return notificationService.getNotificationsByUserId(user_id);
    }

    // POST /notifications
    @PostMapping
    public Notification createNotification(@RequestBody Notification notification) {
        return notificationService.createNotification(notification);
    }

    // PUT /notifications/{id}/read
    @PutMapping("/{id}/read")
    public Notification markNotificationAsRead(@PathVariable int id) {
        return notificationService.markAsRead(id);
    }
}
