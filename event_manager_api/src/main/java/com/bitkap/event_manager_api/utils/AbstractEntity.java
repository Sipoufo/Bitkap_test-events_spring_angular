package com.bitkap.event_manager_api.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.Instant;

/**
 * @author SIPOUFO Yvan
 *
 * @implNote Entity use as completion part of all the others entities and help to determine who and when the data is insert or update.
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
public class AbstractEntity implements Serializable {
    // Use to determine when the data has been inserted
    @CreationTimestamp
    @Column(name = "created_at")
    protected Instant createdAt;

    // Use to determine when the data has been updated
    @UpdateTimestamp
    @Column(name = "updated_at")
    protected Instant updatedAt;

    // Use to determine who inserted the data
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "u_user_created_by_id")
    protected User createdBy;

    // Use to determine who updated the data
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "u_user_updated_by_id")
    protected User updatedBy;
}
