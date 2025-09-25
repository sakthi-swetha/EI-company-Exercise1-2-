package creational.singleton;

import java.time.LocalDateTime;

public class Logger {
    private static Logger instance;
    private Logger() {}

    public static synchronized Logger getInstance() {
        if (instance == null) instance = new Logger();
        return instance;
    }

    public void info(String message) { log("INFO", message); }
    public void warn(String message) { log("WARN", message); }
    public void error(String message) { log("ERROR", message); }

    private void log(String level, String message) {
        System.out.println(LocalDateTime.now() + " [" + level + "] " + message);
    }
}
