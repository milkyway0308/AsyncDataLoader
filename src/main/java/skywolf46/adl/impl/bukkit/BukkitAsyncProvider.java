package skywolf46.adl.impl.bukkit;

import skywolf46.adl.abstraction.AbstractDataLoader;
import skywolf46.adl.abstraction.AbstractDataTask;
import skywolf46.adl.abstraction.AbstractDataTaskFactory;
import skywolf46.adl.impl.bukkit.task.BukkitDataTask;

public class BukkitAsyncProvider extends AbstractDataTaskFactory {
    @Override
    public AbstractDataTask createSaveTask(AbstractDataLoader loader) {
        return new BukkitDataTask(loader);
    }

    @Override
    public AbstractDataTask createLoadTask(AbstractDataLoader dl) {
        return new BukkitDataTask(dl);
    }
}
