package skywolf46.adl.abstraction;

import skywolf46.adl.AsyncDataLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public abstract class AbstractDataLoader {
    private AtomicInteger saveCountdown = new AtomicInteger(60);
    private final AtomicBoolean isLoaded = new AtomicBoolean(false);
    private List<Consumer<? extends AbstractDataLoader>> lds = new ArrayList<>();
    private AbstractDataTask task = null;
    private List<Runnable> rs = new ArrayList<>();

    public void load() {
        unload();
        loadFile();
        synchronized (isLoaded) {
            System.out.println("Queued");
            isLoaded.set(true);
            for (Runnable ra : rs)
                ra.run();
            rs.clear();
        }

    }

    public void unload() {
        isLoaded.set(false);
    }

    public final void queue(Runnable r) {
        synchronized (isLoaded) {
            if (!isLoaded.get()) {
                rs.add(r);
                return;
            }
        }
        r.run();
    }

    public boolean isLoaded() {
        return isLoaded.get();
    }

    protected abstract void loadFile();

    public abstract File getFile();

    public abstract AbstractDataSnapshot createSnapshot();

    public final void mark(Class<? extends AbstractDataTask> cls, int countdown) {
        if (task == null || !task.getClass().equals(cls)) {
            if (AsyncDataLoader.getTaskFactory(cls) == null) {
                return;
            }
            task = AsyncDataLoader.getTaskFactory(cls).createSaveTask(this);
        }
        saveCountdown.set(countdown);
    }

    public final void load(Class<? extends AbstractDataTask> cls) {
        AsyncDataLoader.getTaskFactory(cls).createLoadTask(this).load();
    }

    public AtomicInteger getSaveCountdown() {
        return saveCountdown;
    }
}
