package skywolf46.adl.impl.sql.abstraction;

import skywolf46.adl.abstraction.AbstractDataLoader;
import skywolf46.adl.impl.sql.SQLManagement;
import skywolf46.bss.api.SQLTable;

public abstract class AbstractSQLDataLoader extends AbstractDataLoader {
    private final String tableName;

    public AbstractSQLDataLoader(String tableName) {
        this.tableName = tableName;
    }


    public String getTableName() {
        return tableName;
    }


    @Override
    public abstract AbstractSQLSnapshot<?> createSnapshot();


    public abstract void loadData(SQLTable table);

    @Override
    public void load() {
        super.load();
        unload();
        loadData();
    }

    @Override
    protected void loadData() {
        SQLManagement.getSQLThread().addTask(connection -> {
            loadData(SQLTable.of(connection, tableName));
            setLoaded(true);
//            runQueued();
        });
    }

}
