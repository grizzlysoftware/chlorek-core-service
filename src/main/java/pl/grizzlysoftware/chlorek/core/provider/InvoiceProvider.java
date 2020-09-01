package pl.grizzlysoftware.chlorek.core.provider;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public interface InvoiceProvider {
    long invoicePositionsCount();
    boolean existsByNumber(String number);
    Optional<LocalDateTime> latestInvoiceIssueDate();
}
