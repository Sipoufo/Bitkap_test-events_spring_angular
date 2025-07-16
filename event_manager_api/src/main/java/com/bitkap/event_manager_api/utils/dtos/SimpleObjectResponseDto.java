package com.bitkap.event_manager_api.utils.dtos;

import com.bitkap.event_manager_api.utils.GlobalParams.ResponseStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author SIPOUFO Yvan
 *
 * @implNote Class use for model of simple response.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SimpleObjectResponseDto<ResponseObject> extends OperationResponseDto {
    private ResponseObject items;

    public SimpleObjectResponseDto(ResponseObject items) {
        super(ResponseStatusEnum.SUCCESS, "");
        this.items = items;
    }

    public SimpleObjectResponseDto(String message) {
        super(ResponseStatusEnum.SUCCESS, message);
    }

    public SimpleObjectResponseDto(String operationMessage, ResponseObject items) {
        super(ResponseStatusEnum.SUCCESS, operationMessage);
        this.items = items;
    }
}
