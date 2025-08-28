package com.product.payload;

import lombok.Getter;

import java.util.Date;
@Getter
public class ErrorDetails {
    private String message;
    private Date time;
    private String description;

    public ErrorDetails(String message, Date time, String description) {
        this.message = message;
        this.time = time;
        this.description = description;
    }
}
