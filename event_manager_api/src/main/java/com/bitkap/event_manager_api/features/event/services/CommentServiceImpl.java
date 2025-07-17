package com.bitkap.event_manager_api.features.event.services;

import com.bitkap.event_manager_api.exception.ObjectNotFoundException;
import com.bitkap.event_manager_api.features.event.dtos.CommentRequestDto;
import com.bitkap.event_manager_api.features.event.entities.Comment;
import com.bitkap.event_manager_api.features.event.entities.Event;
import com.bitkap.event_manager_api.features.event.mappers.CommentMapper;
import com.bitkap.event_manager_api.features.event.repositories.CommentRepository;
import com.bitkap.event_manager_api.utils.GlobalParams;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SIPOUFO Yvan
 *
 * @implNote That is the service implements CommentService interface, use to manage all actions concerning comment.
 */
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final EventService eventService;
    private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    /**
     * Process:
     *  1. Find comment by id
     *  2. Return comment
     *
     * @param id is the id of comment for whom we want to obtain information.
     * @return the comment object that containing all information relating to this user.
     */
    @Override
    public Comment findOneById(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> {
            logger.info(GlobalParams.ResponseStatusEnum.ERROR.name() + " : ObjectNotFoundException");
            logger.error("Comment not found !");
            return new ObjectNotFoundException("Comment not found !");
        });

        logger.info(GlobalParams.ResponseStatusEnum.SUCCESS.name() + ": Comment returned with id = " + comment.getId() + " !");
        return comment;
    }

    /**
     * Process:
     *  1. Generate Pageable object
     *  2. Get list of all comments withe pageable info
     *  3. Return result of comments
     *
     * @param page is the page index of list of comments.
     * @param size is the size of one page.
     * @return list of comment that containing all comment depend on pagination information.
     */
    @Override
    public Page<Comment> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Comment> comments = commentRepository.findAll(pageable);

        logger.info(GlobalParams.ResponseStatusEnum.SUCCESS.name() + ": List of comments returned with size = " + comments.getSize() + " !");
        return comments;
    }

    /**
     * Process:
     *  1. Check if event exist
     *  2. Get list of all comments depend on id of event
     *  3. Return result of comments
     *
     * @param eventId is the id of event.
     * @return list of comment that containing all comment depend on event id.
     */
    @Override
    public List<Comment> findAllByEvent(Long eventId) {
        eventService.findOneById(eventId);

        List<Comment> comments = commentRepository.findByEventId(eventId);

        logger.info(GlobalParams.ResponseStatusEnum.SUCCESS.name() + ": List of events returned with size = " + comments.size() + " !");
        return comments;
    }

    /**
     * Process:
     *  1. Find event
     *  2. Save and return comment saved
     *
     * @param commentRequestDto is the object of comment that we want to save.
     * @return comment saved.
     */
    @Override
    public Comment save(CommentRequestDto commentRequestDto) {
        Event event = eventService.findOneById(commentRequestDto.getEventId());

        Comment comment = CommentMapper.toEntity(commentRequestDto);
        comment.setEvent(event);

        logger.info(GlobalParams.ResponseStatusEnum.SUCCESS.name() + ": Comment with id = " + comment.getId() + " saved !");
        return commentRepository.save(comment);
    }

    /**
     * Process:
     *  1. Find comment
     *  2. Put enabled of comment to false
     *
     * @param id is the id of comment that we want to delete.
     */
    @Override
    public void delete(Long id) {
        findOneById(id);
        commentRepository.deleteById(id);


        logger.info(GlobalParams.ResponseStatusEnum.SUCCESS.name() + ": Comment with id = " + id + " deleted !");
    }
}
