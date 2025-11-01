package org.aplicacao.BankTransactionalAnalyzer.application;

import org.aplicacao.BankTransactionalAnalyzer.enums.FilesName;
import org.aplicacao.BankTransactionalAnalyzer.entities.BankTransactional;
import org.aplicacao.BankTransactionalAnalyzer.services.BankService;
import org.aplicacao.BankTransactionalAnalyzer.utils.BankStatementCSVParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class program {

    public static void main(String[] args) throws IOException {
        final BankStatementCSVParser bankStatementCSVParser = new BankStatementCSVParser();
        final BankService bankService = new BankService();

        final Path path = Paths.get(FilesName.BANK_TRANSACTIONS.getName());
        final List<String> lines = Files.readAllLines(path);

        final List<BankTransactional> bankTransactions = bankStatementCSVParser.parseLinesFromCSV(lines);

        System.out.println("The total for all transactions is: "+bankService.calculateTotalAmount(bankTransactions));
        System.out.println("The total in January is: "+bankService.selectInMonth(bankTransactions, Month.JANUARY));
    }
}
