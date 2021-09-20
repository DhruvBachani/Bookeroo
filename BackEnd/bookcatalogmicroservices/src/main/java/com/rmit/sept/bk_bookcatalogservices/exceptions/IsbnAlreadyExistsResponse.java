package com.rmit.sept.bk_bookcatalogservices.exceptions;

public class IsbnAlreadyExistsResponse {

    private String field;

    public IsbnAlreadyExistsResponse(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}