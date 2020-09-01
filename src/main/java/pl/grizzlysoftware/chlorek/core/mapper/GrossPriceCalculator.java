package pl.grizzlysoftware.chlorek.core.mapper;

import java.math.BigDecimal;
import java.util.function.BiFunction;

import static pl.grizzlysoftware.commons.NumberUtils.with2Scale;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class GrossPriceCalculator implements BiFunction<Number, Number, Number> {
    @Override
    public BigDecimal apply(Number netPrice, Number vat) {
        if (netPrice == null || vat == null) {
            return null;
        }

        var vat_ = vat.doubleValue();

        if (vat_ < 0 || vat_ > 1.0) {
            throw new IllegalArgumentException("vat cannot be neither a negative number or greater than 1");
        }

        return with2Scale(netPrice.doubleValue() + netPrice.doubleValue() * vat_);
    }
}
