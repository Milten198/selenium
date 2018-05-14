package util.objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSearchItem {

    private String name;
    private String price;
    private String category;

    public ProductSearchItem(String name, String price, String category) {
        setName(name);
        setPrice(price);
        setCategory(category);
    }

}
