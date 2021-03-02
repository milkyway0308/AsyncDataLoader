package skywolf46.adl.abstraction.sql.threadbase

import skywolf46.adl.abstraction.AbstractDataLoader
import skywolf46.adl.abstraction.sql.AbstractSQLBasedSnapshot
import skywolf46.bss.threads.SQLThread
import java.sql.Connection

abstract class AbstractSQLThreadBasedDataLoader : AbstractDataLoader<SQLThread, AbstractSQLThreadBasedSnapshot>() {


}