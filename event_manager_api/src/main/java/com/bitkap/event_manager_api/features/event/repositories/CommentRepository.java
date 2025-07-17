package com.bitkap.event_manager_api.features.event.repositories;

import com.bitkap.event_manager_api.features.event.entities.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author SIPOUFO Yvan
 *
 * @implNote That's the repository use to manage all db request concerning comment.
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(
            value = "SELECT com " +
                    "FROM Comment com " +
                    "WHERE (:query IS NULL OR :query = '' OR upper(com.value) LIKE concat('%', upper(:query), '%')) " +
                    "ORDER BY com.createdAt DESC",
            countQuery = "SELECT COUNT(com.id) " +
                    "FROM Comment com " +
                    "WHERE (:query IS NULL OR :query = '' OR upper(com.value) LIKE concat('%', upper(:query), '%')) "
    )
    Page<Comment> findSelect(String query, Pageable pageable);

    List<Comment> findByEventId(Long eventId);
}
