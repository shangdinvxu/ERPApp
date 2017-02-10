package tarce.testnew.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.ButterKnife;
import butterknife.InjectView;
import tarce.testnew.R;
import tarce.testnew.Utils.GlideCacheUtil;
import tarce.testnew.http.RetrofitClient;
import tarce.testnew.http.api.MRPApi;

public class ProductionDetailNoClickActivity extends AppCompatActivity {
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.imageView)
    ImageView imageView;
    @InjectView(R.id.partNO)
    TextView partNO;
    @InjectView(R.id.partNOText)
    TextView partNOText;
    @InjectView(R.id.location)
    TextView location;
    @InjectView(R.id.locationText)
    TextView locationText;
    @InjectView(R.id.theoretical_qty)
    TextView theoreticalQty;
    @InjectView(R.id.theoretical_qtyText)
    TextView theoreticalQtyText;
    @InjectView(R.id.product_qty)
    TextView productQty;
    @InjectView(R.id.product_qtyText)
    TextView productQtyText;
    private MRPApi mrpApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production_detail_no_click);
        ButterKnife.inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mrpApi = RetrofitClient.getInstance(ProductionDetailNoClickActivity.this).create(MRPApi.class);
        initIntent();
    }

    private void initIntent() {
        Bundle bundle = getIntent().getBundleExtra("bundle");
        String image_medium = bundle.getString("image_medium");
        String product_name = bundle.getString("product_name");
        int product_qty = bundle.getInt("product_qty");
        int theoretical_qty = bundle.getInt("theoretical_qty");
        partNOText.setText(product_name);
        productQtyText.setText(product_qty+"");
        theoreticalQtyText.setText(theoretical_qty+"");
        GlideCacheUtil.getInstance().clearImageAllCache(ProductionDetailNoClickActivity.this);
        Glide.with(ProductionDetailNoClickActivity.this)
                .load(image_medium)
                .into(imageView);
    }

}
