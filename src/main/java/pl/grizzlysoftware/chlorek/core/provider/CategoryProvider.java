package pl.grizzlysoftware.chlorek.core.provider;

import pl.grizzlysoftware.chlorek.core.model.Category;

import java.util.Collection;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public interface CategoryProvider {
    Collection<Category> getCategories(int limit, int offset, String order);
    Collection<Category> getCategories();
    Category getCategory(Long id);
}
