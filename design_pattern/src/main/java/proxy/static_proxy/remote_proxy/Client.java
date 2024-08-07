package proxy.static_proxy.remote_proxy;

public class Client {
    public static void main(String[] args) {
        RemoteService remoteServiceProxy = new
                RemoteServiceProxy("https://example.com/remote-service");
        // 使用远程代理访问远程服务
        String dataId = "example_data_id";
        String result = remoteServiceProxy.fetchData(dataId);
        System.out.println("Result: " + result);

    }
}
