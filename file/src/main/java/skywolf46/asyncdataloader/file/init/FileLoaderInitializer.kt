package skywolf46.asyncdataloader.file.init

import skywolf46.asyncdataloader.core.abstraction.AbstractDataLoaderInitializer
import skywolf46.asyncdataloader.file.provider.SafeInputProvider
import skywolf46.asyncdataloader.file.provider.SafeOutputProvider

class FileLoaderInitializer : AbstractDataLoaderInitializer(){
    override fun load() {
        println("AsyncDataLoader - File | Initializing...")
        println("AsyncDataLoader - File | Registering provider..")
        SafeInputProvider.register()
        SafeOutputProvider.register()

    }

}