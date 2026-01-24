package org.aplicacao.BankTransactionalAnalyzer.application;

import org.aplicacao.BankTransactionalAnalyzer.entities.SummaryStatistics;
import org.aplicacao.BankTransactionalAnalyzer.enums.FilesName;
import org.aplicacao.BankTransactionalAnalyzer.entities.BankTransactional;
import org.aplicacao.BankTransactionalAnalyzer.exception.DateInTheFutureException;
import org.aplicacao.BankTransactionalAnalyzer.exception.DescriptionTooLongException;
import org.aplicacao.BankTransactionalAnalyzer.exception.InvalidAmountException;
import org.aplicacao.BankTransactionalAnalyzer.exception.InvalidDateFormat;
import org.aplicacao.BankTransactionalAnalyzer.exporters.HtmlExporter;
import org.aplicacao.BankTransactionalAnalyzer.exporters.Exporter;
import org.aplicacao.BankTransactionalAnalyzer.utils.BankStatementCSVParser;
import org.aplicacao.BankTransactionalAnalyzer.services.BankStatementProcessor;
import org.aplicacao.BankTransactionalAnalyzer.utils.BankStatementParser;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class BankStatementAnalyzer {
    public void analyzer(final BankStatementParser bankStatementParser) throws IOException {
        final List<String> lines = Files.readAllLines(Paths.get(FilesName.BANK_TRANSACTIONS.getName()));
        final List<BankTransactional> transactions = bankStatementParser.parseLinesFrom(lines);
        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(transactions);


        final List<BankTransactional> filtered = bankStatementProcessor.findTransactions(transactional ->
                        transactional.getDate().getMonth() == Month.FEBRUARY &&
                        transactional.getAmount() >= 1000);

        SummaryStatistics statistics = new SummaryStatistics(bankStatementProcessor.calculateTotalAmount(),
                            bankStatementProcessor.maxTransaction(), bankStatementProcessor.minTransaction());

        Exporter exporter = new HtmlExporter();
        String html = exporter.export(statistics);

        System.out.println("The total for all transactions is: "+ bankStatementProcessor.calculateTotalAmount());
        System.out.println("The total in January is: "+ bankStatementProcessor.calculateTotalAmountByMonth(Month.JANUARY));
        System.out.println("The total in February is: "+ bankStatementProcessor.calculateTotalAmountByMonth(Month.FEBRUARY));
        System.out.println("The total salary received is: "+bankStatementProcessor.calculateTotalByCategory("Salary"));
        System.out.println("The transactions InFebruary And Expansive "+filtered);

        Files.writeString(Path.of("report.html"), html);
        System.out.println("Html created!");
    }

    public static void main(String[] args) throws IOException {
       BankStatementAnalyzer analyzer = new BankStatementAnalyzer();
       BankStatementParser bankStatementParser = new BankStatementCSVParser();

       analyzer.analyzer(bankStatementParser);
    }
}
