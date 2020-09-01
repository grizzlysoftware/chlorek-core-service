package pl.grizzlysoftware.chlorek.core.resolver;

import pl.grizzlysoftware.chlorek.core.mapper.StringToContainerTypeMapper;
import pl.grizzlysoftware.chlorek.core.model.ContainerType;
import pl.grizzlysoftware.chlorek.core.model.Taggable;

import static pl.grizzlysoftware.chlorek.core.model.TagRegistry.CONTAINER_TYPE_TAG;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class ContainerTypeTagValueResolver extends AbstractTagValueResolver<ContainerType> {
    private StringToContainerTypeMapper toContainerTypeMapper;

    public ContainerTypeTagValueResolver() {
        super(CONTAINER_TYPE_TAG);
        toContainerTypeMapper = new StringToContainerTypeMapper();
    }

    @Override
    protected ContainerType resolve(Taggable taggable, String tagName) {
        if (taggable == null) {
            throw new IllegalArgumentException("Unable to resolve ContainerType from null");
        }
        var tag = taggable.getTag(tagName);
        var output = toContainerTypeMapper.apply(tag.value);
        return output;
    }
}
