package tarce.testnew.http.bean.requestBean;

/**
 * Created by Daniel.Xu on 2017/1/14.
 */

public class GetMrpProductionRequestBean {
    private String state ;

    public GetMrpProductionRequestBean(int limit, int offset, String state) {
        this.limit = limit;
        this.offset = offset;
        this.state = state;
    }

    private int offset ;
    private int limit ;
}
