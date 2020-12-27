package skywolf46.adl.impl.sql;

import skywolf46.adl.abstraction.AbstractDataLoader;
import skywolf46.adl.abstraction.AbstractDataTaskFactory;
import skywolf46.adl.impl.sql.abstraction.AbstractSQLDataLoader;
import skywolf46.adl.impl.sql.abstraction.AbstractSQLSnapshot;
import skywolf46.adl.impl.sql.task.SQLDataTask;

public class SQLTaskProvider extends AbstractDataTaskFactory<SQLDataTask> {
    @Override
    public SQLDataTask createSaveTask(AbstractDataLoader loader) {
        if(!(loader instanceof AbstractSQLDataLoader)){
            throw new IllegalStateException("SQLTaskProvider only support extension of AbstractSQLDataLoader");
        }
        return new SQLDataTask((AbstractSQLDataLoader) loader);
    }

    @Override
    public SQLDataTask createLoadTask(AbstractDataLoader dl) {
        if(!(dl instanceof AbstractSQLDataLoader)){
            throw new IllegalStateException("SQLTaskProvider only support extension of AbstractSQLDataLoader");
        }
        return new SQLDataTask((AbstractSQLDataLoader) dl);
    }
}
