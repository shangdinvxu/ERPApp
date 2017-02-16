package tarce.testnew.http.bean.requestBean;

/**
 * Created by Daniel.Xu on 2017/2/15.
 */

public class GetStrockMoveRequsetBean {
    public GetStrockMoveRequsetBean(int limit, int offset, int product_id) {
        this.limit = limit;
        this.offset = offset;
        this.product_id = product_id;
    }

    private int   product_id ;
    private int   offset  ;
    private int   limit   ;
}
