package tarce.testnew.activity.Invenytory;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tarce.testnew.R;
import tarce.testnew.Utils.MyLog;
import tarce.testnew.activity.BaseAppCompatActivity;
import tarce.testnew.http.RetrofitClient;
import tarce.testnew.http.api.InventoryApi;
import tarce.testnew.http.bean.responseBean.GetGroupByListresponse;

/**
 * Created by Daniel.Xu on 2017/2/17.
 */

public class TransferActivity extends BaseAppCompatActivity {
    private String TAG = this.getClass().getSimpleName();
    private ViewGroup containerView;
    private TreeHolder.IconTreeItem nodeItem;
    private InventoryApi inventoryApi;
    private AndroidTreeView tView;
    private List<GetGroupByListresponse.ResultBean.ResDataBean> res_data ;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_transfers;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        containerView = (ViewGroup)findViewById(R.id.contaer);
        inventoryApi = RetrofitClient.getInstance(TransferActivity.this).create(InventoryApi.class);
        initData();
    }

    private void initListener() {
        if (tView!=null){
            tView.setDefaultNodeClickListener(new TreeNode.TreeNodeClickListener() {
                @Override
                public void onClick(TreeNode node, Object value) {
                    MyLog.e(TAG,node.getPath());
                    HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
                    objectObjectHashMap.put("groupby","state");
                    objectObjectHashMap.put("picking_type_id",res_data.get(Integer.parseInt(node.getPath())-1).getPicking_type_id().get(0));
                    objectObjectHashMap.put("model","stock.picking");
                    Call<GetGroupByListresponse> groupsByList = inventoryApi.getGroupsByList(objectObjectHashMap);
                    groupsByList.enqueue(new Callback<GetGroupByListresponse>() {
                        @Override
                        public void onResponse(Call<GetGroupByListresponse> call, Response<GetGroupByListresponse> response) {

                        }

                        @Override
                        public void onFailure(Call<GetGroupByListresponse> call, Throwable t) {

                        }
                    });
                }
            });
        }
    }

    private void initData() {
        final TreeNode root = TreeNode.root();
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("groupby","picking_type_id");
        objectObjectHashMap.put("model","stock.picking");
        Call<GetGroupByListresponse> groupsByList = inventoryApi.getGroupsByList(objectObjectHashMap);
        groupsByList.enqueue(new Callback<GetGroupByListresponse>() {

            @Override
            public void onResponse(Call<GetGroupByListresponse> call, Response<GetGroupByListresponse> response) {
                res_data = response.body().getResult().getRes_data();
                for (GetGroupByListresponse.ResultBean.ResDataBean resDataBean :res_data){
                    String name = resDataBean.getPicking_type_id().get(1).toString();
                    root.addChild( new TreeNode(new TreeHolder.IconTreeItem(name)).setViewHolder(new TreeHolder(TransferActivity.this)));
                }
                tView = new AndroidTreeView(TransferActivity.this, root);
                containerView.addView(tView.getView());
                initListener();
            }

            @Override
            public void onFailure(Call<GetGroupByListresponse> call, Throwable t) {

            }
        });
    }


    private void initView() {


//        TreeNode parent = new TreeNode("MyParentNode");
//        TreeNode child0 = new TreeNode("ChildNode0");
//        TreeNode child10 = new TreeNode("ChildNode0");
//        TreeNode child20 = new TreeNode("ChildNode0");
//        TreeNode child1 = new TreeNode("ChildNode1");
//        child0.addChildren(child10,child20);
//        parent.addChildren(child0, child1);
//        root.addChild(parent);

//        root.addChildren(socialNetworks,social);
//        AndroidTreeView tView = new AndroidTreeView(TransferActivity.this, root);
//        containerView.addView(tView.getView());
    }


}
