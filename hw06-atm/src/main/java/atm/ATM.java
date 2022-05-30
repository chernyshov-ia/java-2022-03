package atm;

import java.util.Optional;

public interface ATM {
    /**
     * Returns the amount of the remaining funds
     *
     * @return the amount of the remaining funds
     */
    int getBalance();

    /**
     * Аccept banknotes of different denominations
     */
    boolean accept(Banknote banknote);

    /**
     * Issues the requested amount with a minimum number of
     * banknotes or an error if the amount cannot be issued
     *
     * @return List of banknotes
     */
    Optional<BanknoteStack> issue(int amount);
}
