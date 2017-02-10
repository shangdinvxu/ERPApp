package tarce.testnew.greendaoBean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Daniel.Xu on 2017/2/9.
 */
@Entity
public class SaveInventory {

    private int theoretical_qty;
    private int product_qty;
    private String image_medium;
    private int id;
    @Index(unique = true)
    private String product_name;
    private String area ;
    @Generated(hash = 2134795141)
    public SaveInventory(int theoretical_qty, int product_qty, String image_medium,
            int id, String product_name, String area) {
        this.theoretical_qty = theoretical_qty;
        this.product_qty = product_qty;
        this.image_medium = image_medium;
        this.id = id;
        this.product_name = product_name;
        this.area = area;
    }
    @Generated(hash = 1949803626)
    public SaveInventory() {
    }
    public int getTheoretical_qty() {
        return this.theoretical_qty;
    }
    public void setTheoretical_qty(int theoretical_qty) {
        this.theoretical_qty = theoretical_qty;
    }
    public int getProduct_qty() {
        return this.product_qty;
    }
    public void setProduct_qty(int product_qty) {
        this.product_qty = product_qty;
    }
    public String getImage_medium() {
        return this.image_medium;
    }
    public void setImage_medium(String image_medium) {
        this.image_medium = image_medium;
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getProduct_name() {
        return this.product_name;
    }
    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }
    public String getArea() {
        return this.area;
    }
    public void setArea(String area) {
        this.area = area;
    }
}
