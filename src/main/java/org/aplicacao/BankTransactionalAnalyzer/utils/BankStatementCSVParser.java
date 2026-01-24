package org.aplicacao.BankTransactionalAnalyzer.utils;

import org.aplicacao.BankTransactionalAnalyzer.entities.BankTransactional;
import org.aplicacao.BankTransactionalAnalyzer.exception.DateInTheFutureException;
import org.aplicacao.BankTransactionalAnalyzer.exception.DescriptionTooLongException;
import org.aplicacao.BankTransactionalAnalyzer.exception.InvalidAmountException;
import org.aplicacao.BankTransactionalAnalyzer.exception.InvalidDateFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParser implements BankStatementParser{
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public BankTransactional parseFrom(final String line){
        final String[] columns = line.split(",");

        final String date = columns[0];
        final String amount = columns[1];
        final String description = columns[2];

        OverlySpecificBankStatementValidator validator = new OverlySpecificBankStatementValidator(
                description, date, amount, dtf
        );
        validator.validate();

        final LocalDate parseDate = LocalDate.parse(date, dtf);
        final double parseDouble = Double.parseDouble(amount);

        return new BankTransactional(parseDouble, description, parseDate);
    }

    @Override
    public List<BankTransactional> parseLinesFrom(final List<String> lines){
        final List<BankTransactional> bankTransactions = new ArrayList<>();
        for(final String line: lines){
            bankTransactions.add(parseFrom(line));
        }
        return bankTransactions;
    }

}
