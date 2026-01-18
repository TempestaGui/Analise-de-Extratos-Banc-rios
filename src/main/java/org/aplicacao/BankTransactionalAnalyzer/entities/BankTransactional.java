package org.aplicacao.BankTransactionalAnalyzer.entities;

import java.time.LocalDate;
import java.util.Objects;

public class BankTransactional {
    private Double amount;
    private String description;
    private LocalDate date;

    public BankTransactional(Double amount, String description, LocalDate date) {
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }


    public LocalDate getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BankTransactional that = (BankTransactional) o;
        return Objects.equals(amount, that.amount) && Objects.equals(description, that.description) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, description, date);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
