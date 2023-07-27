package hello.core.order;

public class Order {

    private Long memberId;
    private String itemNane;
    private int itemPrice;
    private int discountPrice;

    public Order(Long memberId, String itemNane, int itemPrice, int discountPrice) {
        this.memberId = memberId;
        this.itemNane = itemNane;
        this.itemPrice = itemPrice;
        this.discountPrice = discountPrice;
    }

    public int calculatePrice() {
        return itemPrice-discountPrice;
    }



    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getItemNane() {
        return itemNane;
    }

    public void setItemNane(String itemNane) {
        this.itemNane = itemNane;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }


    @Override
    public String toString() {
        return "Order{" +
                "memberId=" + memberId +
                ", itemNane='" + itemNane + '\'' +
                ", itemPrice=" + itemPrice +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
