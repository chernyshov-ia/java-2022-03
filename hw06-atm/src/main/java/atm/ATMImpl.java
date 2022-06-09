package atm;

import java.util.*;

public class ATMImpl implements ATM {
    private Set<BanknoteCell> cells;
    private IssueStrategy issueStrategy;

    public ATMImpl(Set<BanknoteCell> cells, IssueStrategy issueStrategy) {
        this.cells = cells;
        this.issueStrategy = issueStrategy;
    }

    @Override
    public int getBalance() {
        int balance = 0;
        for (BanknoteCell cell : cells) {
            balance = balance + cell.getValue() * cell.getQuantity();
        }
        return balance;
    }

    @Override
    public boolean accept(Banknote banknote) {
        var cell = cells.stream().filter(c -> c.getValue() == banknote.getValue()).findFirst();
        if (cell.isEmpty()) {
            return false;
        }
        cell.get().accept(1);
        return true;
    }

    @Override
    public Optional<BanknoteStack> issue(int amount) {
        // расчитываем пачку денег для выдачи
        var bs = issueStrategy.getBanknoteStack(cells, amount);

        // если нельзя выдать пачку денег, то уходим
        if (bs.isEmpty()) {
            return Optional.empty();
        }

        // получаем нужную пачку денег из ячеек
        for (Banknote banknote : bs.get().getBanknotes()) {
            // поиск ячейки для выдачи банкнот
            var cell = cells.stream()
                    .filter(a -> a.getBanknote().equals(banknote))
                    .findFirst().orElseThrow();

            // выдача N банкнот
            if (!cell.issue(bs.get().getBanknoteQuantity(banknote))) {
                throw new RuntimeException("not enough banknotes");
            }
        }
        return bs;
    }

    @Override
    public String getDescription() {
        StringJoiner sj = new StringJoiner(",", "[", "]");
        for (BanknoteCell cell : cells) {
            sj.add(cell.getDescription());
        }

        return "ATM{balance:" + getBalance() + ", cells:" + sj + "}";
    }
}
