package tarce.testnew.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import greendao.DaoSession;
import greendao.MenuListBeanDao;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import tarce.testnew.GreenDaoManager;
import tarce.testnew.IntentFactory;
import tarce.testnew.MyApplication;
import tarce.testnew.R;
import tarce.testnew.Utils.MyLog;
import tarce.testnew.greendaoBean.LoginResponseBean;
import tarce.testnew.greendaoBean.MenuListBean;
import tarce.testnew.http.MyCallback;
import tarce.testnew.http.OKHttpFactory;
import tarce.testnew.http.RetrofitClient;
import tarce.testnew.http.api.LoginApi;
import tarce.testnew.http.bean.requestBean.LoginDatabase;
import tarce.testnew.http.bean.requestBean.loginBean;
import tarce.testnew.http.bean.responseBean.GetMenuListResponse;
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
    private MenuListBeanDao menuListBeanDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        loginApi = RetrofitClient.getInstance(LoginActivity.this).create(LoginApi.class);
        daoSession = GreenDaoManager.getInstance().getmDaoSession();
        menuListBeanDao = daoSession.getMenuListBeanDao();
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
                                    daoSession.getLoginResponseBeanDao()
                                            .insertOrReplace(new LoginResponseBean(user_id, name, groupsBean.getName(), groupsBean.getId()));
                                }
                            });
                    getMenuList();
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

    public void getMenuList() {
        loginApi.getMenuList().enqueue(new MyCallback<GetMenuListResponse>() {
            @Override
            public void onResponse(Call<GetMenuListResponse> call, Response<GetMenuListResponse> response) {
                List<GetMenuListResponse.ResDataBean> res_data = response.body().getRes_data();
                if (res_data.size() > 0) {
                    int user_id = res_data.get(0).getUser_id();
                    MyApplication.userID = user_id ;
                    for (GetMenuListResponse.ResDataBean resDataBean :res_data) {
                        menuListBeanDao.insertOrReplace(new MenuListBean(resDataBean.getId(), resDataBean.getAction()
                                , resDataBean.getSequence(), resDataBean.getWeb_icon(), resDataBean.getName()
                                , user_id, resDataBean.isParent_id() ? -1 : 0));
                        if (resDataBean.getChildren().size() > 0) {
                            List<GetMenuListResponse.ChildBean> children = resDataBean.getChildren();
                            for (GetMenuListResponse.ChildBean children1 : children) {
                                String[] parent_id = children1.getParent_id();
                                String parentID = parent_id[0];
                                menuListBeanDao.insertOrReplace(
                                        new MenuListBean(children1.getId(), children1.getAction(),
                                                children1.getSequence(), children1.getWeb_icon()
                                                , children1.getName(),user_id, Integer.parseInt(parentID)));
                                if (children1.getChildren().size() > 0) {
                                    List<GetMenuListResponse.ChildBean> children3 = children1.getChildren();
                                    for (GetMenuListResponse.ChildBean childrenMinUnit : children3) {
                                        String[] parent_idMinUnit = childrenMinUnit.getParent_id();
                                        String parentMinUnitID = parent_idMinUnit[0];
                                        menuListBeanDao.insertOrReplace(
                                                new MenuListBean(childrenMinUnit.getId(), childrenMinUnit.getAction(),
                                                        childrenMinUnit.getSequence(), childrenMinUnit.getWeb_icon()
                                                        , childrenMinUnit.getName(), user_id, Integer.parseInt(parentMinUnitID)));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }
}
