package com.crewmeister.cmcodingchallenge.currency.exception;

import java.util.Date;

public class ExceptionResponse {

    private Date timestamp;
    private String message;


    public ExceptionResponse(final Date timestamp, final String message) {
        super();
        this.timestamp = timestamp;
        this.message = message;

    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

}

