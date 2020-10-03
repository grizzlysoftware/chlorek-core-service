package pl.grizzlysoftware.chlorek.core.mapper;

import pl.grizzlysoftware.chlorek.core.model.Invoice;
import pl.grizzlysoftware.chlorek.core.model.Stockup;
import pl.grizzlysoftware.commons.NumberUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static pl.grizzlysoftware.commons.NumberUtils.with2Scale;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class CanonicalStockupToCanonicalInvoiceMapper implements Function<Stockup, Invoice> {
    @Override
    public Invoice apply(Stockup in) {
        if (in == null) {
            return null;
        }
        var out = new Invoice();
        if (in.invoice == null) {
            return out;
        }
        out.number = in.invoice.number;
        out.issuedAt = in.invoice.issuedAt;
        out.isPaid = in.invoice.isPaid;
        if (in.invoice.isPaid && in.invoice.paymentDueTo == null) {
            out.paymentDueTo = in.invoice.issuedAt;
        } else {
            out.paymentDueTo = in.invoice.paymentDueTo;
        }
        out.grossValue = in.invoice.grossValue;
        out.supplierId = in.invoice.supplierId;
        out.netValue = ofNullable(in.invoice.items)
                .map(e -> e.stream())
                .map(e -> e.mapToDouble(k -> k.netPurchaseTotalPrice.doubleValue()).sum())
                .map(NumberUtils::with2Scale)
                .orElse(BigDecimal.ZERO);

        return out;
    }
}
