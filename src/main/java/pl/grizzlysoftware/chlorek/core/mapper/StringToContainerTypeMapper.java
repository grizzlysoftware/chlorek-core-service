package pl.grizzlysoftware.chlorek.core.mapper;

import pl.grizzlysoftware.chlorek.core.model.ContainerType;

import java.util.function.Function;

import static java.util.Arrays.stream;
import static pl.grizzlysoftware.chlorek.core.model.ContainerType.IRRELEVANT;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class StringToContainerTypeMapper implements Function<String, ContainerType> {
    @Override
    public ContainerType apply(String containerType) {
        return stream(ContainerType.values())
                .filter(e -> e.name().equals(containerType))
                .findAny()
                .orElse(IRRELEVANT);
    }
}
