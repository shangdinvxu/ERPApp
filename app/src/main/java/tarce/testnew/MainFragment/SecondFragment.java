package tarce.testnew.MainFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import java.util.ArrayList;
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
import tarce.testnew.activity.makeOrder.MrpRecycleViewAdapter;
import tarce.testnew.http.api.MRPApi;
import tarce.testnew.http.bean.requestBean.GetMrpProductionRequestBean;
import tarce.testnew.http.bean.responseBean.GetMrpProductionResponseBean;

/**
 * Created by Daniel.Xu on 2017/1/5.
 */

public class SecondFragment extends Fragment {
    private String TAG = this.getClass().getSimpleName();
    @InjectView(R.id.swipe_refresh_header)
    RefreshHeaderView swipeRefreshHeader;
    @InjectView(R.id.swipe_target)
    RecyclerView recycleView;
    @InjectView(R.id.swipe_load_more_footer)
    LoaderMoreView swipeLoadMoreFooter;
    @InjectView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;
    private ArrayList<String> strings;
    private MrpRecycleViewAdapter mrpRecycleViewAdapter;
    private MRPApi mrpApi;
    private int index = 0 ;
    List<GetMrpProductionResponseBean.ResultBean.ResDataBean> res_data ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment, null);
      /*  ButterKnife.inject(this, view);
        mrpApi = RetrofitClient.getInstance(getActivity()).create(MRPApi.class);
        getMrpProduction();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycleView.setLayoutManager(layoutManager);
        recycleView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));
        mrpRecycleViewAdapter = new MrpRecycleViewAdapter(getActivity());
        initListener();*/
        return view;
    }



    public void getMrpProduction(){
        Call<GetMrpProductionResponseBean> confirmed = mrpApi.getMrpProduction(new GetMrpProductionRequestBean(2, 0, "confirmed"));
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
                Call<GetMrpProductionResponseBean> confirmed = mrpApi.getMrpProduction(new GetMrpProductionRequestBean(2, 0, "confirmed"));
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
                Call<GetMrpProductionResponseBean> confirmed = mrpApi.getMrpProduction(new GetMrpProductionRequestBean(2, index*2, "confirmed"));
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
