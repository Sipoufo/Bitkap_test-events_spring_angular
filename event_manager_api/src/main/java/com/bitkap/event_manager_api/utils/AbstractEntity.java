package com.bitkap.event_manager_api.utils;

import com.bitkap.event_manager_api.features.user.entities.User;
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
    Instant createdAt;

    // Use to determine when the data has been updated
    @UpdateTimestamp
    @Column(name = "updated_at")
    Instant updatedAt;

    // Use to determine who inserted the data
    @Column(name = "created_by_user_id")
    String createdByUserId;

    @Column(name = "created_by_user_name")
    String createdByUserName;

    // Use to determine who updated the data
    @Column(name = "update_by_user_id")
    String updateByUserId;

    @Column(name = "update_by_user_name")
    String updateByUserName;
}
