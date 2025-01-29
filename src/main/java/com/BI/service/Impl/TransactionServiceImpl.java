package com.BI.service.Impl;


import com.BI.Exceptions.Custom.UserNotFoundException;
import com.BI.dto.ResponseDto.Transactions;
import com.BI.service.ITransactionService;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements ITransactionService {

    //simular usuarios

    List<Integer> validUser = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

    private boolean userExist(int userId){
        return validUser.contains(userId);
    }

    private final Faker faker = new Faker(new Locale("es"));


    //metodo para crear datos de ingresos y gastos de un usuario
    private Transactions createTransaction(int userId, String forcedType) {
        List<String> expensesCategory = Arrays.asList("Transporte", "Salud",
                "Vivienda", "Viaje", "Entretenimiento",
                "Educación");

        List<String> incomeCategory = Arrays.asList("Salario", "Freelance", "Inversión", "Venta de casa", "Regalo", "Otros");

        if(!userExist(userId)) {
            throw new UserNotFoundException("Usuario no encontrado");
        }

        String type = forcedType != null ? forcedType : faker.options().option("income", "expenses");
        double amount = faker.number().randomDouble(2, 50, 5000);

        String title = type.equals("income") ? faker.company().profession() : faker.commerce().productName();
        String category = type.equals("income") ? faker.options().option(incomeCategory.toArray(new String[0]))
                : faker.options().option(expensesCategory.toArray(new String[0]));

        // Generar fecha del año actual
        LocalDate startDate = LocalDate.of(2024, 12, 1);
        LocalDate endDate = LocalDate.now();
        Date dateInRange = faker.date().between(
                Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
        );
        String date = new SimpleDateFormat("yyyy-MM-dd").format(dateInRange);

        return new Transactions(userId, faker.number().numberBetween(1, 3), title, category, date, type, amount);
    }

    public List<Transactions> getTransactions(int userId) {
        List<Transactions> transactionsUser = new ArrayList<>();

        transactionsUser.add(createTransaction(userId, "income"));
        transactionsUser.add(createTransaction(userId, "expenses"));

        for (int i = 0; i < 18; i++) {
            transactionsUser.add(createTransaction(userId, null));
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
