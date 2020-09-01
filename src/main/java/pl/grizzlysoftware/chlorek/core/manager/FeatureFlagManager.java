package pl.grizzlysoftware.chlorek.core.manager;

import java.time.ZonedDateTime;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public interface FeatureFlagManager {
    void setEnabled(Long id, boolean enabled, ZonedDateTime updatedAt, String updatedBy);
}
