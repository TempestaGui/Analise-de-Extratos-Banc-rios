package org.aplicacao.BankTransactionalAnalyzer.utils;

import org.aplicacao.BankTransactionalAnalyzer.entities.BankTransactional;
import org.aplicacao.BankTransactionalAnalyzer.exception.DateInTheFutureException;
import org.aplicacao.BankTransactionalAnalyzer.exception.DescriptionTooLongException;
import org.aplicacao.BankTransactionalAnalyzer.exception.InvalidAmountException;
import org.aplicacao.BankTransactionalAnalyzer.exception.InvalidDateFormat;

import java.util.List;

public interface BankStatementParser {
    BankTransactional parseFrom(String line) throws DateInTheFutureException, DescriptionTooLongException, InvalidAmountException, InvalidDateFormat;
    List<BankTransactional> parseLinesFrom(List<String> lines) throws DateInTheFutureException, DescriptionTooLongException, InvalidAmountException, InvalidDateFormat;
}
