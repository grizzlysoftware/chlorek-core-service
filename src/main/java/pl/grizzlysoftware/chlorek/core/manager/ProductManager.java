package pl.grizzlysoftware.chlorek.core.manager;

import pl.grizzlysoftware.chlorek.core.model.Product;
import pl.grizzlysoftware.chlorek.core.model.ProductIngredient;

import java.util.Collection;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public interface ProductManager {
    Product createProduct(Product in);
    Product deleteProduct(Long id);
    void createProductIngredient(ProductIngredient in);
    Product updateProduct(Product product);
    void saveAll(Collection<? extends Product> in);
    void removeAll();
}
