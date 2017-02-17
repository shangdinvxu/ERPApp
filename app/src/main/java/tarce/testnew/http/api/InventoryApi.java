package tarce.testnew.http.api;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import tarce.testnew.http.bean.responseBean.GetGroupByListresponse;

/**
 * Created by Daniel.Xu on 2017/2/17.
 */

public interface InventoryApi {
    @POST("get_group_by_list")
    Call<GetGroupByListresponse> getGroupsByList(@Body HashMap hashMap);

    @POST("get_group_by_list")
    Call<GetGroupByListresponse> getGroupsByListSecond(@Body HashMap hashMap);
}
