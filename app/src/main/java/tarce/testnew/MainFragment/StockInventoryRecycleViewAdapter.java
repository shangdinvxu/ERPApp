package tarce.testnew.MainFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tarce.testnew.R;
import tarce.testnew.http.bean.requestBean.GetStockInventoryRequestBean;
import tarce.testnew.http.bean.responseBean.GetMrpProductionResponseBean;
import tarce.testnew.http.bean.responseBean.GetStockInventoryResponse;

/**
 * Created by Daniel.Xu on 2017/1/23.
 */

public class StockInventoryRecycleViewAdapter extends RecyclerView.Adapter<StockInventoryRecycleViewAdapter.MrpProductionHolder> {
    private Context mContext ;
    private OnItemClickListener itemClickListener;
    private List<GetStockInventoryResponse.ResultBean.ResDataBean> mItems ;
    @Override
    public MrpProductionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MrpProductionHolder holder = new MrpProductionHolder(LayoutInflater.from(
                mContext).inflate(R.layout.stock_inventory_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MrpProductionHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClickListener!=null)
                    itemClickListener.onItemClick(v, position);
            }
        });
        holder.mMO.setText(mItems.get(position).getName());
        holder.mTime.setText(mItems.get(position).getDate());
        holder.mState.setText(mItems.get(position).getState());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public StockInventoryRecycleViewAdapter(Context context) {
        this.mContext = context;
    }

    public void setItems(List<GetStockInventoryResponse.ResultBean.ResDataBean> items){
        this.mItems = items ;
    }


    public void setItemClickListener(OnItemClickListener onItemClickListener){
        this.itemClickListener = onItemClickListener ;
    }
    public List<GetStockInventoryResponse.ResultBean.ResDataBean> getItems(){
        return mItems;
    }


    public class MrpProductionHolder extends RecyclerView.ViewHolder {
        TextView mTime;
        TextView mMO;
        TextView mState;
        public MrpProductionHolder ( View itemView ) {
            super(itemView );
            mTime = (TextView) itemView.findViewById(R.id.time);
            mMO = (TextView) itemView.findViewById(R.id.MO);
            mState = (TextView) itemView.findViewById(R.id.state);
        }
    }

}
