package tarce.testnew.http;

/**
 * Created by Daniel.Xu on 2017/1/14.
 */
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tarce.testnew.Utils.MyLog;

public abstract class MyCallback<T> implements Callback<T> {
    @Override
    public abstract void onResponse(Call<T> call, Response<T> response);

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        MyLog.e("MyCallback",t.toString());
    }



}
