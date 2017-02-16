package tarce.testnew.activity.product;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;

import butterknife.ButterKnife;
import butterknife.InjectView;
import tarce.testnew.R;
import tarce.testnew.activity.BaseAppCompatActivity;
import tarce.testnew.http.bean.responseBean.GetStockMoveResponseBean;

/**
 * Created by Daniel.Xu on 2017/2/15.
 */

public class MoveInventoryDetailActivity extends BaseAppCompatActivity {
    @InjectView(R.id.product_name)
    TextView productName;
    @InjectView(R.id.state)
    TextView state;
    @InjectView(R.id.number)
    TextView number;
    @InjectView(R.id.description)
    TextView description;
    @InjectView(R.id.location)
    TextView location;
    @InjectView(R.id.partNO)
    TextView partNO;
    @InjectView(R.id.location_purpose)
    TextView locationPurpose;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_move_inventory_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        getSubTitle().setVisibility(View.GONE);
        initData();
    }

    private void initData() {
        GetStockMoveResponseBean.ResultBean.ResDataBean  bean = (GetStockMoveResponseBean.ResultBean.ResDataBean) getIntent().getSerializableExtra("bean");
        productName.setText(bean.getProduct_id().getProduct_name());
        number.setText(bean.getProduct_uom_qty()+"");
        String productType =null;
        if (bean.getState().equals("draft")){
            productType = "新建";
        }else if (bean.getState().equals("cancel")){
            productType = "取消";
        }else if (bean.getState().equals("waiting")){
            productType = "等待其他移动";
        }else if (bean.getState().equals("confirmed")){
            productType = "等待可用";
        }else if (bean.getState().equals("assigned")){
            productType = "可用";
        }else {
            productType = "完成";
        }
        state.setText(productType);
        description.setText(bean.getName());
        location.setText(bean.getLocation());
        locationPurpose.setText(bean.getLocation_dest());

    }
}
