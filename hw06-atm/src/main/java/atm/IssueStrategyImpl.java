package atm;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class IssueStrategyImpl implements IssueStrategy{
    @Override
    public Optional<BanknoteStack> getBanknoteStack(Set<BanknoteCell> cells, int amount) {

        List<BanknoteCell> sorted = cells.stream()
                .sorted(Comparator.comparingInt(BanknoteCell::getValue).reversed())
                .toList();

        BanknoteStack bs = new BanknoteStack();

        for (BanknoteCell cell : sorted) {
            if (bs.getTotalAmount() == amount) {
                break;
            }

            int q = Math.min((amount - bs.getTotalAmount()) / cell.getValue(), cell.getQuantity());

            if (q > 0) {
                bs.add(cell.getBanknote(), q);
            }
        }

        if (bs.getTotalAmount() == amount) {
            return Optional.of(bs);
        }

        return Optional.empty();
    }
}
