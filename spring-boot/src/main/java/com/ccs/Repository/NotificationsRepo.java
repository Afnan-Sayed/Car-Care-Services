package com.ccs.Repository;

import com.ccs.Models.Notification;
import java.util.*;
import java.time.LocalDateTime;

public class NotificationsRepo {

    private final List<Notification> notifications = new ArrayList<>();

    public List<Notification> findByUserId(int userId) {
        List<Notification> result = new ArrayList<>();
        for (Notification n : notifications) {
            if (n.getUser().getId() == userId) {
                result.add(n);
            }
        }
        return result;
    }

    public Notification save(Notification notification) {
        notification.setCreatedAt(LocalDateTime.now());
        notifications.add(notification);
        return notification;
    }

    public Notification markAsRead(int id) {
        for (Notification n : notifications) {
            if (n.getId() == id) {
                n.setRead(true);
                return n;
            }
        }
        return null;
    }
}
