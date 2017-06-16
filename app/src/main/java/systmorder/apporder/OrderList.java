package systmorder.apporder;

/**
 * Created by mansoull on 16/6/2017.
 */

public class OrderList {

    private String menuName;
    private String menuPrice;

    public OrderList(){

    }

    public OrderList(String menuName, String menuPrice) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
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
}
