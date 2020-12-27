package skywolf46.adl.interfaces;

public interface IDataQueueable {
    boolean isLoaded();

    void queue(Runnable r);
}
