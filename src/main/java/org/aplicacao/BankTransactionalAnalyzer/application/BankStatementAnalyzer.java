package org.aplicacao.BankTransactionalAnalyzer.application;

import org.aplicacao.BankTransactionalAnalyzer.entities.SummaryStatistics;
import org.aplicacao.BankTransactionalAnalyzer.enums.FilesName;
import org.aplicacao.BankTransactionalAnalyzer.entities.BankTransactional;
import org.aplicacao.BankTransactionalAnalyzer.exporters.HtmlExporter;
import org.aplicacao.BankTransactionalAnalyzer.exporters.Exporter;
import org.aplicacao.BankTransactionalAnalyzer.utils.BankStatementCSVParser;
import org.aplicacao.BankTransactionalAnalyzer.services.BankStatementProcessor;
import org.aplicacao.BankTransactionalAnalyzer.utils.BankStatementParser;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class BankStatementAnalyzer {

    public String analyzer(final BankStatementParser bankStatementParser) throws IOException {
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

        String result = "total: " + bankStatementProcessor.calculateTotalAmount()+
                "\nJanuary: " + bankStatementProcessor.calculateTotalAmountByMonth(Month.JANUARY) +
                "\nFebruary: " + bankStatementProcessor.calculateTotalAmountByMonth(Month.FEBRUARY) +
                "\nsalary: " + bankStatementProcessor.calculateTotalByCategory("Salary") +
                "\ntransactions: " + bankStatementProcessor.calculateTotalTransaction() +
                String.format("\nAverage: %.2f", bankStatementProcessor.averageTransactions()) +
                "\nThe transactions InFebruary And Expansive " + filtered;

        Files.writeString(Path.of("report.html"), html);
        System.out.println("Html created!");

        return result;
    }


    public static void main(String[] args) throws IOException {
       BankStatementAnalyzer analyzer = new BankStatementAnalyzer();
       BankStatementParser bankStatementParser = new BankStatementCSVParser();


        JFrame frame = new JFrame("Bank Transaction analyzer");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel label = new JLabel("your transactions");
        label.setBounds(130, 30, 150, 30);

        JButton button = new JButton("Click here");
        button.setBounds(120, 80, 150, 30);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String result = analyzer.analyzer(bankStatementParser);
                    JOptionPane.showMessageDialog(frame, result);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        frame.add(label);
        frame.add(button);

        frame.setVisible(true);

       analyzer.analyzer(bankStatementParser);
    }
}
