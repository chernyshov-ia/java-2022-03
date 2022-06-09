package atm;

public class BanknoteCellImpl implements BanknoteCell {
    private int quantity;
    final private Banknote banknote;

    public BanknoteCellImpl(Banknote banknote) {
        this.banknote = banknote;
        this.quantity = 0;
    }

    public BanknoteCellImpl(Banknote banknote, int quantity) {
        this.banknote = banknote;
        this.quantity = quantity;
    }
    @Override
    public Banknote getBanknote() {
        return banknote;
    }

    @Override
    public int getValue() {
        return banknote.getValue();
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void accept(int quantity) {
        this.quantity = this.quantity + quantity;
    }

    @Override
    public boolean issue(int quantity) {
        if (quantity > this.quantity) {
            return false;
        }
        this.quantity = this.quantity - quantity;
        return true;
    }

    @Override
    public String getDescription() {
        return "{"  + getBanknote().getValue() +  " => " + getQuantity() + "}";
    }
}
