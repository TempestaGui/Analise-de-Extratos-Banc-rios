package org.aplicacao.BankTransactionalAnalyzer.services;

import org.aplicacao.BankTransactionalAnalyzer.entities.BankTransactional;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BankStatementProcessor {
    private final List<BankTransactional> bankTransactionalList;

    public BankStatementProcessor(List<BankTransactional> bankTransactionalList) {
        this.bankTransactionalList = bankTransactionalList;
    }

    public double calculateTotalAmount(){
        return summarizeTransaction(((accumulator, transactional) ->
                accumulator + transactional.getAmount()));
    }

    public double calculateTotalTransaction(){
        return summarizeTransaction(((accumulator, transactional) ->
                transactional != null ? accumulator + 1 : accumulator));
    }

    public double calculateTotalAmountByMonth(final Month month){
        return summarizeTransaction(((accumulator, transactional) ->
                transactional.getDate().getMonth() == month ? accumulator + transactional.getAmount() : accumulator));
    }

    public double calculateTotalByCategory(final String category){
        return summarizeTransaction(((accumulator, transactional) ->
                transactional.getDescription().equals(category) ? accumulator + transactional.getAmount() : accumulator));
    }

    public double maxTransaction(){
        return summarizeTransaction(((accumulator, transactional) ->
               Math.max(accumulator, transactional.getAmount())));
    }

    public double minTransaction(){
        return summarizeTransaction(((accumulator, transactional) ->
                Math.min(accumulator, transactional.getAmount())));
    }

    public double averageTransactions(){
        double total = calculateTotalAmount();
        double qntTransaction = calculateTotalTransaction();
        return total / qntTransaction;
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

    public double summarizeTransaction(final BankTransactionSummarizer bankTransactionSummarizer){
        double result = 0;
        for(final BankTransactional transactional: bankTransactionalList){
            result = bankTransactionSummarizer.summarize(result, transactional);
        }
        return result;
    }
}
