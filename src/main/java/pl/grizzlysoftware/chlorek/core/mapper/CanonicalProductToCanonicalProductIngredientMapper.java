package pl.grizzlysoftware.chlorek.core.mapper;

import pl.grizzlysoftware.chlorek.core.model.Product;
import pl.grizzlysoftware.chlorek.core.model.ProductIngredient;

import java.util.function.Function;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class CanonicalProductToCanonicalProductIngredientMapper implements Function<Product, ProductIngredient> {
    @Override
    public ProductIngredient apply(Product in) {
        if(in == null) {
            return null;
        }

        ProductIngredient out = new ProductIngredient();
        out.id = 844761495674065L;    //TODO hardcoded ingredient ID
        out.quantity = 1;
        out.unit = "Piece";
        out.productId = in.id;

        return out;
    }
}
