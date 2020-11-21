package skywolf46.adl.impl.bukkit.abstraction;

import org.bukkit.configuration.file.YamlConfiguration;
import skywolf46.adl.abstraction.AbstractDataSnapshot;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public abstract class AbstractYamlDataSnapshot<X> extends AbstractDataStreamSnapshot<X> {
    public AbstractYamlDataSnapshot(X object) {
        super(object);
    }

    @Override
    public void write(DataOutputStream stream) throws IOException {
        YamlConfiguration conf = new YamlConfiguration();
        write(conf);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(stream, StandardCharsets.UTF_8));
        bw.append(conf.saveToString());
        bw.flush();
        bw.close();
    }

    public abstract void write(YamlConfiguration conf);
}
