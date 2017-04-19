package tarce.testnew.activity.makeOrder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tarce.testnew.R;
import tarce.testnew.activity.OnItemClickListener;
import tarce.testnew.http.bean.responseBean.OrderDetailResponse;

/**
 * Created by Daniel.Xu on 2017/2/17.
 */

public class StockMoveLinesAdapter extends RecyclerView.Adapter<StockMoveLinesAdapter.StockInventroyViewHolder> {

    private List<OrderDetailResponse.ResultBean.ResDataBean.StockMoveLinesBean> stockMoveLinesBeans ;
    private Context context ;
    public StockMoveLinesAdapter(Context context) {
        this.context = context ;
    }
    private OnItemClickListener itemClickListener;

    @Override
    public StockInventroyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        StockInventroyViewHolder viewHolder = new StockInventroyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.stock_view_lines_adapter, parent,
                false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StockInventroyViewHolder holder, final int position) {
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
                if(itemClickListener!=null)
                    itemClickListener.onItemLongClick(view, position);
                return true;
            }
        });
        holder.product.setText(stockMoveLinesBeans.get(position).getProduct_id());
        holder.qty_available.setText(stockMoveLinesBeans.get(position).getQty_available()+"");
        holder.virtual_available.setText(stockMoveLinesBeans.get(position).getVirtual_available()+"");
        holder.product_uom_qty.setText(stockMoveLinesBeans.get(position).getProduct_uom_qty()+"");
        holder.quantity_done.setText(stockMoveLinesBeans.get(position).getQuantity_done()+"");
        holder.quantity_ready.setText(stockMoveLinesBeans.get(position).getQuantity_available()+"");
    }

    public void setItems( List<OrderDetailResponse.ResultBean.ResDataBean.StockMoveLinesBean> stockMoveLinesBeans){
        this.stockMoveLinesBeans = stockMoveLinesBeans ;
        notifyDataSetChanged();
    }


    public void setItemClickListener(OnItemClickListener onItemClickListener){
        this.itemClickListener = onItemClickListener ;
    }

    public List<OrderDetailResponse.ResultBean.ResDataBean.StockMoveLinesBean> getItems(){
        return stockMoveLinesBeans ;
    }

    @Override
    public int getItemCount() {
        return stockMoveLinesBeans.size();
    }

    public class StockInventroyViewHolder extends RecyclerView.ViewHolder{
        TextView qty_available;
        TextView virtual_available;
        TextView quantity_ready;
        TextView product_uom_qty;
        TextView quantity_done;
        TextView product;

        public StockInventroyViewHolder(View itemView) {
            super(itemView);
            qty_available = (TextView) itemView.findViewById(R.id.qty_available);
            virtual_available = (TextView) itemView.findViewById(R.id.virtual_available);
            quantity_ready = (TextView) itemView.findViewById(R.id.quantity_available);
            product_uom_qty = (TextView) itemView.findViewById(R.id.product_uom_qty);
            quantity_done = (TextView) itemView.findViewById(R.id.quantity_done);
            product = (TextView) itemView.findViewById(R.id.product);
        }
    }
}
