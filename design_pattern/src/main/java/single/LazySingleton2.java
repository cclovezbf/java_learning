package single;

public class LazySingleton2 {
    private static LazySingleton2 instance;

    private LazySingleton2() {
    }

    public synchronized static LazySingleton2 getInstance() {
        if (instance == null) {
            instance = new LazySingleton2();
        }
        return instance;
    }
}
