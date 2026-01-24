import org.aplicacao.BankTransactionalAnalyzer.utils.Notification;
import org.aplicacao.BankTransactionalAnalyzer.utils.OverlySpecificBankStatementValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;

public class OverlySpecificBankStatementValidatorTest {

    private final DateTimeFormatter drf = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Test
    void shouldNotHaveErrorsWhenDateIsValid(){
        //Given
        var validator = new OverlySpecificBankStatementValidator(
                "Salary",
                "10-01-2012",
                "2500.0", drf);
        //When
        Notification notification = validator.validate();

        //Then
        Assertions.assertFalse(notification.hasErrors());
    }

    @Test
    void shouldReturnErrorWhenDescriptionIsToLong(){
        //Given
        String longDescription = "a".repeat(101);

        var validator = new OverlySpecificBankStatementValidator(
                longDescription,
                "10-01-2002",
                "2000.0", drf
        );
        //When
        Notification notification = validator.validate();

        //Then
        Assertions.assertTrue(notification.hasErrors());
        Assertions.assertTrue(notification.errorMessage().contains("The description is too long"));
    }

    @Test
    void shouldReturnErrorWhenDateFormatIsInvalid(){
        //Given
        var validator = new OverlySpecificBankStatementValidator(
                "Test",
                "invalid-date",
                "2000.0",drf
        );
        //When
        Notification notification = validator.validate();
        //Then
        Assertions.assertTrue(notification.hasErrors());
        Assertions.assertTrue(notification.errorMessage().contains("invalid date format"));
    }

    @Test
    void ShouldReturnErrorWhenAmountFormatIsInvalid(){
        //Given
        var validator = new OverlySpecificBankStatementValidator(
                "test",
                "10-01-2002",
                "error", drf
        );
        //When
        Notification notification = validator.validate();
        //Then
        Assertions.assertTrue(notification.hasErrors());
        Assertions.assertTrue(notification.errorMessage().contains("invalid amount format"));
    }

    @Test
    void shouldReturnErrorWhenDataIsInFuture(){
        //Given
        var futureDate = "23-01-2099";

        var validator = new OverlySpecificBankStatementValidator(
                "teste",
                futureDate,
                "2000.0", drf
        );
        //When
        Notification notification = validator.validate();
        //Then
        Assertions.assertTrue(notification.hasErrors());
        Assertions.assertTrue(notification.errorMessage().contains("date cannot be in the future"));
    }

    @Test
    void shouldReturnMultipleErrors(){
        //Given
        var validator = new OverlySpecificBankStatementValidator(
                "a".repeat(200),
                "invalid-date",
                "error", drf
        );
        //When
        Notification notification = validator.validate();
        //Then
        Assertions.assertTrue(notification.hasErrors());
        Assertions.assertEquals(3, notification.getErrors().size());
    }
}
