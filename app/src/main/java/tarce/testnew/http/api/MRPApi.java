package tarce.testnew.http.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;
import tarce.testnew.http.bean.requestBean.GetMrpProductionRequestBean;
import tarce.testnew.http.bean.requestBean.GetStockInventoryRequestBean;
import tarce.testnew.http.bean.requestBean.findProductByConditionRequest;
import tarce.testnew.http.bean.responseBean.CreatInventoryResponse;
import tarce.testnew.http.bean.responseBean.GetAreaListResponse;
import tarce.testnew.http.bean.responseBean.GetMrpProductionResponseBean;
import tarce.testnew.http.bean.responseBean.GetStockInventoryDetailResponse;
import tarce.testnew.http.bean.responseBean.GetStockInventoryResponse;
import tarce.testnew.http.bean.responseBean.FindProductByConditionResponse;

/**
 * Created by Daniel.Xu on 2017/1/14.
 */

public interface MRPApi {
    @POST("get_mrp_production")
    Call<GetMrpProductionResponseBean> getMrpProduction(@Body GetMrpProductionRequestBean  requestBean);

    @POST("get_stock_inventory_list")
    Call<GetStockInventoryResponse> getStockInventoryList(@Body GetStockInventoryRequestBean getStockInventoryRequestBean);

    @POST("find_product_by_condition")
    Call<FindProductByConditionResponse> findProductByCondition(@Body findProductByConditionRequest findProductByConditionRequest);

    @POST("get_area_list")
    Observable<GetAreaListResponse> getAreaList(@Body HashMap map);

    @POST("get_stock_inventory_detail")
    Call<GetStockInventoryDetailResponse> getStockInventoryDetail(@Body HashMap map);

    @POST("create_stock_inventory")
    Call<CreatInventoryResponse> creatStockInventory(@Body GetStockInventoryDetailResponse.ResultBean.ResDataBean resDataBean);



}
