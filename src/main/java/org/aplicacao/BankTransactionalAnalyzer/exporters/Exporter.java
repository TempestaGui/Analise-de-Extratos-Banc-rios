package org.aplicacao.BankTransactionalAnalyzer.exporters;

import org.aplicacao.BankTransactionalAnalyzer.entities.SummaryStatistics;

public interface Exporter {
    String export(SummaryStatistics summaryStatistics);
}
