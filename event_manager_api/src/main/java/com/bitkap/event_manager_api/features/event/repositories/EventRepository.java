package com.bitkap.event_manager_api.features.event.repositories;

import com.bitkap.event_manager_api.features.event.entities.Event;
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
 * @implNote That's the repository use to manage all db request concerning event.
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query(
            value = "SELECT eve " +
                    "FROM Event eve " +
                    "WHERE (:query IS NULL OR :query = '' OR upper(eve.title) LIKE concat('%', upper(:query), '%')) " +
                    "AND (:query IS NULL OR :query = '' OR upper(eve.description) LIKE concat('%', upper(:query), '%')) " +
                    "ORDER BY eve.createdAt DESC",
            countQuery = "SELECT COUNT(eve.id) " +
                    "FROM Event eve " +
                    "WHERE (:query IS NULL OR :query = '' OR upper(eve.title) LIKE concat('%', upper(:query), '%')) " +
                    "AND (:query IS NULL OR :query = '' OR upper(eve.description) LIKE concat('%', upper(:query), '%')) "
    )
    Page<Event> findSelect(String query, Pageable pageable);

    Optional<Event> findByTitle(String title);

    List<Event> findByUserIdAndEnabledTrue(Long userId);

    Page<Event> findByEnabledTrue(Pageable pageable);
}
