package com.bitkap.event_manager_api.features.event.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * @author SIPOUFO Yvan
 *
 * @implNote That is the dto use to retrieve event information for different operations.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventRequestDto {
    @NotBlank(message = "Title is required !")
    @Size(max = 100, message = "Title is too high !")
    private String title;

    @NotNull(message = "Id of user is required !")
    private Long userId;

    private String description;
    private Instant eventDate;
}
