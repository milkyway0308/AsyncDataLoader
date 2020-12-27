package skywolf46.adl.abstraction;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDataTask {
    private static final List<AbstractDataTask> tasks = new ArrayList<>();

    public AbstractDataTask() {
        tasks.add(this);
    }

    protected final void removeTask() {
        tasks.remove(this);
    }

    public static void endTask() {
        new ArrayList<>(tasks).forEach(r -> {
            r.saveSync();
            r.cleanUp();
        });
        tasks.clear();
    }

    public abstract void save();

    public abstract void saveSync();

    public abstract void load();

    public abstract void loadSync();

    public abstract void cleanUp();
}
