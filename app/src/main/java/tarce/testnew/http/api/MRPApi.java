package tarce.testnew.http.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import tarce.testnew.http.bean.requestBean.GetMrpProductionRequestBean;
import tarce.testnew.http.bean.responseBean.GetMrpProductionResponseBean;

/**
 * Created by Daniel.Xu on 2017/1/14.
 */

public interface MRPApi {
    @POST("get_mrp_production")
    Call<GetMrpProductionResponseBean> getMrpProduction(@Body GetMrpProductionRequestBean  requestBean);

}
