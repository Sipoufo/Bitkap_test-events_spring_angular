package com.bitkap.event_manager_api.features.event.mappers;

import com.bitkap.event_manager_api.features.event.dtos.EventRequestDto;
import com.bitkap.event_manager_api.features.event.dtos.EventResponseDto;
import com.bitkap.event_manager_api.features.event.entities.Event;
import com.bitkap.event_manager_api.features.user.mappers.UserMapper;

import java.util.stream.Collectors;

/**
 * @author SIPOUFO Yvan
 *
 * @implNote That's the event mapper class, which maps event objects.
 */
public class EventMapper {
    // Map event request object to event entity object.
    public static Event toEntity(EventRequestDto eventRequestDto) {
        return Event
                .builder()
                .title(eventRequestDto.getTitle())
                .description(eventRequestDto.getDescription())
                .eventDate(eventRequestDto.getEventDate())
                .build();
    }

    // Map event entity object to event response object.
    public static EventResponseDto toResponse(Event event) {
        return EventResponseDto
                .builder()
                .id(event.getId())
                .title(event.getTitle())
                .description(event.getDescription())
                .eventDate(event.getEventDate())
                .organizerId(event.getUser().getId())
                .organizerName(event.getUser().getDisplayName())
                .commentSize(
                        event.getComments().size()
                )
//                .createByUserId(event.getCreatedBy().getId())
//                .createByUserName(event.getCreatedBy().getDisplayName())
                .createAt(event.getCreatedAt())
                .enabled(event.getEnabled())
                .build();
    }
}
