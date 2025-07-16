package com.bitkap.event_manager_api.utils.dtos;

import com.bitkap.event_manager_api.utils.GlobalParams.ResponseStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OperationResponseDto {
    private ResponseStatusEnum operationStatus;
    private String operationMessage;
}
