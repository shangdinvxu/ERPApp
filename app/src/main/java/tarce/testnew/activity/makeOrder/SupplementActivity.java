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
import retrofit2.Response;
import tarce.testnew.R;
import tarce.testnew.Utils.AlertAialogUtils;
import tarce.testnew.activity.BaseAppCompatActivity;
import tarce.testnew.activity.OnItemClickListener;
import tarce.testnew.http.MyCallback;
import tarce.testnew.http.RetrofitClient;
import tarce.testnew.http.api.MakeOrderApi;
import tarce.testnew.http.bean.responseBean.OrderDetailResponse;

/**
 * Created by Daniel.Xu on 2017/2/17.
 */

public class SupplementActivity extends BaseAppCompatActivity {
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
    private MakeOrderApi makeOrderApi;
    private OrderDetailResponse.ResultBean.ResDataBean bean;
    private SupplementAdapter supplementAdapter;
    private int order_id;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_supplement;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        makeOrderApi = RetrofitClient.getInstance(SupplementActivity.this).create(MakeOrderApi.class);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        supplementAdapter.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int postion) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SupplementActivity.this);
                final EditText editText = new EditText(SupplementActivity.this);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(editText)
                        .setMessage("更新产品数量")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String s = editText.getText().toString();
                        Double doub = 0.0 ;
                        if (s.length()!=0){
                            doub = Double.parseDouble(s) ;
                        }
                        supplementAdapter.getItems().get(postion).setOver_picking_qty(doub);
                        supplementAdapter.notifyDataSetChanged();
                    }
                }).show();
            }
            @Override
            public void onItemLongClick(View view, int postion) {

            }
        });
    }

    @OnClick(R.id.submit)
    void onClick(View view){
        final List<OrderDetailResponse.ResultBean.ResDataBean.StockMoveLinesBean> items = supplementAdapter.getItems();
        for (OrderDetailResponse.ResultBean.ResDataBean.StockMoveLinesBean item :items){
            if (item.getOver_picking_qty().equals(Double.valueOf(0))){
                android.support.v7.app.AlertDialog.Builder commonDialog = AlertAialogUtils.getCommonDialog(SupplementActivity.this, "请填写备料");
                commonDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
                return;
            }
        }
        android.support.v7.app.AlertDialog.Builder dialog = AlertAialogUtils.getCommonDialog(SupplementActivity.this, "是否确定");
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
                objectObjectHashMap.put("order_id", order_id);
                for (OrderDetailResponse.ResultBean.ResDataBean.StockMoveLinesBean bean :items){
                    bean.setStock_move_lines_id(bean.getId());
                }
                objectObjectHashMap.put("stock_moves",items);
                Call<OrderDetailResponse> orderDetailResponseCall = makeOrderApi.overPicking(objectObjectHashMap);
                orderDetailResponseCall.enqueue(new MyCallback<OrderDetailResponse>() {
                    @Override
                    public void onResponse(Call<OrderDetailResponse> call, Response<OrderDetailResponse> response) {
                        if (response.body().getResult()!=null){
                            Intent intent = new Intent(SupplementActivity.this, MakeOrderDetailActivity.class);
                            intent.putExtra("bean",response.body().getResult().getRes_data());
                            setResult(2,intent);
                            finish();
                        }
                    }
                });
            }
        }).show();
    }


    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(SupplementActivity.this);
        recycleView.setLayoutManager(layoutManager);
        recycleView.addItemDecoration(new DividerItemDecoration(SupplementActivity.this,
                DividerItemDecoration.VERTICAL));
    }

    private void initData() {
        bean = (OrderDetailResponse.ResultBean.ResDataBean) getIntent().getSerializableExtra("bean");
        order_id = bean.getOrder_id();
        supplementAdapter = new SupplementAdapter(SupplementActivity.this);
        supplementAdapter.setItems(bean.getStock_move_lines());
        recycleView.setAdapter(supplementAdapter);
    }

}
