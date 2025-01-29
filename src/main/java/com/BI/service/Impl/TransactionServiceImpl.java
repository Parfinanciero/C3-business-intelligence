package com.BI.service.Impl;


import com.BI.Exceptions.Custom.UserNotFoundException;
import com.BI.dto.ResponseDto.Transactions;
import com.BI.service.ITransactionService;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements ITransactionService {

    //simular usuarios

    List<Integer> validUser = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

    private boolean userExist(int userId){
        return validUser.contains(userId);
    }

    private final Faker faker = new Faker();


    //metodo para crear datos de ingresos y gastos de un usuario
    private Transactions createTransaction(int userId) {

        //validar que el usuario exista

        if(!userExist(userId)){
            throw  new UserNotFoundException("Usuario no encontrado");
        }
        String type = faker.options().option("income", "expenses");
        double amount = faker.number().randomDouble(2, 50, 5000);
        String title = type.equals("income") ? faker.company().profession() : faker.commerce().productName();
        String category = type.equals("income") ? "Work" : faker.commerce().department();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(faker.date().past(365, java.util.concurrent.TimeUnit.DAYS));

        return new Transactions(userId, faker.number().numberBetween(1, 3), title, category, date, type, amount);
    }

    public List<Transactions> getTransactions(int userId) {
        List<Transactions> transactionsUser = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            transactionsUser.add(createTransaction(userId));
        }

        return transactionsUser;
    }

    public List<Transactions> getTransactionByUserAndMonth(int userId, String month) {
        // Primero genera las transacciones para el usuario
        List<Transactions> transactionsUser = getTransactions(userId);

        // Luego filtra por mes
        return transactionsUser.stream()
                .filter(transaction -> {
                    String transactionMonth = transaction.getDate().substring(5, 7);
                    return transactionMonth.equals(month);
                })
                .collect(Collectors.toList());
    }


}
