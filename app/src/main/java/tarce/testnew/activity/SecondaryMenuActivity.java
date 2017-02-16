package tarce.testnew.activity;

import android.os.Bundle;
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
import tarce.testnew.IntentFactory;
import tarce.testnew.MyApplication;
import tarce.testnew.R;
import tarce.testnew.greendao.GreendaoUtils.MenuListBeanUtils;
import tarce.testnew.greendao.greendaoBeans.MenuListBean;
import tarce.testnew.http.RetrofitClient;
import tarce.testnew.http.api.MRPApi;

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
    private List<MenuListBean> menuById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        menuListBeanUtils = new MenuListBeanUtils();
        id = getIntent().getLongExtra("id", 0);
        menuById = menuListBeanUtils.getMenuById(MyApplication.userID, (int) id);
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
                /**先找到title指针再判断*/
            }else if (strings.get(postion).equals("产品")&&strings.get(recyclerViewAdapter.getTitle(postion)).equals("标题:库存管理")){
                IntentFactory.start_ProductActivity(SecondaryMenuActivity.this,"all");
            }else if (strings.get(postion).equals("产品")&&strings.get(recyclerViewAdapter.getTitle(postion)).equals("标题:销售")){
                IntentFactory.start_ProductActivity(SecondaryMenuActivity.this,"sale");
            }else if (strings.get(postion).equals("产品")&&strings.get(recyclerViewAdapter.getTitle(postion)).equals("标题:采购")){
                IntentFactory.start_ProductActivity(SecondaryMenuActivity.this,"purchase");
            }else if (strings.get(postion).equals("费用产品")&&strings.get(recyclerViewAdapter.getTitle(postion)).equals("标题:配置")){
                IntentFactory.start_ProductActivity(SecondaryMenuActivity.this,"expensed");
            }else if (strings.get(postion).equals("制造订单")){
                IntentFactory.start_makeOrderActivity(SecondaryMenuActivity.this);
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
            strings.add("标题:"+menuByParentId.get(i).getName());
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

