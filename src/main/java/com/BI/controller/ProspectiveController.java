package com.BI.controller;

import com.BI.dto.ResponseDto.ProspectiveResponseDto;
import com.BI.service.IProspectiveService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/finanzas")
public class ProspectiveController {

    private final IProspectiveService prospectiveService;

    @Autowired
    public  ProspectiveController(IProspectiveService prospectiveService) {
        this.prospectiveService = prospectiveService;
    }

    @Operation( summary = "Obtner prospectivas de usuarios",
            description = "Aqui podras obtener las prospectivas de un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "prospective successfully retrieved"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    }
    )
    @GetMapping("/prospective/{userId}/{month}")
    public ResponseEntity<ProspectiveResponseDto> getProspective(@PathVariable Integer userId, @PathVariable String month) {
        ProspectiveResponseDto prospectiveUser = this.prospectiveService.getProspective(userId,month);
        return ResponseEntity.status(HttpStatus.OK).body(prospectiveUser);

    }
}
