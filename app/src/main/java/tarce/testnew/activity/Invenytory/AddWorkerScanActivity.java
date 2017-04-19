package tarce.testnew.activity.Invenytory;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import tarce.testnew.Utils.MyLog;
import tarce.testnew.Utils.ToastUtils;

/**
 * Created by Daniel.Xu on 2017/2/22.
 */

public class AddWorkerScanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(com.uuzuche.lib_zxing.R.layout.camera);
        CaptureFragment captureFragment = new CaptureFragment();
        captureFragment.setAnalyzeCallback(analyzeCallback);
        getSupportFragmentManager().beginTransaction().replace(com.uuzuche.lib_zxing.R.id.fl_zxing_container, captureFragment).commit();
    }


    /**
     * 二维码解析回调函数
     */
    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            ToastUtils.showToast(AddWorkerScanActivity.this,result);


        }

        @Override
        public void onAnalyzeFailed() {
            ToastUtils.showToast(AddWorkerScanActivity.this,"扫描失败");
        }
    };
}
