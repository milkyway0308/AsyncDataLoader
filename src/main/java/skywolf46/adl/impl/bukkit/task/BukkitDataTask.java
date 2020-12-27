package skywolf46.adl.impl.bukkit.task;

import org.bukkit.Bukkit;
import skywolf46.adl.AsyncDataLoader;
import skywolf46.adl.abstraction.AbstractDataLoader;
import skywolf46.adl.abstraction.AbstractDataSnapshot;
import skywolf46.adl.abstraction.AbstractDataTask;
import skywolf46.adl.abstraction.AbstractFileBasedDataLoader;
import skywolf46.adl.impl.bukkit.abstraction.AbstractDataStreamSnapshot;

import java.io.*;

public class BukkitDataTask extends AbstractDataTask implements Runnable {
    private final AbstractFileBasedDataLoader adl;
    private final int task;

    public BukkitDataTask(AbstractDataLoader adl) {
        if (!(adl instanceof AbstractFileBasedDataLoader)) {
            throw new IllegalStateException("BukkitDataTask only supports snapshot extension of AbstractFileBasedDataLoader");
        }
        this.adl = (AbstractFileBasedDataLoader) adl;
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(AsyncDataLoader.inst(), this, 20L, 20L);
    }

    @Override
    public void run() {
//        System.out.println("Countdown: " + adl.getSaveCountdown());
        if (adl.getSaveCountdown().decrementAndGet() <= 0) {
            save(null);
        }
    }


    @Override
    public void save(Runnable afterTask) {
        File file = adl.getFile();
        AbstractDataSnapshot<?> snap = adl.createSnapshot();
        try {
            Bukkit.getScheduler().runTaskAsynchronously(AsyncDataLoader.inst(), () -> {
                saveSnapshot(file, snap);
                if (afterTask != null)
                    afterTask.run();
            });
        } catch (Exception ex) {
            saveSync();
        }
        cleanUp();
    }

    @Override
    public void saveSync() {
        saveSnapshot(adl.getFile(), adl.createSnapshot());
    }

    @Override
    public void load() {
        Bukkit.getScheduler().runTaskAsynchronously(AsyncDataLoader.inst(), adl::load);
    }

    @Override
    public void loadSync() {
        adl.load();
    }

    @Override
    public void cleanUp() {
        removeTask();
        Bukkit.getScheduler().cancelTask(task);
    }

    private void saveSnapshot(File file, AbstractDataSnapshot<?> snap) {
        if (!(snap instanceof AbstractDataStreamSnapshot)) {
            throw new IllegalStateException("Error: BukkitDataTask only supports extension of AbstractDataStreamSnapshot");
        }
        if (!file.exists()) {
            if (file.getParentFile() != null)
                file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(file, false));
            ((AbstractDataStreamSnapshot<?>) snap).write(dos);
            dos.flush();
            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
