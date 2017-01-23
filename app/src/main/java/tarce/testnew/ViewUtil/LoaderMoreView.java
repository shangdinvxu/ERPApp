package tarce.testnew.ViewUtil;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeLoadMoreTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

/**
 * Created by Daniel.Xu on 2017/1/6.
 */

public class LoaderMoreView extends TextView implements SwipeTrigger,SwipeLoadMoreTrigger {
    public LoaderMoreView(Context context) {
        super(context);
    }

    public LoaderMoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoaderMoreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onLoadMore() {
        setText("加载更多");
    }  @Override
    public void onPrepare() {
        setText("");
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

    public void onRelease() {
        setText("加载更多");
    }  @Override
    public void onComplete() {
        setText("完成");
    }  @Override
    public void onReset() {
        setText("");
    }
}
