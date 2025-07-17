package com.bitkap.event_manager_api.features.event.services;

import com.bitkap.event_manager_api.features.event.dtos.CommentRequestDto;
import com.bitkap.event_manager_api.features.event.entities.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author SIPOUFO Yvan
 *
 * @implNote That is the service interface use to manage all actions concerning comment.
 */
public interface CommentService {
    /**
     * Function uses to find one comment by his id.
     *
     * @param id is the id of comment for whom we want to obtain information.
     * @return the comment object that containing all information relating to this user.
     */
    Comment findOneById(Long id);

    /**
     * Function uses to find all comment with pagination.
     *
     * @param page is the page index of list of comments.
     * @param size is the size of one page.
     * @return list of comment that containing all comment depend on pagination information.
     */
    Page<Comment> findAll(int page, int size);

    /**
     * Function uses to find all comment depend on event.
     * @param eventId is the id of event.
     * @return list of comment that containing all comment depend on event id.
     */
    List<Comment> findAllByEvent(Long eventId);

    /**
     * Function uses to save comment.
     * @param commentRequestDto is the object of comment that we want to save.
     * @return comment saved.
     */
    Comment save(CommentRequestDto commentRequestDto);

    /**
     * Function uses for delete comment.
     * @param id is the id of comment that we want to delete.
     */
    void delete(Long id);
}
