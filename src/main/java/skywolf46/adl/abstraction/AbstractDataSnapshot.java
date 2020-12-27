package skywolf46.adl.abstraction;

import java.io.DataOutputStream;
import java.io.IOException;

public abstract class AbstractDataSnapshot<X> {

    private X obj;

    public AbstractDataSnapshot(X object) {
        this.obj = object;
    }

}
