package com.igojeferson.geographic.distance.exception;

public class NotFoundException extends RuntimeException {

    private Object[] messageParams;

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Object[] messageParams) {
        super(message);
        this.messageParams = messageParams;
    }

    public Object[] getMessageParams() {
        return messageParams;
    }
}
