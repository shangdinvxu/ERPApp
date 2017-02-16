package tarce.testnew;

import android.content.Context;
import android.content.Intent;

import tarce.testnew.activity.LoginActivity;
import tarce.testnew.activity.MainActivity;
import tarce.testnew.activity.Invenytory.ScanActivity;
import tarce.testnew.activity.Invenytory.StockInventoryActivity;
import tarce.testnew.activity.makeOrder.MakeOrderActivity;
import tarce.testnew.activity.product.ProductActivity;
import tarce.testnew.activity.product.ProductSearchActivity;

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

    /**产品*/
    public static void start_ProductActivity(Context context,String type){
        Intent intent = new Intent(context, ProductActivity.class);
        intent.putExtra("type",type);
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

    public static void start_searchActivity(Context context){
        Intent intent = new Intent(context, ProductSearchActivity.class);
        context.startActivity(intent);
    }

    /**制造订单*/
    public static void start_makeOrderActivity(Context context){
        Intent intent = new Intent(context, MakeOrderActivity.class);
        context.startActivity(intent);
    }



}
