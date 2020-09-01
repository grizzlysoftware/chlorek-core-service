package pl.grizzlysoftware.chlorek.core.util;

import lombok.extern.slf4j.Slf4j;
import pl.grizzlysoftware.chlorek.core.applier.UpdatedAtTagApplier;
import pl.grizzlysoftware.chlorek.core.model.Product;
import pl.grizzlysoftware.chlorek.core.service.ProductService;
import pl.grizzlysoftware.commons.ConcurrentUtils;

import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;

@Slf4j
public class ProductUpdater implements Consumer<Product> {
    private ProductService productService;
    private UpdatedAtTagApplier updatedAtTagApplier;
    private long updateDelay;

    public ProductUpdater(ProductService productService, long updateDelay) {
        this.productService = requireNonNull(productService);
        this.updateDelay = updateDelay;
        this.updatedAtTagApplier = new UpdatedAtTagApplier();
    }

    public ProductUpdater(ProductService productService) {
        this(productService, 50);
    }

    private ProductUpdater() {
        //for tests purpose
        this.updatedAtTagApplier = new UpdatedAtTagApplier();
    }

    @Override
    public void accept(Product product) {
        try {
            updatedAtTagApplier.accept(product);
            productService.updateProduct(product);
            ConcurrentUtils.sleepSilently(updateDelay);
        } catch (Exception e) {
            log.warn("Unable to update product", e.getMessage());
        }
    }
}
