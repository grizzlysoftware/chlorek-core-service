package pl.grizzlysoftware.chlorek.core.provider;

import pl.grizzlysoftware.chlorek.core.model.Product;
import pl.grizzlysoftware.chlorek.core.model.ProductWithStockStatus;

import java.util.Collection;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public interface ProductProvider {
    Product getProduct(Long id);
    Collection<Product> getProductsByEan(String ean);
    Collection<Product> getProducts(String sort);
    Collection<Product> getProducts(int limit, int offset, String sort);
    Collection<ProductWithStockStatus> getProductsWithStockStatus(long warehouseId, String sort);
    Collection<ProductWithStockStatus> getProductsWithStockStatus(long warehouseId, int limit, int offset, String sort);
}
