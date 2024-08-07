package responsibility.handle;

public enum RequestType {
    REQUEST_A("A","第一个处理"),
    REQUEST_B("B","第二个处理"),
    REQUEST_C("C","第三个处理");

    public final String code;
    public final String desc ;

    RequestType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
