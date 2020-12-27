package skywolf46.adl.impl.sql;

import skywolf46.bss.client.BukkitSQLSupport;
import skywolf46.bss.threads.SQLThread;

import java.sql.SQLException;

public class SQLManagement {
    private static SQLThread thread;

    static {
        try {
            thread = BukkitSQLSupport.createThread(4);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static SQLThread getSQLThread() {
        return thread;
    }
}
