package pl.grizzlysoftware.chlorek.core.provider;

import pl.grizzlysoftware.chlorek.core.model.InvoicePosition;

import java.time.LocalDateTime;
import java.util.Collection;

public interface SalesProvider {
    Collection<InvoicePosition> getInvoicePositions(LocalDateTime startDate, LocalDateTime endDate, int page, int pageSize, String sort);
    Collection<InvoicePosition> getInvoicePositions(LocalDateTime startDate, LocalDateTime endDate);
}
