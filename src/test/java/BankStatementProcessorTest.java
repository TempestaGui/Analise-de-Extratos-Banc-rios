import org.aplicacao.BankTransactionalAnalyzer.entities.BankTransactional;
import org.aplicacao.BankTransactionalAnalyzer.services.BankStatementProcessor;
import org.aplicacao.BankTransactionalAnalyzer.services.BankTransactionFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankStatementProcessorTest {

    private BankStatementProcessor processor;

    @BeforeEach
    void setUp() {
        BankTransactional t1 = new BankTransactional(
                -50.0, "teste", LocalDate.of(2017, Month.JANUARY, 30));
        BankTransactional t2 = new BankTransactional(
                100.0, "teste", LocalDate.of(2017, Month.JANUARY, 30));

        processor = new BankStatementProcessor(List.of(t1, t2));
    }

    @Test
    public void shouldCalculateTotalAmount() throws Exception {
        //Given
        BankTransactional transactional = new BankTransactional(
                50.0, "teste", LocalDate.of(2017, Month.JANUARY, 30));

        processor = new BankStatementProcessor(List.of(transactional));

        //when
        double total = processor.calculateTotalAmount();

        //then
        assertEquals(50.0, total);
    }

    @Test
    public void shouldCalculateTotalAmountByMonth() throws Exception {
        //Given
        setUp();

        //when
        double total = processor.calculateTotalAmountByMonth(Month.JANUARY);

        //then
        assertEquals(50, total,0.001);
    }

    @Test
    public void shouldCalculateTotalAmountByDescription() throws Exception {
        //Given
        String description = "teste";
        setUp();

        //When
        double total = processor.calculateTotalByCategory(description);

        //Then
        assertEquals(50, total, 0.001);
    }

    @Test
    public void shouldFindMaxTransaction() throws Exception {
        //Given
        setUp();

        //when
        double max = processor.maxTransaction();

        //Then
        assertEquals(100, max);
    }

    @Test
    public void shouldFIndMinTransaction() throws Exception {
        //Given
        setUp();

        //When
        double min = processor.minTransaction();

        //Then
        assertEquals(-50, min);
    }

    @Test
    public void shouldFindTransaction() throws Exception {
        //Given
        setUp();

        //When
        List<BankTransactional> result = processor.findTransactions(t -> t.getAmount() >= 50);

        //Then
        assertEquals(1, result.size());
        assertEquals(100, result.getFirst().getAmount());
    }
}