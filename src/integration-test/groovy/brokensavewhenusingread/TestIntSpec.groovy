package brokensavewhenusingread

import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import spock.lang.Specification

@Integration
@Rollback
class TestIntSpec extends Specification {

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
