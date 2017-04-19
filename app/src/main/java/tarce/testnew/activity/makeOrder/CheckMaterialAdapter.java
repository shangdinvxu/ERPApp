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

public class CheckMaterialAdapter extends RecyclerView.Adapter<CheckMaterialAdapter.CheckMaterialViewHolder> {

    private List<OrderDetailResponse.ResultBean.ResDataBean.StockMoveLinesBean> stockMoveLinesBeans ;
    private Context context ;
    public CheckMaterialAdapter(Context context) {
        this.context = context ;
    }
    private OnItemClickListener itemClickListener;

    @Override
    public CheckMaterialViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CheckMaterialViewHolder viewHolder = new CheckMaterialViewHolder(LayoutInflater.from(
                context).inflate(R.layout.activity_check_material_item, parent,
                false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CheckMaterialViewHolder holder, final int position) {
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
        holder.number.setText(stockMoveLinesBeans.get(position).getReturn_qty()+"");
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

    public class CheckMaterialViewHolder extends RecyclerView.ViewHolder{
        TextView number;
        TextView product;

        public CheckMaterialViewHolder(View itemView) {
            super(itemView);
            number = (TextView) itemView.findViewById(R.id.number);
            product = (TextView) itemView.findViewById(R.id.product);
        }
    }
}
