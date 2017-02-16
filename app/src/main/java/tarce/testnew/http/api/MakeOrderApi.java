package tarce.testnew.http.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import tarce.testnew.http.bean.requestBean.GetMrpProductionRequestBean;
import tarce.testnew.http.bean.responseBean.GetMrpProductionResponseBean;

/**
 * Created by Daniel.Xu on 2017/2/16.
 */

public interface MakeOrderApi {
    @POST("get_mrp_production")
    Call<GetMrpProductionResponseBean> getMrpProduction(@Body GetMrpProductionRequestBean requestBean);
}
