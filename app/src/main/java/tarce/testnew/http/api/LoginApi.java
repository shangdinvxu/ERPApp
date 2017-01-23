package tarce.testnew.http.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;
import tarce.testnew.http.bean.requestBean.LoginDatabase;
import tarce.testnew.http.bean.responseBean.LoginResponse;
import tarce.testnew.http.bean.requestBean.loginBean;

/**
 * Created by Daniel.Xu on 2017/1/5.
 */

public interface LoginApi {
    @GET("get_db_list")
    Observable<LoginDatabase> getDatabase();

    @POST("login")
    Call<LoginResponse> toLogin(@Body loginBean bean);

    @GET("get_menu_list")
    Call<String> getMenuList();
}
