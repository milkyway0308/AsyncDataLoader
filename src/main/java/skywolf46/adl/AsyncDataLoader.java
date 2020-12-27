package skywolf46.adl;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import skywolf46.adl.abstraction.AbstractDataTask;
import skywolf46.adl.abstraction.AbstractDataTaskFactory;
import skywolf46.adl.impl.bukkit.BukkitAsyncProvider;
import skywolf46.adl.impl.bukkit.task.BukkitDataTask;
import skywolf46.adl.impl.sql.SQLTaskProvider;
import skywolf46.adl.impl.sql.task.SQLDataTask;

import java.util.HashMap;

public class AsyncDataLoader extends JavaPlugin {
    private static AsyncDataLoader inst;
    private static HashMap<Class<? extends AbstractDataTask>, AbstractDataTaskFactory<?>> factory = new HashMap<>();

    public static AsyncDataLoader inst() {
        return inst;
    }

    public static <X extends AbstractDataTask> AbstractDataTaskFactory<X> getTaskFactory(Class<X> cls) {
        return (AbstractDataTaskFactory<X>) factory.get(cls);
    }

    @Override
    public void onEnable() {
        inst = this;
        registerTaskFactory(BukkitDataTask.class, new BukkitAsyncProvider<>());
        if (Bukkit.getPluginManager().getPlugin("BukkitSQLSupport") != null)
            registerTaskFactory(SQLDataTask.class, new SQLTaskProvider());
    }

    @Override
    public void onDisable() {
        AbstractDataTask.endTask();
    }

    public static <X extends AbstractDataTask> void registerTaskFactory(Class<X> task, AbstractDataTaskFactory<X> fact) {
        factory.put(task, fact);
    }
}
