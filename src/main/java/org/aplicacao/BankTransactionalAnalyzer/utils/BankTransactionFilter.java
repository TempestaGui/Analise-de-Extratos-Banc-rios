package org.aplicacao.BankTransactionalAnalyzer.utils;

import org.aplicacao.BankTransactionalAnalyzer.entities.BankTransactional;

@FunctionalInterface
public interface BankTransactionFilter {
    boolean test(BankTransactional transactional);
}
