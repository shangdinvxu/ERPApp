package tarce.testnew.Utils;

/**
 * Created by Daniel.Xu on 2017/2/16.
 */

public class StringSwitchUtils {
    public static String switchString(String string){
        String s = null ;
        if (string.equals("confirmed")){
           s="已确认" ;
        }else if (string.equals("waiting_material")){
            s="等待备料" ;
        }else if (string.equals("prepare_material_ing")){
            s="备料中..." ;
        }else if (string.equals("finish_prepare_material")){
            s="备料完成" ;
        }else if (string.equals("already_picking")){
            s="已领料" ;
        }else if (string.equals("progress")){
            s="进行中" ;
        }else if (string.equals("waiting_quality_inspection")){
            s="等待品检" ;
        }else if (string.equals("quality_inspection_ing")){
            s="品检中" ;
        }else if (string.equals("waiting_post_inventory")){
            s="等待入库" ;
        }else if (string.equals("waiting_inventory_material")){
            s="等待清点物料" ;
        }else if (string.equals("waiting_warehouse_inspection")){
            s="等待检验物料" ;
        }else if (string.equals("done")){
            s="完成" ;
        }else if (string.equals("cancel")){
            s="已取消" ;
        }
        return  s ;
    }


    public static String getProductionOrderType(String string){
        String s = null ;
        if (string.equals("stockup")){
            s="备货制" ;
        }else if (string.equals("ordering")){
            s="订单制" ;
        }
        return s ;
    }


}
