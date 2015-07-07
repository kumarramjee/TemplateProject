package riocatlog.mobimedia.com.templaterioproject.ui.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import riocatlog.mobimedia.com.templaterioproject.R;
import riocatlog.mobimedia.com.templaterioproject.ui.model.MainCategories;
import riocatlog.mobimedia.com.templaterioproject.ui.model.ProductData;
import riocatlog.mobimedia.com.templaterioproject.ui.model.ProductList;

/**
 * Created by ram on 7/7/15.
 */
public class ProductdataAdapter extends BaseAdapter {
    Context mContext;
    List<ProductData> mProductdata = new ArrayList<ProductData>();

    public ProductdataAdapter(Context mContext, List<ProductData> mprodust) {
        this.mContext = mContext;
        this.mProductdata = mprodust;
    }

    @Override
    public int getCount() {
        return mProductdata.size();
    }

    @Override
    public Object getItem(int position) {
        return mProductdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.productdataitem, null);
            holder = new ViewHolder();
            holder.entityid = (TextView) convertView.findViewById(R.id.entityid);
            holder.entityname = (TextView) convertView.findViewById(R.id.entityname);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ProductData mproductdata = (ProductData) getItem(position);

        holder.entityid.setText(mproductdata.entity_id);
        holder.entityname.setText(mproductdata.entity_type_id);

        return convertView;
    }

    public class ViewHolder {
        public TextView entityid;
        public TextView entityname;

    }


}
