package responsibility.handle;

/***
 * 定义具体处理类
 */
public class ConcreteHandlerB extends Handler {

    @Override
    public void handleRequest(Request request) {
        if (request.getRequestType().equals(RequestType.REQUEST_B)) {
            System.out.println("ConcreteHandlerB处理请求" +request.getName());
        } else if (successor != null) {
            successor.handleRequest(request);
        }
    }
}