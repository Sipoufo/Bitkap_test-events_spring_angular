package com.bitkap.event_manager_api.features.event.entities;

import com.bitkap.event_manager_api.features.user.entities.User;
import com.bitkap.event_manager_api.utils.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

/**
 * @author SIPOUFO Yvan
 *
 * @implNote Entity use for comment.
 */
@Entity
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "e_comment")
public class Comment extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value", nullable = false, length = 100)
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
}
