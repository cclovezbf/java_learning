package responsibility.handle;

/***
 * 定义具体处理类
 */
public class ConcreteHandlerC extends Handler {

    @Override
    public void handleRequest(Request request) {
        if (request.getRequestType().equals(RequestType.REQUEST_C)) {
            System.out.println("ConcreteHandlerC处理请求" +request.getName());
        } else if (successor != null) {
            successor.handleRequest(request);
        }
    }
}