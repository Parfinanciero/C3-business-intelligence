package com.BI.service.Impl;

import com.BI.Exceptions.InvalidRequestException;
import com.BI.dto.ResponseDto.BalanceSheetDto;
import com.BI.dto.ResponseDto.CashResponseDto;
import com.BI.dto.ResponseDto.MetricResponseDto;
import com.BI.service.IExpensesService;
import com.BI.service.IMetricService;
import com.BI.service.IncomeService;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

@Service
public class MetricsServiceImpl implements IMetricService {

    private final Faker faker = new Faker();
    private final IncomeService incomeService;
    private final IExpensesService expensesService;

    private final IMetricService metricService;

    public  MetricsServiceImpl (IncomeService incomeService, IExpensesService expensesService, IMetricService metricService){
        this.incomeService = incomeService;
        this.expensesService = expensesService;
        this.metricService = metricService;
    }

    @Override
    public MetricResponseDto calculateIncomeAndExpenseRatio(Integer idUser, String month) {
        if(idUser== null|| month==null || month.isEmpty()){
            throw new InvalidRequestException("el id y mes no pueden estar vacios");
        }
        //obtenemos ingresos  y gastos totales usando los metodos
        CashResponseDto totalIncome = this.incomeService.calculateTotalIncome(idUser,month);
        CashResponseDto totalExpense = this.expensesService.calculateTotalExpenses(idUser,month);
        double  incomeTotal = totalIncome.getTotalExpenses();
        double expenseTotal = totalExpense.getTotalExpenses();

        // esta validacion se hace para tener en cuenta que el ingreso puede ser cero
        // y en matematicas no se puede dividir por cero
        // para evitar ese error validamos para no dividir por cero
        if(incomeTotal ==0 ){
            throw new ArithmeticException(" No tiene ingresos no se puede caluclar metrica ");
        }
        return null;
    }

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
