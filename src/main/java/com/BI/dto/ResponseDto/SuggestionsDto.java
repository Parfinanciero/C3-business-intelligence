package com.BI.dto.ResponseDto;

public class SuggestionsDto {
    private String suggestion;

    public SuggestionsDto(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }



}
