package org.vaadin.rise.place.deprecated.token;

public final class TokenFormatException extends RuntimeException {
    private static final long serialVersionUID = 3707135366220900675L;

    public TokenFormatException() {
    }

    public TokenFormatException(String message) {
        super(message);
    }

    public TokenFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenFormatException(Throwable cause) {
        super(cause);
    }

}
