package com.BI.service.Impl;

import com.BI.dto.ResponseDto.ProspectiveResponseDto;
import com.BI.dto.ResponseDto.Transactions;
import com.BI.service.IExpensesService;
import com.BI.service.IProspectiveService;
import com.BI.service.ITransactionService;
import com.BI.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class ProspectiveServiceImpl implements IProspectiveService {

    private final ITransactionService transactionService;


    @Autowired
    public ProspectiveServiceImpl(ITransactionService transactionService, IncomeService incomeService, IExpensesService expensesService) {
        this.transactionService = transactionService;

    }

    /**
     * Servicio para calcular la proyección financiera de un usuario en un mes específico.
     *
     * @param userId ID del usuario.
     * @param month Mes en formato "MM" para el cual se calcula la proyección.
     * @return Objeto ProspectiveResponseDto con detalles de ingresos, gastos y proyecciones.
     */
    @Override
    public ProspectiveResponseDto getProspective(Integer userId, String month) {
        List<Transactions> transactionsUser = this.transactionService.getTransactionByUserAndMonth(userId,month);

        double ingresos = calculateTotal(transactionsUser, "income");
        double gastos = calculateTotal(transactionsUser, "expenses");

        double ahorroProyectado = ingresos - gastos;
        double ratioIngresosGastos = ingresos / (gastos > 0 ? gastos : 1);

        double proyeccionIngresos = ingresos * 1.2;
        double proyeccionGastos = gastos * 1.15;

        return new ProspectiveResponseDto(month,ingresos,gastos,userId,proyeccionIngresos,proyeccionGastos,ahorroProyectado,ratioIngresosGastos,calculateComplianceGoal());
    }



    /**
     * Calcula el total de ingresos o gastos según el tipo de transacción.
     *
     * @param transactions Lista de transacciones del usuario.
     * @param type Tipo de transacción ("income" o "expenses").
     * @return Total acumulado del tipo de transacción con dos decimales.
     *
     */
    private double calculateTotal(List<Transactions> transactions, String type) {
        return BigDecimal.valueOf(transactions.stream()
                        .filter(t -> t.getType().equals(type))
                        .mapToDouble(Transactions::getAmount)
                        .sum())
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    private double calculateComplianceGoal() {
        return 90 + (Math.random() * 5);
    }


}
