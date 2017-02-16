package tarce.testnew.activity.Invenytory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tarce.testnew.activity.OnItemClickListener;
import tarce.testnew.R;
import tarce.testnew.greendao.greendaoBeans.SaveInventory;

/**
 * Created by Daniel.Xu on 2017/1/23.
 */

public class LocalInventoryDetailRecycleViewAdapter extends RecyclerView.Adapter<LocalInventoryDetailRecycleViewAdapter.MrpProductionHolder> {
    private Context mContext ;
    private OnItemClickListener itemClickListener;
    private List<SaveInventory> mItems ;
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
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (itemClickListener!=null)
                    itemClickListener.onItemLongClick(view,position);
                return true;
            }
        });

        holder.mNumber.setText((position+1)+"");
        holder.mTheoretical_qty.setText(mItems.get(position).getTheoretical_qty()+"");
        holder.mProduct_qty.setText(mItems.get(position).getProduct_qty()+"");
        holder.mProduction.setText(mItems.get(position).getProduct_name());

        holder.mLocation.setText("");
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public LocalInventoryDetailRecycleViewAdapter(Context context) {
        this.mContext = context;
    }

    public void setItems(List<SaveInventory> items){
        this.mItems = items ;
    }


    public void setItemClickListener(OnItemClickListener onItemClickListener){
        this.itemClickListener = onItemClickListener ;
    }
    public List<SaveInventory> getItems(){
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
