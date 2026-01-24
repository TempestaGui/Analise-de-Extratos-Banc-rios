package org.aplicacao.BankTransactionalAnalyzer.utils;

import org.aplicacao.BankTransactionalAnalyzer.exception.DateInTheFutureException;
import org.aplicacao.BankTransactionalAnalyzer.exception.DescriptionTooLongException;
import org.aplicacao.BankTransactionalAnalyzer.exception.InvalidAmountException;
import org.aplicacao.BankTransactionalAnalyzer.exception.InvalidDateFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class OverlySpecificBankStatementValidator {

    private final String description;
    private final String date;
    private final String amount;
    private final DateTimeFormatter formatter;

    public OverlySpecificBankStatementValidator(String description, String date, String amount, DateTimeFormatter formatter) {
        this.description = description;
        this.date = date;
        this.amount = amount;
        this.formatter = formatter;
    }

    public void validate() {
        if(this.description.length() > 100){
            throw new IllegalArgumentException("The description is too long");
        }

        final LocalDate parseDate;
        try{
            parseDate = LocalDate.parse(this.date, formatter);
        }catch (DateTimeParseException e){
            throw new IllegalArgumentException("invalid date format");
        }

        try{
            Double.parseDouble(this.amount);
        }catch (NumberFormatException e) {
            throw new IllegalArgumentException("invalid format for amount");
        }
    }
}
