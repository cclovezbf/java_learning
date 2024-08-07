package responsibility.validate;



abstract class SexHandler {
    public void handleRequest(ValidateType validateType,Person person) {
        if (validateType.equals(ValidateType.VALIDATE_SEX)){
            System.out.println("开始进行性别校验");
            String sex = person.getSex();
        }


    }
}
