package com.bitkap.event_manager_api.features.event.mappers;

import com.bitkap.event_manager_api.features.event.dtos.CommentRequestDto;
import com.bitkap.event_manager_api.features.event.dtos.CommentResponseDto;
import com.bitkap.event_manager_api.features.event.dtos.EventRequestDto;
import com.bitkap.event_manager_api.features.event.dtos.EventResponseDto;
import com.bitkap.event_manager_api.features.event.entities.Comment;
import com.bitkap.event_manager_api.features.event.entities.Event;
import com.bitkap.event_manager_api.features.user.mappers.UserMapper;

import java.util.stream.Collectors;

/**
 * @author SIPOUFO Yvan
 *
 * @implNote That's the comment mapper class, which maps comments objects.
 */
public class CommentMapper {
    // Map comment request object to comment entity object.
    public static Comment toEntity(CommentRequestDto commentRequestDto) {
        return Comment
                .builder()
                .value(commentRequestDto.getValue())
                .build();
    }

    // Map event entity object to event response object.
    public static CommentResponseDto toResponse(Comment comment) {
        return CommentResponseDto
                .builder()
                .id(comment.getId())
                .value(comment.getValue())
                .eventId(comment.getEvent().getId())
                .eventTitle(comment.getEvent().getTitle())
                .createByUserId(comment.getCreatedByUserId())
                .createByUserName(comment.getCreatedByUserName())
                .createAt(comment.getCreatedAt())
                .build();
    }
}
