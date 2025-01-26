package com.BI.service.Impl;

import com.BI.dto.ResponseDto.BalanceSheetDto;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

@Service
public class MetricsServiceImpl {

    private final Faker faker = new Faker();

    public BalanceSheetDto balanceSheet(long id){

        double totalIncome =Math.abs(faker.number().randomDouble(0, 1000, 10000));
        double totalExpense = faker.number().randomDouble(0, 1000, 10000);

        double balanceSheet =  totalIncome - totalExpense;

        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
        formatter.setMaximumFractionDigits(0);

        return new BalanceSheetDto(
                formatter.format(totalIncome),
                formatter.format(totalExpense),
                formatter.format(balanceSheet)
        );
    }
}
