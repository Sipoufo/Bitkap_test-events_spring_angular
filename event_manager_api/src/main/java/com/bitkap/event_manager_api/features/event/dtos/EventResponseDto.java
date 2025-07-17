package com.bitkap.event_manager_api.features.event.dtos;

import com.bitkap.event_manager_api.features.user.dtos.UserSimpleResponseDto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

/**
 * @author SIPOUFO Yvan
 *
 * @implNote That is the dto use to render event information for different operations.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventResponseDto {
    private Long id;
    private String title;
    private String description;
    private Instant eventDate;
    private Long organizerId;
    private String organizerName;
    private int commentSize;
    private Long createByUserId;
    private String createByUserName;
    private Instant createAt;
    private Boolean enabled;
}
