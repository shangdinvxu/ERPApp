package tarce.testnew.MainFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tarce.testnew.R;
import tarce.testnew.http.bean.responseBean.GetMrpProductionResponseBean;

/**
 * Created by Daniel.Xu on 2017/1/23.
 */

public class MrpRecycleViewAdapter extends RecyclerView.Adapter<MrpRecycleViewAdapter.MrpProductionHolder> {
    private Context mContext ;
    private List<GetMrpProductionResponseBean.ResultBean.ResDataBean> mItems ;
    @Override
    public MrpProductionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MrpProductionHolder holder = new MrpProductionHolder(LayoutInflater.from(
                mContext).inflate(R.layout.mrp_production_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MrpProductionHolder holder, int position) {
        holder.mMO.setText(mItems.get(position).getDisplay_name());
        holder.mTime.setText(mItems.get(position).getDate_planned_start());
        holder.mProduct_name.setText(mItems.get(position).getProduct_name());
        holder.mState.setText(mItems.get(position).getState());
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
