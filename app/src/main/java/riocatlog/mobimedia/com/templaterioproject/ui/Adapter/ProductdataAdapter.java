package riocatlog.mobimedia.com.templaterioproject.ui.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import riocatlog.mobimedia.com.templaterioproject.R;
import riocatlog.mobimedia.com.templaterioproject.ui.model.ProductData;

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
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.productdataitemliste, null);
            holder.entityid = (TextView) convertView.findViewById(R.id.entityid);
            holder.entityidset = (TextView) convertView.findViewById(R.id.entityname);
            holder.type_id = (TextView) convertView.findViewById(R.id.type_id);
            holder.price = (TextView) convertView.findViewById(R.id.price);
            holder.visibility = (TextView) convertView.findViewById(R.id.visibility);
            holder.descritiponmtxt = (TextView) convertView.findViewById(R.id.descritiponmtxt);
            holder.descritiponmtxt.setText("Description");
            holder.description = (TextView) convertView.findViewById(R.id.description);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ProductData mproductdata = (ProductData) getItem(position);

        holder.entityid.setText(mproductdata.entity_id);
        holder.entityidset.setText(mproductdata.entity_type_id);
        holder.type_id.setText(mproductdata.type_id);
        holder.price.setText(mproductdata.price);
        holder.visibility.setText(mproductdata.visibility);
        holder.description.setText(mproductdata.description);
        return convertView;
    }

    /*private view holder class*/
    static class ViewHolder {
        TextView entityid;
        TextView entityidset;
        TextView price;
        TextView type_id;
        TextView visibility;
        TextView descritiponmtxt;
        TextView description;
    }

}
