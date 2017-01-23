package tarce.testnew.http;

import retrofit2.Call;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by Daniel.Xu on 2016/12/30.
 */

public class RequestFactory {
    private static Retrofit retrofit ;
    private  static RequestFactory requestFactory ;
     private RequestFactory(){
         retrofit = RetrofitClient.getInstance();
    }

    public static RequestFactory getInstance(){
        if (requestFactory==null){
            requestFactory=new RequestFactory();
        }
        return requestFactory ;
    }


/*    public Observable<User> getUserInformation(String user){
        Observable<User> userInformation = retrofit.create(GitHubService.class).getUserInformation(user);
        return userInformation ;
    }

    public Call<User> getUser(String user){
        Call<User> user1 = retrofit.create(userServer.class).getUser(user);
        return user1 ;
    }*/

}
