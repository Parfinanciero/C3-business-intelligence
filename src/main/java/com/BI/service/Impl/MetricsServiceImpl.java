package com.BI.service.Impl;

import com.BI.Exceptions.Custom.CustomArithmeticExceptions;
import com.BI.Exceptions.Custom.InvalidRequestException;
import com.BI.Exceptions.Custom.NoIncomeException;
import com.BI.Utils.FinancialStatus;
import com.BI.dto.ResponseDto.BalanceSheetDto;
import com.BI.dto.ResponseDto.CashResponseDto;
import com.BI.dto.ResponseDto.MetricResponseDto;
import com.BI.service.IExpensesService;
import com.BI.service.IMetricService;
import com.BI.service.IncomeService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

@Service
public class MetricsServiceImpl implements IMetricService {

    private final Faker faker = new Faker();
    private final IncomeService incomeService;
    private final IExpensesService expensesService;

    @Autowired
    public  MetricsServiceImpl (IncomeService incomeService, IExpensesService expensesService){
        this.incomeService = incomeService;
        this.expensesService = expensesService;
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

        BigDecimal income = new BigDecimal(incomeTotal);
        BigDecimal expense = new BigDecimal(expenseTotal);



        // esta validacion se hace para tener en cuenta que el ingreso puede ser cero
        // y en matematicas no se puede dividir por cero
        // para evitar ese error validamos para no dividir por cero
       if(income.compareTo(BigDecimal.ZERO) == 0){
       throw  new NoIncomeException("No se puede realizar el calculo porque no tiene ingresos.");
       }

       if(expense.compareTo(BigDecimal.ZERO) == 0){
           throw  new CustomArithmeticExceptions("No se puede realizar el cálculo debido a la división por cero en los gastos.");

       }

        BigDecimal proportion = income.divide(expense, 2, RoundingMode.HALF_UP);


        // ahora vamos a determinar el estado
        FinancialStatus status;
        if(proportion.compareTo(BigDecimal.ONE) > 0 ){
           status =   FinancialStatus.OVERSPENDING;
        } else if (proportion.compareTo(BigDecimal.ONE) < 0){
            status = FinancialStatus.SAVINGS_POSSIBLE;
        } else {
            status = FinancialStatus.BALANCED;
        }
        //finalmente vamos a a devolver el Dto
        return  new MetricResponseDto(idUser,month,incomeTotal,expenseTotal,proportion, status);
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
