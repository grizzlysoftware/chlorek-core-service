package pl.grizzlysoftware.chlorek.core.resolver

import pl.grizzlysoftware.chlorek.core.model.ContainerType
import pl.grizzlysoftware.chlorek.core.model.Tag
import pl.grizzlysoftware.chlorek.core.model.Taggable
import pl.grizzlysoftware.chlorek.core.model.TaggableImpl
import pl.grizzlysoftware.chlorek.core.mapper.StringToContainerTypeMapper
import spock.lang.Specification
import spock.lang.Unroll

import static pl.grizzlysoftware.chlorek.core.model.ContainerType.IRRELEVANT
import static pl.grizzlysoftware.chlorek.core.model.TagRegistry.CONTAINER_TYPE_TAG

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
class ContainerTypeTagValueResolverTest extends Specification {
    def "returns IRRELEVANT if ContainerType for given String argument was not found"(stringContainerType) {
        given:
            def taggable = new TaggableImpl([stringContainerType])
            def m = new ContainerTypeTagValueResolver()
        when:
            def output = m.resolve(taggable)
        then:
            output == IRRELEVANT
        where:
            stringContainerType << ["CONTAINER_TYPEx", "x"]
    }

    def "returns IRRELEVANT if tags in taggable are empty"() {
        given:
            def taggable = new TaggableImpl([])
            def m = new ContainerTypeTagValueResolver()
        when:
            def output = m.resolve(taggable)
        then:
            output == IRRELEVANT
    }

    @Unroll
    def "returns ContainerType for any given String argument matching ContainerType name"(stringContainerType, expectedContainerType) {
        given:
            def taggable = new TaggableImpl([])
            taggable.addTag(new Tag(CONTAINER_TYPE_TAG, stringContainerType))
            def m = new ContainerTypeTagValueResolver()
        when:
            def output = m.resolve(taggable)
        then:
            output == expectedContainerType
        where:
            stringContainerType << Arrays.stream(ContainerType.values()).map({ e-> e.name()}).collect()
            expectedContainerType << ContainerType.values()
    }

    def "invokes StringToContainerTypeMapper when resolving ContainerType"() {
        given:
            def taggable = Mock(Taggable) {
                getTag(_) >> Mock(Tag)
            }
            def m = new ContainerTypeTagValueResolver()
            m.toContainerTypeMapper = Mock(StringToContainerTypeMapper)
        when:
            m.resolve(taggable, null)
        then:
            1 * m.toContainerTypeMapper.apply(_)
    }

    def "throws IllegalArgumentException when given taggable is null"() {
        given:
            def m = new ContainerTypeTagValueResolver()
        when:
            m.resolve(null, null)
        then:
            thrown(IllegalArgumentException)
    }
}
