package atm;

import java.util.HashSet;

public class IoC {
    private IoC(){
        throw new UnsupportedOperationException();
    }

    public static ATM getATM() {
        var cells = new HashSet<BanknoteCell>();
        cells.add(new BanknoteCellImpl(Banknote.RUB5000, 2));
        cells.add(new BanknoteCellImpl(Banknote.RUB1000, 10));
        cells.add(new BanknoteCellImpl(Banknote.RUB500, 20));
        cells.add(new BanknoteCellImpl(Banknote.RUB50, 10));
        return new ATMImpl(cells, new IssueStrategyImpl());
    }
}
