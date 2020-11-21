package skywolf46.adl.impl.bukkit.abstraction;

import skywolf46.adl.abstraction.AbstractDataSnapshot;

import java.io.DataOutputStream;
import java.io.IOException;

public abstract class AbstractDataStreamSnapshot<X> extends AbstractDataSnapshot<X> {

    public AbstractDataStreamSnapshot(X object) {
        super(object);
    }

    public abstract void write(DataOutputStream dos) throws IOException;
}
