package com.safaricom.tibcoLogger.dto;

import java.time.LocalDateTime;

public class PagedApiResponse<T> {

    private boolean success;
    private String message;
    private T data;
    private int page;
    private int limit;
    private long totalRecords;
    private int totalPages;
    private LocalDateTime timestamp;

    public PagedApiResponse() {
    }

    public PagedApiResponse(boolean success, String message, T data, int page, int limit,
                            long totalRecords, int totalPages, LocalDateTime timestamp) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.page = page;
        this.limit = limit;
        this.totalRecords = totalRecords;
        this.totalPages = totalPages;
        this.timestamp = timestamp;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}