package skywolf46.asyncdataloader.core.init

import skywolf46.asyncdataloader.core.abstraction.AbstractDataLoaderInitializer

class TestInitializer : AbstractDataLoaderInitializer() {
    override fun load() {
        println("Loading.")
    }

}