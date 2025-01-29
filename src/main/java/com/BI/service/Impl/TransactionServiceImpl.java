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

    private Transactions createTransaction(int userId, String forcedType, String forcedMonth) {

        List<String> expensesCategory = Arrays.asList("Transporte", "Salud",
                "Vivienda", "Viaje", "Entretenimiento", "Educación");

        List<String> incomeCategory = Arrays.asList("Salario", "Freelance", "Inversión", "Venta de casa", "Regalo", "Otros");

        if(!userExist(userId)) {
            throw new UserNotFoundException("Usuario no encontrado");
        }

        String type = forcedType != null ? forcedType : faker.options().option("income", "expenses");

        // Aseguramos que el monto sea mayor que cero
        double amount = faker.number().randomDouble(2, 1, 5000);

        String title = type.equals("income") ? faker.company().profession() : faker.commerce().productName();
        String category = type.equals("income") ? faker.options().option(incomeCategory.toArray(new String[0]))
                : faker.options().option(expensesCategory.toArray(new String[0]));

        // Generar fecha para el mes especificado o aleatoria entre enero y febrero
        LocalDate date;
        if (forcedMonth != null) {
            int year = 2025;
            int month = Integer.parseInt(forcedMonth);
            int day = faker.number().numberBetween(1, month == 2 ? 29 : 31);
            date = LocalDate.of(year, month, day);
        } else {
            LocalDate startDate = LocalDate.of(2025, 1, 1);
            LocalDate endDate = LocalDate.of(2025, 12, 30);
            date = faker.date().between(
                    Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
            ).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }

        String formattedDate = date.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        return new Transactions(userId, faker.number().numberBetween(1, 3), title, category, formattedDate, type, amount);
    }

    public List<Transactions> getTransactions(int userId) {
        List<Transactions> transactionsUser = new ArrayList<>();


        transactionsUser.add(createTransaction(userId, "income", "01"));
        transactionsUser.add(createTransaction(userId, "expenses", "01"));



        // Generar transacciones adicionales aleatorias, asegurando balance entre ingresos y gastos
        int remainingTransactions = 16;
        int incomeCount = 2;
        int expensesCount = 2;

        while (remainingTransactions > 0) {
            String type;
            if (incomeCount < 8) {
                type = "income";
                incomeCount++;
            } else if (expensesCount < 8) {
                type = "expenses";
                expensesCount++;
            } else {
                type = faker.options().option("income", "expenses");
                if (type.equals("income")) incomeCount++;
                else expensesCount++;
            }

            transactionsUser.add(createTransaction(userId, type, null));
            remainingTransactions--;
        }

        // Mezclar las transacciones para que no estén en un orden predecible
        Collections.shuffle(transactionsUser);

        return transactionsUser;
    }

    public List<Transactions> getTransactionByUserAndMonth(int userId, String month) {

        List<Transactions> transactionsUser = getTransactions(userId);

        return transactionsUser.stream()
                .filter(transaction -> {
                    String transactionMonth = transaction.getDate().substring(5, 7);
                    return transactionMonth.equals(month);
                })
                .collect(Collectors.toList());
    }


}
