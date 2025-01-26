package com.BI.service.Impl;

import com.BI.dto.ResponseDto.ProspectiveResponseDto;
import com.BI.dto.ResponseDto.Transactions;
import com.BI.service.IExpensesService;
import com.BI.service.IProspectiveService;
import com.BI.service.ITransactionService;
import com.BI.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProspectiveServiceImpl implements IProspectiveService {

    private final ITransactionService transactionService;
    private final IncomeService incomeService;
    private  final IExpensesService expensesService;

    @Autowired
    public ProspectiveServiceImpl(ITransactionService transactionService, IncomeService incomeService, IExpensesService expensesService) {
        this.transactionService = transactionService;
        this.expensesService = expensesService;
        this.incomeService = incomeService;
    }
    @Override
    public ProspectiveResponseDto getProspective(Integer userId, String month) {
        //obtener gastos e ingresos de un usuario
        List<Transactions> transactionsUser = this.transactionService.getTransactionByUserAndMonth(userId,month);

        //filtrar ingresos y gastos
        double ingresos = calculateTotal(transactionsUser, "income");
        double gastos = calculateTotal(transactionsUser, "expenses");

        double ahorroProyectado = ingresos - gastos;
        double ratioIngresosGastos = ingresos / (gastos > 0 ? gastos : 1);

        // Proyección de ingresos y gastos
        double proyeccionIngresos = ingresos * 1.2;
        double proyeccionGastos = gastos * 1.15;

        return new ProspectiveResponseDto(month,ingresos,gastos,userId,proyeccionIngresos,proyeccionGastos,ahorroProyectado,ratioIngresosGastos,calculateComplianceGoal());
    }

// metodo para validar id del usuario
    private void validateUser(Integer userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("ID de usuario inválido");
        }
        // Posible verificación adicional de existencia de usuario
    }

    // Método para calcular total por tipo
    private double calculateTotal(List<Transactions> transactions, String type) {
        return transactions.stream()
                .filter(t -> t.getType().equals(type))
                .mapToDouble(Transactions::getAmount)
                .sum();
    }
//meta de cumplimiento
    private double calculateComplianceGoal() {
        return 90 + (Math.random() * 5);
    }


}
