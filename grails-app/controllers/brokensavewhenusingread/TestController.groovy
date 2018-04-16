package brokensavewhenusingread

class TestController {

    def index() { }

    def testGet(final Long id) {
        Test test = Test.get(id)

        test.properties = [testType: "changed?"]

        test.save()
    }

    def testRead(final Long id) {
        Test test = Test.read(id)

        test.properties = [testType: "changed?"]

        test.save()
    }
}
