package tarce.testnew.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tarce.testnew.MainFragment.OnItemClickListener;
import tarce.testnew.MainFragment.StockInventoryDetailRecycleViewAdapter;
import tarce.testnew.MainFragment.LocalInventoryDetailRecycleViewAdapter;
import tarce.testnew.R;
import tarce.testnew.Utils.AvatarHelper;
import tarce.testnew.Utils.MyLog;
import tarce.testnew.Utils.SaveLocalUtils;
import tarce.testnew.Utils.SelectPicPopupWindow;
import tarce.testnew.greendaoBean.GreendaoUtils.SaveInventroyUtils;
import tarce.testnew.greendaoBean.SaveInventory;
import tarce.testnew.http.RetrofitClient;
import tarce.testnew.http.api.MRPApi;
import tarce.testnew.http.bean.responseBean.CreatInventoryResponse;
import tarce.testnew.http.bean.responseBean.FindProductByConditionResponse;
import tarce.testnew.http.bean.responseBean.GetStockInventoryDetailResponse;

public class ScanActivity extends AppCompatActivity {

    private  String TAG = this.getClass().getSimpleName();

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.recycleView)
    RecyclerView recycleView;
    @InjectView(R.id.submit_inventory)
    Button submitInventory;
    @InjectView(R.id.content_scan)
    LinearLayout contentScan;
    @InjectView(R.id.sheetName)
    EditText sheetName;
    private int REQUEST_CODE = 1;
    private MRPApi mrpApi;
    private LocalInventoryDetailRecycleViewAdapter localInventoryDetailRecycleViewAdapter;
    private SaveInventroyUtils saveInventroyUtils;
    private List<SaveInventory> saveInventories;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        view = LayoutInflater.from(ScanActivity.this).inflate(R.layout.activity_scan, null);
        ButterKnife.inject(this);
        initToolbar();
        mrpApi = RetrofitClient.getInstance(ScanActivity.this).create(MRPApi.class);
        initRecycleview();
        initListener();
        saveInventroyUtils = new SaveInventroyUtils();
        searchLocal();
    }

    private void initRecycleview() {
        localInventoryDetailRecycleViewAdapter = new LocalInventoryDetailRecycleViewAdapter(ScanActivity.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(ScanActivity.this);
        recycleView.setLayoutManager(layoutManager);
        recycleView.addItemDecoration(new DividerItemDecoration(ScanActivity.this,
                DividerItemDecoration.VERTICAL));
    }

    private void searchLocal() {
        saveInventories = saveInventroyUtils.searchALL();
        if (saveInventories !=null&& saveInventories.size()>0){
            localInventoryDetailRecycleViewAdapter.setItems(saveInventories);
            recycleView.setAdapter(localInventoryDetailRecycleViewAdapter);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        searchLocal();
    }

    private void initToolbar() {
        toolbar.setTitle("");
        toolbar.showOverflowMenu();
        toolbar.setNavigationIcon(R.mipmap.back);
        setSupportActionBar(toolbar);
    }

    private void initListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        localInventoryDetailRecycleViewAdapter.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Intent intent = new Intent(ScanActivity.this, SaveActivity.class);
                intent.putExtra("postion",postion);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int postion) {

            }
        });

    }


    @OnClick(R.id.submit_inventory)
    void setSubmitInventory(View view) {
        if (sheetName.getText().toString().length()==0){
            Toast.makeText(ScanActivity.this,"请填写表单名",Toast.LENGTH_SHORT).show();
        }else {
            GetStockInventoryDetailResponse.ResultBean.ResDataBean resDataBean = new GetStockInventoryDetailResponse.ResultBean.ResDataBean();
            resDataBean.setName(sheetName.getText().toString());
            ArrayList<GetStockInventoryDetailResponse.ResultBean.ResDataBean.LineIdsBean> lineIdsBeenlist= new ArrayList<>();
            for (SaveInventory saveInventory: saveInventories){
                GetStockInventoryDetailResponse.ResultBean.ResDataBean.LineIdsBean lineIdsBean = new GetStockInventoryDetailResponse.ResultBean.ResDataBean.LineIdsBean();
                lineIdsBean.setProduct_qty(saveInventory.getProduct_qty());
                lineIdsBean.setTheoretical_qty(saveInventory.getTheoretical_qty());
                GetStockInventoryDetailResponse.ResultBean.ResDataBean.LineIdsBean.ProductBean productBean = new GetStockInventoryDetailResponse.ResultBean.ResDataBean.LineIdsBean.ProductBean();
                productBean.setImage_medium(saveInventory.getImage_medium());
                productBean.setProduct_name(saveInventory.getProduct_name());
                productBean.setId(saveInventory.getId());
                GetStockInventoryDetailResponse.ResultBean.ResDataBean.LineIdsBean.ProductBean.AreaBean areaBean = new GetStockInventoryDetailResponse.ResultBean.ResDataBean.LineIdsBean.ProductBean.AreaBean();
                areaBean.setArea_name(false);
                areaBean.setId(false);
                productBean.setArea(areaBean);
                lineIdsBean.setProduct(productBean);
                lineIdsBeenlist.add(lineIdsBean);
            }
            resDataBean.setLine_ids(lineIdsBeenlist);
            Call<CreatInventoryResponse> stringCall = mrpApi.creatStockInventory(resDataBean);
            stringCall.enqueue(new Callback<CreatInventoryResponse>() {
                @Override
                public void onResponse(Call<CreatInventoryResponse> call, Response<CreatInventoryResponse> response) {
                    MyLog.e("response","onresponse");
                    int res_code = response.body().getResult().getRes_code();
                    if (res_code==1){
                        saveInventroyUtils.deleteDate();
                        finish();
                        Toast.makeText(ScanActivity.this,"提交成功",Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<CreatInventoryResponse> call, Throwable t) {

                }
            });
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.scan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(ScanActivity.this, CaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Intent intent = new Intent(ScanActivity.this, SaveActivity.class);
                    intent.putExtra("result", result);
                    startActivity(intent);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(ScanActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }

        }
    }

}
