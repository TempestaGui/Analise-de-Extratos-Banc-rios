package org.aplicacao.BankTransactionalAnalyzer.utils;

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

    public Notification validate() {
        final Notification notification = new Notification();

        if(this.description.length() > 100){
            notification.addError("The description is too long");
        }

        final LocalDate parseDate;
        try{
            parseDate = LocalDate.parse(this.date, formatter);
            if (parseDate.isAfter(LocalDate.now())){
                notification.addError("date cannot be in the future");
            }
        }catch (DateTimeParseException e){
            notification.addError("invalid date format");
        }

        try{
            Double.parseDouble(this.amount);
        }catch (NumberFormatException e) {
            notification.addError("invalid amount format");
        }
        return notification;
    }
}
