package skywolf46.adl.impl.sql.abstraction;

import skywolf46.adl.abstraction.AbstractDataSnapshot;
import skywolf46.bss.api.SQLTable;

public abstract class AbstractSQLSnapshot<X extends AbstractSQLDataLoader> extends AbstractDataSnapshot<X> {
    public AbstractSQLSnapshot(X object) {
        super(object);
    }


    public abstract void write(SQLTable table);

}
