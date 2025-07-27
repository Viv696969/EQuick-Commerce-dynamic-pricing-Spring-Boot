package com.vivekemipre.dynamicpricing.dto;

import lombok.AllArgsConstructor;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
public class PagedResponse<T> {
    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean hasNext;
    private boolean hasPrevious;

    public PagedResponse(List<T> content, int pageNumber, int pageSize, long totalElements,
                         int totalPages, boolean hasNext, boolean hasPrevious) {
        this.content = content;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.hasNext = hasNext;
        this.hasPrevious = hasPrevious;
    }



}