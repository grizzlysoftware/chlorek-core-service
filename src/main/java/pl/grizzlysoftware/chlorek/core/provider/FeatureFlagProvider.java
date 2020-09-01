package pl.grizzlysoftware.chlorek.core.provider;

import pl.grizzlysoftware.chlorek.core.model.FeatureFlag;

import java.util.Collection;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public interface FeatureFlagProvider {
    boolean isEnabled(String name);
    boolean isEnabled(Long id);
    Collection<FeatureFlag> findAll();
}
