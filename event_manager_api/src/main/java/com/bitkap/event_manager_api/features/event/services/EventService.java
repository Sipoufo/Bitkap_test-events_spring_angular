package com.bitkap.event_manager_api.features.event.services;

import com.bitkap.event_manager_api.features.event.dtos.EventRequestDto;
import com.bitkap.event_manager_api.features.event.entities.Event;
import com.bitkap.event_manager_api.features.event.entities.Event;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author SIPOUFO Yvan
 *
 * @implNote That is the service interface use to manage all actions concerning event.
 */
public interface EventService {
    /**
     * Function uses to find one event by his id.
     *
     * @param id is the id of event for whom we want to obtain information.
     * @return the event object that containing all information relating to this user.
     */
    Event findOneById(Long id);

    /**
     * Function uses to find all event with pagination.
     *
     * @param page is the page index of list of events.
     * @param size is the size of one page.
     * @return list of event that containing all event depend on pagination information.
     */
    Page<Event> findAll(int page, int size);

    /**
     * Function uses to find all event depend on event.
     * @param userId is the id of user.
     * @return list of event that containing all event depend on user id.
     */
    List<Event> findAllByUser(Long userId);

    /**
     * Function uses to save event.
     * @param eventRequestDto is the object of event that we want to save.
     * @return event saved.
     */
    Event save(EventRequestDto eventRequestDto);

    /**
     * Function uses to save event.
     * @param id is the id of event.
     * @param eventRequestDto is the object of event that we want to save.
     * @return event updated.
     */
    Event update(Long id, EventRequestDto eventRequestDto);

    /**
     * Function uses for delete event.
     * @param id is the id of event that we want to delete.
     */
    void delete(Long id);

    /**
     * Function uses for delete event.
     * @param query represent the value of items that we want.
     * @return list of event that containing all event depend on user query.
     */
    Page<Event> findSelect(String query);
}
