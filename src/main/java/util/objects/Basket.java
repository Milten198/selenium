package util.objects;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class Basket {

    private List<BasketItem> basketItems;
    private int summaryQuantity;
    private BigDecimal summaryPrice;

    public Basket() {
        this.basketItems = new LinkedList<>();
        this.summaryPrice = BigDecimal.ZERO;
    }

    private boolean isProductAlreadyInBasket(BasketItem basketItem) {
        for(BasketItem item : basketItems) {
            if(item.getName().equals(basketItem.getName())) {
                return true;
            }
        }
        return false;
    }

    public void addItemToBasket(BasketItem basketItem) {
        if(isProductAlreadyInBasket(basketItem)) {
            getItemByName(basketItem.getName()).increaseQuantity(basketItem.getQuantity());
        }
        else {
            basketItems.add(basketItem);
        }
    }

    public void calculateSummaryQuantity() {
        for (BasketItem basketItem : basketItems) {
            summaryQuantity += Integer.parseInt(basketItem.getQuantity());
        }
    }

    public void calculateSummaryPrice() {
        for (BasketItem basketItem : basketItems) {
            summaryPrice = summaryPrice.add(new BigDecimal(basketItem.getPrice()).multiply(new BigDecimal(basketItem.getQuantity())));
        }
    }

    public BasketItem getItemByName(String productName) {
        for(BasketItem item : basketItems) {
            if(item.getName().equals(productName)) {
                return item;
            }
        }
        return null;
    }
}
