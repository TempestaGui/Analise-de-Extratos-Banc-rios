package org.aplicacao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BankTransactionalAnalyzerSimple2_1 {
    private static final String RESOURCE = "src/main/resources/extratosBancarioMark.csv";
    public static void main(String[] args) throws IOException {
        Path path = Paths.get(RESOURCE);
        List<String> lines = Files.readAllLines(path);
        double total = 0;
        for(String line : lines){
            final String[] columns = line.split(",");
            double amount  = Double.parseDouble(columns[1]);
            total += amount;
        }
        System.out.println("The total for all transactions is: "+total);

    }
}