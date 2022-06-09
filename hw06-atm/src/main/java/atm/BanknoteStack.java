package atm;

import java.util.*;

public class BanknoteStack {
    final private Map<Banknote, Integer> banknotes;

    public BanknoteStack() {
        this.banknotes = new HashMap<>();
    }

    public void add(Banknote banknote, int quantity) {
        banknotes.put(banknote, banknotes.getOrDefault(banknote, 0) + quantity);
    }

    public int getTotalAmount() {
        int amount = 0;
        for (var entry : banknotes.entrySet()) {
            amount = amount + entry.getKey().getValue() * entry.getValue();
        }
        return amount;
    }

    public int getBanknoteQuantity(Banknote banknote) {
        return banknotes.getOrDefault(banknote, 0);
    }

    public Set<Banknote> getBanknotes() {
        return Collections.unmodifiableSet(banknotes.keySet());
    }

    @Override
    public String toString() {
        var sj = new StringJoiner(",","[","]");
        for (var entry : banknotes.entrySet()) {
           sj.add("{" + entry.getKey().getValue() + " => " + entry.getValue() + "шт}");
        }
        return "Пачка[" + sj + ']';
    }
}
