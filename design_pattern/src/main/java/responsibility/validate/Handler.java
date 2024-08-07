package responsibility.validate;



abstract class Handler {

    protected Handler nextHandler;

    public void setSuccessor(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleRequest(ValidateType validateType,Person person);
}
