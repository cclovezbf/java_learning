package proxy.static_proxy.remote_proxy;

public class RemoteServiceImpl implements RemoteService {
    @Override
    public String fetchData(String dataId) {
        // 实际操作，例如从数据库获取数据
        return "Data from remote service: " + dataId;

    }
}
