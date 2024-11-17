package com.jel.delivery.controller;

import com.jel.delivery.dto.DeliveryCostDto;
import com.jel.delivery.dto.ParcelRequestDto;
import com.jel.delivery.service.DeliveryCostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(("/delivery"))
public class DeliveryCostController {

    private final DeliveryCostService deliveryCostService;

    public DeliveryCostController(DeliveryCostService deliveryCostService) {
        this.deliveryCostService = deliveryCostService;
    }

    @PostMapping("/cost")
    public ResponseEntity<DeliveryCostDto> calculateDeliveryCost(@RequestBody ParcelRequestDto requestDto) {
        return ResponseEntity.of(Optional.ofNullable(this.deliveryCostService.calculateDeliveryCost(requestDto)));
    }


}
