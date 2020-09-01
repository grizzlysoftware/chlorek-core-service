package pl.grizzlysoftware.chlorek.core.applier

import pl.grizzlysoftware.chlorek.core.model.Tag
import pl.grizzlysoftware.chlorek.core.model.TaggableImpl
import spock.lang.Specification

import java.time.ZoneId
import java.time.ZonedDateTime

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME
import static pl.grizzlysoftware.chlorek.core.model.TagRegistry.UPDATED_AT_TAG
import static pl.grizzlysoftware.commons.TimeUtils.nowUtc

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
class UpdatedAtTagApplierTest extends Specification {
    def "it should add new UPDATED_AT_TAG"() {
        given:
            def input = new TaggableImpl()
            def m = new UpdatedAtTagApplier()
        when:
            input.hasTag(UPDATED_AT_TAG) == false
            m.accept(input)
        then:
            input.hasTag(UPDATED_AT_TAG) == true
    }

    def "it should replace existing UPDATED_AT_TAG after updating"() {
        given:
            def inputTimestamp = ZonedDateTime.of(2020, 03, 16, 21, 31, 00, 00, ZoneId.of("UTC"))
            def inputTag = new Tag(UPDATED_AT_TAG, ISO_DATE_TIME.format(inputTimestamp))
            def input = new TaggableImpl()
            input.addTag(inputTag)
            def m = new UpdatedAtTagApplier()
        when:
            input.hasTag(UPDATED_AT_TAG) == true
            m.accept(input)
        then:
            input.hasTag(UPDATED_AT_TAG) == true
        when:
            def outputTag = input.getTag(UPDATED_AT_TAG)
        then:
            inputTag.value != outputTag.value
    }

    def "it should replace existing UPDATED_AT_TAG after updating with NEWER timestamp"() {
        given:
            def inputTimestamp = ZonedDateTime.of(2020, 03, 16, 21, 31, 00, 00, ZoneId.of("UTC"))
            def inputTag = new Tag(UPDATED_AT_TAG, ISO_DATE_TIME.format(inputTimestamp))
            def input = new TaggableImpl()
            input.addTag(inputTag)
            def m = new UpdatedAtTagApplier()
        when:
            input.hasTag(UPDATED_AT_TAG) == true
            m.accept(input)
            def outputTag = input.getTag(UPDATED_AT_TAG)
            def outputTimestamp = ZonedDateTime.parse(outputTag.value)
        then:
            outputTimestamp.isAfter(inputTimestamp)
            outputTimestamp.isBefore(nowUtc()) || outputTimestamp.isEqual(nowU)
    }

    def "it should create UPDATED_AT_TAG with current timestamp"() {
        given:
            def input = new TaggableImpl()
            def m = new UpdatedAtTagApplier()
        when:
            m.accept(input)
            def expectedTimestamp = nowUtc()
            def outputTimestamp = ZonedDateTime.parse(input.getTag(UPDATED_AT_TAG).value)
        then:
            outputTimestamp.isBefore(expectedTimestamp) || outputTimestamp.isEqual(expectedTimestamp)
    }

    def "it should create a tag with UTC timestamp"() {
        given:
            def input = new TaggableImpl()
            def m = new UpdatedAtTagApplier()
        when:
            m.accept(input)
            def outputTag = input.getTag(UPDATED_AT_TAG)
            def outputTimestamp = ZonedDateTime.parse(outputTag.value)
        then:
            outputTimestamp.getZone().getId() == "UTC"
    }
}
