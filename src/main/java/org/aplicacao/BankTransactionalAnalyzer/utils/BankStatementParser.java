package org.aplicacao.BankTransactionalAnalyzer.utils;

import org.aplicacao.BankTransactionalAnalyzer.entities.BankTransactional;

import java.util.List;

public interface BankStatementParser {
    BankTransactional parseFrom(String line);
    List<BankTransactional> parseLinesFrom(List<String> lines);
}
