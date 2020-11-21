package skywolf46.adl;

import org.bukkit.plugin.java.JavaPlugin;
import skywolf46.adl.abstraction.AbstractDataTask;
import skywolf46.adl.abstraction.AbstractDataTaskFactory;
import skywolf46.adl.impl.bukkit.BukkitAsyncProvider;
import skywolf46.adl.impl.bukkit.task.BukkitDataTask;

import java.util.HashMap;

public class AsyncDataLoader extends JavaPlugin {
    private static AsyncDataLoader inst;
    private static HashMap<Class<? extends AbstractDataTask>, AbstractDataTaskFactory> factory = new HashMap<>();

    public static AsyncDataLoader inst() {
        return inst;
    }

    public static AbstractDataTaskFactory getTaskFactory(Class<? extends AbstractDataTask> cls) {
        return factory.get(cls);
    }

    @Override
    public void onEnable() {
        inst = this;
        registerTaskFactory(BukkitDataTask.class, new BukkitAsyncProvider());
    }

    @Override
    public void onDisable() {
        AbstractDataTask.endTask();
    }

    public static void registerTaskFactory(Class<? extends AbstractDataTask> task, AbstractDataTaskFactory fact) {
        factory.put(task, fact);
    }
}
