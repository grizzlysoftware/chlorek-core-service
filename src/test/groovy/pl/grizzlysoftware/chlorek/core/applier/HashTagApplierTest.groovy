package pl.grizzlysoftware.chlorek.core.applier

import groovy.transform.EqualsAndHashCode
import pl.grizzlysoftware.chlorek.core.model.ContainerType
import pl.grizzlysoftware.chlorek.core.model.NullTag
import pl.grizzlysoftware.chlorek.core.model.TaggableImpl
import spock.lang.Specification

import static pl.grizzlysoftware.chlorek.core.model.TagRegistry.HASH_TAG

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
class HashTagApplierTest extends Specification {
    def "applies HASH tag to Taggable"() {
        given:
            def taggable = new TaggableImplWithHashCode()
            def m = new HashTagApplier()
        when:
            m.accept(taggable)
        then:
            def tag = taggable.getTag(HASH_TAG)
            (tag instanceof NullTag) != true
            tag.value == taggable.hashCode() + ""
    }

    def "updates HASH tag on Taggable"() {
        given:
            def taggable = new TaggableImplWithHashCode()
            def m = new HashTagApplier()
        when:
            m.accept(taggable)
            def preUpdateHash = taggable.hashCode()+""
            taggable.property = "some value"
        then:
            m.accept(taggable)
        then:
            def tag = taggable.getTag(HASH_TAG)
            tag.value == taggable.hashCode() + ""
            tag.value != preUpdateHash
    }

    @EqualsAndHashCode
    class TaggableImplWithHashCode extends TaggableImpl {
        String property
    }
}
