package pl.grizzlysoftware.chlorek.core.mapper

import pl.grizzlysoftware.chlorek.core.model.Invoice
import pl.grizzlysoftware.chlorek.core.model.InvoiceItem
import pl.grizzlysoftware.chlorek.core.model.Stockup
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDate

/**
 * Created by Bartosz Pawłowski on 03/10/2020.
 */
class CanonicalStockupToCanonicalInvoiceMapperTest extends Specification {
    def "returns null for given null argument"() {
        given:
            def input = null
            def m = new CanonicalStockupToCanonicalInvoiceMapper()
        when:
            def output = m.apply(input)
        then:
            output == null
    }

    def "returns empty invoice if stockup.invoice is null"() {
        given:
            def input = new Stockup()
            def m = new CanonicalStockupToCanonicalInvoiceMapper()
        when:
            def output = m.apply(input)
        then:
            output != null
    }

    def "maps stockup.invoice.number to number properly"() {
        given:
            def input = new Stockup()
            input.invoice = new Invoice()
            input.invoice.number = "131/FXGH/123"
            def m = new CanonicalStockupToCanonicalInvoiceMapper()
        when:
            def output = m.apply(input)
        then:
            output.number == input.invoice.number
    }

    def "maps stockup.invoice.issuedAt to issuedAt properly"() {
        given:
            def input = new Stockup()
            input.invoice = new Invoice()
            input.invoice.issuedAt = LocalDate.of(2020, 03, 16)
            def m = new CanonicalStockupToCanonicalInvoiceMapper()
        when:
            def output = m.apply(input)
        then:
            output.issuedAt == input.invoice.issuedAt
    }

    @Unroll
    def "maps stockup.invoice.isPaid to isPaid properly"(isPaid) {
        given:
            def input = new Stockup()
            input.invoice = new Invoice()
            input.invoice.isPaid = isPaid
            def m = new CanonicalStockupToCanonicalInvoiceMapper()
        when:
            def output = m.apply(input)
        then:
            output.isPaid == input.invoice.isPaid
        where:
            isPaid << [true, false]
    }

    def "maps stockup.invoice.issuedAt to paymentDueTo when stockup.invoice.isPaid is true and stockup.invoice.paymentDueTo is null"() {
        given:
            def input = new Stockup()
            input.invoice = new Invoice()
            input.invoice.issuedAt = LocalDate.of(2020, 03, 16)
            input.invoice.isPaid = true
            def m = new CanonicalStockupToCanonicalInvoiceMapper()
        when:
            def output = m.apply(input)
        then:
            output.paymentDueTo == input.invoice.issuedAt
    }

    def "maps stockup.invoice.paymentDueTo to paymentDueTo if stockup.invoice.isPaid is false or stockup.invoice.paymentDueTo is not null"(isPaid, paymentDueTo) {
        given:
            def input = new Stockup()
            input.invoice = new Invoice()
            input.invoice.paymentDueTo = paymentDueTo
            input.invoice.isPaid = isPaid
            def m = new CanonicalStockupToCanonicalInvoiceMapper()
        when:
            def output = m.apply(input)
        then:
            output.paymentDueTo == input.invoice.paymentDueTo
        where:
            isPaid | paymentDueTo
            false  | LocalDate.of(2020, 03, 16)
            true   | null

    }

    @Unroll
    def "maps stockup.invoice.grossValue to grossValue properly"() {
        given:
            def input = new Stockup()
            input.invoice = new Invoice()
            input.invoice.grossValue = 2560.91
            def m = new CanonicalStockupToCanonicalInvoiceMapper()
        when:
            def output = m.apply(input)
        then:
            output.grossValue == input.invoice.grossValue
    }

    @Unroll
    def "maps stockup.invoice.supplierId to supplierId properly"() {
        given:
            def input = new Stockup()
            input.invoice = new Invoice()
            input.invoice.supplierId = 99999999L
            def m = new CanonicalStockupToCanonicalInvoiceMapper()
        when:
            def output = m.apply(input)
        then:
            output.supplierId == input.invoice.supplierId
    }


    def "maps sum of stockup.invoice.items[n].netPurchaseTotalPrice to netValue properly"() {
        given:
            def input = new Stockup()
            input.invoice = new Invoice()
            input.invoice.items = [
                    invoiceItem(2000),
                    invoiceItem(3000),
                    invoiceItem(4000)
            ]
            def m = new CanonicalStockupToCanonicalInvoiceMapper()
        when:
            def output = m.apply(input)
        then:
            output.netValue == 9000.00
    }

    def "maps sum of stockup.invoice.items[n].netPurchaseTotalPrice to netValue with 2 digit precision"() {
        given:
            def input = new Stockup()
            input.invoice = new Invoice()
            input.invoice.items = [
                    invoiceItem(2000.100000001),
                    invoiceItem(3000.2),
                    invoiceItem(4000.391)
            ]
            def m = new CanonicalStockupToCanonicalInvoiceMapper()
        when:
            def output = m.apply(input)
        then:
            output.netValue == 9000.69
    }

    def invoiceItem(netPurchaseTotalPrice) {
        def output = new InvoiceItem()
        output.netPurchaseTotalPrice = netPurchaseTotalPrice
        return output
    }
}
