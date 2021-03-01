package skywolf46.adl.abstraction.sql

import skywolf46.adl.abstraction.AbstractDataLoader
import java.sql.Connection

abstract class AbstractSQLBasedDataLoader : AbstractDataLoader<Connection, AbstractSQLBasedSnapshot>() {


}