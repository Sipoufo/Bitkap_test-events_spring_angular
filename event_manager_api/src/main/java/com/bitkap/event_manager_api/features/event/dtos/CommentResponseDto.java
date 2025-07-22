package com.bitkap.event_manager_api.features.event.dtos;

import com.bitkap.event_manager_api.features.user.dtos.UserSimpleResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

/**
 * @author SIPOUFO Yvan
 *
 * @implNote That is the dto use to render comment information for different operations.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {
    private Long id;
    private String value;
    private Long eventId;
    private String eventTitle;
    private String createByUserId;
    private String createByUserName;
    private Instant createAt;
}
