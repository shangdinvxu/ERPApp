package tarce.testnew.activity.Invenytory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tarce.testnew.R;
import tarce.testnew.activity.OnItemClickListener;
import tarce.testnew.greendao.greendaoBeans.ContactsBean;

/**
 * Created by Daniel.Xu on 2017/2/21.
 */

public class SearchSuppleAdapter extends RecyclerView.Adapter<SearchSuppleAdapter.SearchViewHolder> {

    private Context context ;
    private OnItemClickListener itemClickListener;

    public void setList(List<ContactsBean> list) {
        this.list = list;
    }

    public List<ContactsBean> getList() {
        return list;
    }

    List<ContactsBean> list ;
    public SearchSuppleAdapter(Context context) {
        this.context = context ;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SearchViewHolder holder = new SearchViewHolder(LayoutInflater.from(
                context).inflate(R.layout.search_supple_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClickListener!=null)
                    itemClickListener.onItemClick(v, position);
            }
        });
        holder.textView.setText(list.get(position).getName());
    }



    @Override
    public int getItemCount() {
        return list.size();
    }
    public void setItemClickListener(OnItemClickListener onItemClickListener){
        this.itemClickListener = onItemClickListener ;
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {

        TextView  textView;
        public SearchViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }


    }

}
