package ee.opilane.webshop.controller.exceptions;

public class FailedException extends Throwable {

    public FailedException(String message) {
        super(message);
    }
}