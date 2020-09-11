package dev.orf1.worldmanipulator;

import org.bukkit.plugin.java.JavaPlugin;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public final class Main extends JavaPlugin {

    private final static Unsafe UNSAFE;
    private final static Field theUnsafe;

    static {
        try {
            theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            UNSAFE = (Unsafe) theUnsafe.get(null);
        } catch (Throwable reason) {
            throw new RuntimeException(reason);
        }
    }

    public static Unsafe getUnsafe() {
        Class cc = sun.reflect.Reflection.getCallerClass(2);
        if (cc.getClassLoader() != null)
            throw new SecurityException("Unsafe");
        return UNSAFE;
    }

    @Override
    public void onEnable() {
        registerCommands();
    }

    public void registerCommands() {
        getCommand("create").setExecutor(new CreateCommand());
        getCommand("reloadworld").setExecutor(new ReloadWorldCommand());
    }


}