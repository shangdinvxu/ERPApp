package tarce.testnew.activity.makeOrder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tarce.testnew.activity.OnItemClickListener;
import tarce.testnew.R;
import tarce.testnew.http.bean.responseBean.GetMrpProductionResponseBean;

/**
 * Created by Daniel.Xu on 2017/1/23.
 */

public class MrpRecycleViewAdapter extends RecyclerView.Adapter<MrpRecycleViewAdapter.MrpProductionHolder> {
    private Context mContext ;
    private List<GetMrpProductionResponseBean.ResultBean.ResDataBean> mItems ;
    private OnItemClickListener onItemClickListener ;
    @Override
    public MrpProductionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MrpProductionHolder holder = new MrpProductionHolder(LayoutInflater.from(
                mContext).inflate(R.layout.mrp_production_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MrpProductionHolder holder, final int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener!=null)
                    onItemClickListener.onItemClick(view, position);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (onItemClickListener!=null)
                    onItemClickListener.onItemLongClick(view,position);
                return true;
            }
        });


        holder.mMO.setText(mItems.get(position).getDisplay_name());
        holder.mTime.setText(mItems.get(position).getDate_planned_start());
        holder.mProduct_name.setText(mItems.get(position).getProduct_name());

        if (mItems.get(position).getState().equals("confirmed")){
            holder.mState.setText("已确认");
        }else if (mItems.get(position).getState().equals("waiting_material")){
            holder.mState.setText("等待备料");
        }else if (mItems.get(position).getState().equals("prepare_material_ing")){
            holder.mState.setText("备料中...");
        }else if (mItems.get(position).getState().equals("finish_prepare_material")){
            holder.mState.setText("备料完成");
        }else if (mItems.get(position).getState().equals("already_picking")){
            holder.mState.setText("已领料");
        }else if (mItems.get(position).getState().equals("progress")){
            holder.mState.setText("进行中");
        }else if (mItems.get(position).getState().equals("waiting_quality_inspection")){
            holder.mState.setText("等待品检");
        }else if (mItems.get(position).getState().equals("quality_inspection_ing")){
            holder.mState.setText("品检中");
        }else if (mItems.get(position).getState().equals("waiting_post_inventory")){
            holder.mState.setText("等待入库");
        }else if (mItems.get(position).getState().equals("waiting_inventory_material")){
            holder.mState.setText("等待清点物料");
        }else if (mItems.get(position).getState().equals("waiting_warehouse_inspection")){
            holder.mState.setText("等待检验物料");
        }else if (mItems.get(position).getState().equals("done")){
            holder.mState.setText("完成");
        }else if (mItems.get(position).getState().equals("cancel")){
            holder.mState.setText("已取消");
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public MrpRecycleViewAdapter(Context context) {
        this.mContext = context;
    }

    public void setItems(List<GetMrpProductionResponseBean.ResultBean.ResDataBean> items){
        this.mItems = items ;
    }


    public void setOnclickListener(OnItemClickListener onclickListener){
        this.onItemClickListener = onclickListener ;
    }



    public List<GetMrpProductionResponseBean.ResultBean.ResDataBean> getItems(){
        return mItems;
    }


    public class MrpProductionHolder extends RecyclerView.ViewHolder {
        TextView mTime;
        TextView mMO;
        TextView mProduct_name;
        TextView mState;
        public MrpProductionHolder ( View itemView ) {
            super(itemView );
            mTime = (TextView) itemView.findViewById(R.id.time);
            mMO = (TextView) itemView.findViewById(R.id.MO);
            mProduct_name = (TextView) itemView.findViewById(R.id.product_name);
            mState = (TextView) itemView.findViewById(R.id.state);
        }
    }

}
