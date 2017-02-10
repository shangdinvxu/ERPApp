package tarce.testnew.MainFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tarce.testnew.R;
import tarce.testnew.http.bean.responseBean.GetStockInventoryDetailResponse;
import tarce.testnew.http.bean.responseBean.GetStockInventoryResponse;

/**
 * Created by Daniel.Xu on 2017/1/23.
 */

public class StockInventoryDetailRecycleViewAdapter extends RecyclerView.Adapter<StockInventoryDetailRecycleViewAdapter.MrpProductionHolder> {
    private Context mContext ;
    private OnItemClickListener itemClickListener;
    private List<GetStockInventoryDetailResponse.ResultBean.ResDataBean.LineIdsBean> mItems ;
    @Override
    public MrpProductionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MrpProductionHolder holder = new MrpProductionHolder(LayoutInflater.from(
                mContext).inflate(R.layout.content_stock_inventory_detail_item, parent,
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
        holder.mNumber.setText((position+1)+"");
        holder.mTheoretical_qty.setText(mItems.get(position).getTheoretical_qty()+"");
        holder.mProduct_qty.setText(mItems.get(position).getProduct_qty()+"");
        holder.mProduction.setText(mItems.get(position).getProduct().getProduct_name());
        holder.mLocation.setText("");
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public StockInventoryDetailRecycleViewAdapter(Context context) {
        this.mContext = context;
    }

    public void setItems(List<GetStockInventoryDetailResponse.ResultBean.ResDataBean.LineIdsBean> items){
        this.mItems = items ;
    }


    public void setItemClickListener(OnItemClickListener onItemClickListener){
        this.itemClickListener = onItemClickListener ;
    }
    public List<GetStockInventoryDetailResponse.ResultBean.ResDataBean.LineIdsBean> getItems(){
        return mItems;
    }


    public class MrpProductionHolder extends RecyclerView.ViewHolder {
        TextView mNumber;
        TextView mProduction;
        TextView mLocation;
        TextView mTheoretical_qty;
        TextView mProduct_qty;
        public MrpProductionHolder ( View itemView ) {
            super(itemView );
            mNumber = (TextView) itemView.findViewById(R.id.number);
            mProduction = (TextView) itemView.findViewById(R.id.production);
            mLocation = (TextView) itemView.findViewById(R.id.location);
            mTheoretical_qty = (TextView) itemView.findViewById(R.id.theoretical_qty);
            mProduct_qty = (TextView) itemView.findViewById(R.id.product_qty);
        }
    }

}
