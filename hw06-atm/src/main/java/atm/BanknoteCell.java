package atm;

public interface BanknoteCell {
    int getValue();

    int getQuantity();

    void accept(int quantity);

    boolean issue(int quantity);
}
