package com.BI.service.Impl;

import com.BI.Utils.Status;
import com.BI.dto.ResponseDto.GoalResponseDto;
import com.BI.dto.ResponseDto.SuggestionsDto;
import com.BI.service.IGoalsService;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GoalServiceImpl implements IGoalsService {

    private final Faker faker = new Faker();

    @Override
    public List<GoalResponseDto> getUserGoalsByUserId(String userId) {
        List<GoalResponseDto> goals = generateMockGoals(userId);
        goals.forEach(this::evaluateAndUpdateGoal);
        return goals;
    }

    @Override
    public GoalResponseDto evaluateAndUpdateGoal(GoalResponseDto goal) {
        if (Math.abs(goal.getCurrentAmount() - goal.getGoalAmount()) < 0.01) {
            goal.setStatus(Status.COMPLETED);
            goal.getSuggestionsUSer().add(new SuggestionsDto("Felicitaciones has alcanzado la meta"));
        } else {
            goal.setStatus(Status.IN_PROGRESS);
            generateSuggestions(goal);
        }
        return goal;
    }


        private List<GoalResponseDto> generateMockGoals(String userId) {
            List<GoalResponseDto> goals = new ArrayList<>();
            int numberOfGoals = faker.number().numberBetween(2, 5);

            for (int i = 0; i < numberOfGoals; i++) {
                goals.add(generateMockGoal(userId));
            }

            return goals;
        }

    private GoalResponseDto generateMockGoal(String userId) {
        String[] goalTypes = {
                "Ahorrar", "Invertir", "Reducir gastos en",
                "Aumentar ingresos por", "Pagar deuda de",
                "Fondo de emergencia para"
        };

        String[] categories = {
                "vivienda", "educación", "transporte",
                "salud", "entretenimiento", "alimentación",
                "viajes"
        };

        double targetValue = faker.number().randomDouble(2, 1000, 50000);
        double currentValue = faker.number().randomDouble(2, 0, (int) targetValue);

        GoalResponseDto goalResponseDto = new GoalResponseDto();
        goalResponseDto.setUserId(UUID.randomUUID().toString());
        goalResponseDto.setUserId(userId);
        goalResponseDto.setTitle(goalTypes[faker.random().nextInt(goalTypes.length)] + " " +
                categories[faker.random().nextInt(categories.length)]);
        goalResponseDto.setCurrentAmount(currentValue);
        goalResponseDto.setGoalAmount(targetValue);
        goalResponseDto.setStatus(Status.CREATED);
        goalResponseDto.setSuggestionsUSer(new ArrayList<>());

        return goalResponseDto;
    }

    private void generateSuggestions(GoalResponseDto goal) {
            double progressPercentage = (goal.getCurrentAmount() / goal.getGoalAmount()) * 100;
            double remaining = goal.getGoalAmount() - goal.getCurrentAmount();
            List<SuggestionsDto> suggestions = new ArrayList<>();

            // Sugerencias específicas según el tipo de meta
            if (goal.getTitle().startsWith("Ahorrar")) {
                suggestions.add(new SuggestionsDto(String.format(
                        "Establece un ahorro automático mensual de %.2f para alcanzar tu meta",
                        remaining / 12)));
                suggestions.add(new SuggestionsDto(
                        "Reduce gastos en servicios de streaming y subscripciones no esenciales"));
                suggestions.add(new SuggestionsDto(
                        "Considera usar una aplicación de seguimiento de gastos"));
            } else if (goal.getTitle().startsWith("Invertir")) {
                suggestions.add(new SuggestionsDto(
                        "Diversifica tu portafolio de inversiones"));
                suggestions.add(new SuggestionsDto(
                        "Consulta con un asesor financiero sobre estrategias de inversión"));
                suggestions.add(new SuggestionsDto(String.format(
                        "Necesitas invertir %.2f más para alcanzar tu objetivo",
                        remaining)));
            } else if (goal.getTitle().startsWith("Reducir gastos")) {
                suggestions.add(new SuggestionsDto(
                        "Crea un presupuesto mensual detallado"));
                suggestions.add(new SuggestionsDto(
                        "Identifica y elimina gastos innecesarios"));
                suggestions.add(new SuggestionsDto(String.format(
                        "Busca reducir %.2f en gastos mensuales",
                        remaining / 6)));
            }

            // Sugerencia basada en el progreso
            if (progressPercentage < 25) {
                suggestions.add(new SuggestionsDto(
                        "Estás en la fase inicial. ¡Mantén el impulso!"));
            } else if (progressPercentage < 50) {
                suggestions.add(new SuggestionsDto(
                        "¡Vas por buen camino! Has completado el primer cuarto de tu meta."));
            } else if (progressPercentage < 75) {
                suggestions.add(new SuggestionsDto(
                        "¡Excelente progreso! Ya has superado la mitad de tu objetivo."));
            } else {
                suggestions.add(new SuggestionsDto(
                        "¡Estás muy cerca! Solo te falta un pequeño esfuerzo final."));
            }

            goal.setSuggestionsUSer(suggestions);
        }
}
