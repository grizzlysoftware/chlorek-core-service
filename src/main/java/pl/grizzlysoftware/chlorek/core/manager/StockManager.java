package pl.grizzlysoftware.chlorek.core.manager;

import pl.grizzlysoftware.chlorek.core.model.Stockup;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public interface StockManager {
    void stockup(Stockup stockup);
    void pendingStockup(Stockup stockup);
    void deletePendingStockupByInvoiceNr(String invoiceNumber);
}
