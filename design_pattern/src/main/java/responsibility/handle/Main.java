package responsibility.handle;

public class Main {
    public static void main(String[] args) {
        Handler handlerA = new ConcreteHandlerA();
        Handler handlerB = new ConcreteHandlerB();
        Handler handlerC = new ConcreteHandlerC();

        handlerA.setSuccessor(handlerB);
        handlerB.setSuccessor(handlerC);

        Request request1 = new Request("已处理 1", RequestType.REQUEST_A);
        Request request2 = new Request("已处理 2", RequestType.REQUEST_B);
        Request request3 = new Request("已处理 3", RequestType.REQUEST_C);

        handlerA.handleRequest(request1);
        handlerA.handleRequest(request2);
        handlerA.handleRequest(request3);
    }
}
