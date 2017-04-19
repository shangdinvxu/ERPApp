package tarce.testnew.activity.Invenytory;

import android.os.Bundle;
import android.renderscript.Allocation;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import greendao.ContactsBeanDao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tarce.testnew.GreenDaoManager;
import tarce.testnew.R;
import tarce.testnew.activity.BaseAppCompatActivity;
import tarce.testnew.activity.OnItemClickListener;
import tarce.testnew.greendao.greendaoBeans.ContactsBean;
import tarce.testnew.http.RetrofitClient;
import tarce.testnew.http.api.InventoryApi;
import tarce.testnew.http.bean.responseBean.GetGroupByListresponse;
import tarce.testnew.http.bean.responseBean.SearchSupplierResponse;

/**
 * Created by Daniel.Xu on 2017/2/17.
 */

public class TransferActivity extends BaseAppCompatActivity {
    @InjectView(R.id.expandablelistview)
    ExpandableListView expandablelistview;
    @InjectView(R.id.toolbar_subtitle)
    TextView toolbarSubtitle;
    @InjectView(R.id.toolbar_title)
    TextView toolbarTitle;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.search_edittext)
    EditText searchEdittext;
    @InjectView(R.id.button)
    Button button;
    @InjectView(R.id.contaer)
    LinearLayout contaer;
    @InjectView(R.id.recycleView)
    RecyclerView recycleView;
    private String TAG = this.getClass().getSimpleName();
    private InventoryApi inventoryApi;
    private List<GetGroupByListresponse.ResultBean.ResDataBean> res_data;
    private ContactsBeanDao contactsBeanDao;
    private SearchSuppleAdapter searchSuppleAdapter;
    List<String> partents = new ArrayList<>();
    String[][] childrens = new String[][]{};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_transfers;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        inventoryApi = RetrofitClient.getInstance(TransferActivity.this).create(InventoryApi.class);
        contactsBeanDao = GreenDaoManager.getInstance().getmDaoSession().getContactsBeanDao();
        initAdapter();
        initListener();
        initData();
    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(TransferActivity.this);
        recycleView.setLayoutManager(layoutManager);
        recycleView.addItemDecoration(new DividerItemDecoration(TransferActivity.this,
                DividerItemDecoration.VERTICAL));
        searchSuppleAdapter = new SearchSuppleAdapter(TransferActivity.this);
    }

    private void initListener() {
        expandablelistview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Toast.makeText(TransferActivity.this, "点击的是" + i + "----------" + "点击的i1是" + i1, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        searchEdittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = searchEdittext.getText().toString();
                Query<ContactsBean> build = contactsBeanDao.queryBuilder().where(ContactsBeanDao.Properties.Name.like("%" + s + "%")).build();
                List<ContactsBean> list = build.list();

                if (list != null&&list.size()>0) {
                    searchSuppleAdapter.setList(list);
                    recycleView.setAdapter(searchSuppleAdapter);
                }else {

                }
            }
        });
        searchSuppleAdapter.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
//                searchSuppleAdapter.getList();
            }

            @Override
            public void onItemLongClick(View view, int postion) {

            }
        });
    }

    @OnClick(R.id.button)
    void setButton(View view) {
        initSearchALL();
    }

    private void initSearchALL() {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("name", null);
        // type: ‘supplier’ or ‘customer’
        objectObjectHashMap.put("type", "supplier");
        Call<SearchSupplierResponse> stringCall = inventoryApi.searchSupplier(objectObjectHashMap);
        stringCall.enqueue(new Callback<SearchSupplierResponse>() {
            @Override
            public void onResponse(Call<SearchSupplierResponse> call, Response<SearchSupplierResponse> response) {
                if (response.body().getResult() != null) {
                    List<SearchSupplierResponse.ResultBean.ResDataBean> res_data = response.body().getResult().getRes_data();
                    if (res_data != null && res_data.size() > 0) {
                        for (SearchSupplierResponse.ResultBean.ResDataBean resDataBean : res_data) {
                            contactsBeanDao.insertOrReplace(new ContactsBean(resDataBean.getComment(), resDataBean.getPhone()
                                    , resDataBean.getPartner_id(), resDataBean.getName(), resDataBean.getX_qq()));
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<SearchSupplierResponse> call, Throwable t) {
            }
        });
    }


    private void initData() {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("groupby","picking_type_id");
        objectObjectHashMap.put("model","stock.picking");
        Call<GetGroupByListresponse> groupsByList = inventoryApi.getGroupsByList(objectObjectHashMap);
        groupsByList.enqueue(new Callback<GetGroupByListresponse>() {
            @Override
            public void onResponse(Call<GetGroupByListresponse> call, Response<GetGroupByListresponse> response) {
                List<GetGroupByListresponse.ResultBean.ResDataBean> res_data = response.body().getResult().getRes_data();
                for (GetGroupByListresponse.ResultBean.ResDataBean resDataBean : res_data){
                    String name = resDataBean.getPicking_type_id().get(1).toString();
                    int picking_type_id_count = resDataBean.getPicking_type_id_count();
                    partents.add(name+"("+picking_type_id_count+")");
                }
                String[] parents = partents.toArray(new String[partents.size()]);
                MyExpandableListAdapter myExpandableListAdapter = new MyExpandableListAdapter(TransferActivity.this, parents, childrens);
                expandablelistview.setAdapter(myExpandableListAdapter);
            }

            @Override
            public void onFailure(Call<GetGroupByListresponse> call, Throwable t) {

            }
        });

//        partents = new String[]{
//                "WORD", "EXCEL", "EMAIL", "PPT"
//        };
//         childrens = new String[][]{
//                {"文档编辑", "文档排版", "文档处理", "文档打印"},
//                {"表格编辑", "表格排版", "表格处理", "表格打印"},
//                {"收发邮件", "管理邮箱", "登录登出", "注册绑定"},
//                {"演示编辑", "演示排版", "演示处理", "演示打印"},
//        };
//        MyExpandableListAdapter myExpandableListAdapter = new MyExpandableListAdapter(TransferActivity.this, partents, childrens);
//        expandablelistview.setAdapter(myExpandableListAdapter);

    }


}
