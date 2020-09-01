package pl.grizzlysoftware.chlorek.core.mapper;

import pl.grizzlysoftware.chlorek.core.model.Invoice;
import pl.grizzlysoftware.chlorek.core.model.Stockup;

import java.util.function.Function;

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

        return out;
    }
}
