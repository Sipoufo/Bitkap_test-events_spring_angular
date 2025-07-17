package com.bitkap.event_manager_api.utils.dtos;

import com.bitkap.event_manager_api.utils.GlobalParams;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PageObjectResponseDto<ResponseObject> extends OperationResponseDto {
    private boolean isFirst;
    private boolean isLast;
    private int currentPageNumber;
    private int pageSize;
    private int numberOfElements;
    private int totalPages;
    private long totalElements;
    private Sort sort;
    private List<ResponseObject> items;

    public PageObjectResponseDto(GlobalParams.ResponseStatusEnum operationStatus, String message) {
        super(operationStatus, message);
    }

    public PageObjectResponseDto(String operationMessage, Page<ResponseObject> pg) {
        super(GlobalParams.ResponseStatusEnum.SUCCESS, operationMessage);
        this.setPageStats(pg);
    }

    private void setPageStats(Page<ResponseObject> pg) {
        this.isFirst = pg.isFirst();
        this.isLast = pg.isLast();
        this.currentPageNumber = pg.getNumber();
        this.pageSize = pg.getSize();
        this.numberOfElements = pg.getNumberOfElements();
        this.totalPages = pg.getTotalPages();
        this.totalElements = pg.getTotalElements();
        this.sort = pg.getSort();
        this.items = pg.getContent();
    }
}