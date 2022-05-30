package atm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ATMImpl implements ATM{
    private Set<BanknoteCell> cells;

    public ATMImpl( Set<BanknoteCell> cells ) {
        this.cells = cells;
    }

    @Override
    public int getBalance() {
        return 0;
    }

    @Override
    public void accept(Banknote banknote) {

    }

    @Override
    public BanknoteStack issue(int amount) {
        return null;
    }
}
