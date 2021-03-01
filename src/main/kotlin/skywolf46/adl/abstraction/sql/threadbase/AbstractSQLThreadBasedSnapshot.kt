package skywolf46.adl.abstraction.sql.threadbase

import skywolf46.adl.abstraction.AbstractDataSnapshot
import skywolf46.bss.threads.SQLThread

abstract class AbstractSQLThreadBasedSnapshot(target: SQLThread) : AbstractDataSnapshot<SQLThread>(target) {

}