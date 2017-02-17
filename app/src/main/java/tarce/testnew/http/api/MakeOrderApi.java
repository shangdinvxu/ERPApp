package tarce.testnew.http.api;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import tarce.testnew.http.bean.requestBean.GetMrpProductionRequestBean;
import tarce.testnew.http.bean.responseBean.GetMrpProductionResponseBean;
import tarce.testnew.http.bean.responseBean.OrderDetailResponse;

/**
 * Created by Daniel.Xu on 2017/2/16.
 */

public interface MakeOrderApi {
    @POST("get_mrp_production")
    Call<GetMrpProductionResponseBean> getMrpProduction(@Body GetMrpProductionRequestBean requestBean);

    @POST("get_order_detail")
    Call<OrderDetailResponse> getOrderDetail(@Body HashMap hashMap);

    @POST("confirm_order")
    Call<OrderDetailResponse> confirmOrder(@Body HashMap hashMap);

    @POST("update_produce")
    Call<OrderDetailResponse> updateProduce(@Body HashMap hashMap);

    @POST("cancel_order")
    Call<OrderDetailResponse> cancelOrder(@Body HashMap hashMap);

    @POST("prepare_material_ing")
    Call<OrderDetailResponse> prepareMaterialing(@Body HashMap hashMap);

    @POST("finish_prepare_material")
    Call<OrderDetailResponse> finishPrepareMaterial(@Body HashMap hashMap);

    @POST("already_picking")
    Call<OrderDetailResponse> alreadyPicking(@Body HashMap hashMap);

    @POST("start_produce")
    Call<OrderDetailResponse> startProduce(@Body HashMap hashMap);

    @POST("over_picking")
    Call<OrderDetailResponse> overPicking(@Body HashMap hashMap);

    @POST("do_produce")
    Call<OrderDetailResponse> doProduce(@Body HashMap hashMap);

}
