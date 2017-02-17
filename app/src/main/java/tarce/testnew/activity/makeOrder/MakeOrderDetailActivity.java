package tarce.testnew.activity.makeOrder;

import android.app.AlertDialog;
import android.app.ProgressDialog;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tarce.testnew.R;
import tarce.testnew.Utils.AlertAialogUtils;
import tarce.testnew.Utils.MyLog;
import tarce.testnew.Utils.StringSwitchUtils;
import tarce.testnew.activity.BaseAppCompatActivity;
import tarce.testnew.activity.Invenytory.StockInventoryActivity;
import tarce.testnew.http.MyCallback;
import tarce.testnew.http.RetrofitClient;
import tarce.testnew.http.api.MakeOrderApi;
import tarce.testnew.http.bean.responseBean.OrderDetailResponse;

/**
 * Created by Daniel.Xu on 2017/2/16.
 */

public class MakeOrderDetailActivity extends BaseAppCompatActivity {
    private String TAG = this.getClass().getSimpleName();
    @InjectView(R.id.toolbar_subtitle)
    TextView toolbarSubtitle;
    @InjectView(R.id.toolbar_title)
    TextView toolbarTitle;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.production)
    TextView production;
    @InjectView(R.id.quantity)
    TextView quantity;
    @InjectView(R.id.billOfMaterial)
    TextView billOfMaterial;
    @InjectView(R.id.documentStatus)
    TextView documentStatus;
    @InjectView(R.id.production_list_types)
    TextView productionListTypes;
    @InjectView(R.id.deadline_start)
    TextView deadlineStart;
    @InjectView(R.id.principal)
    TextView principal;
    @InjectView(R.id.source)
    TextView source;
    @InjectView(R.id.warehouse)
    TextView warehouse;
    @InjectView(R.id.button_1)
    Button button1;
    @InjectView(R.id.button_2)
    Button button2;
    @InjectView(R.id.button_3)
    Button button3;
    @InjectView(R.id.recycleView)
    RecyclerView recycleView;
    private MakeOrderApi makeOrderApi;
    private int order_id;
    private String productOrderType;
    private int databaseSwitch = 0;
    private OrderDetailResponse.ResultBean.ResDataBean res_data ;
    private static final int startSubmitActivityCode = 1 ;
    private static final int startSupplementactivityCode =startSubmitActivityCode+1  ;
    private List<OrderDetailResponse.ResultBean.ResDataBean.StockMoveLinesBean> stock_move_lines;
    private boolean visible = true ;

    @Override
    protected int getLayoutId() {
        return R.layout.mrp_production_item_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        makeOrderApi = RetrofitClient.getInstance(MakeOrderDetailActivity.this).create(MakeOrderApi.class);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(MakeOrderDetailActivity.this);
        recycleView.setLayoutManager(layoutManager);
        recycleView.addItemDecoration(new DividerItemDecoration(MakeOrderDetailActivity.this,
                DividerItemDecoration.VERTICAL));
    }

    private void initListener() {
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button1.getText().toString().equals("确认生产")) {
                    final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(MakeOrderDetailActivity.this);
                    builder.setTitle("选择生产单类型");
                    builder.setIcon(android.R.drawable.ic_menu_more);
                    builder.setCancelable(true);
                    String[] strings = {"备货制(不需要产出全部数量)", "订单制"};
                    builder.setSingleChoiceItems(strings, 0, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            databaseSwitch = i;
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }).setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
                                    objectObjectHashMap.put("order_id", order_id);
                                    objectObjectHashMap.put("order_type", databaseSwitch == 0 ? "stockup" : "ordering");
                                    Call<OrderDetailResponse> orderDetailResponseCall = makeOrderApi.confirmOrder(objectObjectHashMap);
                                    request(orderDetailResponseCall);
                                }
                            });
                    android.support.v7.app.AlertDialog dialog = builder.create();
                    dialog.setCanceledOnTouchOutside(true);
                    dialog.show();
                }else if (button1.getText().toString().equals("开始备料")){
                    android.support.v7.app.AlertDialog.Builder commonDialog = AlertAialogUtils.getCommonDialog(MakeOrderDetailActivity.this, "是否确定");
                    commonDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
                            objectObjectHashMap.put("order_id", order_id);
                            Call<OrderDetailResponse> orderDetailResponseCall = makeOrderApi.prepareMaterialing(objectObjectHashMap);
                            request(orderDetailResponseCall);
                        }
                    }).show();
                }else if (button1.getText().toString().equals("完成备料")){
                    Intent intent = new Intent(MakeOrderDetailActivity.this, SubmitActivity.class);
                    intent.putExtra("bean",res_data);
                    startActivityForResult(intent,startSubmitActivityCode);
                }else if (button1.getText().toString().equals("领料登记")){
                    android.support.v7.app.AlertDialog.Builder commonDialog = AlertAialogUtils.getCommonDialog(MakeOrderDetailActivity.this, "是否确定");
                    commonDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
                            objectObjectHashMap.put("order_id", order_id);
                            Call<OrderDetailResponse> orderDetailResponseCall = makeOrderApi.alreadyPicking(objectObjectHashMap);
                            request(orderDetailResponseCall);
                        }
                    }).show();
                }else if (button1.getText().toString().equals("开始生产")){
                    android.support.v7.app.AlertDialog.Builder commonDialog = AlertAialogUtils.getCommonDialog(MakeOrderDetailActivity.this, "是否确定");
                    commonDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
                            objectObjectHashMap.put("order_id", order_id);
                            Call<OrderDetailResponse> orderDetailResponseCall = makeOrderApi.startProduce(objectObjectHashMap);
                            request(orderDetailResponseCall);
                        }
                    }).show();
                }
                else if (button1.getText().toString().equals("生产完成,送往品检")){
                    android.support.v7.app.AlertDialog.Builder commonDialog = AlertAialogUtils.getCommonDialog(MakeOrderDetailActivity.this, "是否确定");
                    commonDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
                            objectObjectHashMap.put("order_id", order_id);
                            // TODO: 2017/2/17
                            /**写到生产完成送往品检部分**/
//                            Call<OrderDetailResponse> orderDetailResponseCall = makeOrderApi.(objectObjectHashMap);
//                            request(orderDetailResponseCall);
                        }
                    }).show();
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button2.getText().toString().equals("更新生产数量")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MakeOrderDetailActivity.this);
                    final EditText editText = new EditText(MakeOrderDetailActivity.this);
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.setText(0 + "");
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
                            int intNumber = Integer.parseInt(s);
                            HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
                            objectObjectHashMap.put("order_id", order_id);
                            objectObjectHashMap.put("product_qty", intNumber);
                            Call<OrderDetailResponse> orderDetailResponseCall = makeOrderApi.updateProduce(objectObjectHashMap);

                            request(orderDetailResponseCall);
                        }
                    }).show();
                }else if (button2.getText().toString().equals("补领料")){
                    Intent intent = new Intent(MakeOrderDetailActivity.this, SupplementActivity.class);
                    intent.putExtra("bean",res_data);
                    startActivityForResult(intent,startSupplementactivityCode);
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button3.getText().toString().equals("取消")){
                    android.support.v7.app.AlertDialog.Builder commonDialog = AlertAialogUtils.getCommonDialog(MakeOrderDetailActivity.this, "是否取消?");
                    commonDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
                            objectObjectHashMap.put("order_id", order_id);
                            Call<OrderDetailResponse> orderDetailResponseCall = makeOrderApi.cancelOrder(objectObjectHashMap);
                            request(orderDetailResponseCall);
                        }
                    }).show();
                }else if (button3.getText().toString().equals("产出")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MakeOrderDetailActivity.this);
                    final EditText editText = new EditText(MakeOrderDetailActivity.this);
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.setText(1.0+"");
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
                            HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
                            objectObjectHashMap.put("order_id", order_id);
                            objectObjectHashMap.put("produce_qty", Double.parseDouble(s));
                            Call<OrderDetailResponse> orderDetailResponseCall = makeOrderApi.doProduce(objectObjectHashMap);
                            request(orderDetailResponseCall);
                            visible= false;
                        }
                    }).show();

                }

            }
        });
    }

    private void request(Call<OrderDetailResponse> orderDetailResponseCall) {
        final ProgressDialog progressDialog = new ProgressDialog(MakeOrderDetailActivity.this);
        progressDialog.show();
        orderDetailResponseCall.enqueue(new MyCallback<OrderDetailResponse>() {
            @Override
            public void onResponse(Call<OrderDetailResponse> call, Response<OrderDetailResponse> response) {
                progressDialog.dismiss();
                if (response.body().getResult() != null) {
                    OrderDetailResponse.ResultBean.ResDataBean res_data = response.body().getResult().getRes_data();
                    refreshViewByBean(res_data);
                }
            }

            @Override
            public void onFailure(Call<OrderDetailResponse> call, Throwable t) {
                MyLog.e(TAG,t.toString());
                progressDialog.dismiss();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)  {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==startSubmitActivityCode||requestCode==startSupplementactivityCode){
            res_data = (OrderDetailResponse.ResultBean.ResDataBean) data.getSerializableExtra("bean");
            refreshViewByBean(res_data);
        }
    }

    private void initData() {
        order_id = getIntent().getIntExtra("order_id", -1);
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("order_id", order_id);
        Call<OrderDetailResponse> orderDetail = makeOrderApi.getOrderDetail(hashMap);
        request(orderDetail);
    }


    private void refreshViewByBean(OrderDetailResponse.ResultBean.ResDataBean res_data) {
        this.res_data = res_data ;
        production.setText(res_data.getProduct_name());
        quantity.setText(res_data.getQty_produced() + "/" + res_data.getProduct_qty());
        billOfMaterial.setText(res_data.getBom_name());
        documentStatus.setText(StringSwitchUtils.switchString(res_data.getState()));
        productOrderType = res_data.getProduction_order_type();
        productionListTypes.setText(StringSwitchUtils.getProductionOrderType(res_data.getProduction_order_type()));
        deadlineStart.setText(res_data.getDate_planned_start());
        principal.setText(res_data.getUser_id());
        source.setText(res_data.getCur_location() + "");
        stock_move_lines = res_data.getStock_move_lines();
        if (stock_move_lines != null && stock_move_lines.size() > 0) {
            StockMoveLinesAdapter stockMoveLinesAdapter = new StockMoveLinesAdapter(MakeOrderDetailActivity.this);
            stockMoveLinesAdapter.setItems(stock_move_lines);
            recycleView.setAdapter(stockMoveLinesAdapter);
        }
        checkState(StringSwitchUtils.switchString(res_data.getState()));
    }


    /**根据state 更新页面*/
    private void checkState(String string) {
        if (string.equals("已确认")) {
            button1.setText("确认生产");
            button2.setText("更新生产数量");
            button3.setText("取消");
        } else if (string.equals("等待备料")) {
            button1.setText("开始备料");
            button2.setVisibility(View.GONE);
            button3.setText("取消");
        } else if (string.equals("已取消")) {
            button1.setVisibility(View.GONE);
            button2.setVisibility(View.GONE);
            button3.setVisibility(View.GONE);
        }else if (string.equals("备料中...")){
            button1.setText("完成备料");
            button2.setVisibility(View.GONE);
            button3.setText("取消");
        }else if (string.equals("备料完成")){
            button1.setText("领料登记");
            button2.setVisibility(View.GONE);
            button3.setVisibility(View.GONE);
        }else if (string.equals("已领料")){
            button1.setText("开始生产");
            button2.setVisibility(View.GONE);
            button3.setVisibility(View.GONE);
        }else if (string.equals("进行中")){
            button1.setText("生产完成,送往品检");
            button2.setText("补领料");
            button2.setVisibility(View.VISIBLE);
            if (visible){
                button3.setVisibility(View.VISIBLE);
            }else {
                button3.setVisibility(View.GONE);
            }
            button3.setText("产出");
        }
    }


}
