package atm;

import java.util.HashSet;

public class IoC {
    private IoC(){
        throw new UnsupportedOperationException();
    }

    public static ATM getATM() {
        var cells = new HashSet<BanknoteCell>();
        cells.add(new BanknoteCellImpl(Banknote.RUB5000));
        cells.add(new BanknoteCellImpl(Banknote.RUB1000));
        cells.add(new BanknoteCellImpl(Banknote.RUB500));
        cells.add(new BanknoteCellImpl(Banknote.RUB50));
        return new ATMImpl(cells);
    }
}
