package com.bitkap.event_manager_api.features.user.mappers;

import com.bitkap.event_manager_api.features.user.dtos.UserSimpleResponseDto;
import com.bitkap.event_manager_api.features.user.entities.User;

import java.util.stream.Collectors;

/**
 * @author SIPOUFO Yvan
 *
 * @implNote That's the user mapper class, which maps users objects.
 */
public class UserMapper {
    // Map user entity object to user simple response object.
    public static UserSimpleResponseDto toResponse(User user) {
        return UserSimpleResponseDto
                .builder()
                .id(user.getId())
                .displayName(user.getDisplayName())
                .email(user.getEmail())
                .build();
    }
}
