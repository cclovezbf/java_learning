package proxy.dynamic_proxy;

public interface DataQuery {
    String query(String queryKey);
    String queryAll(String queryKey);
}