package org.aplicacao.BankTransactionalAnalyzer.services;

import org.aplicacao.BankTransactionalAnalyzer.entities.BankTransactional;
import org.aplicacao.BankTransactionalAnalyzer.utils.BankTransactionFilter;

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

    public List<BankTransactional> findTransactions(final BankTransactionFilter filter){
        final List<BankTransactional> result = new ArrayList<>();
        for(final BankTransactional transactional: bankTransactionalList){
            if(filter.test(transactional)){
                result.add(transactional);
            }
        }
        return result;
    }
}
