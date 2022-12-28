package com.example.xeTraining.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

//import java.time.String;
import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {
    private HttpStatus status;
    private String timeStamp;
    private String type;
    private int code;
    private String traceID;
    private List<String> errors;

    public ErrorResponse()
    {
        LocalDateTime date = LocalDateTime.now();
        timeStamp = String.valueOf(date);
    }

    public ErrorResponse(HttpStatus status) {
        this();
        this.status = status;
    }

    public ErrorResponse(HttpStatus status, String type, int code, String traceID) {
        this();
        this.type = type;
        this.status = status;
        this.code = code;
        this.traceID = traceID;
    }

    public ErrorResponse(HttpStatus status, String timeStamp, String type, int code, String traceID) {
        this.status = status;
        this.timeStamp = timeStamp;
        this.type = type;
        this.code = code;
        this.traceID = traceID;
    }


    public ErrorResponse(HttpStatus status, String timeStamp, String type, int code, String traceID, List<String> errors) {
        this.status = status;
        this.timeStamp = timeStamp;
        this.type = type;
        this.code = code;
        this.traceID = traceID;
        this.errors = errors;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTraceID() {
        return traceID;
    }

    public void setTraceID(String traceID) {
        this.traceID = traceID;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
