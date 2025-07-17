package com.bitkap.event_manager_api.features.event.services;

import com.bitkap.event_manager_api.exception.ObjectExistsException;
import com.bitkap.event_manager_api.exception.ObjectNotFoundException;
import com.bitkap.event_manager_api.features.event.dtos.EventRequestDto;
import com.bitkap.event_manager_api.features.event.entities.Event;
import com.bitkap.event_manager_api.features.event.mappers.EventMapper;
import com.bitkap.event_manager_api.features.event.repositories.EventRepository;
import com.bitkap.event_manager_api.features.user.entities.User;
import com.bitkap.event_manager_api.features.user.services.UserService;
import com.bitkap.event_manager_api.utils.GlobalParams;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author SIPOUFO Yvan
 *
 * @implNote That is the service implements EventService interface, use to manage all actions concerning event.
 */
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService{
    private final EventRepository eventRepository;
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);

    /**
     * Process:
     *  1. Find event by id
     *  2. Check if event is enabled
     *  3. Return event
     *
     * @param id is the id of event for whom we want to obtain information.
     * @return the event object that containing all information relating to this user
     */
    @Override
    public Event findOneById(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(() -> {
            logger.info(GlobalParams.ResponseStatusEnum.ERROR.name() + " : ObjectNotFoundException");
            logger.error("Event not found !");
            return new ObjectNotFoundException("Event not found !");
        });

        if (!event.getEnabled()) {
            logger.info(GlobalParams.ResponseStatusEnum.ERROR + " : ObjectNotFoundException");
            logger.error("Event not found !");
            throw new ObjectNotFoundException("Event not found !");
        }

        logger.info(GlobalParams.ResponseStatusEnum.SUCCESS.name() + ": Event returned with id = " + event.getId() + " !");
        return event;
    }

    /**
     * Process:
     *  1. Generate Pageable object
     *  2. Get list of all events withe pageable info
     *  3. Return result of events
     *
     * @param page is the page index of list of events.
     * @param size is the size of one page.
     * @return list of event that containing all event depend on pagination information.
     */
    @Override
    public Page<Event> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Event> events = eventRepository.findByEnabledTrue(pageable);

        logger.info(GlobalParams.ResponseStatusEnum.SUCCESS.name() + ": List of events returned with size = " + events.getContent().size() + " !");
        return events;
    }

    /**
     * Process:
     *  1. Check if event exist
     *  2. Get list of all events depend on id of user
     *  3. Return result of events
     *
     * @param userId is the id of user.
     * @return list of event that containing all event depend on user id.
     */
    @Override
    public List<Event> findAllByUser(Long userId) {
        userService.findOneById(userId);

        List<Event> events = eventRepository.findByUserIdAndEnabledTrue(userId);

        logger.info(GlobalParams.ResponseStatusEnum.SUCCESS.name() + ": List of events returned with size = " + events.size() + " !");
        return events;
    }

    /**
     * Process:
     *  1. Find event
     *  2. Check if title exit
     *  3. Save and return event saved
     *
     * @param eventRequestDto is the object of event that we want to save.
     * @return event saved.
     */
    @Override
    public Event save(EventRequestDto eventRequestDto) {
        User user = userService.findOneById(eventRequestDto.getUserId());

        Optional<Event> eventFind = eventRepository.findByTitle(eventRequestDto.getTitle());
        if (eventFind.isPresent()) {
            logger.info(GlobalParams.ResponseStatusEnum.ERROR + " : ObjectExistsException");
            logger.error("Event with same title exit !");
            throw new ObjectExistsException("Event with same title exit !");
        }

        Event event = EventMapper.toEntity(eventRequestDto);
        event.setUser(user);

        logger.info(GlobalParams.ResponseStatusEnum.SUCCESS.name() + ": Event with id = " + event.getId() + " saved !");
        return eventRepository.save(event);
    }
    
    /**
     * Process:
     *  1. Find event
     *  2. Check if title exit
     *  3. Save and return event saved
     *
     * @param id is the id of event.
     * @param eventRequestDto is the object of event that we want to save.
     * @return event updated.
     */
    @Override
    public Event update(Long id, EventRequestDto eventRequestDto) {
        User user = userService.findOneById(eventRequestDto.getUserId());

        Optional<Event> eventCheck = eventRepository.findByTitle(eventRequestDto.getTitle());
        if (eventCheck.isPresent() && !Objects.equals(eventCheck.get().getId(), id)) {
            logger.info(GlobalParams.ResponseStatusEnum.ERROR + " : ObjectExistsException");
            logger.error("Event with same title exit !");
            throw new ObjectExistsException("Event with same title exit !");
        }

        Event event = findOneById(id);
        event.setTitle(eventRequestDto.getTitle());
        event.setDescription(eventRequestDto.getDescription());
        event.setEventDate(eventRequestDto.getEventDate());
        event.setUser(user);

        logger.info(GlobalParams.ResponseStatusEnum.SUCCESS.name() + ": Event with id = " + event.getId() + " updated !");
        return eventRepository.save(event);
    }


    /**
     * Process:
     *  1. Find event
     *  2. Put enabled of event to false
     *
     * @param id is the id of event that we want to delete.
     */
    @Override
    public void delete(Long id) {
        Event event = findOneById(id);
        event.setEnabled(false);
        eventRepository.save(event);


        logger.info(GlobalParams.ResponseStatusEnum.SUCCESS.name() + ": Event with id = " + event.getId() + " deleted !");
    }

    /**
     * Process:
     *  1. Find event
     *
     * @param query represent the value of items that we want.
     * @return list of event that containing all event depend on user query.
     */
    @Override
    public Page<Event> findSelect(String query) {
        Pageable pageable = PageRequest.of(0, 20);

        return eventRepository.findSelect(query, pageable);
    }
}
