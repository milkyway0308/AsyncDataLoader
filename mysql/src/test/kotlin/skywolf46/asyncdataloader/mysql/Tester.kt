package skywolf46.asyncdataloader.mysql

import org.junit.jupiter.api.Test
import skywolf46.asyncdataloader.mysql.test.impl.TestTripleVector

class Tester {

    @Test
    fun tableCreationTest() {
        println(TestTripleVector().toSQLTableString("test"))
    }


    @Test
    fun compareEqualStringTest() {
        println(TestTripleVector().toSQLEqualString("test"))
    }

}