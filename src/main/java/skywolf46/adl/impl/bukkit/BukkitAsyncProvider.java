package skywolf46.adl.impl.bukkit;

import skywolf46.adl.abstraction.AbstractDataLoader;
import skywolf46.adl.abstraction.AbstractDataTask;
import skywolf46.adl.abstraction.AbstractDataTaskFactory;
import skywolf46.adl.impl.bukkit.task.BukkitDataTask;

public class BukkitAsyncProvider<X extends AbstractDataTask> extends AbstractDataTaskFactory<X> {
    @Override
    public X createSaveTask(AbstractDataLoader loader) {
        return (X) new BukkitDataTask(loader);
    }

    @Override
    public X  createLoadTask(AbstractDataLoader dl) {
        return (X) new BukkitDataTask(dl);
    }
}
