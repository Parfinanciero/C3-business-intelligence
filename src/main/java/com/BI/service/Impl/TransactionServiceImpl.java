package com.BI.service.Impl;


import com.BI.Exceptions.Custom.UserNotFoundException;
import com.BI.dto.ResponseDto.Transactions;
import com.BI.service.ITransactionService;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements ITransactionService {

    //simular usuarios
    private final Faker faker = new Faker(new Locale("es"));
    private final List<Integer> validUser = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    private boolean userExist(int userId) {
        return validUser.contains(userId);
    }
    /**
     * Genera una transacción aleatoria para un usuario en un mes específico.
     * Se asegura de que la transacción tenga una categoría, fecha y monto válido.
     *
     * @param userId ID del usuario al que pertenece la transacción.
     * @param type Tipo de transacción ("income" o "expenses").
     * @param month Mes de la transacción en formato "MM".
     * @return Objeto Transactions con datos generados aleatoriamente.
     * @throws UserNotFoundException si el usuario no existe.
     */

    private Transactions createTransaction(int userId, String type, String month) {
        List<String> expensesCategory = Arrays.asList("Transporte", "Salud", "Vivienda", "Viaje", "Entretenimiento", "Educación");
        List<String> incomeCategory = Arrays.asList("Salario", "Freelance", "Inversión", "Venta de casa", "Regalo", "Otros");

        if (!validUser.contains(userId)) {
            throw new UserNotFoundException("Usuario no encontrado");
        }

        double amount = type.equals("income")
                ? BigDecimal.valueOf(faker.number().numberBetween(800, 5000)).setScale(2, RoundingMode.HALF_UP).doubleValue()
                : BigDecimal.valueOf(faker.number().numberBetween(50, 2000)).setScale(2, RoundingMode.HALF_UP).doubleValue();

        String category = type.equals("income")
                ? faker.options().option(incomeCategory.toArray(new String[0]))
                : faker.options().option(expensesCategory.toArray(new String[0]));

        String date = String.format("2025-%s-%02d", month, faker.number().numberBetween(1, 28));

        return new Transactions(
                userId,
                faker.number().numberBetween(1, 3),
                type.equals("income") ? faker.company().profession() : faker.commerce().productName(),
                category,
                date,
                type,
                amount
        );
    }

    /**
     * Genera una lista de transacciones de prueba para un usuario.
     * Incluye al menos un ingreso y un gasto por mes, además de transacciones adicionales.
     *
     * @param userId ID del usuario para el cual se generan las transacciones.
     * @return Lista de transacciones generadas aleatoriamente para 12 meses.
     */

    public List<Transactions> getTransactions(int userId) {
        List<Transactions> transactions = new ArrayList<>();
        for (int month = 1; month <= 12; month++) {
            String formattedMonth = String.format("%02d", month);

            transactions.add(createTransaction(userId, "income", formattedMonth));

            transactions.add(createTransaction(userId, "expenses", formattedMonth));

            for (int i = 0; i < 5; i++) {
                String type = (i % 2 == 0) ? "income" : "expenses";
                transactions.add(createTransaction(userId, type, formattedMonth));
            }
        }

        return transactions;
    }

    /**
     * Filtra y devuelve las transacciones de un usuario para un mes específico.
     *
     * @param userId ID del usuario cuyas transacciones se van a obtener.
     * @param month Mes en formato "MM" para filtrar las transacciones.
     * @return Lista de transacciones del usuario correspondientes al mes indicado.
     */

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
