package pl.grizzlysoftware.chlorek.core.provider;

import pl.grizzlysoftware.chlorek.core.model.Supplier;

import java.util.Collection;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public interface SupplierProvider {
    Collection<Supplier> getSuppliers();
}
