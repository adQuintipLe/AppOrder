package systmorder.apporder;

/**
 * Created by mansoull on 1/6/2017.
 */

public class MenuList {

    private String menuMain;
    private String menuItem;

    MenuList(){

    }

    public MenuList(String menuMain, String menuItem) {
        this.menuMain = menuMain;
        this.menuItem = menuItem;
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
}
