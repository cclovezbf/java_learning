package responsibility.validate;

public enum ValidateType {
    VALIDATE_SEX("A","性别校验"),
    VALIDATE_HEIGHT("B","身高校验"),
    VALIDATE_WEIGHT("C","体重校验");

    public final String code;
    public final String desc ;

    ValidateType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
