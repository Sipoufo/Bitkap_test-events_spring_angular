package com.bitkap.event_manager_api.features.event.entities;

import com.bitkap.event_manager_api.utils.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SIPOUFO Yvan
 *
 * @implNote Entity use for event.
 */
@Entity
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "e_event")
public class Event extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, unique = true, length = 100)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "event_date", nullable = false)
    private Instant eventDate;

    @Builder.Default
    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @Builder.Default()
    @Column(name = "enabled", nullable = false)
    private Boolean enabled = true;
}
