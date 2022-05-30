package atm;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BanknoteStack {
    private Map<Banknote, Integer> banknotes;

    public BanknoteStack() {
        this.banknotes = new HashMap<>();
    }

    public void add(Banknote banknote, int quantity) {
        banknotes.put(banknote, banknotes.getOrDefault(banknote, 0));
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
}
