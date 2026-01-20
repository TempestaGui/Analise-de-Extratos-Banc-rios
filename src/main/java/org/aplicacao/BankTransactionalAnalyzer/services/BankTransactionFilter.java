package org.aplicacao.BankTransactionalAnalyzer.services;

import org.aplicacao.BankTransactionalAnalyzer.entities.BankTransactional;

@FunctionalInterface
public interface BankTransactionFilter {
    boolean test(BankTransactional transactional);
}
