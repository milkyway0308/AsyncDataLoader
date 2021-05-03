package skywolf46.asyncdataloader.core.abstraction

import skywolf46.asyncdataloader.core.abstraction.loader.AbstractDataLoader

abstract class SelfReferencedKeyBasedStorage<K, SELF : AbstractDataLoader<SELF>> :
    AbstractKeyBasedStorage<K, SELF, SELF>()