package xyz.lannt.truyencvcollector.application.client.truyencv;

public class TruyenCvClientException extends RuntimeException {

    private static final long serialVersionUID = -1105225383050156261L;

    public TruyenCvClientException(Throwable cause) {
        super(cause);
    }

    public TruyenCvClientException(String mess) {
        super(mess);
    }
}
