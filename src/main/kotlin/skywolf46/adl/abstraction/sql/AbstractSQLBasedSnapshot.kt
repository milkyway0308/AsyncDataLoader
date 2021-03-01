package skywolf46.adl.abstraction.sql

import skywolf46.adl.abstraction.AbstractDataSnapshot
import java.sql.Connection

abstract class AbstractSQLBasedSnapshot(target: Connection) : AbstractDataSnapshot<Connection>(target) {

}