package tarce.testnew.Utils;

import android.content.Context;
import android.widget.Toast;

import tarce.testnew.R;

/**
 * Created by Daniel.Xu on 2017/2/21.
 */

public class ToastUtils {
    public static void showToast(Context context , String string){
        Toast.makeText(context,string,Toast.LENGTH_SHORT).show();

    }
}
