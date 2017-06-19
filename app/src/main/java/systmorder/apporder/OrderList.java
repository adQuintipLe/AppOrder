package systmorder.apporder;

/**
 * Created by mansoull on 16/6/2017.
 */

public class OrderList {

    private String menuName;
    private String menuPrice;
    private String menuQuantity;

    public OrderList(){

    }

    public OrderList(String menuName, String menuPrice, String menuQuantity) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuQuantity = menuQuantity;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(String menuPrice) {
        this.menuPrice = menuPrice;
    }

    public String getMenuQuantity() {
        return menuQuantity;
    }

    public void setMenuQuantity(String menuQuantity) {
        this.menuQuantity = menuQuantity;
    }
}
