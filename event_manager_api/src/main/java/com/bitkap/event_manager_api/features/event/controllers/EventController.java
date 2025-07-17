package com.bitkap.event_manager_api.features.event.controllers;

import com.bitkap.event_manager_api.features.event.dtos.EventRequestDto;
import com.bitkap.event_manager_api.features.event.dtos.EventResponseDto;
import com.bitkap.event_manager_api.features.event.entities.Event;
import com.bitkap.event_manager_api.features.event.mappers.EventMapper;
import com.bitkap.event_manager_api.features.event.services.EventService;
import com.bitkap.event_manager_api.utils.GlobalParams;
import com.bitkap.event_manager_api.utils.dtos.ListObjectResponseDto;
import com.bitkap.event_manager_api.utils.dtos.PageObjectResponseDto;
import com.bitkap.event_manager_api.utils.dtos.SimpleObjectResponseDto;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

/**
 *
 * @author SIPOUFO Yvan
 *
 * @implNote That is the controller that mange all events in the application.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(GlobalParams.API_PREFIX + "/event")
@Tag(name = "Event", description = "The event API part.")
public class EventController {
    private final EventService eventService;

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event successful returned !"),
            @ApiResponse(responseCode = "404", description = "Event not found !")
    })
    @ResponseStatus(HttpStatus.OK)
    public SimpleObjectResponseDto<EventResponseDto> findOneById(@PathVariable("id") Long id) {
        return new SimpleObjectResponseDto<>(GlobalParams.ResponseStatusEnum.SUCCESS.name(), EventMapper.toResponse(eventService.findOneById(id)));
    }

    @GetMapping("")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events successful returned !"),
    })
    @ResponseStatus(HttpStatus.OK)
    public PageObjectResponseDto<EventResponseDto> findAll(
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "10", required = false) Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Event> events = eventService.findAll(page, size);
        Page<EventResponseDto> eventResult = new PageImpl<>(
                events.getContent().stream()
                    .map(EventMapper::toResponse)
                    .collect(Collectors.toList()),
                pageable,
                events.getContent().size()
        );

        return new PageObjectResponseDto<>(
                GlobalParams.ResponseStatusEnum.SUCCESS.name(),
                eventResult
        );
    }

    @GetMapping("/user/{userId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events successful returned !"),
    })
    @ResponseStatus(HttpStatus.OK)
    public ListObjectResponseDto<EventResponseDto> findAllByUser(@PathVariable("userId") Long userId) {
        return new ListObjectResponseDto<>(
                GlobalParams.ResponseStatusEnum.SUCCESS.name(),
                eventService.findAllByUser(userId).stream()
                        .map(EventMapper::toResponse)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Event successful saved !"),
            @ApiResponse(responseCode = "403", description = "Event with same title exit !")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public SimpleObjectResponseDto<EventResponseDto> save(@Valid @RequestBody EventRequestDto eventRequestDto) {
        return new SimpleObjectResponseDto<>(GlobalParams.ResponseStatusEnum.SUCCESS.name(), EventMapper.toResponse(eventService.save(eventRequestDto)));
    }

    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Event successful updated !"),
            @ApiResponse(responseCode = "403", description = "Event with same title exit !")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public SimpleObjectResponseDto<EventResponseDto> update(@PathVariable("id") Long id, @Valid @RequestBody EventRequestDto eventRequestDto) {
        return new SimpleObjectResponseDto<>(GlobalParams.ResponseStatusEnum.SUCCESS.name(), EventMapper.toResponse(eventService.update(id, eventRequestDto)));
    }

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event successful deleted !"),
            @ApiResponse(responseCode = "404", description = "Event not found !")
    })
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        eventService.delete(id);
    }

    @GetMapping("/select")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event successful deleted !")
    })
    @ResponseStatus(HttpStatus.OK)
    public PageObjectResponseDto<EventResponseDto> select(
            @RequestParam(value = "query", required = false, defaultValue = "") String query
    ) {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Event> events = eventService.findSelect(query);
        Page<EventResponseDto> eventResult = new PageImpl<>(
                events.getContent().stream()
                        .map(EventMapper::toResponse)
                        .collect(Collectors.toList()),
                pageable,
                events.getSize()
        );

        return new PageObjectResponseDto<>(
                GlobalParams.ResponseStatusEnum.SUCCESS.name(),
                eventResult
        );
    }
}
