package skywolf46.adl.impl.sql.task;

import org.bukkit.Bukkit;
import skywolf46.adl.AsyncDataLoader;
import skywolf46.adl.abstraction.AbstractDataTask;
import skywolf46.adl.impl.sql.SQLManagement;
import skywolf46.adl.impl.sql.abstraction.AbstractSQLDataLoader;
import skywolf46.adl.impl.sql.abstraction.AbstractSQLSnapshot;
import skywolf46.bss.api.SQLTable;
import skywolf46.bss.client.BukkitSQLSupport;

public class SQLDataTask extends AbstractDataTask implements Runnable {


    private final AbstractSQLDataLoader loader;
    private final int task;

    public SQLDataTask(AbstractSQLDataLoader snap) {
        this.loader = snap;
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(AsyncDataLoader.inst(), this, 1L, 1L);
    }

    @Override
    public void run() {
        if (loader.getSaveCountdown().decrementAndGet() <= 0) {
            save(null);
        }
    }

    @Override
    public void save(Runnable afterTask) {
        AbstractSQLSnapshot<?> snap = loader.createSnapshot();
        SQLManagement.getSQLThread().addTask(connection -> {
            snap.write(SQLTable.of(connection, loader.getTableName()));
            if (afterTask != null)
                afterTask.run();
        });
    }

    @Override
    public void saveSync() {
        loader.createSnapshot().write(SQLTable.of(BukkitSQLSupport.getSQL(), loader.getTableName()));
    }

    @Override
    public void load() {
        SQLManagement.getSQLThread().addTask(connection -> {
            loader.loadData(new SQLTable(connection, loader.getTableName()));
        });
    }

    @Override
    public void loadSync() {
        loader.loadData(new SQLTable(BukkitSQLSupport.getSQL(), loader.getTableName()));
    }

    @Override
    public void cleanUp() {
        removeTask();
        Bukkit.getScheduler().cancelTask(task);
    }
}
