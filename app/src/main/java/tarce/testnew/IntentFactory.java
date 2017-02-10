package tarce.testnew;

import android.content.Context;
import android.content.Intent;

import tarce.testnew.activity.LoginActivity;
import tarce.testnew.activity.MainActivity;
import tarce.testnew.activity.ScanActivity;
import tarce.testnew.activity.StockInventoryActivity;

/**
 * Created by Daniel.Xu on 2017/1/5.
 */

public class IntentFactory {
    public static void start_MainActivity(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static void start_StockInventoryActivity(Context context){
        Intent intent = new Intent(context, StockInventoryActivity.class);
        context.startActivity(intent);
    }

    public static void start_ScanActivity(Context context){
        Intent intent = new Intent(context, ScanActivity.class);
        context.startActivity(intent);
    }

    public static void start_LoginActivity(Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }




}
