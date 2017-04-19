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
import android.widget.Toast;

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
import tarce.testnew.activity.BaseAppCompatActivity;
import tarce.testnew.activity.OnItemClickListener;
import tarce.testnew.http.RetrofitClient;
import tarce.testnew.http.api.MRPApi;
import tarce.testnew.http.api.MakeOrderApi;
import tarce.testnew.http.bean.responseBean.OrderDetailResponse;

/**
 * Created by Daniel.Xu on 2017/2/20.
 */

public class CheckMaterialActivity extends BaseAppCompatActivity {

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
    private CheckMaterialAdapter checkMaterialAdapter;
    private List<OrderDetailResponse.ResultBean.ResDataBean.StockMoveLinesBean> stock_move_lines;
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
        makeOrderApi = RetrofitClient.getInstance(CheckMaterialActivity.this).create(MakeOrderApi.class);
        initView();
        initData();
        initlistener();
    }

    private void initlistener() {
        checkMaterialAdapter.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int postion) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CheckMaterialActivity.this);
                final EditText editText = new EditText(CheckMaterialActivity.this);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(editText)
                        .setMessage("输入"+stock_move_lines.get(postion).getProduct_id()+"的退料数量")
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
                        checkMaterialAdapter.getItems().get(postion).setReturn_qty(doub);
                        checkMaterialAdapter.notifyDataSetChanged();
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
        final List<OrderDetailResponse.ResultBean.ResDataBean.StockMoveLinesBean> items = checkMaterialAdapter.getItems();
        android.support.v7.app.AlertDialog.Builder commonDialog = AlertAialogUtils.getCommonDialog(CheckMaterialActivity.this, "是否确定");
        commonDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
                objectObjectHashMap.put("order_id", order_id);
                for (OrderDetailResponse.ResultBean.ResDataBean.StockMoveLinesBean bean :items){
                    bean.setStock_move_lines_id(bean.getId());
                }
                objectObjectHashMap.put("stock_moves",items);
                objectObjectHashMap.put("is_check","no");
                Call<OrderDetailResponse> orderDetailResponseCall = makeOrderApi.returnMaterial(objectObjectHashMap);
                orderDetailResponseCall.enqueue(new Callback<OrderDetailResponse>() {
                    @Override
                    public void onResponse(Call<OrderDetailResponse> call, Response<OrderDetailResponse> response) {
                        if (response.body().getResult()!=null){
                            if (response.body().getResult().getRes_code()==1){
                                Intent intent = new Intent(CheckMaterialActivity.this, MakeOrderDetailActivity.class);
                                intent.putExtra("bean",response.body().getResult().getRes_data());
                                setResult(1,intent);
                                finish();
                            }else {
                                Toast.makeText(CheckMaterialActivity.this,response.body().getResult().getRes_data().getError(),Toast.LENGTH_SHORT).show();
                            }
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(CheckMaterialActivity.this);
        recycleView.setLayoutManager(layoutManager);
        recycleView.addItemDecoration(new DividerItemDecoration(CheckMaterialActivity.this,
                DividerItemDecoration.VERTICAL));

    }

    private void initData() {
        OrderDetailResponse.ResultBean.ResDataBean bean = (OrderDetailResponse.ResultBean.ResDataBean) getIntent().getSerializableExtra("bean");
        order_id = bean.getOrder_id();
        stock_move_lines = bean.getStock_move_lines();
        checkMaterialAdapter = new CheckMaterialAdapter(CheckMaterialActivity.this);
        checkMaterialAdapter.setItems(stock_move_lines);
        recycleView.setAdapter(checkMaterialAdapter);
    }

}
