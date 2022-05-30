package atm;

public class BanknoteCellImpl implements BanknoteCell{
    private int quantity;
    final private Banknote banknote;

    public BanknoteCellImpl(Banknote banknote) {
        this.banknote = banknote;
        this.quantity = 0;
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
        this.quantity++;
    }

    @Override
    public boolean issue(int quantity) {
        if(quantity > this.quantity) {
            return false;
        }
        this.quantity = this.quantity - quantity;
        return true;
    }
}
