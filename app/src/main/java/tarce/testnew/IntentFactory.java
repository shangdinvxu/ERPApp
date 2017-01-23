package tarce.testnew;

import android.content.Context;
import android.content.Intent;

import tarce.testnew.activity.MainActivity;

/**
 * Created by Daniel.Xu on 2017/1/5.
 */

public class IntentFactory {
    public static void start_MainActivity(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}
