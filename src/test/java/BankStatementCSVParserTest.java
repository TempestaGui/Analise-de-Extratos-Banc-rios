import org.aplicacao.BankTransactionalAnalyzer.entities.BankTransactional;
import org.aplicacao.BankTransactionalAnalyzer.utils.BankStatementCSVParser;
import org.aplicacao.BankTransactionalAnalyzer.utils.BankStatementParser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BankStatementCSVParserTest {

    private final BankStatementParser statementParser = new BankStatementCSVParser();

    @Test
    public void shouldParseOneCorrectLine() throws Exception {
        //Given
        final String line  = "30-01-2017,-50,Tesco";

        //When
        final BankTransactional result = statementParser.parseFrom(line);

        final BankTransactional expected =
                    new BankTransactional(-50.0,"Tesco",LocalDate.of(2017, Month.JANUARY, 30));
        final double tolerance = 0.0d;

        //Then
        assertEquals(expected.getDate(), result.getDate());
        assertEquals(expected.getAmount(), result.getAmount(), tolerance);
        assertEquals(expected.getDescription(), result.getDescription());
    }

    @Test
    public void shouldParseLines() throws Exception {
        //Given
        final List<String> lines = new ArrayList<>(Arrays.asList(
                "30-01-2017,-50,Tesco",
                "30-01-2017,-50,Tesco"));

        //When
        final List<BankTransactional> result = statementParser.parseLinesFrom(lines);

        //Then
        assertEquals(2, result.size());

        BankTransactional transactional = result.get(0);

        assertEquals(LocalDate.of(2017,Month.JANUARY,30), transactional.getDate());
        assertEquals(-50, transactional.getAmount());
        assertEquals("Tesco", transactional.getDescription());
    }

}
