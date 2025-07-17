package com.bitkap.event_manager_api.features.user.dtos;

import com.bitkap.event_manager_api.features.event.dtos.EventResponseDto;
import com.bitkap.event_manager_api.features.event.entities.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author SIPOUFO Yvan
 *
 * @implNote That is the dto use to render simple version of user information.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSimpleResponseDto {
    private Long id;
    private String displayName;
    private String email;
    private List<EventResponseDto> events;
}
