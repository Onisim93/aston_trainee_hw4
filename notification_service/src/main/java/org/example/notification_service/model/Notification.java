package org.example.notification_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "notifications")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    private UUID idempotencyKey;

    private String text;

    @CreatedDate
    private LocalDateTime created;
}
