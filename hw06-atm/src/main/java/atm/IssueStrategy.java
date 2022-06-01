package atm;

import java.util.Optional;
import java.util.Set;

public interface IssueStrategy {
    Optional<BanknoteStack> getBanknoteStack(Set<BanknoteCell> cells, int amount);
}
