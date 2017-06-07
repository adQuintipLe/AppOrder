package systmorder.apporder;

/**
 * Created by mansoull on 1/6/2017.
 */

public class MenuList {

    private String menuMain;
    private String menuItem;
    private String menuPrice;
    private String menuImage;

    MenuList(){

    }

    public MenuList(String menuMain, String menuItem, String menuPrice, String menuImage) {
        this.menuMain = menuMain;
        this.menuItem = menuItem;
        this.menuPrice = menuPrice;
        this.menuImage = menuImage;
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

    public String getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(String menuImage) {
        this.menuImage = menuImage;
    }
}
