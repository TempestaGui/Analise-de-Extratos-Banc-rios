package org.aplicacao.BankTransactionalAnalyzer.services;

import org.aplicacao.BankTransactionalAnalyzer.entities.BankTransactional;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BankService {

    public  double calculateTotalAmount(List<BankTransactional> bankTransactions){
        double total = 0.0;
        for(final BankTransactional bankTransactional:  bankTransactions){
            total += bankTransactional.getAmount();
        }
        return total;
    }

    public List<String> selectInMonth(List<BankTransactional> bankTransactions, Month month){
        final List<String> bankTransactionsInMonth = new ArrayList<>();
        for(final BankTransactional bankTransaction:bankTransactions){
            if(bankTransaction.getDate().getMonth() == month){
                bankTransactionsInMonth.add(bankTransaction.getDescription());
            }
        }
        return bankTransactionsInMonth;
    }
}
