package com.BI.service.Impl;

import com.BI.dto.ResponseDto.BalanceSheetDto;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

@Service
public class MetricsServiceImpl {

    public BalanceSheetDto balanceSheet(int id){

        double totalIncome = 0;
        double totalExpense =0;

        Faker faker = new Faker();

        totalIncome = faker.number().randomDouble(2,1000,5000);
        totalExpense = faker.number().randomDouble(2,500,4000);

        double balanceSheet = totalIncome - totalExpense;

        return new BalanceSheetDto(balanceSheet,totalExpense,totalIncome);
    }
}
