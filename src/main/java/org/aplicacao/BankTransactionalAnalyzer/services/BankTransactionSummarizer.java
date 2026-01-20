package org.aplicacao.BankTransactionalAnalyzer.services;

import org.aplicacao.BankTransactionalAnalyzer.entities.BankTransactional;

@FunctionalInterface
public interface BankTransactionSummarizer {
    double summarize(double accumulator, BankTransactional transactional);
}
