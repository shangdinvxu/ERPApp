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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;
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
import tarce.testnew.http.MyCallback;
import tarce.testnew.http.RetrofitClient;
import tarce.testnew.http.api.MakeOrderApi;
import tarce.testnew.http.bean.responseBean.OrderDetailResponse;

/**
 * Created by Daniel.Xu on 2017/2/17.
 */

public class SubmitActivity extends BaseAppCompatActivity {
    @InjectView(R.id.toolbar_subtitle)
    TextView toolbarSubtitle;
    @InjectView(R.id.toolbar_title)
    TextView toolbarTitle;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.recycleView)
    RecyclerView recycleView;
    @InjectView(R.id.submit_number)
    Button submitNumber;
    private SubmitAdapter submitAdapter;
    private MakeOrderApi makeOrderApi;
    private int order_id;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_submit;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        makeOrderApi = RetrofitClient.getInstance(SubmitActivity.this).create(MakeOrderApi.class);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        submitAdapter.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int postion) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SubmitActivity.this);
                final EditText editText = new EditText(SubmitActivity.this);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                editText.setText(submitAdapter.getItems().get(postion).getSuggest_qty()+"");
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
                        submitAdapter.getItems().get(postion).setQuantity_ready(Double.parseDouble(s));
                        submitAdapter.notifyDataSetChanged();
                    }
                }).show();
            }
            @Override
            public void onItemLongClick(View view, int postion) {

            }
        });
    }

    @OnClick(R.id.submit_number)
    void submit(View view){
        final List<OrderDetailResponse.ResultBean.ResDataBean.StockMoveLinesBean> items = submitAdapter.getItems();
        for (OrderDetailResponse.ResultBean.ResDataBean.StockMoveLinesBean item :items){
            if (item.getQuantity_ready().equals(Double.valueOf(0))){
                android.support.v7.app.AlertDialog.Builder commonDialog = AlertAialogUtils.getCommonDialog(SubmitActivity.this, "请填写备料");
                commonDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
                return;
            }
        }
        android.support.v7.app.AlertDialog.Builder dialog = AlertAialogUtils.getCommonDialog(SubmitActivity.this, "是否确定");
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
                objectObjectHashMap.put("order_id", order_id);
                for (OrderDetailResponse.ResultBean.ResDataBean.StockMoveLinesBean bean :items){
                    bean.setStock_move_lines_id(bean.getId());
                }
                objectObjectHashMap.put("stock_moves",items);
                Call<OrderDetailResponse> orderDetailResponseCall = makeOrderApi.finishPrepareMaterial(objectObjectHashMap);
                orderDetailResponseCall.enqueue(new MyCallback<OrderDetailResponse>() {
                    @Override
                    public void onResponse(Call<OrderDetailResponse> call, Response<OrderDetailResponse> response) {
                        if (response.body().getResult()!=null){
                            Intent intent = new Intent(SubmitActivity.this, MakeOrderDetailActivity.class);
                            intent.putExtra("bean",response.body().getResult().getRes_data());
                            setResult(1,intent);
                            finish();
                        }
                    }
                });
            }
        }).show();
    }



    private void initData() {
        OrderDetailResponse.ResultBean.ResDataBean bean = (OrderDetailResponse.ResultBean.ResDataBean) getIntent().getSerializableExtra("bean");
        order_id = bean.getOrder_id();
        submitAdapter = new SubmitAdapter(SubmitActivity.this);
        submitAdapter.setItems(bean.getStock_move_lines());
        recycleView.setAdapter(submitAdapter);
    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(SubmitActivity.this);
        recycleView.setLayoutManager(layoutManager);
        recycleView.addItemDecoration(new DividerItemDecoration(SubmitActivity.this,
                DividerItemDecoration.VERTICAL));
    }
}
