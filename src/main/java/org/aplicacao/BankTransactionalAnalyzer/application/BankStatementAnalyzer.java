package org.aplicacao.BankTransactionalAnalyzer.application;

import org.aplicacao.BankTransactionalAnalyzer.enums.FilesName;
import org.aplicacao.BankTransactionalAnalyzer.entities.BankTransactional;
import org.aplicacao.BankTransactionalAnalyzer.utils.BankStatementCSVParser;
import org.aplicacao.BankTransactionalAnalyzer.services.BankStatementProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class BankStatementAnalyzer {

    public static void main(String[] args) throws IOException {
        final BankStatementCSVParser bankStatementCSVParser = new BankStatementCSVParser();
        final BankStatementProcessor bankStatementProcessor;

        final List<String> lines = Files.readAllLines(Paths.get(FilesName.BANK_TRANSACTIONS.getName()));

        final List<BankTransactional> transactions = bankStatementCSVParser.parseLinesFromCSV(lines);
        bankStatementProcessor = new BankStatementProcessor(transactions);

        System.out.println("The total for all transactions is: "+ bankStatementProcessor.calculateTotalAmount());
        System.out.println("The total in January is: "+ bankStatementProcessor.calculateTotalAmountByMonth(Month.JANUARY));
        System.out.println("The total in February is: "+ bankStatementProcessor.calculateTotalAmountByMonth(Month.FEBRUARY));
        System.out.println("The total salary received is: "+bankStatementProcessor.calculateTotalByCategory("Salary"));
    }
}
