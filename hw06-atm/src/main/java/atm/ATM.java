package atm;

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
    void accept(Banknote banknote);

    /**
     * Issues the requested amount with a minimum number of
     * banknotes or an error if the amount cannot be issued
     *
     * @return List of banknotes
     */
    BanknoteStack issue(int amount);
}
