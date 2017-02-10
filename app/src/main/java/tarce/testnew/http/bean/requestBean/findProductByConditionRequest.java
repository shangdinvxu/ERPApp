package tarce.testnew.http.bean.requestBean;

import android.service.notification.Condition;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Daniel.Xu on 2017/2/8.
 */

public class findProductByConditionRequest {

    public findProductByConditionRequest(Map condition) {
        this.condition = condition;
    }

    private Map condition ;

}
