package tarce.testnew.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tarce.testnew.IntentFactory;
import tarce.testnew.MainFragment.OnItemClickListener;
import tarce.testnew.MainFragment.StockInventoryRecycleViewAdapter;
import tarce.testnew.R;
import tarce.testnew.Utils.MyLog;
import tarce.testnew.ViewUtil.LoaderMoreView;
import tarce.testnew.ViewUtil.RefreshHeaderView;
import tarce.testnew.http.RetrofitClient;
import tarce.testnew.http.api.MRPApi;
import tarce.testnew.http.bean.requestBean.GetStockInventoryRequestBean;
import tarce.testnew.http.bean.responseBean.GetStockInventoryResponse;

public class StockInventoryActivity extends AppCompatActivity {
    private String TAG = this.getClass().getSimpleName();

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.swipe_refresh_header)
    RefreshHeaderView swipeRefreshHeader;
    @InjectView(R.id.swipe_target)
    RecyclerView recycleView;
    @InjectView(R.id.swipe_load_more_footer)
    LoaderMoreView swipeLoadMoreFooter;
    @InjectView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;
    @InjectView(R.id.content_stock_inventory)
    RelativeLayout contentStockInventory;
    private MRPApi mrpApi;
    private int index = 0 ;
    private StockInventoryRecycleViewAdapter stockInventoryRecycleViewAdapter;
    private List<GetStockInventoryResponse.ResultBean.ResDataBean> res_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_inventory);
        ButterKnife.inject(this);
        toolbar.setTitle("库存调整");
        setSupportActionBar(toolbar);

        mrpApi = RetrofitClient.getInstance(this).create(MRPApi.class);
        getStockInventoryList();
        res_data = new ArrayList<GetStockInventoryResponse.ResultBean.ResDataBean>();


        LinearLayoutManager layoutManager = new LinearLayoutManager(StockInventoryActivity.this);
        recycleView.setLayoutManager(layoutManager);
        recycleView.addItemDecoration(new DividerItemDecoration(StockInventoryActivity.this,
                DividerItemDecoration.VERTICAL));
        stockInventoryRecycleViewAdapter = new StockInventoryRecycleViewAdapter(StockInventoryActivity.this);
        initListener();

    }

    private void initListener() {
        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                index = 0 ;
                Call<GetStockInventoryResponse> confirmed = mrpApi.getStockInventoryList(new GetStockInventoryRequestBean(80, index));
                confirmed.enqueue(new Callback<GetStockInventoryResponse>() {
                    @Override
                    public void onResponse(Call<GetStockInventoryResponse> call, Response<GetStockInventoryResponse> response) {
                        res_data = response.body().getResult().getRes_data();
                        stockInventoryRecycleViewAdapter.setItems(res_data);
                        stockInventoryRecycleViewAdapter.notifyDataSetChanged();
                        swipeToLoadLayout.setRefreshing(false);
                    }
                    @Override
                    public void onFailure(Call<GetStockInventoryResponse> call, Throwable t) {

                    }
                });
            }
        });
        //为swipeToLoadLayout设置上拉加载更多监听者
      /*  swipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                index++;
                Call<GetStockInventoryResponse> confirmed = mrpApi.getStockInventoryList(new GetStockInventoryRequestBean(80, index));
                confirmed.enqueue(new Callback<GetStockInventoryResponse>() {
                    @Override
                    public void onResponse(Call<GetStockInventoryResponse> call, Response<GetStockInventoryResponse> response) {
                        List<GetStockInventoryResponse.ResultBean.ResDataBean> res_data = response.body().getResult().getRes_data();
                        List<GetStockInventoryResponse.ResultBean.ResDataBean> items = stockInventoryRecycleViewAdapter.getItems();
                        items.addAll(res_data);
                        stockInventoryRecycleViewAdapter.notifyDataSetChanged();         //设置上拉加载更多结束
                        swipeToLoadLayout.setLoadingMore(false);
                    }
                    @Override
                    public void onFailure(Call<GetStockInventoryResponse> call, Throwable t) {

                    }
                });

            }
        });*/
        swipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                swipeToLoadLayout.setLoadingMore(false);
            }
        });

        stockInventoryRecycleViewAdapter.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                int id = res_data.get(postion).getId();
                Intent intent = new Intent(StockInventoryActivity.this,StockInventoryDetailActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int postion) {

            }
        });


    }

    private void getStockInventoryList() {
        Call<GetStockInventoryResponse> stockInventoryList = mrpApi.getStockInventoryList(new GetStockInventoryRequestBean(80, 1));
        stockInventoryList.enqueue(new Callback<GetStockInventoryResponse>() {
            @Override
            public void onResponse(Call<GetStockInventoryResponse> call, Response<GetStockInventoryResponse> response) {
                 res_data = response.body().getResult().getRes_data();
                stockInventoryRecycleViewAdapter.setItems(res_data);
                recycleView.setAdapter(stockInventoryRecycleViewAdapter);
            }

            @Override
            public void onFailure(Call<GetStockInventoryResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.portal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        IntentFactory.start_ScanActivity(StockInventoryActivity.this);
        return super.onOptionsItemSelected(item);
    }
}
