package com.BI.service.Impl;

import com.BI.Exceptions.InvalidRequestException;
import com.BI.dto.ResponseDto.CashResponseDto;
import com.BI.dto.ResponseDto.Transactions;
import com.BI.service.IncomeService;
import com.BI.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeServiceImpl implements IncomeService {

    private final ITransactionService transactionService;

    @Autowired
    public IncomeServiceImpl(ITransactionService transactionService){
        this.transactionService = transactionService;
    }

    @Override
    public CashResponseDto calculateTotalIncome(Integer id) {

        if(id == null){
            throw new InvalidRequestException("el id no puede estar vacio");
        }
        List<Transactions> transactionsUser = transactionService.getTransactions(id);
        Double totalIncome = transactionsUser.stream()
                .filter(transactions -> "income".equals(transactions.getType()))
                .mapToDouble(Transactions::getAmount)
                .sum();

        return new CashResponseDto(id,totalIncome);
    }

}
