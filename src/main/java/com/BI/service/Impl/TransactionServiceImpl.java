package com.BI.service.Impl;


import com.BI.Exceptions.InvalidRequestException;
import com.BI.Exceptions.UserNotFoundException;
import com.BI.dto.ResponseDto.CalculateExpensesResponse;
import com.BI.dto.ResponseDto.Transactions;
import com.BI.service.TransactionService;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

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
            throw  new UserNotFoundException("Usuario no encontrado" + userId + "no existe");
        }
        String type = faker.options().option("income", "expenses");
        double amount = faker.number().randomDouble(2, 50, 5000);
        String title = type.equals("income") ? faker.company().profession() : faker.commerce().productName();
        String category = type.equals("income") ? "Work" : faker.commerce().department();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(faker.date().past(365, java.util.concurrent.TimeUnit.DAYS));

        return new Transactions(userId, faker.number().numberBetween(1, 3), title, category, date, type, amount);
    }

    public List<Transactions> getExpenses(int userId) {
        List<Transactions> transactionsUser = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            transactionsUser.add(createTransaction(userId));
        }

        return transactionsUser;
    }



    //metodo para sumar los gastos totales
    //se crea una lista de la clase transacciones
    //se usa la clase filter para crea una lista con los que cumples con la conficion
    // que el tipo se igual a expenses
    // se convierte el la transaccion en una suma del atributo amount
    @Override
    public CalculateExpensesResponse calculateTotalExpenses(Integer id) {

        if(id == null){
            throw new InvalidRequestException("el id no puede estar vacio");
        }

        List<Transactions> transactionsUser = getExpenses(id);
       Double totalExpenses = transactionsUser.stream()
               .filter(transaction -> "expenses".equals(transaction.getType()))
               .mapToDouble(Transactions::getAmount)
               .sum();

        return  new CalculateExpensesResponse(id,totalExpenses);
    }

    @Override
    public CalculateExpensesResponse calculateTotalIncome(Integer id) {

        if(id == null){
            throw new InvalidRequestException("el id no puede estar vacio");
        }
        List<Transactions> transactionsUser = getExpenses(id);
        Double totalIncome = transactionsUser.stream()
                .filter(transactions -> "income".equals(transactions.getType()))
                .mapToDouble(Transactions::getAmount)
                .sum();

        return new CalculateExpensesResponse(id,totalIncome);
    }


}
