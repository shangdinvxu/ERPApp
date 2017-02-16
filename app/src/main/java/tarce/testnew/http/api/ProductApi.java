package tarce.testnew.http.api;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import tarce.testnew.http.bean.requestBean.GetStrockMoveRequsetBean;
import tarce.testnew.http.bean.requestBean.GetproductlistRequsetBean;
import tarce.testnew.http.bean.responseBean.GetProductlistResponseBean;
import tarce.testnew.http.bean.responseBean.GetStockMoveResponseBean;

/**
 * Created by Daniel.Xu on 2017/2/14.
 */

public interface ProductApi {
    //产品列表
    @POST("get_product_list")
    Call<GetProductlistResponseBean> getProductList(@Body GetproductlistRequsetBean getproductlistRequsetBean);

    @POST("get_product_list")
    Call<GetProductlistResponseBean> getProductByMap(@Body HashMap map);

    @POST("get_stock_moves_by_product_id")
    Call<GetStockMoveResponseBean> getStockMove(@Body GetStrockMoveRequsetBean getStrockMoveRequsetBean);

}
