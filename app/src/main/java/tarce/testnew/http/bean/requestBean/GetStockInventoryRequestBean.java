package tarce.testnew.http.bean.requestBean;

/**
 * Created by Daniel.Xu on 2017/1/14.
 */

public class GetStockInventoryRequestBean {

    public GetStockInventoryRequestBean(int limit, int offset) {
        this.limit = limit;
        this.offset = offset;
    }

    private int offset ;
    private int limit ;
}
