package skywolf46.adl.abstraction;

import skywolf46.adl.AsyncDataLoader;
import skywolf46.adl.interfaces.IDataQueueable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractDataLoader implements IDataQueueable {
    private AtomicInteger saveCountdown = new AtomicInteger(60);
    private final AtomicBoolean isLoaded = new AtomicBoolean(false);
    private AbstractDataTask task = null;
    private List<Runnable> rs = new ArrayList<>();

    public void load() {
        unload();
        loadData();
        synchronized (isLoaded) {
            isLoaded.set(true);
            for (Runnable ra : rs)
                ra.run();
            rs.clear();
        }
    }

    public void unload() {
        isLoaded.set(false);
    }

    protected synchronized void setLoaded(boolean isLoaded) {
        this.isLoaded.set(isLoaded);
        if(isLoaded)
            runQueued();
    }

    public final void queue(Runnable r) {
        synchronized (isLoaded) {
            if (!isLoaded()) {
                rs.add(r);
                return;
            }
        }
        r.run();
    }

    public void runQueued() {
        synchronized (isLoaded) {
            for (Runnable r : rs)
                r.run();
            rs.clear();
        }
    }

    public boolean isLoaded() {
        return isLoaded.get();
    }

    protected abstract void loadData();


    public abstract AbstractDataSnapshot<?> createSnapshot();

    public final void mark(Class<? extends AbstractDataTask> cls, int countdown) {
//        System.out.println("Marked!");
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
