package com.bitkap.event_manager_api.features.event.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * @author SIPOUFO Yvan
 *
 * @implNote That is the dto use to retrieve comment information for different operations.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {
    @NotBlank(message = "Value is required !")
    private String value;

    @NotNull(message = "Id of event is required !")
    private Long eventId;
}
