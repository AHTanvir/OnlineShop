package anwar.onlineshop.Model;

/**
 * Created by anwar on 9/10/2017.
 */

public class ProductCategoryModel {
    String name;
    String total;

    public ProductCategoryModel(String name, String total) {
        this.name = name;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
