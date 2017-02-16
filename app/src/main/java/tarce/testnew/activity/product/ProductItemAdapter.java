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
import tarce.testnew.http.bean.responseBean.GetProductlistResponseBean;

/**
 * Created by Daniel.Xu on 2017/2/14.
 */

public class ProductItemAdapter extends RecyclerView.Adapter<ProductItemAdapter.GroupViewHolder> {
    private List<GetProductlistResponseBean.ResultBean.ResDataBean> mData = new ArrayList<>();
    private Context mContext;
    private OnItemClickListener itemClickListener;

    @Override
    public GroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from (mContext);
        View v = mInflater.inflate(R.layout.recycleview_product_item, null);
        GroupViewHolder groupViewHolder = new GroupViewHolder(v);
        return groupViewHolder;
    }

    public void setmData(List<GetProductlistResponseBean.ResultBean.ResDataBean> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    public List<GetProductlistResponseBean.ResultBean.ResDataBean> getItems(){
        return mData;
    }

    public ProductItemAdapter(Context mContext) {
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
        holder.own_number.setText(mData.get(position).getQty_available()+"");
        holder.prediction_number.setText(mData.get(position).getVirtual_available()+"");
        holder.type.setText(mData.get(position).getCateg_id());
        holder.reference.setText(mData.get(position).getDefault_code()+" "+mData.get(position).getProduct_name());
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
