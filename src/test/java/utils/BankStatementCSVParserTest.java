package utils;

import org.aplicacao.BankTransactionalAnalyzer.entities.BankTransactional;
import org.aplicacao.BankTransactionalAnalyzer.utils.BankStatementCSVParser;
import org.aplicacao.BankTransactionalAnalyzer.utils.BankStatementParser;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

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
        Assert.assertEquals(expected.getDate(), result.getDate());
        Assert.assertEquals(expected.getAmount(), result.getAmount(), tolerance);
        Assert.assertEquals(expected.getDescription(), result.getDescription());
    }

}
