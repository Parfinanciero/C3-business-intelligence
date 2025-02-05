package com.BI;

import com.BI.Utils.Status;
import com.BI.controller.GoalsController;
import com.BI.dto.ResponseDto.GoalResponseDto;
import com.BI.dto.ResponseDto.SuggestionsDto;
import com.BI.service.IGoalsService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = GoalsController.class)
public class GoalsTestController {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private IGoalsService goalsService;

    @Test
    public void testGetGoals() throws Exception {
        //construir
        String userId = "1";
        String title = "ahorro";
        Double currentAmount = 200.0000;
        Double goalAmount = 300.000;

        SuggestionsDto suggestionList = new SuggestionsDto();
        suggestionList.setSuggestion("sigues asi");
        suggestionList.setSuggestion("puedes lograrlo");

        List<SuggestionsDto> suggestions = new ArrayList<>();
        suggestions.add(suggestionList);


        GoalResponseDto goalResponse = new GoalResponseDto();
        goalResponse.setUserId(userId);
        goalResponse.setGoalAmount(goalAmount);
        goalResponse.setCurrentAmount(currentAmount);
        goalResponse.setTitle(title);
        goalResponse.setStatus(Status.IN_PROGRESS);
        goalResponse.setSuggestionsUSer(suggestions);

        mockMvc.perform(get("/metas/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(userId))
                .andExpect(jsonPath("$.goalAmount").value(goalAmount))
                .andExpect(jsonPath("$.currentAmount").value(currentAmount))
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.status").value("IN_PROGRESS"))
                .andExpect(jsonPath("$.suggestionsUSer[0].suggestion").value("sigues asi"));
    }


    }


