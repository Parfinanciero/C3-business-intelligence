package com.BI.dto.ResponseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserExpensesResponse {
    private String name;
    private Long userId;
    private double totalExpenses;
}
