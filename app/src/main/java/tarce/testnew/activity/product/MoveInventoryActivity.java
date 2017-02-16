package tarce.testnew.activity.product;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tarce.testnew.activity.OnItemClickListener;
import tarce.testnew.R;
import tarce.testnew.ViewUtil.LoaderMoreView;
import tarce.testnew.ViewUtil.RefreshHeaderView;
import tarce.testnew.activity.BaseAppCompatActivity;
import tarce.testnew.http.RetrofitClient;
import tarce.testnew.http.api.ProductApi;
import tarce.testnew.http.bean.requestBean.GetStrockMoveRequsetBean;
import tarce.testnew.http.bean.responseBean.GetStockMoveResponseBean;

/**
 * Created by Daniel.Xu on 2017/2/15.
 */

public class MoveInventoryActivity extends BaseAppCompatActivity {

    @InjectView(R.id.toolbar_subtitle)
    TextView toolbarSubtitle;
    @InjectView(R.id.toolbar_title)
    TextView toolbarTitle;
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
    private ProductApi productApi;
    private MoveStockAdapter moveStockAdapter;
    private int index = 0 ;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_move_inventory;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        getSubTitle().setVisibility(View.GONE);
        productApi = RetrofitClient.getInstance(MoveInventoryActivity.this).create(ProductApi.class);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        moveStockAdapter.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Intent intent = new Intent(MoveInventoryActivity.this, MoveInventoryDetailActivity.class);
                GetStockMoveResponseBean.ResultBean.ResDataBean resDataBean = moveStockAdapter.getItems().get(postion);
                intent.putExtra("bean", resDataBean);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int postion) {

            }
        });
        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                index = 0;
                int product_id = getIntent().getIntExtra("product_id", -1);
                Call<GetStockMoveResponseBean> stockMove = productApi.getStockMove(new GetStrockMoveRequsetBean(100, 0, product_id));
                stockMove.enqueue(new Callback<GetStockMoveResponseBean>() {
                    @Override
                    public void onResponse(Call<GetStockMoveResponseBean> call, Response<GetStockMoveResponseBean> response) {
                        if (response.body().getResult().getRes_data() != null) {
                            List<GetStockMoveResponseBean.ResultBean.ResDataBean> res_data = response.body().getResult().getRes_data();
                            moveStockAdapter.setmData(res_data);
                            recycleView.setAdapter(moveStockAdapter);
                            swipeToLoadLayout.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<GetStockMoveResponseBean> call, Throwable t) {

                    }
                });
            }
        });

        swipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                index++;
                int product_id = getIntent().getIntExtra("product_id", -1);
                Call<GetStockMoveResponseBean> stockMove = productApi.getStockMove(new GetStrockMoveRequsetBean(100, index*100, product_id));
                stockMove.enqueue(new Callback<GetStockMoveResponseBean>() {
                    @Override
                    public void onResponse(Call<GetStockMoveResponseBean> call, Response<GetStockMoveResponseBean> response) {
                        if (response.body().getResult().getRes_data() != null) {
                            List<GetStockMoveResponseBean.ResultBean.ResDataBean> res_data = response.body().getResult().getRes_data();
                            List<GetStockMoveResponseBean.ResultBean.ResDataBean> items = moveStockAdapter.getItems();
                            items.addAll(res_data);
                            moveStockAdapter.setmData(items);
                        }
                        swipeToLoadLayout.setLoadingMore(false);
                    }

                    @Override
                    public void onFailure(Call<GetStockMoveResponseBean> call, Throwable t) {

                    }
                });
            }
        });


    }

    private void initView() {
        moveStockAdapter = new MoveStockAdapter(MoveInventoryActivity.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MoveInventoryActivity.this);
        recycleView.setLayoutManager(layoutManager);
        recycleView.addItemDecoration(new DividerItemDecoration(MoveInventoryActivity.this,
                DividerItemDecoration.VERTICAL));

    }

    private void initData() {
        int product_id = getIntent().getIntExtra("product_id", -1);
        Call<GetStockMoveResponseBean> stockMove = productApi.getStockMove(new GetStrockMoveRequsetBean(100, 0, product_id));
        stockMove.enqueue(new Callback<GetStockMoveResponseBean>() {
            @Override
            public void onResponse(Call<GetStockMoveResponseBean> call, Response<GetStockMoveResponseBean> response) {
                if (response.body().getResult().getRes_data() != null) {
                    List<GetStockMoveResponseBean.ResultBean.ResDataBean> res_data = response.body().getResult().getRes_data();
                    moveStockAdapter.setmData(res_data);
                    recycleView.setAdapter(moveStockAdapter);
                }
            }

            @Override
            public void onFailure(Call<GetStockMoveResponseBean> call, Throwable t) {

            }
        });
    }
}
