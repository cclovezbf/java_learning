package single;

public class LazySingleton4 {
    private volatile static LazySingleton4 singleton;

    private LazySingleton4() {
    }

    public static LazySingleton4 getInstance() {
        if (singleton == null) {
            synchronized (LazySingleton4.class) {
                if (singleton == null) {
                    singleton = new LazySingleton4();
                }
            }
        }
        return singleton;
    }
}
