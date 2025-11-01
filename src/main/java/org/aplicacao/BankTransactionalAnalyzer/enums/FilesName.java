package org.aplicacao.BankTransactionalAnalyzer.enums;

public enum FilesName {
    BANK_TRANSACTIONS("src/main/resources/extratosBancarioMark.csv");

    private String name;
    FilesName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
