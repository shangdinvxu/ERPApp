package tarce.testnew.ViewUtil;

/**
 * Created by Daniel.Xu on 2017/1/6.
 */

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeLoadMoreTrigger;
import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

/**
 * 基础的refreshHeadView
 */
public class RefreshHeaderView extends TextView implements SwipeRefreshTrigger, SwipeTrigger {

    public RefreshHeaderView(Context context) {
        super(context);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public void onPrepare() {

    }


    @Override
    public void onMove(int i, boolean b, boolean b1) {
        if (!b) {
            if (i <= -getHeight()) {
            setText("释放加载更多");
        } else {
            setText("滑动加载更多");
        }
        } else {
            setText("加载更多返回");
        }
    }


    @Override
    public void onRelease() {
        setText("加载更多");

    }

    @Override
    public void onComplete() {
        setText("刷新成功");
    }


    @Override
    public void onReset() {

    }



    @Override
    public void onRefresh() {
        setText("正在刷新");
    }
}