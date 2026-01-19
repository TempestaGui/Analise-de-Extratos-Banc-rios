package org.aplicacao.BankTransactionalAnalyzer.services.filters;

import org.aplicacao.BankTransactionalAnalyzer.entities.BankTransactional;
import org.aplicacao.BankTransactionalAnalyzer.utils.BankTransactionFilter;

import java.time.Month;

public class BankTransactionIsInFebruaryAndExpansive implements BankTransactionFilter {
    @Override
    public boolean test(final BankTransactional transactional) {
        return transactional.getDate().getMonth() == Month.FEBRUARY
                && transactional.getAmount() >= 1_000;
    }
}
