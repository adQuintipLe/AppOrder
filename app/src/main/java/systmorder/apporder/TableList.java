package systmorder.apporder;

/**
 * Created by mansoull on 30/5/2017.
 */

public class TableList {

    private String tableNo;
    private String tblNo;
    private String orderID;
    private String viewOrderId;

    public  TableList(){

    }

    public TableList(String tableNo, String tblNo, String orderID, String viewOrderId) {
        this.tableNo = tableNo;
        this.tblNo = tblNo;
        this.orderID = orderID;
        this.viewOrderId = viewOrderId;
    }

    public String getTableNo() {
        return tableNo;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getTblNo() {
        return tblNo;
    }

    public void setTblNo(String tblNo) {
        this.tblNo = tblNo;
    }

    public String getViewOrderId() {
        return viewOrderId;
    }

    public void setViewOrderId(String viewOrderId) {
        this.viewOrderId = viewOrderId;
    }
}
