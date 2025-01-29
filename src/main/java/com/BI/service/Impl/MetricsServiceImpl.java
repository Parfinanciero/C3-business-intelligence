package com.BI.service.Impl;

import com.BI.Exceptions.Custom.InvalidRequestException;
import com.BI.Utils.FinancialStatus;
import com.BI.dto.ResponseDto.*;
import com.BI.service.IExpensesService;
import com.BI.service.IMetricService;
import com.BI.service.ITransactionService;
import com.BI.service.IncomeService;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class MetricsServiceImpl implements IMetricService {

    private final Faker faker = new Faker();
    private final IncomeService incomeService;
    private final IExpensesService expensesService;
    private final ITransactionService transactionService;
    private final Logger logger = LoggerFactory.getLogger(MetricsServiceImpl.class);

    @Autowired
    public  MetricsServiceImpl (IncomeService incomeService, IExpensesService expensesService, ITransactionService transactionService){
        this.incomeService = incomeService;
        this.expensesService = expensesService;
        this.transactionService = transactionService;
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

    @Override
    public List<CashByCategoryResponse> calculateExpensesByCategory(Integer id, String month) {
        logger.info("Iniciando cálculo de gastos por categoría - id: {}, mes: {}", id, month);

        if(id == null || month == null || month.isEmpty()){
            logger.error("ID o mes inválidos - id: {}, mes: {}", id, month);
            throw new InvalidRequestException("el id y mes no pueden estar vacíos");
        }

        List<Transactions> transactionsUser = this.transactionService.getTransactionByUserAndMonth(id, month);

        List<Transactions> expenses = transactionsUser.stream()
                .filter(transaction -> "expenses".equals(transaction.getType()))
                .toList();

        double totalExpenses = expenses.stream()
                .mapToDouble(Transactions::getAmount)
                .sum();

        if (totalExpenses == 0) {
            logger.info("No se encontraron gastos para el período");
            return Collections.emptyList();
        }

        return expenses.stream()
                .collect(Collectors.groupingBy(
                        Transactions::getCategory,
                        Collectors.summingDouble(Transactions::getAmount)
                )).entrySet().stream()
                .map(entry -> {
                    double percentage = (entry.getValue() / totalExpenses) * 100;
                    double roundedPercentage = Math.round(percentage * 100.0) / 100.0;
                    logger.debug("Categoría: {}, Monto: {}, Total: {}, Porcentaje: {}",
                            entry.getKey(),
                            entry.getValue(),
                            totalExpenses,
                            roundedPercentage);
                    return new CashByCategoryResponse(entry.getKey(), roundedPercentage);
                })
                .toList();
    }
}
