package tarce.testnew.activity.Invenytory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.unnamed.b.atv.model.TreeNode;

import tarce.testnew.R;

/**
 * Created by Daniel.Xu on 2017/2/17.
 */

public class TreeHolder  extends TreeNode.BaseNodeViewHolder<TreeHolder.IconTreeItem> {
    private String s ;
    public TreeHolder(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(TreeNode node, IconTreeItem value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.layout_profile_node, null, false);
        TextView tvValue = (TextView) view.findViewById(R.id.node_value);
        tvValue.setText(value.text);
        return view;
    }
    public static class IconTreeItem {
        public IconTreeItem(String text) {
            this.text = text;
        }
        public String text;
    }
}
