package responsibility.handle;

/***
 * 定义具体处理类
 */
public class ConcreteHandlerA extends Handler {

    @Override
    public void handleRequest(Request request) {
        if (request.getRequestType().equals(RequestType.REQUEST_A)) {
            System.out.println("ConcreteHandlerA处理请求" +request.getName());
        } else if (successor != null) {
            successor.handleRequest(request);
        }
    }
}