package tarce.testnew.http.bean.requestBean;

/**
 * Created by Daniel.Xu on 2017/2/14.
 */

public class GetproductlistRequsetBean {
    public GetproductlistRequsetBean(int limit,  String product_type,int offset) {
        this.limit = limit;
        this.offset = offset;
        this.product_type = product_type;
    }

    private String product_type ;
    private int limit ;
    private int offset ;

}
