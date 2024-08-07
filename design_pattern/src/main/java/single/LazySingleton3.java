package single;

public class LazySingleton3 {
    private  static LazySingleton3 singleton;

    private LazySingleton3() {
    }

    public static LazySingleton3 getInstance() {
        if (singleton == null) {
            System.out.println("=null");
            synchronized (LazySingleton3.class) {
                if (singleton == null) {
                    singleton = new LazySingleton3();
                    System.out.println("new");
                }
            }
        }
        return singleton;
    }
}
