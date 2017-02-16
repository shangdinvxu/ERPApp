package tarce.testnew.activity.product;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;

import java.io.Serializable;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import tarce.testnew.R;
import tarce.testnew.activity.BaseAppCompatActivity;
import tarce.testnew.http.bean.responseBean.GetProductlistResponseBean;

/**
 * Created by Daniel.Xu on 2017/2/15.
 */

public class ProductDetailActivity extends BaseAppCompatActivity implements Serializable {
    @InjectView(R.id.toolbar_subtitle)
    TextView toolbarSubtitle;
    @InjectView(R.id.toolbar_title)
    TextView toolbarTitle;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.imageView)
    ImageView imageView;
    @InjectView(R.id.partNO)
    TextView partNO;
    @InjectView(R.id.product_type)
    TextView productTypeTextView;
    @InjectView(R.id.location)
    TextView location;
    @InjectView(R.id.default_code)
    TextView defaultCode;
    @InjectView(R.id.theoretical_qty)
    TextView theoreticalQty;
    @InjectView(R.id.inner_code)
    TextView innerCode;
    @InjectView(R.id.product_qty)
    TextView productQty;
    @InjectView(R.id.inner_spec)
    TextView innerSpec;
    @InjectView(R.id.product_specs)
    TextView productSpecs;
    @InjectView(R.id.categ_id)
    TextView categId;
    @InjectView(R.id.move_inventory)
    ImageView moveInventory;
    @InjectView(R.id.move_inventory_linearlayout)
    LinearLayout moveInventoryLinearlayout;
    private GetProductlistResponseBean.ResultBean.ResDataBean bean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_product_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbarTitle().setText("产品");
        getSubTitle().setVisibility(View.GONE);
        ButterKnife.inject(this);
        initData();
    }

    private void initData() {
        bean = (GetProductlistResponseBean.ResultBean.ResDataBean) getIntent().getSerializableExtra("bean");
        Glide.with(ProductDetailActivity.this)
              .load(bean.getImage_medium())
                .into(imageView);
        String productType =null;
        if (bean.getType().equals("product")){
            productType = "可库存产品";
        }else if (bean.getType().equals("service")){
            productType = "服务";
        }else {
            // str isEqualToString:@"consu"])
            productType = "可消耗";
        }
        productTypeTextView.setText(productType);
        defaultCode.setText(bean.getDefault_code());
        innerCode.setText(bean.getInner_code());
        innerSpec.setText(bean.getInner_spec());
        productSpecs.setText(bean.getProduct_specs());
        categId.setText(bean.getCateg_id());
    }

    @OnClick(R.id.move_inventory_linearlayout)
    void setMoveInventory(View view){
        Intent intent = new Intent(ProductDetailActivity.this, MoveInventoryActivity.class);
        intent.putExtra("product_id",bean.getProduct_id());
        startActivity(intent);
    }


}
