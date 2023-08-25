package com.fcmb.interview.testredo.service;

import com.fcmb.interview.testredo.model.domain.TransactionHistory;
import com.fcmb.interview.testredo.model.dto.AirtimePurchaseDTO;
import com.fcmb.interview.testredo.model.dto.TransferDTO;

public interface TransferService {
    TransactionHistory doTransfer(TransferDTO transferDTO);
    TransactionHistory buyAirtime(AirtimePurchaseDTO airtimePurchaseDTO);
}
