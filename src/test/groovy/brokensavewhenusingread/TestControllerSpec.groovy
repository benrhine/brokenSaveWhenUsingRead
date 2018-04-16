package brokensavewhenusingread

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@Mock(Test)
@TestFor(TestController)
class TestControllerSpec extends Specification {

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
            controller.testGet(original.id)
        then:
            Test.get(original.id).testType == "changed?"
    }

    void "test object update when using read"() {
        when:
            controller.testRead(original.id)
        then:
            Test.get(original.id).testType == "changed?"
    }
}
