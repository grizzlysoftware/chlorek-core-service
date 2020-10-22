package pl.grizzlysoftware.chlorek.core.mapper

import pl.grizzlysoftware.chlorek.core.applier.HashTagApplier
import pl.grizzlysoftware.chlorek.core.applier.UpdatedAtTagApplier
import pl.grizzlysoftware.chlorek.core.model.Product
import pl.grizzlysoftware.chlorek.core.service.ProductService
import pl.grizzlysoftware.chlorek.core.util.ProductUpdater
import spock.lang.Specification

class ProductUpdaterTest extends Specification {
    def "throws NullPointerException when given arg is null"() {
        when:
            new ProductUpdater(null, 0)
        then:
            thrown(NullPointerException)
        when:
            new ProductUpdater(null)
        then:
            thrown(NullPointerException)
    }

    def "invokes updateProduct on ProductService"() {
        given:
            def m = new ProductUpdater()
            m.productService = Mock(ProductService)
        when:
            m.accept(new Product())
        then:
            1 * m.productService.updateProduct(_)
    }

    def "invokes UpdatedAtTagApplier"() {
        given:
            def m = new ProductUpdater()
            m.updatedAtTagApplier = Mock(UpdatedAtTagApplier)
        when:
            m.accept(new Product())
        then:
            1 * m.updatedAtTagApplier.accept(_)
    }

    def "invokes HashTagApplier"() {
        given:
            def m = new ProductUpdater()
            m.hashTagApplier = Mock(HashTagApplier)
        when:
            m.accept(new Product())
        then:
            1 * m.hashTagApplier.accept(_)
    }

    def "invokes UpdatedAtTagApplier before updateProduct on ProductService"() {
        given:
            def m = new ProductUpdater()
            m.productService = Mock(ProductService)
            m.updatedAtTagApplier = Mock(UpdatedAtTagApplier)
        when:
            m.accept(new Product())
        then:
            1 * m.updatedAtTagApplier.accept(_)
        then:
            1 * m.productService.updateProduct(_)
    }

    def "invokes HashTagApplier before updateProduct on ProductService"() {
        given:
            def m = new ProductUpdater()
            m.productService = Mock(ProductService)
            m.hashTagApplier = Mock(HashTagApplier)
        when:
            m.accept(new Product())
        then:
            1 * m.hashTagApplier.accept(_)
        then:
            1 * m.productService.updateProduct(_)
    }

    def "consumes thrown exceptions during execution"() {
        given:
            def m = new ProductUpdater()
            m.productService = Mock(ProductService) {
                updateProduct(_) >> {
                    throw new RuntimeException()
                }
            }
        when:
            m.accept(new Product())
        then:
            noExceptionThrown()
    }
}
