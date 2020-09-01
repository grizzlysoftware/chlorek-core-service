package pl.grizzlysoftware.chlorek.core.provider;

import pl.grizzlysoftware.chlorek.core.model.Warehouse;

import java.util.Collection;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public interface WarehouseProvider {
    Collection<Warehouse> getWarehouses();
    Collection<Warehouse> getWarehouses(int limit, int offset);
    Warehouse getWarehouse(Long id);
}
