package skywolf46.asyncdataloader.core.test

import org.junit.jupiter.api.Test
import skywolf46.asyncdataloader.core.init.DataLoaderInitializer

class InitializationTest {
    @Test
    fun checkInitialize(){
        DataLoaderInitializer.initialize()
    }
}