package tarce.testnew.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import greendao.DaoSession;
import retrofit2.Call;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import tarce.testnew.GreenDaoManager;
import tarce.testnew.IntentFactory;
import tarce.testnew.R;
import tarce.testnew.Utils.MyLog;
import tarce.testnew.greendaoBean.LoginResponseBean;
import tarce.testnew.http.MyCallback;
import tarce.testnew.http.RetrofitClient;
import tarce.testnew.http.api.LoginApi;
import tarce.testnew.http.bean.requestBean.LoginDatabase;
import tarce.testnew.http.bean.requestBean.loginBean;
import tarce.testnew.http.bean.responseBean.LoginResponse;

/**
 * Created by Daniel.Xu on 2017/1/5.
 */

public class LoginActivity extends Activity {
    @InjectView(R.id.database)
    Button database;
    @InjectView(R.id.email)
    EditText email;
    @InjectView(R.id.password)
    EditText password;
    @InjectView(R.id.to_login)
    Button toLogin;
    private String TAG = LoginActivity.class.getSimpleName();
    private int databaseSwitch = 0;
    private LoginApi loginApi;
    private DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        loginApi = RetrofitClient.getInstance().create(LoginApi.class);
        daoSession = GreenDaoManager.getInstance().getmDaoSession();
    }


    @OnClick(R.id.database)
    void setDatabase(View view) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("选择数据库");
        builder.setIcon(android.R.drawable.ic_menu_more);
        builder.setCancelable(true);
        Observable<LoginDatabase> database = loginApi.getDatabase();
        database.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Subscriber<LoginDatabase>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                MyLog.e(TAG, e.toString());

                            }

                            @Override
                            public void onNext(LoginDatabase loginDatabase) {
                                final List<String> res_data = loginDatabase.getRes_data();
                                final String[] databaseArr = res_data.toArray(new String[res_data.size()]);
                                builder.setSingleChoiceItems(databaseArr, databaseArr.length, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        databaseSwitch = i;
                                    }
                                });
                                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        LoginActivity.this.database.setText(res_data.get(databaseSwitch));
                                    }
                                });
                                AlertDialog dialog = builder.create();
                                dialog.setCanceledOnTouchOutside(true);
                                dialog.show();
                            }
                        }
            );
    }

    @OnClick(R.id.to_login)
    void toLogin(View view) {
        String chooseDB = database.getText().toString();
        String emailString = this.email.getText().toString();
        String passwordString = password.getText().toString();

        Call<LoginResponse> stringCall = loginApi.toLogin(new loginBean(emailString, passwordString, chooseDB));
        stringCall.enqueue(new MyCallback<LoginResponse>() {
            private Call<String> menuListCall;
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.body().getResult().getRes_code() == 1) {
                    final int user_id = response.body().getResult().getRes_data().getUser_id();
                    final String name = response.body().getResult().getRes_data().getName();
                    List<LoginResponse.ResultBean.ResDataBean.GroupsBean> groups = response.body().getResult().getRes_data().getGroups();
                    Observable.from(groups)
                            .subscribe(new Action1<LoginResponse.ResultBean.ResDataBean.GroupsBean>() {
                                @Override
                                public void call(LoginResponse.ResultBean.ResDataBean.GroupsBean groupsBean) {
                                    daoSession.insertOrReplace(new LoginResponseBean(user_id, name, groupsBean.getName(), groupsBean.getId()));
                                }
                            });
                    IntentFactory.start_MainActivity(LoginActivity.this);
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                super.onFailure(call, t);
                Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
            }
        });
    }



}
