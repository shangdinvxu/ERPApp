package tarce.testnew.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.hugeterry.updatefun.UpdateFunGO;
import cn.hugeterry.updatefun.config.UpdateKey;
import greendao.DaoSession;
import greendao.MenuListBeanDao;
import retrofit2.Call;
import retrofit2.Callback;
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
import tarce.testnew.ViewUtil.SharePreferenceUtils;
import tarce.testnew.greendao.GreendaoUtils.UserLoginUtils;
import tarce.testnew.greendao.greendaoBeans.LoginResponseBean;
import tarce.testnew.greendao.greendaoBeans.MenuListBean;
import tarce.testnew.greendao.greendaoBeans.UserLogin;
import tarce.testnew.http.MyCallback;
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
    AutoCompleteTextView email;
    @InjectView(R.id.password)
    EditText password;
    @InjectView(R.id.to_login)
    Button toLogin;
    @InjectView(R.id.httpUrl)
    EditText httpUrl;
    private String TAG = LoginActivity.class.getSimpleName();
    private int databaseSwitch = 0;
    private LoginApi loginApi;
    private DaoSession daoSession;
    private MenuListBeanDao menuListBeanDao;
    private ProgressDialog progressDialog;
    private List<UserLogin> userLogins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        daoSession = GreenDaoManager.getInstance().getmDaoSession();
        menuListBeanDao = daoSession.getMenuListBeanDao();
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("loading");
        checkOutoLogin();
        initEmailAdapter();
        initListener();

    }



    private void initListener() {
        email.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String password = userLogins.get(i).getPassword();
                LoginActivity.this.password.setText(password);
            }
        });
    }

    private void initEmailAdapter() {
        email.setThreshold(1);
        userLogins = new UserLoginUtils().searchAll();
        ArrayList<String> strings = new ArrayList<>();
        if (userLogins !=null&& userLogins.size()>0){
            for (UserLogin s : userLogins){
                strings.add(s.getUserName());
            }
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(LoginActivity.this, R.layout.auto_text_listview, strings);
        email.setAdapter(stringArrayAdapter);
        }
    }





    private void checkOutoLogin() {
        int user_id = SharePreferenceUtils.getInt("user_id", -1000, LoginActivity.this);
        if (user_id != -1000) {
            String url = SharePreferenceUtils.getString("url", "null", LoginActivity.this);
            LoginActivity.this.httpUrl.setText(url);
            MyApplication.Url = url ;
            loginApi = RetrofitClient.getInstance(LoginActivity.this).create(LoginApi.class);
            String database = SharePreferenceUtils.getString("database", "null", LoginActivity.this);
            LoginActivity.this.database.setText(database);
            String email = SharePreferenceUtils.getString("email", "error", LoginActivity.this);
            LoginActivity.this.email.setText(email);
            String password = SharePreferenceUtils.getString("password", "error", LoginActivity.this);
            LoginActivity.this.password.setText(password);
            toLogin(new View(LoginActivity.this));
        }
    }


    @OnClick(R.id.database)
    void setDatabase(View view) {
        String URL = httpUrl.getText().toString();
        MyApplication.Url = URL ;
        loginApi = RetrofitClient.getInstance(LoginActivity.this).create(LoginApi.class);
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
                                        SharePreferenceUtils.putString("database", res_data.get(databaseSwitch), LoginActivity.this);
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
        progressDialog.show();
        String chooseDB = database.getText().toString();
        final String emailString = this.email.getText().toString();
        final String passwordString = password.getText().toString();
        final String url = httpUrl.getText().toString();

        Call<LoginResponse> stringCall = loginApi.toLogin(new loginBean(emailString, passwordString, chooseDB));
        stringCall.enqueue(new MyCallback<LoginResponse>() {
            private Call<String> menuListCall;

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.body()==null)
                    return;
                if (response.body().getResult().getRes_code() == 1) {
                    final int user_id = response.body().getResult().getRes_data().getUser_id();
                    SharePreferenceUtils.putInt("user_id", user_id, LoginActivity.this);
                    SharePreferenceUtils.putString("email", emailString, LoginActivity.this);
                    SharePreferenceUtils.putString("url",url,LoginActivity.this);
                    SharePreferenceUtils.putString("password", passwordString, LoginActivity.this);
                    final String name = response.body().getResult().getRes_data().getName();
                    new UserLoginUtils().insertUser(new UserLogin(emailString,passwordString));
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
                }else {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
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
                    MyApplication.userID = user_id;
                    for (GetMenuListResponse.ResDataBean resDataBean : res_data) {
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
                                                , children1.getName(), user_id, Integer.parseInt(parentID)));
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
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                IntentFactory.start_MainActivity(LoginActivity.this);
            }
        });
    }
}
