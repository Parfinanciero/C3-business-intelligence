package com.BI.service.Impl;

import com.BI.Exceptions.InvalidRequestException;
import com.BI.dto.ResponseDto.CashResponseDto;
import com.BI.dto.ResponseDto.Transactions;
import com.BI.service.IExpensesService;
import com.BI.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl  implements IExpensesService {


    private final ITransactionService transactionService;

    @Autowired
    public ExpenseServiceImpl (ITransactionService transactionService){
        this.transactionService = transactionService;
    }

    //metodo para sumar los gastos totales
    //se crea una lista de la clase transacciones
    //se usa la clase filter para crea una lista con los que cumples con la conficion
    // que el tipo se igual a expenses
    // se convierte el la transaccion en una suma del atributo amount
    @Override
    public CashResponseDto calculateTotalExpenses(Integer id) {

        if(id == null){
            throw new InvalidRequestException("el id no puede estar vacio");
        }

        List<Transactions> transactionsUser = this.transactionService.getTransactions(id);
        Double totalExpenses = transactionsUser.stream()
                .filter(transaction -> "expenses".equals(transaction.getType()))
                .mapToDouble(Transactions::getAmount)
                .sum();

        return  new CashResponseDto(id,totalExpenses);
    }

}
