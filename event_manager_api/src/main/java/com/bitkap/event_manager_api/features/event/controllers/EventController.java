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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
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
    @Operation(summary = "Find one event.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event successful returned !"),
            @ApiResponse(responseCode = "404", description = "Event not found !")
    })
    @ResponseStatus(HttpStatus.OK)
    public SimpleObjectResponseDto<EventResponseDto> findOneById(@PathVariable("id") Long id) {
        return new SimpleObjectResponseDto<>(GlobalParams.ResponseStatusEnum.SUCCESS.name(), EventMapper.toResponse(eventService.findOneById(id)));
    }

    @GetMapping("/all")
    @SecurityRequirements
    @Operation(
            summary = "Find all events."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events successful returned !"),
    })
    @ResponseStatus(HttpStatus.OK)
    public PageObjectResponseDto<EventResponseDto> findAll(
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "10", required = false) Integer size
    ) {
        Page<Event> events = eventService.findAll(page, size);
        System.out.println("total page = " + events.getTotalPages());

        Page<EventResponseDto> eventResult = new PageImpl<>(
                events.getContent().stream()
                    .map(EventMapper::toResponse)
                    .collect(Collectors.toList()),
                events.getPageable(),
                events.getTotalElements()
        );

        System.out.println("total page eventResult = " + eventResult.getTotalPages());

        return new PageObjectResponseDto<>(
                GlobalParams.ResponseStatusEnum.SUCCESS.name(),
                eventResult
        );
    }

    @GetMapping("/user/current-user")
    @Operation(summary = "Find all events for current user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events successful returned !"),
    })
    @ResponseStatus(HttpStatus.OK)
    public ListObjectResponseDto<EventResponseDto> findAllByUser() {
        return new ListObjectResponseDto<>(
                GlobalParams.ResponseStatusEnum.SUCCESS.name(),
                eventService.findAllForCurrentUser().stream()
                        .map(EventMapper::toResponse)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("")
    @Operation(summary = "Save one event.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Event successful saved !"),
            @ApiResponse(responseCode = "403", description = "Event with same title exit !")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public SimpleObjectResponseDto<EventResponseDto> save(@Valid @RequestBody EventRequestDto eventRequestDto) {
        return new SimpleObjectResponseDto<>(GlobalParams.ResponseStatusEnum.SUCCESS.name(), EventMapper.toResponse(eventService.save(eventRequestDto)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update one event.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Event successful updated !"),
            @ApiResponse(responseCode = "403", description = "Event with same title exit !")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public SimpleObjectResponseDto<EventResponseDto> update(@PathVariable("id") Long id, @Valid @RequestBody EventRequestDto eventRequestDto) {
        return new SimpleObjectResponseDto<>(GlobalParams.ResponseStatusEnum.SUCCESS.name(), EventMapper.toResponse(eventService.update(id, eventRequestDto)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete one event.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event successful deleted !"),
            @ApiResponse(responseCode = "404", description = "Event not found !")
    })
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        eventService.delete(id);
    }

    @GetMapping("/select")
    @Operation(summary = "Get all event depending on query.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event successful deleted !")
    })
    @ResponseStatus(HttpStatus.OK)
    public PageObjectResponseDto<EventResponseDto> select(
            @RequestParam(value = "query", required = false, defaultValue = "") String query
    ) {
        Page<Event> events = eventService.findSelect(query);
        Page<EventResponseDto> eventResult = new PageImpl<>(
                events.getContent().stream()
                        .map(EventMapper::toResponse)
                        .collect(Collectors.toList()),
                events.getPageable(),
                events.getTotalElements()
        );

        return new PageObjectResponseDto<>(
                GlobalParams.ResponseStatusEnum.SUCCESS.name(),
                eventResult
        );
    }
}
