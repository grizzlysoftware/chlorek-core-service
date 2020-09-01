package pl.grizzlysoftware.chlorek.core.manager;

import pl.grizzlysoftware.chlorek.core.model.Invoice;
import pl.grizzlysoftware.chlorek.core.model.InvoicePosition;

import java.util.Collection;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public interface InvoiceManager {
    Invoice saveInvoice(Invoice invoice);
    void saveInvoicePositions(Collection<InvoicePosition> invoices);
    void setInvoicePaymentStatus(Long id, Boolean isPaid);
}
