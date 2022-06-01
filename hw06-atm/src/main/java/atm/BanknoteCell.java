package atm;

public interface BanknoteCell {
    Banknote getBanknote();

    int getValue();

    int getQuantity();

    void accept(int quantity);

    boolean issue(int quantity);

    String getDescription();
}
