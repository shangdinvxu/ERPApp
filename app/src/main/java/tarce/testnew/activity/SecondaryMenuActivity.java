package tarce.testnew.activity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tarce.testnew.IntentFactory;
import tarce.testnew.MainFragment.OnItemClickListener;
import tarce.testnew.MainFragment.RecyclerViewAdapter;
import tarce.testnew.MyApplication;
import tarce.testnew.R;
import tarce.testnew.greendaoBean.GreendaoUtils.MenuListBeanUtils;
import tarce.testnew.greendaoBean.MenuListBean;
import tarce.testnew.http.RetrofitClient;
import tarce.testnew.http.api.MRPApi;
import tarce.testnew.http.bean.requestBean.GetStockInventoryRequestBean;

public class SecondaryMenuActivity extends AppCompatActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.recycleView)
    RecyclerView recycleView;
    @InjectView(R.id.content_main2)
    RelativeLayout contentMain2;
    private MenuListBeanUtils menuListBeanUtils;
    private long id;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<String> strings;
    private MRPApi mrpApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        menuListBeanUtils = new MenuListBeanUtils();
        id = getIntent().getLongExtra("id", 0);
        List<MenuListBean> menuById = menuListBeanUtils.getMenuById(MyApplication.userID, (int) id);
        toolbar.setTitle(menuById.get(0).getName());
        setSupportActionBar(toolbar);
        mrpApi = RetrofitClient.getInstance(this).create(MRPApi.class);
        initView();
        initListener();

    }

    private void initListener() {
    recyclerViewAdapter.setItemClickListener(new OnItemClickListener() {
        @Override
        public void onItemClick(View view, int postion) {

            if (strings.get(postion).equals("库存调整")){
                IntentFactory.start_StockInventoryActivity(SecondaryMenuActivity.this);
            }

        }

        @Override
        public void onItemLongClick(View view, int postion) {

        }
    });

    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器,RecycleView一定要加的.
        recycleView.setLayoutManager(layoutManager);
        strings = new ArrayList<>();
        List<MenuListBean> menuByParentId = menuListBeanUtils.getMenuByParentId(MyApplication.userID, (int) id);
        for (int i = 0 ; i<menuByParentId.size();i++){
            strings.add("this is title:"+menuByParentId.get(i).getName());
            long secondId = menuByParentId.get(i).getId();
            List<MenuListBean> menuByParentIdSecond = menuListBeanUtils.getMenuByParentId(MyApplication.userID, (int) secondId);
            if (menuByParentIdSecond.size()>0){
                for (int iSecond = 0 ; iSecond<menuByParentIdSecond.size();iSecond++){
                    strings.add(menuByParentIdSecond.get(iSecond).getName());
                }
            }
        }
        recyclerViewAdapter = new RecyclerViewAdapter(this, strings);
        recycleView.setAdapter(recyclerViewAdapter);
    }






}

