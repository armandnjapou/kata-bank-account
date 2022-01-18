package business;

import java.math.BigDecimal;

public class Balance {
    private final BigDecimal value;

    public Balance(BigDecimal value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Balance balance = (Balance) o;

        return value != null ? value.equals(balance.value) : balance.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
