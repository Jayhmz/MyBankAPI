package com.fcmb.interview.testredo.controller;

import com.fcmb.interview.testredo.model.domain.TransactionHistory;
import com.fcmb.interview.testredo.model.dto.AirtimePurchaseDTO;
import com.fcmb.interview.testredo.model.dto.TransferDTO;
import com.fcmb.interview.testredo.model.response.ResponseDTO;
import com.fcmb.interview.testredo.service.TransferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TransferController {
    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<ResponseDTO<TransactionHistory>> doTransfer(@RequestBody TransferDTO transferDTO){
        ResponseDTO<TransactionHistory> response = new ResponseDTO<>();
        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("Transfer done successfully");
        response.setData(transferService.doTransfer(transferDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("/airtime")
    public ResponseEntity<ResponseDTO<TransactionHistory>> buyAirtime(@RequestBody AirtimePurchaseDTO airtimePurchaseDTO){
        ResponseDTO<TransactionHistory> response = new ResponseDTO<>();
        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("Airtime purchase done successfully");
        response.setData(transferService.buyAirtime(airtimePurchaseDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
