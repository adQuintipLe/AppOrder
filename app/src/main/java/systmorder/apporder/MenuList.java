package systmorder.apporder;

/**
 * Created by mansoull on 1/6/2017.
 */

public class MenuList {

    private String menuMain;
    private String menuItem;
    private String menuPrice;

    MenuList(){

    }

    public MenuList(String menuMain, String menuItem, String menuPrice) {
        this.menuMain = menuMain;
        this.menuItem = menuItem;
        this.menuPrice = menuPrice;
    }

    public String getMenuMain() {
        return menuMain;
    }

    public void setMenuMain(String menuMain) {
        this.menuMain = menuMain;
    }

    public String getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(String menuItem) {
        this.menuItem = menuItem;
    }

    public String getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(String menuPrice) {
        this.menuPrice = menuPrice;
    }
}
