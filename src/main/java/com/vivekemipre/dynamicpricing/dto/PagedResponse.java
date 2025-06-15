package com.vivekemipre.dynamicpricing.dto;

import java.util.List;

public class PagedResponse<T> {
    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean hasNext;
    private boolean hasPrevious;

    // constructor, getters, setters
}