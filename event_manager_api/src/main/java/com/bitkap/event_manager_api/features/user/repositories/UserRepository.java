package com.bitkap.event_manager_api.features.user.repositories;

import com.bitkap.event_manager_api.features.event.entities.Event;
import com.bitkap.event_manager_api.features.user.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author SIPOUFO Yvan
 *
 * @implNote That's the repository use to manage all db request concerning user.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
