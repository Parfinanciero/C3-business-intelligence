package com.BI.service;

import com.BI.dto.ResponseDto.Transactions;

import java.util.List;

public interface ITransactionService {

    List<Transactions> getTransactionByUserAndMonth(int userId, String month);
    List<Transactions> getTransactions(int userId);

}
