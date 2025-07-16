package com.bitkap.event_manager_api.utils.dtos;

import com.bitkap.event_manager_api.utils.GlobalParams.ResponseStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author SIPOUFO Yvan
 *
 * @implNote Class use for model of response with more items.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ListObjectResponseDto<ResponseObject> extends OperationResponseDto {
    private List<ResponseObject> items;

    public ListObjectResponseDto(ResponseStatusEnum operationStatus, String message) {
        super(operationStatus, message);
    }

    public ListObjectResponseDto(String operationMessage, List<ResponseObject> items) {
        super(ResponseStatusEnum.SUCCESS, operationMessage);
        this.items = items;
    }

    public ListObjectResponseDto(List<ResponseObject> items) {
        super(ResponseStatusEnum.SUCCESS, "");
        this.items = items;
    }
}
