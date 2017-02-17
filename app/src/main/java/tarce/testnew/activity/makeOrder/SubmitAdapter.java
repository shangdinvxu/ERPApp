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

public class SubmitAdapter extends RecyclerView.Adapter<SubmitAdapter.SubmitViewholder> {

    private List<OrderDetailResponse.ResultBean.ResDataBean.StockMoveLinesBean> stockMoveLinesBeans ;
    private Context context ;
    public SubmitAdapter(Context context) {
        this.context = context ;
    }
    private OnItemClickListener itemClickListener;

    @Override
    public SubmitViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        SubmitViewholder viewHolder = new SubmitViewholder(LayoutInflater.from(
                context).inflate(R.layout.submit_view_lines_adapter, parent,
                false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SubmitViewholder holder, final int position) {
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
        holder.quantity_available.setText(stockMoveLinesBeans.get(position).getQty_available()+"");
        holder.suggest_qty.setText(stockMoveLinesBeans.get(position).getSuggest_qty()+"");
        holder.product_uom_qty.setText(stockMoveLinesBeans.get(position).getProduct_uom_qty()+"");
        holder.preparationNumber.setText(stockMoveLinesBeans.get(position).getQuantity_ready()+"");
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

    public class SubmitViewholder extends RecyclerView.ViewHolder{
        TextView quantity_available;
        TextView suggest_qty;
        TextView product_uom_qty;
        TextView preparationNumber;
        TextView product;

        public SubmitViewholder(View itemView) {
            super(itemView);
            quantity_available = (TextView) itemView.findViewById(R.id.quantity_available);
            suggest_qty = (TextView) itemView.findViewById(R.id.suggest_qty);
            product_uom_qty = (TextView) itemView.findViewById(R.id.product_uom_qty);
            preparationNumber = (TextView) itemView.findViewById(R.id.PreparationNumber);
            product = (TextView) itemView.findViewById(R.id.product);
        }
    }
}
