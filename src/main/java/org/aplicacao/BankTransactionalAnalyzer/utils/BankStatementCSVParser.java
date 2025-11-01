package org.aplicacao.BankTransactionalAnalyzer.utils;

import org.aplicacao.BankTransactionalAnalyzer.entities.BankTransactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParser {
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private BankTransactional parseFromCSV(final String line){
        final String[] columns = line.split(",");

        final LocalDate date = LocalDate.parse(columns[0], dtf);
        final double amount = Double.parseDouble(columns[1]);
        final String description = columns[2];

        return new BankTransactional(amount, description, date);
    }

    public List<BankTransactional> parseLinesFromCSV(final List<String> lines){
        final List<BankTransactional> bankTransactions = new ArrayList<>();
        for(final String line: lines){
            bankTransactions.add(parseFromCSV(line));
        }
        return bankTransactions;
    }
}
