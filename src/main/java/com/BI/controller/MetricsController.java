package com.BI.controller;

import com.BI.service.Impl.MetricsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/finanzas/metricas")
public class MetricsController {

    @Autowired
    private MetricsServiceImpl metricsService;

    @GetMapping("balance-general/{id}")
    public ResponseEntity<?> balanceSheet(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(metricsService.balanceSheet(id));
    }
}
