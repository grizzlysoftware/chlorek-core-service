package pl.grizzlysoftware.chlorek.core.applier

import pl.grizzlysoftware.chlorek.core.model.ContainerType
import pl.grizzlysoftware.chlorek.core.model.NullTag
import pl.grizzlysoftware.chlorek.core.model.Tag
import pl.grizzlysoftware.chlorek.core.model.Taggable
import pl.grizzlysoftware.chlorek.core.model.TaggableImpl
import spock.lang.Specification

import static pl.grizzlysoftware.chlorek.core.model.TagRegistry.CONTAINER_TYPE_TAG

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
class ContainerTypeTagApplierTest extends Specification {
    def "applies tag to Taggable that is also of type Container"() {
        given:
            def taggable = new TaggableImpl()
            taggable.containerType = ContainerType.BOTTLE_NOT_RETURNABLE
            def m = new ContainerTypeTagApplier()
        when:
            m.accept(taggable)
        then:
            def tag = taggable.getTag(CONTAINER_TYPE_TAG)
            (tag instanceof NullTag) != true
            tag.value == taggable.containerType.name()
    }

    def "does not apply tag to Taggable when given containerType is null"() {
        given:
            def taggable = new TaggableImpl()
            def m = new ContainerTypeTagApplier()
        when:
            m.accept(taggable)
        then:
            taggable.hasTag(CONTAINER_TYPE_TAG) == false
    }

    def "does not apply tag to Taggable that is not of type Container"() {
        given:
            def taggable = Mock(Taggable)
            def m = new ContainerTypeTagApplier()
        when:
            m.accept(taggable)
        then:
            0 * taggable.addTag(_ as String)
            0 * taggable.addTag(_ as Tag)
    }
}
