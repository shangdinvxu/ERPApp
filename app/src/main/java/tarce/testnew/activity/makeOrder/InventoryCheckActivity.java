package tarce.testnew.activity.makeOrder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tarce.testnew.R;
import tarce.testnew.Utils.AlertAialogUtils;
import tarce.testnew.Utils.MyLog;
import tarce.testnew.activity.BaseAppCompatActivity;
import tarce.testnew.activity.OnItemClickListener;
import tarce.testnew.http.RetrofitClient;
import tarce.testnew.http.api.MakeOrderApi;
import tarce.testnew.http.bean.responseBean.GetReturnDetailResponse;
import tarce.testnew.http.bean.responseBean.OrderDetailResponse;

/**
 * Created by Daniel.Xu on 2017/2/20.
 */

public class InventoryCheckActivity extends BaseAppCompatActivity {
    private String TAG = this.getClass().getSimpleName();
    @InjectView(R.id.toolbar_subtitle)
    TextView toolbarSubtitle;
    @InjectView(R.id.toolbar_title)
    TextView toolbarTitle;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.recycleView)
    RecyclerView recycleView;
    @InjectView(R.id.submit)
    Button submit;
    private InventoryCheckAdapter inventoryCheckAdapter;
    private MakeOrderApi makeOrderApi;
    private int order_id;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_check_material;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        makeOrderApi = RetrofitClient.getInstance(InventoryCheckActivity.this).create(MakeOrderApi.class);
        initView();
        initData();
        initlistener();
    }

    private void initlistener() {
        inventoryCheckAdapter.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int postion) {
                AlertDialog.Builder builder = new AlertDialog.Builder(InventoryCheckActivity.this);
                final EditText editText = new EditText(InventoryCheckActivity.this);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(editText)
                        .setMessage("输入"+inventoryCheckAdapter.getItems().get(postion).getProduct_id()+"的退料数量")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String s = editText.getText().toString();
                        int doub = 0 ;
                        if (s.length()!=0){
                            doub = Integer.parseInt(s) ;
                        }
                        inventoryCheckAdapter.getItems().get(postion).setReturn_qty(doub);
                        inventoryCheckAdapter.notifyDataSetChanged();
                    }
                }).show();
            }

            @Override
            public void onItemLongClick(View view, int postion) {

            }
        });
    }

    @OnClick(R.id.submit)
    void setSubmit(View view){
     final  List<GetReturnDetailResponse.ResultBean.ResDataBean> items = inventoryCheckAdapter.getItems();
        android.support.v7.app.AlertDialog.Builder commonDialog = AlertAialogUtils.getCommonDialog(InventoryCheckActivity.this, "是否确定");
        commonDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
                objectObjectHashMap.put("order_id", order_id);
                objectObjectHashMap.put("stock_moves",items);
                objectObjectHashMap.put("is_check",1);
                Call<OrderDetailResponse> orderDetailResponseCall = makeOrderApi.returnMaterial(objectObjectHashMap);
                orderDetailResponseCall.enqueue(new Callback<OrderDetailResponse>() {
                    @Override
                    public void onResponse(Call<OrderDetailResponse> call, Response<OrderDetailResponse> response) {
                        if (response.body().getResult()!=null){
                            Intent intent = new Intent(InventoryCheckActivity.this, MakeOrderDetailActivity.class);
                            intent.putExtra("bean",response.body().getResult().getRes_data());
                            setResult(1,intent);
                            finish();
                        }
                    }
                    @Override
                    public void onFailure(Call<OrderDetailResponse> call, Throwable t) {

                    }
                });
            }
        }).show();


    }


    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(InventoryCheckActivity.this);
        recycleView.setLayoutManager(layoutManager);
        recycleView.addItemDecoration(new DividerItemDecoration(InventoryCheckActivity.this,
                DividerItemDecoration.VERTICAL));
        inventoryCheckAdapter = new InventoryCheckAdapter(InventoryCheckActivity.this);

    }

    private void initData() {
        order_id =  getIntent().getIntExtra("order_id", -1);
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("order_id", order_id);
        Call<GetReturnDetailResponse> returnDetail = makeOrderApi.getReturnDetail(objectObjectHashMap);
        returnDetail.enqueue(new Callback<GetReturnDetailResponse>() {
            @Override
            public void onResponse(Call<GetReturnDetailResponse> call, Response<GetReturnDetailResponse> response) {
                if (response.body().getResult()!=null){
                    List<GetReturnDetailResponse.ResultBean.ResDataBean> res_data = response.body().getResult().getRes_data();
                    inventoryCheckAdapter.setItems(res_data);
                    recycleView.setAdapter(inventoryCheckAdapter);
                }
            }

            @Override
            public void onFailure(Call<GetReturnDetailResponse> call, Throwable t) {
                MyLog.e(TAG,t.toString());
            }
        });

    }

}
