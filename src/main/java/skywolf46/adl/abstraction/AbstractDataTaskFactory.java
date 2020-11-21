package skywolf46.adl.abstraction;

public abstract class AbstractDataTaskFactory<X extends AbstractDataTask> {
    public abstract X createSaveTask(AbstractDataLoader loader);

    public abstract X createLoadTask(AbstractDataLoader dl);
}
