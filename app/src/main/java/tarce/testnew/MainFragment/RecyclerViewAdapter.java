package tarce.testnew.MainFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tarce.testnew.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  
    private static final int TYPE_IMAGE = 0;  
    private static final int TYPE_GROUP = 1;  
    private List<String> mData = new ArrayList<>();
    private Context mContext;
    private OnItemClickListener itemClickListener;

    private boolean isTitle(int pos){  
        if(mData.get(pos).startsWith("this is title:")) {  
            return true;  
        }  
        return false;  
    }  
  
    public RecyclerViewAdapter(Context mContext, List<String> mData) {  
        this.mContext = mContext;  
        this.mData = mData;  
    }  
  
    @Override  
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from (mContext);
        switch ( viewType ) {  
            case TYPE_IMAGE:  
                ViewGroup vImage = ( ViewGroup ) mInflater.inflate ( R.layout.item_image, parent, false );
                ImageViewHolder vhImage = new ImageViewHolder ( vImage );  
                return vhImage;  
            case TYPE_GROUP:  
                ViewGroup vGroup = ( ViewGroup ) mInflater.inflate ( R.layout.item_text, parent, false );  
                GroupViewHolder vhGroup = new GroupViewHolder ( vGroup );  
                return vhGroup;  
        }  
        return null;  
    }  
  
    @Override  
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //为整体布局设置点击事件，触发接口的回调
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClickListener!=null)
                    itemClickListener.onItemClick(v, position);
            }
        });

        switch ( holder.getItemViewType () ) {  
            case TYPE_IMAGE:  
                ImageViewHolder imageViewHolder = ( ImageViewHolder ) holder;  
                imageViewHolder.mImage.setImageResource ( R.drawable.ic_menu_camera);
                imageViewHolder.mTitle.setText(mData.get(position));  
                break;  
            case TYPE_GROUP:  
                GroupViewHolder groupViewHolder = ( GroupViewHolder ) holder;
                groupViewHolder.mTitle.setText ( mData.get(position));
                break;  
        }  
    }  
  
    @Override  
    public int getItemCount() {  
        return mData.size ();  
    }  
  
    @Override  
    public int getItemViewType ( int position ) {  
        int viewType;  
        if (!isTitle(position) ) {  
            viewType = TYPE_IMAGE;  
        } else {  
            viewType = TYPE_GROUP;  
        }  
        return viewType;  
    }  



    public void setItemClickListener(OnItemClickListener onItemClickListener){
        this.itemClickListener = onItemClickListener ;
    }



    public class GroupViewHolder extends RecyclerView.ViewHolder {  
        TextView mTitle;
        public GroupViewHolder ( View itemView ) {
            super(itemView);  
            mTitle= (TextView) itemView.findViewById(R.id.text);  
        }  
  
    }  
    public class ImageViewHolder extends RecyclerView.ViewHolder {  
        TextView mTitle;  
        ImageView mImage;
        public ImageViewHolder ( View itemView ) {  
            super(itemView );  
            mTitle= (TextView) itemView.findViewById(R.id.text);  
            mImage= (ImageView) itemView.findViewById(R.id.image);  
        }  
    }  
  
}