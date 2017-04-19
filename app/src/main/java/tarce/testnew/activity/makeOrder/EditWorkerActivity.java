package tarce.testnew.activity.makeOrder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import tarce.testnew.R;
import tarce.testnew.activity.BaseAppCompatActivity;
import tarce.testnew.activity.Invenytory.AddWorkerScanActivity;

/**
 * Created by Daniel.Xu on 2017/2/21.
 */

public class EditWorkerActivity extends BaseAppCompatActivity {

    @InjectView(R.id.toolbar_subtitle)
    TextView toolbarSubtitle;
    @InjectView(R.id.toolbar_title)
    TextView toolbarTitle;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_editworker;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        getSubTitle().setText("添加工人");
    }

    @OnClick(R.id.toolbar_subtitle)
    void addWorker(View view){
        Intent intent = new Intent(EditWorkerActivity.this, AddWorkerScanActivity.class);
        startActivity(intent);
    }
}
