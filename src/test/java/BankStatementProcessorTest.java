import org.aplicacao.BankTransactionalAnalyzer.entities.BankTransactional;
import org.aplicacao.BankTransactionalAnalyzer.services.BankStatementProcessor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankStatementProcessorTest {

    private BankStatementProcessor processor;

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
        BankTransactional t1 = new BankTransactional(
                50.0, "teste", LocalDate.of(2017, Month.JANUARY, 30));
        BankTransactional t2 = new BankTransactional(
                100.0, "teste", LocalDate.of(2017, Month.JANUARY, 30));

        processor = new BankStatementProcessor(List.of(t1,t2));

        //when
        double total = processor.calculateTotalAmountByMonth(Month.JANUARY);

        //then
        assertEquals(150.0, total);
    }

    @Test
    public void shouldCalculateTotalAmountByDescription() throws Exception {
        //Given
        String description = "teste";

        BankTransactional t1 = new BankTransactional(
                50.0, "teste", LocalDate.of(2017, Month.JANUARY, 30));
        BankTransactional t2 = new BankTransactional(
                100.0, "teste", LocalDate.of(2017, Month.JANUARY, 30));

        processor = new BankStatementProcessor(List.of(t1,t2));

        //When
        double total = processor.calculateTotalByCategory(description);

        //Then
        assertEquals(150.0, total);
    }
}
