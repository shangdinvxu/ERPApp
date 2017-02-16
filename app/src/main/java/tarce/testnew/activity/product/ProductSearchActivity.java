package tarce.testnew.activity.product;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tarce.testnew.activity.OnItemClickListener;
import tarce.testnew.R;
import tarce.testnew.Utils.MyLog;
import tarce.testnew.activity.BaseAppCompatActivity;
import tarce.testnew.http.RetrofitClient;
import tarce.testnew.http.api.ProductApi;
import tarce.testnew.http.bean.responseBean.GetProductlistResponseBean;

/**
 * Created by Daniel.Xu on 2017/2/14.
 */

public class ProductSearchActivity extends BaseAppCompatActivity {
    private String TAG  = this.getClass().getSimpleName() ;
    @InjectView(R.id.toolbar_subtitle)
    TextView toolbarSubtitle;
    @InjectView(R.id.toolbar_title)
    TextView toolbarTitle;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.radioButton_productNumber)
    RadioButton radioButtonProductNumber;
    @InjectView(R.id.radioButton_productName)
    RadioButton radioButtonProductName;
    @InjectView(R.id.radioGroup)
    RadioGroup radioGroup;
    @InjectView(R.id.recycleView)
    RecyclerView recycleView;
    @InjectView(R.id.edittext)
    EditText edittext;
    @InjectView(R.id.search_button)
    Button searchButton;
    private ProductApi productApi;
    private ProductItemAdapter productItemAdapter;
    private List<GetProductlistResponseBean.ResultBean.ResDataBean> res_data;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_product_search;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbarTitle().setText("搜索");
        getSubTitle().setVisibility(View.GONE);
        ButterKnife.inject(this);
        productApi = RetrofitClient.getInstance(ProductSearchActivity.this).create(ProductApi.class);
        initView();
        initListener();
    }

    private void initListener() {
        productItemAdapter.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Intent intent = new Intent(ProductSearchActivity.this,ProductDetailActivity.class);
                intent.putExtra("bean", res_data.get(postion));
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int postion) {

            }
        });
    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(ProductSearchActivity.this);
        recycleView.setLayoutManager(layoutManager);
        recycleView.addItemDecoration(new DividerItemDecoration(ProductSearchActivity.this,
                DividerItemDecoration.VERTICAL));
        radioButtonProductNumber.setChecked(true);
        productItemAdapter = new ProductItemAdapter(ProductSearchActivity.this);

    }

    @OnClick(R.id.search_button)
    void toSearch(View view) {
        if (edittext.getText().toString().length()>0){
            HashMap<Object, Object> hashMap = new HashMap<>();
            HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
            if (radioButtonProductNumber.isChecked()){
                objectObjectHashMap.put("default_code",edittext.getText().toString());
            }else {
                objectObjectHashMap.put("name",edittext.getText().toString());
            }
            hashMap.put("condition",objectObjectHashMap);
            hashMap.put("limit",100);
            hashMap.put("offset",0);
            hashMap.put("product_type","all");
            Call<GetProductlistResponseBean> productByMap = productApi.getProductByMap(hashMap);
            productByMap.enqueue(new Callback<GetProductlistResponseBean>() {
                @Override
                public void onResponse(Call<GetProductlistResponseBean> call, Response<GetProductlistResponseBean> response) {
                    if (response.body().getResult().getRes_data()!=null){
                        res_data = response.body().getResult().getRes_data();
                        productItemAdapter.setmData(res_data);
                        recycleView.setAdapter(productItemAdapter);
                    }else {
                        Toast.makeText(ProductSearchActivity.this,"搜索不到",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetProductlistResponseBean> call, Throwable t) {
                    MyLog.e(TAG,t.toString());
                }
            });
        }else {
            Toast.makeText(ProductSearchActivity.this,"请输入查询",Toast.LENGTH_SHORT).show();
        }

    }
}
