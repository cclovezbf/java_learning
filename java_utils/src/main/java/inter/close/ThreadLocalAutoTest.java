package inter.close;

public class ThreadLocalAutoTest implements  AutoCloseable{
    private ThreadLocal<String> ctx=new ThreadLocal<>();

    public void setCtx(String value){
        ctx.set(value);
    }
    public String getCtx(){
        return ctx.get();
    }

    @Override
    public void close() throws Exception {
        System.out.println("remove");
        ctx.remove();
    }

    public static void main(String[] args) {
        String ctx1;
        try (ThreadLocalAutoTest threadLocalAutoTest = new ThreadLocalAutoTest()) {
            threadLocalAutoTest.setCtx("aaaa");
            ctx1 = threadLocalAutoTest.getCtx();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(ctx1);
    }
}
