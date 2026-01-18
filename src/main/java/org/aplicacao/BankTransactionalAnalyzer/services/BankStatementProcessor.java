package org.aplicacao.BankTransactionalAnalyzer.services;

import org.aplicacao.BankTransactionalAnalyzer.entities.BankTransactional;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BankStatementProcessor {
    private final List<BankTransactional> bankTransactionalList;

    public BankStatementProcessor(List<BankTransactional> bankTransactionalList) {
        this.bankTransactionalList = bankTransactionalList;
    }

    public double calculateTotalAmount(){
        double total = 0;
        for(final BankTransactional bankTransactional: bankTransactionalList){
            total += bankTransactional.getAmount();
        }
        return total;
    }

    public double calculateTotalAmountByMonth(final Month month){
        double total = 0;
        for(final BankTransactional bankTransactional: bankTransactionalList){
            if(bankTransactional.getDate().getMonth() == month){
                total += bankTransactional.getAmount();
            }
        }
        return total;
    }

    public double calculateTotalByCategory(final String category){
        double total = 0;
        for(final BankTransactional bankTransactional: bankTransactionalList){
            if(bankTransactional.getDescription().equals(category)){
                total += bankTransactional.getAmount();
            }
        }
        return total;
    }

    public List<BankTransactional> findTransactionsGreaterThanEqual(final double amount){
        final List<BankTransactional> result = new ArrayList<>();
        for(final BankTransactional transactional: bankTransactionalList){
            if(transactional.getAmount() >= amount){
                result.add(transactional);
            }
        }
        return result;
    }

    public List<BankTransactional> findTransactionsInMonth(final Month month){
        final List<BankTransactional> result = new ArrayList<>();
        for(final BankTransactional transactional: bankTransactionalList){
            if(transactional.getDate().getMonth() == month){
                result.add(transactional);
            }
        }
        return result;
    }
}
