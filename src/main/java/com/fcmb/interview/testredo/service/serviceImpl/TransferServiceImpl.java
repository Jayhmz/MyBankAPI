package com.fcmb.interview.testredo.service.serviceImpl;

import com.fcmb.interview.testredo.exception.AccountNotFoundException;
import com.fcmb.interview.testredo.exception.FailureToMakeTransactionException;
import com.fcmb.interview.testredo.exception.InsufficientBalanceException;
import com.fcmb.interview.testredo.exception.NetworkProviderNotAvailable;
import com.fcmb.interview.testredo.model.domain.Account;
import com.fcmb.interview.testredo.model.domain.TransactionHistory;
import com.fcmb.interview.testredo.model.dto.AirtimePurchaseDTO;
import com.fcmb.interview.testredo.model.dto.TransferDTO;
import com.fcmb.interview.testredo.model.enums.NetworkProvider;
import com.fcmb.interview.testredo.model.enums.TransactionType;
import com.fcmb.interview.testredo.repository.AccountRepository;
import com.fcmb.interview.testredo.repository.TransactionHistoryRepository;
import com.fcmb.interview.testredo.service.TransferService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransferServiceImpl implements TransferService {

    private final TransactionHistoryRepository transactionHistoryRepository;
    private final AccountRepository accountRepository;


    public TransferServiceImpl(TransactionHistoryRepository transactionHistoryRepository, AccountRepository accountRepository) {
        this.transactionHistoryRepository = transactionHistoryRepository;
        this.accountRepository = accountRepository;
    }

    /*
     * to do transfer, check if the account balance is more than intended amount to send
     * check if the destination account exists
     * subtract the amount sent to the destination from source and update the source and update the destination
     * */
    @Override
    @Transactional
    public TransactionHistory doTransfer(TransferDTO transferDTO) {
        Account senderAccountDetails = accountRepository.findByAccountNumber(transferDTO.getSourceAccountNumber());
        if (senderAccountDetails.equals(null)) {
            throw new AccountNotFoundException("Invalid account details");
        }
        Account destinationAccountDetails = accountRepository.findByAccountNumber(transferDTO.getDestinationAccountNumber());
        if (destinationAccountDetails.equals(null)) {
            throw new AccountNotFoundException("Destination account details not found");
        }
        BigDecimal availableBalance = senderAccountDetails.getAvailableBalance();
        BigDecimal amount = transferDTO.getAmount();
        System.out.println(">>>>>> sender account type " + senderAccountDetails.getAccountType());
        System.out.println(transactionHistoryRepository.countByMonth(senderAccountDetails.getAccountNumber()));

        TransactionHistory history = new TransactionHistory();
        switch (senderAccountDetails.getAccountType()) {
            case BUSINESS:
                BigDecimal discountAmount = BigDecimal.valueOf(0.0);
                BigDecimal finalAmount = amount;
                boolean transactionMoreThan3 = transactionHistoryRepository.countByMonth(senderAccountDetails.getAccountNumber()) > 3;
                if (amount.compareTo(BigDecimal.valueOf(150000.00)) > 0 && transactionMoreThan3) {
                    discountAmount = amount.multiply(BigDecimal.valueOf(0.27));
                    finalAmount = amount.subtract(discountAmount);
                }
                if (ChronoUnit.YEARS.between(senderAccountDetails.getCreatedAt(), LocalDateTime.now()) >= 4) {
                    System.out.println(">>>>>>> account is more than 4 years in existence");
                    discountAmount.add(amount.multiply(BigDecimal.valueOf(0.10)));
                    finalAmount = finalAmount.subtract(discountAmount);
                    System.out.println("account is more than 4 years in existence <<<<<<<<<<");
                }

                if (availableBalance.compareTo(finalAmount) > 0) {
                    BigDecimal newBalance = availableBalance.subtract(finalAmount);
                    System.out.println(">>> new balance : " +newBalance);
                    senderAccountDetails.setAvailableBalance(newBalance);
                    accountRepository.save(senderAccountDetails);

                    destinationAccountDetails.setAvailableBalance(destinationAccountDetails
                            .getAvailableBalance().add(amount));
                    accountRepository.save(destinationAccountDetails);

                    //sender transactions
                    history.setAmount(amount);
                    history.setSourceAccount(senderAccountDetails.getAccountNumber());
                    history.setDestinationAccount(destinationAccountDetails.getAccountNumber());
                    history.setTransactionType(TransactionType.TRANSFER);
                    history.setDescription(transferDTO.getDescription());
                    //destination transactions
                    TransactionHistory destHistory = new TransactionHistory();
                    destHistory.setSourceAccount(senderAccountDetails.getAccountNumber());
                    destHistory.setDestinationAccount(destinationAccountDetails.getAccountNumber());
                    destHistory.setAmount(amount);
                    destHistory.setDescription("Transfer received from " + senderAccountDetails.getCustomer().getFirstname() +
                            " " + senderAccountDetails.getCustomer().getLastname());
                    transactionHistoryRepository.save(destHistory);
                    return transactionHistoryRepository.save(history);
                } else {
                    throw new InsufficientBalanceException("Insufficient balance");
                }
            case RETAIL:
                System.out.println(">>>>>> inside retail switch");
                    BigDecimal retailDiscountAmount = BigDecimal.valueOf(0.0);
                    BigDecimal retailFinalAmount = amount;
                    if (amount.compareTo(BigDecimal.valueOf(50000.00)) > 0 && transactionHistoryRepository.countByMonth(senderAccountDetails.getAccountNumber()) > 3) {
                        retailDiscountAmount = amount.multiply(BigDecimal.valueOf(0.27));
                        retailFinalAmount = retailFinalAmount.subtract(retailDiscountAmount);
                        System.out.println("transaction greater than 3 in a month");
                    }
                    if (ChronoUnit.YEARS.between(senderAccountDetails.getCreatedAt(), LocalDateTime.now()) >= 4) {
                        retailDiscountAmount.add(amount.multiply(BigDecimal.valueOf(0.10)));
                        retailFinalAmount = amount.subtract(retailDiscountAmount);
                        System.out.println("account is more than 4 years in existence");
                    }

                    if (availableBalance.compareTo(retailFinalAmount) > 0) {
                        BigDecimal newBalance = availableBalance.subtract(retailFinalAmount);
                        senderAccountDetails.setAvailableBalance(newBalance);
                        accountRepository.save(senderAccountDetails);

                        destinationAccountDetails.setAvailableBalance(destinationAccountDetails
                                .getAvailableBalance().add(amount));
                        accountRepository.save(destinationAccountDetails);

                        //sender transactions
                        history.setAmount(amount);
                        history.setSourceAccount(senderAccountDetails.getAccountNumber());
                        history.setDestinationAccount(destinationAccountDetails.getAccountNumber());
                        history.setTransactionType(TransactionType.TRANSFER);
                        history.setDescription(transferDTO.getDescription());
                        //destination transactions
                        TransactionHistory destHistory = new TransactionHistory();
                        destHistory.setSourceAccount(senderAccountDetails.getAccountNumber());
                        destHistory.setDestinationAccount(destinationAccountDetails.getAccountNumber());
                        destHistory.setAmount(amount);
                        destHistory.setDescription("Transfer received from " + senderAccountDetails.getCustomer().getFirstname() +
                                " " + senderAccountDetails.getCustomer().getLastname());
                        transactionHistoryRepository.save(destHistory);
                        return transactionHistoryRepository.save(history);
                    } else {
                        throw new InsufficientBalanceException("Insufficient balance");
                    }
            default:
                throw new FailureToMakeTransactionException("Transfer cannot be made");
        }

    }

    @Override
    public TransactionHistory buyAirtime(AirtimePurchaseDTO airtimePurchaseDTO) {
        TransactionHistory history = new TransactionHistory();
        /*
         * to buy airtime, check if the account balance is more than intended amount of airtime to purchase
         * check if the network provider is valid
         * subtract the amount sent to the destination from source and update the source and update the destination
         * */
        Account senderAccountDetails = accountRepository
                .findByAccountNumber(airtimePurchaseDTO.getSourceAccountNumber());

        if (senderAccountDetails.equals(null)) {
            throw new AccountNotFoundException("Invalid account details");
        }
        BigDecimal availableBalance = senderAccountDetails.getAvailableBalance();
        int comparison = availableBalance.compareTo(airtimePurchaseDTO.getAmount());

        if (comparison > 0) {
            //deduct the amount from the sender
            BigDecimal newBalance = availableBalance.subtract(airtimePurchaseDTO.getAmount());
            senderAccountDetails.setAvailableBalance(newBalance);
            accountRepository.save(senderAccountDetails);

            //verify that network provider is valid
            if (NetworkProvider.valueOf(airtimePurchaseDTO.getNetworkProvider()).equals(null)) {
                throw new NetworkProviderNotAvailable("Network provider not available at the moment");
            }
            //assuming the crediting of airtime logic is available
            System.out.println("Airtime purchased successfully...");
            history.setAmount(airtimePurchaseDTO.getAmount());
            history.setDescription(airtimePurchaseDTO.getDescription());
            history.setSourceAccount(airtimePurchaseDTO.getSourceAccountNumber());
            history.setTransactionType(TransactionType.AIRTIME);
        }

        return transactionHistoryRepository.save(history);
    }


}
