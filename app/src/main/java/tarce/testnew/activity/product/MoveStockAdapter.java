package tarce.testnew.activity.product;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tarce.testnew.activity.OnItemClickListener;
import tarce.testnew.R;
import tarce.testnew.http.bean.responseBean.GetStockMoveResponseBean;

/**
 * Created by Daniel.Xu on 2017/2/14.
 */

public class MoveStockAdapter extends RecyclerView.Adapter<MoveStockAdapter.GroupViewHolder> {
    private List<GetStockMoveResponseBean.ResultBean.ResDataBean> mData = new ArrayList<>();
    private Context mContext;
    private OnItemClickListener itemClickListener;

    @Override
    public GroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from (mContext);
        View v = mInflater.inflate(R.layout.recycleview_product_item, null);
        GroupViewHolder groupViewHolder = new GroupViewHolder(v);
        return groupViewHolder;
    }

    public void setmData(List<GetStockMoveResponseBean.ResultBean.ResDataBean> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    public List<GetStockMoveResponseBean.ResultBean.ResDataBean> getItems(){
        return mData;
    }

    public MoveStockAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onBindViewHolder(GroupViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClickListener!=null)
                    itemClickListener.onItemClick(v, position);
            }
        });
        holder.prediction_number.setText(mData.get(position).getLocation_dest());
        holder.own_number.setText(mData.get(position).getLocation());
        holder.type.setText(mData.get(position).getProduct_uom_qty()+"");
        holder.reference.setText(mData.get(position).getName());
    }



    public void setItemClickListener(OnItemClickListener onItemClickListener){
        this.itemClickListener = onItemClickListener ;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class GroupViewHolder extends RecyclerView.ViewHolder {
        TextView prediction_number;
        TextView type;
        TextView own_number;
        TextView reference;

        public GroupViewHolder ( View itemView ) {
            super(itemView);
            reference= (TextView) itemView.findViewById(R.id.reference);
            type= (TextView) itemView.findViewById(R.id.type);
            own_number= (TextView) itemView.findViewById(R.id.own_number);
            prediction_number= (TextView) itemView.findViewById(R.id.prediction_number);
        }

    }



}
