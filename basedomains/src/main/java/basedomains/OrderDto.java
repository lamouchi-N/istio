package basedomains;


public class OrderDto {
    private Long customerId;
    private Long productId;
    private int quantity;

    public OrderDto(Long customerId) {
        this.customerId = customerId;
    }

    public OrderDto(Long customerId, Long productId, int quantity) {
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
