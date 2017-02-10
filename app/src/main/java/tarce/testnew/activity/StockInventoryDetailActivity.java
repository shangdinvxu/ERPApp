package tarce.testnew.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tarce.testnew.MainFragment.OnItemClickListener;
import tarce.testnew.MainFragment.StockInventoryDetailRecycleViewAdapter;
import tarce.testnew.R;
import tarce.testnew.Utils.MyLog;
import tarce.testnew.http.RetrofitClient;
import tarce.testnew.http.api.MRPApi;
import tarce.testnew.http.bean.responseBean.GetStockInventoryDetailResponse;

public class StockInventoryDetailActivity extends AppCompatActivity {
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.recycleView)
    RecyclerView recycleView;
    @InjectView(R.id.content_stock_inventory_detail)
    LinearLayout contentStockInventoryDetail;
    private String TAG = this.getClass().getSimpleName();

    private MRPApi mrpApi;
    private StockInventoryDetailRecycleViewAdapter stockInventoryDetailRecycleViewAdapter;
    private List<GetStockInventoryDetailResponse.ResultBean.ResDataBean.LineIdsBean> line_ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_inventory_detail);
        ButterKnife.inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mrpApi = RetrofitClient.getInstance(this).create(MRPApi.class);
        stockInventoryDetailRecycleViewAdapter = new StockInventoryDetailRecycleViewAdapter(StockInventoryDetailActivity.this);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        stockInventoryDetailRecycleViewAdapter.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Intent intent = new Intent(StockInventoryDetailActivity.this,ProductionDetailNoClickActivity.class);
                Bundle bundle = new Bundle();
                int product_qty = line_ids.get(postion).getProduct_qty();
                int theoretical_qty = line_ids.get(postion).getTheoretical_qty();
                String image_medium = line_ids.get(postion).getProduct().getImage_medium();
                String product_name = line_ids.get(postion).getProduct().getProduct_name();
                String area_name = (String) line_ids.get(postion).getProduct().getArea().getName();
                bundle.putInt("product_qty",product_qty);
                bundle.putInt("theoretical_qty",theoretical_qty);
                bundle.putString("image_medium",image_medium);
                bundle.putString("product_name",product_name);
                bundle.putString("area_name",area_name);
                intent.putExtra("bundle",bundle);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int postion) {

            }
        });


    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(StockInventoryDetailActivity.this);
        recycleView.setLayoutManager(layoutManager);
        recycleView.addItemDecoration(new DividerItemDecoration(StockInventoryDetailActivity.this,
                DividerItemDecoration.VERTICAL));
    }

    private void initData() {
        int id = getIntent().getIntExtra("id", -1);
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        stringIntegerHashMap.put("inventory_id", id);
        Call<GetStockInventoryDetailResponse> stockInventoryDetail = mrpApi.getStockInventoryDetail(stringIntegerHashMap);
        stockInventoryDetail.enqueue(new Callback<GetStockInventoryDetailResponse>() {
            @Override
            public void onResponse(Call<GetStockInventoryDetailResponse> call, Response<GetStockInventoryDetailResponse> response) {
                line_ids = response.body().getResult().getRes_data().getLine_ids();
                MyLog.e(TAG,"area"+line_ids.get(0).getProduct().getArea().getName()+"");
                stockInventoryDetailRecycleViewAdapter.setItems(line_ids);
                recycleView.setAdapter(stockInventoryDetailRecycleViewAdapter);
            }

            @Override
            public void onFailure(Call<GetStockInventoryDetailResponse> call, Throwable t) {
                MyLog.e(TAG, t.toString());
            }
        });


    }


}
