package basedomains;



public class OrderEvent {
    private String message;
    private String status;
    private OrderDto order;

    public OrderEvent() {
    }

    public OrderEvent(String message, String status, OrderDto order) {
        this.message = message;
        this.status = status;
        this.order = order;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOrder(OrderDto order) {
        this.order = order;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public OrderDto getOrder() {
        return order;
    }
}
