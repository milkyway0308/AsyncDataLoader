package skywolf46.asyncdataloader.mysql.abstraction

import skywolf46.asyncdataloader.mysql.util.SQLTable
import java.math.BigDecimal
import java.net.URL
import java.sql.*
import java.sql.Array

abstract class AbstractSQLPrepare(val table: SQLTable) {
    protected val lst = mutableListOf<PreparedStatement.(Int) -> Unit>()
    fun next(boolean: Boolean) {
        lst += {
            setBoolean(it, boolean)
        }
    }

    fun next(byte: Byte) {
        lst += {
            setByte(it, byte)
        }
    }

    fun next(short: Short) {
        lst += {
            setShort(it, short)
        }
    }


    fun next(int: Int) {
        lst += {
            setInt(it, int)
        }
    }

    fun next(long: Long) {
        lst += {
            setLong(it, long)
        }
    }

    fun next(double: Double) {
        lst += {
            setDouble(it, double)
        }
    }


    fun next(float: Float) {
        lst += {
            setFloat(it, float)
        }
    }


    fun next(str: String) {
        lst += {
            setString(it, str)
        }
    }

    fun next(arr: Array) {
        lst += {
            setArray(it, arr)
        }
    }


    fun next(arr: ByteArray) {
        lst += {
            setBytes(it, arr)
        }
    }


    fun next(xml: SQLXML) {
        lst += {
            setSQLXML(it, xml)
        }
    }


    fun next(blob: Blob) {
        lst += {
            setBlob(it, blob)
        }
    }


    fun next(decimal: BigDecimal) {
        lst += {
            setBigDecimal(it, decimal)
        }
    }


    fun next(date: Date) {
        lst += {
            setDate(it, date)
        }
    }


    fun next(clob: Clob) {
        lst += {
            setClob(it, clob)
        }
    }


    fun next(ref: Ref) {
        lst += {
            setRef(it, ref)
        }
    }


    fun next(time: Time) {
        lst += {
            setTime(it, time)
        }
    }


    fun next(timestamp: Timestamp) {
        lst += {
            setTimestamp(it, timestamp)
        }
    }


    fun next(url: URL) {
        lst += {
            setURL(it, url)
        }
    }

    open fun flush(): SQLTable {
        return table
    }
}

