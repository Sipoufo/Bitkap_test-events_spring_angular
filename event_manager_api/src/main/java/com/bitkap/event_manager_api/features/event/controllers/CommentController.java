package com.bitkap.event_manager_api.features.event.controllers;

import com.bitkap.event_manager_api.features.event.dtos.CommentRequestDto;
import com.bitkap.event_manager_api.features.event.dtos.CommentResponseDto;
import com.bitkap.event_manager_api.features.event.entities.Comment;
import com.bitkap.event_manager_api.features.event.mappers.CommentMapper;
import com.bitkap.event_manager_api.features.event.services.CommentService;
import com.bitkap.event_manager_api.utils.GlobalParams;
import com.bitkap.event_manager_api.utils.dtos.ListObjectResponseDto;
import com.bitkap.event_manager_api.utils.dtos.PageObjectResponseDto;
import com.bitkap.event_manager_api.utils.dtos.SimpleObjectResponseDto;
import io.swagger.v3.oas.annotations.Operation;
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
 * @implNote That is the controller that mange all comments in the application.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(GlobalParams.API_PREFIX + "/comment")
@Tag(name = "Comment", description = "The comment API part.")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{id}")
    @Operation(summary = "Find one comment.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment successful returned !"),
            @ApiResponse(responseCode = "404", description = "Comment not found !")
    })
    @ResponseStatus(HttpStatus.OK)
    public SimpleObjectResponseDto<CommentResponseDto> findOneById(@PathVariable("id") Long id) {
        return new SimpleObjectResponseDto<>(GlobalParams.ResponseStatusEnum.SUCCESS.name(), CommentMapper.toResponse(commentService.findOneById(id)));
    }

    @GetMapping("")
    @Operation(summary = "Find all comments.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comments successful returned !"),
    })
    @ResponseStatus(HttpStatus.OK)
    public PageObjectResponseDto<CommentResponseDto> findAll(
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "10", required = false) Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Comment> comments = commentService.findAll(page, size);
        Page<CommentResponseDto> commentResult = new PageImpl<>(
                comments.getContent().stream()
                    .map(CommentMapper::toResponse)
                    .collect(Collectors.toList()),
                pageable,
                comments.getSize()
        );

        return new PageObjectResponseDto<>(
                GlobalParams.ResponseStatusEnum.SUCCESS.name(),
                commentResult
        );
    }

    @GetMapping("/event/{eventId}")
    @Operation(summary = "Find all comments depend on event id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comments successful returned !"),
    })
    @ResponseStatus(HttpStatus.OK)
    public ListObjectResponseDto<CommentResponseDto> findAllByEvent(@PathVariable("eventId") Long eventId) {
        return new ListObjectResponseDto<>(
                GlobalParams.ResponseStatusEnum.SUCCESS.name(),
                commentService.findAllByEvent(eventId).stream()
                        .map(CommentMapper::toResponse)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("")
    @Operation(summary = "Save one comment.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comment successful saved !"),
            @ApiResponse(responseCode = "403", description = "Comment with same title exit !")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public SimpleObjectResponseDto<CommentResponseDto> save(@Valid @RequestBody CommentRequestDto commentRequestDto) {
        return new SimpleObjectResponseDto<>(GlobalParams.ResponseStatusEnum.SUCCESS.name(), CommentMapper.toResponse(commentService.save(commentRequestDto)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete one comment.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment successful deleted !"),
            @ApiResponse(responseCode = "404", description = "Comment not found !")
    })
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        commentService.delete(id);
    }
}
