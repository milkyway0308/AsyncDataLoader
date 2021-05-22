package skywolf46.asyncdataloader.core.abstraction.enums

enum class LoadState(val isLoadedState: Boolean) {
    READY(false), LOADED(true), EMPTY_LOAD(true), LOAD_FAIL(false);
}