package tarce.testnew.activity.product;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import java.io.Serializable;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tarce.testnew.IntentFactory;
import tarce.testnew.activity.OnItemClickListener;
import tarce.testnew.R;
import tarce.testnew.Utils.MyLog;
import tarce.testnew.ViewUtil.LoaderMoreView;
import tarce.testnew.ViewUtil.RefreshHeaderView;
import tarce.testnew.activity.BaseAppCompatActivity;
import tarce.testnew.http.RetrofitClient;
import tarce.testnew.http.api.ProductApi;
import tarce.testnew.http.bean.requestBean.GetproductlistRequsetBean;
import tarce.testnew.http.bean.responseBean.GetProductlistResponseBean;

/**
 * Created by Daniel.Xu on 2017/2/14.
 */

public class ProductActivity extends BaseAppCompatActivity implements Serializable {
    @InjectView(R.id.search_button)
    Button searchButton;
    private String TAG = this.getClass().getSimpleName();
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
    private int index = 0;
    private ProductItemAdapter productItemAdapter;
    private String type;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_product;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        getToolbarTitle().setText("产品");
        getSubTitle().setVisibility(View.GONE);
        productApi = RetrofitClient.getInstance(ProductActivity.this).create(ProductApi.class);
        type = getIntent().getStringExtra("type");
        initView();
        initProductList();
        initListener();
    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(ProductActivity.this);
        recycleView.setLayoutManager(layoutManager);
        recycleView.addItemDecoration(new DividerItemDecoration(ProductActivity.this,
                DividerItemDecoration.VERTICAL));
        productItemAdapter = new ProductItemAdapter(ProductActivity.this);

    }


    @OnClick(R.id.search_button)
    void toSearch(View view){
        IntentFactory.start_searchActivity(ProductActivity.this);
    }

    private void initListener() {
        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                index = 0;
                Call<GetProductlistResponseBean> all = productApi.getProductList(new GetproductlistRequsetBean(80, type, 0));
                all.enqueue(new Callback<GetProductlistResponseBean>() {
                    @Override
                    public void onResponse(Call<GetProductlistResponseBean> call, Response<GetProductlistResponseBean> response) {
                        if (response.body().getResult() != null) {
                            List<GetProductlistResponseBean.ResultBean.ResDataBean> res_data = response.body().getResult().getRes_data();
                            productItemAdapter.setmData(res_data);
                            swipeToLoadLayout.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<GetProductlistResponseBean> call, Throwable t) {
                    }
                });
            }
        });

        swipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                index++;
                MyLog.e(TAG, "index是" + index);
                Call<GetProductlistResponseBean> all = productApi.getProductList(new GetproductlistRequsetBean(80, type, index * 80));
                all.enqueue(new Callback<GetProductlistResponseBean>() {
                    @Override
                    public void onResponse(Call<GetProductlistResponseBean> call, Response<GetProductlistResponseBean> response) {
                        if (response.body().getResult() != null) {
                            List<GetProductlistResponseBean.ResultBean.ResDataBean> res_data = response.body().getResult().getRes_data();
                            List<GetProductlistResponseBean.ResultBean.ResDataBean> items = productItemAdapter.getItems();
                            items.addAll(res_data);
                            productItemAdapter.setmData(items);
                            swipeToLoadLayout.setLoadingMore(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<GetProductlistResponseBean> call, Throwable t) {
                    }
                });
            }
        });

        productItemAdapter.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Intent intent = new Intent(ProductActivity.this, ProductDetailActivity.class);
                GetProductlistResponseBean.ResultBean.ResDataBean resDataBean = productItemAdapter.getItems().get(postion);
                intent.putExtra("bean",resDataBean);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int postion) {

            }
        });
    }

    private void initProductList() {
        final Call<GetProductlistResponseBean> productList = productApi.getProductList(new GetproductlistRequsetBean(80, type, 0));
        MyLog.e(TAG, "get l ");
        productList.enqueue(new Callback<GetProductlistResponseBean>() {
            @Override
            public void onResponse(Call<GetProductlistResponseBean> call, Response<GetProductlistResponseBean> response) {
                MyLog.e(TAG, "onrespose l");
                if (response.body().getResult()!=null){
                    List<GetProductlistResponseBean.ResultBean.ResDataBean> res_data = response.body().getResult().getRes_data();
                    productItemAdapter.setmData(res_data);
                    recycleView.setAdapter(productItemAdapter);
                }
            }

            @Override
            public void onFailure(Call<GetProductlistResponseBean> call, Throwable t) {
                MyLog.e(TAG, t.toString());
            }
        });

    }


}
