package business;

import java.math.BigDecimal;
import java.util.Objects;

public record Balance(BigDecimal value) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Balance balance = (Balance) o;

        return Objects.equals(value, balance.value);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    public Balance add(Amount amount) {
        return new Balance(value.add(amount.value()));
    }

    public Balance substract(Amount amount) {
        return new Balance(value.subtract(amount.value()));
    }
}
