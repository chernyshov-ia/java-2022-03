package atm;

public enum Banknote {
    RUB50(50), RUB100(100), RUB500(500), RUB1000(1000), RUB5000(5000);

    private int value;

    Banknote(int value) {
      this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Banknote{" +
                "value=" + value +
                '}';
    }
}
