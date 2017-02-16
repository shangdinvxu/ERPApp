package tarce.testnew.activity.makeOrder;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
import tarce.testnew.R;
import tarce.testnew.Utils.MyLog;
import tarce.testnew.ViewUtil.LoaderMoreView;
import tarce.testnew.ViewUtil.RefreshHeaderView;
import tarce.testnew.activity.BaseAppCompatActivity;
import tarce.testnew.http.RetrofitClient;
import tarce.testnew.http.api.MakeOrderApi;
import tarce.testnew.http.bean.requestBean.GetMrpProductionRequestBean;
import tarce.testnew.http.bean.responseBean.GetMrpProductionResponseBean;

/**
 * Created by Daniel.Xu on 2017/2/16.
 */

public class MakeOrderActivity extends BaseAppCompatActivity {
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
    private MakeOrderApi makeOrderApi;
    private MrpRecycleViewAdapter mrpRecycleViewAdapter;
    private int index = 0 ;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_makeorder;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        makeOrderApi = RetrofitClient.getInstance(MakeOrderActivity.this).create(MakeOrderApi.class);
        initView();
        getMrpProduction();
        initListener();
    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(MakeOrderActivity.this);
        recycleView.setLayoutManager(layoutManager);
        recycleView.addItemDecoration(new DividerItemDecoration(MakeOrderActivity.this,
                DividerItemDecoration.VERTICAL));
        mrpRecycleViewAdapter = new MrpRecycleViewAdapter(MakeOrderActivity.this);
    }

    public void getMrpProduction(){
        Call<GetMrpProductionResponseBean> confirmed = makeOrderApi.getMrpProduction(new GetMrpProductionRequestBean(80, 0, null));
        confirmed.enqueue(new Callback<GetMrpProductionResponseBean>() {
            @Override
            public void onResponse(Call<GetMrpProductionResponseBean> call, Response<GetMrpProductionResponseBean> response) {
                List<GetMrpProductionResponseBean.ResultBean.ResDataBean> res_data = response.body().getResult().getRes_data();
                mrpRecycleViewAdapter.setItems(res_data);
                recycleView.setAdapter(mrpRecycleViewAdapter);
            }
            @Override
            public void onFailure(Call<GetMrpProductionResponseBean> call, Throwable t) {
            }
        });
    }

    private void initListener() {
        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                index = 0;
                Call<GetMrpProductionResponseBean> confirmed = makeOrderApi.getMrpProduction(new GetMrpProductionRequestBean(80, 0, null));
                confirmed.enqueue(new Callback<GetMrpProductionResponseBean>() {
                    @Override
                    public void onResponse(Call<GetMrpProductionResponseBean> call, Response<GetMrpProductionResponseBean> response) {
                        mrpRecycleViewAdapter.setItems(response.body().getResult().getRes_data());
                        mrpRecycleViewAdapter.notifyDataSetChanged();
                        swipeToLoadLayout.setRefreshing(false);
                    }
                    @Override
                    public void onFailure(Call<GetMrpProductionResponseBean> call, Throwable t) {

                    }
                });
            }
        });      //为swipeToLoadLayout设置上拉加载更多监听者
        swipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                index++;
                MyLog.e(TAG,"index是"+index);
                Call<GetMrpProductionResponseBean> confirmed = makeOrderApi.getMrpProduction(new GetMrpProductionRequestBean(80, index*80,null));
                confirmed.enqueue(new Callback<GetMrpProductionResponseBean>() {
                    @Override
                    public void onResponse(Call<GetMrpProductionResponseBean> call, Response<GetMrpProductionResponseBean> response) {
                        List<GetMrpProductionResponseBean.ResultBean.ResDataBean> res_data = response.body().getResult().getRes_data();
                        List<GetMrpProductionResponseBean.ResultBean.ResDataBean> items = mrpRecycleViewAdapter.getItems();
                        items.addAll(res_data);
                        mrpRecycleViewAdapter.notifyDataSetChanged();         //设置上拉加载更多结束
                        swipeToLoadLayout.setLoadingMore(false);
                    }
                    @Override
                    public void onFailure(Call<GetMrpProductionResponseBean> call, Throwable t) {

                    }
                });

            }
        });




    }



}
