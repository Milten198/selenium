package util.objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasketItem {

    private String name;
    private String price;
    private String quantity;

    public void increaseQuantity (String numberToIncrease){
        int integerQuantity = Integer.parseInt(quantity);
        integerQuantity += Integer.parseInt(numberToIncrease);
        setQuantity(Integer.toString(integerQuantity));
    }
}
