package tarce.testnew.MainFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
import tarce.testnew.http.RetrofitClient;
import tarce.testnew.http.api.MRPApi;
import tarce.testnew.http.bean.requestBean.GetMrpProductionRequestBean;

/**
 * Created by Daniel.Xu on 2017/1/5.
 */

public class Homefragment extends Fragment {
    private final static String TAG = Homefragment.class.getSimpleName();
    @InjectView(R.id.swipe_refresh_header)
    RefreshHeaderView swipeRefreshHeader;
    @InjectView(R.id.swipe_target)
    RecyclerView swipeTarget;
    @InjectView(R.id.swipe_load_more_footer)
    LoaderMoreView swipeLoadMoreFooter;
    @InjectView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;
    private ArrayList<String> strings;
    private View view;
    private RecyclerViewAdapter myRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.first_fragment, null);
        ButterKnife.inject(this, view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        //设置布局管理器,RecycleView一定要加的.
        swipeTarget.setLayoutManager(layoutManager);
        return view;
    }

    @Override
    public void onStart() {
        strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add(i + "recycleview");
        }
        strings.add(3,"this is title: 1");
        strings.add(4,"this is title: 2");
        strings.add(6,"this is title: 3");
        strings.add(8,"this is title: 4");
        strings.add(9,"this is title: 5");
        myRecyclerAdapter = new RecyclerViewAdapter(getActivity(), strings);
        swipeTarget.setAdapter(myRecyclerAdapter);
        initListener();
        super.onStart();

    }

    private void initListener() {
        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                for (int i = 0; i < 20; i++) {
                    strings.add(0, "刷新获得的数据");
                }
                ;              //设置下拉刷新结束
                myRecyclerAdapter.notifyDataSetChanged();
                swipeToLoadLayout.setRefreshing(false);
            }
        });      //为swipeToLoadLayout设置上拉加载更多监听者
        swipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                for (int i = 0; i < 20; i++) {
                    strings.add("加载更多获得的数据");
                }
                myRecyclerAdapter.notifyDataSetChanged();         //设置上拉加载更多结束
                swipeToLoadLayout.setLoadingMore(false);
            }
        });

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
