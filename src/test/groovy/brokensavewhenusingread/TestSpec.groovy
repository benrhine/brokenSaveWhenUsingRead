package brokensavewhenusingread

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Test)
class TestSpec extends Specification {

    private Test original

    def setup() {
        original = new Test(testType: 'type', testCode: 'code').save()
        assert original.id
        assert original.testType == 'type'
        assert original.testCode == 'code'
    }

    def cleanup() {
    }

    void "test object update when using get"() {
        when:
            Test returned = Test.get(original.id)
            returned.properties = [testType: "changed?"]
            returned.save()
        then:
            returned.testType == "changed?"
    }

    void "test object update when using read"() {
        when:
            Test returned = Test.read(original.id)
            returned.properties = [testType: "changed?"]
            returned.save()
        then:
            returned.testType == "changed?"
    }
}
