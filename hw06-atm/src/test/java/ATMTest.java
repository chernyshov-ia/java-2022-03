import atm.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ATMTest {
    @Test
    @DisplayName("Внести купюры")
    void acceptTest(){
        var cells = new HashSet<BanknoteCell>();
        cells.add(new BanknoteCellImpl(Banknote.RUB5000, 0));
        cells.add(new BanknoteCellImpl(Banknote.RUB1000, 0));
        cells.add(new BanknoteCellImpl(Banknote.RUB500, 0));
        cells.add(new BanknoteCellImpl(Banknote.RUB100, 0));
        cells.add(new BanknoteCellImpl(Banknote.RUB50, 0));

        ATM atm = new ATMImpl( cells, new IssueStrategyImpl() );

        assertThat(atm.getBalance()).isEqualTo(0);

        atm.accept(Banknote.RUB50);
        atm.accept(Banknote.RUB100);
        atm.accept(Banknote.RUB500);
        atm.accept(Banknote.RUB1000);
        atm.accept(Banknote.RUB5000);

        assertThat(atm.getBalance()).isEqualTo(6650);
    }

    @Test
    @DisplayName("Выдать правильную сумму")
    void issueOkTest(){
        var cells = new HashSet<BanknoteCell>();
        cells.add(new BanknoteCellImpl(Banknote.RUB5000, 2));
        cells.add(new BanknoteCellImpl(Banknote.RUB1000, 5));
        cells.add(new BanknoteCellImpl(Banknote.RUB500, 10));
        cells.add(new BanknoteCellImpl(Banknote.RUB100, 10));
        cells.add(new BanknoteCellImpl(Banknote.RUB50, 10));

        ATM atm = new ATMImpl( cells, new IssueStrategyImpl() );

        int initialAmount = 21500;
        assertThat(atm.getBalance()).isEqualTo(initialAmount);

        int issueAmount = 6650;
        var stack = atm.issue(issueAmount);
        assertThat(stack.isPresent()).isEqualTo(true);
        assertThat(stack.orElseThrow().getTotalAmount()).isEqualTo( issueAmount);
        assertThat(atm.getBalance()).isEqualTo(initialAmount - issueAmount);
    }
}
