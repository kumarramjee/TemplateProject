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
import riocatlog.mobimedia.com.templaterioproject.ui.model.MediaGalaryList;
import riocatlog.mobimedia.com.templaterioproject.ui.model.ProductData;

/**
 * Created by ram on 10/7/15.
 */
public class MediaGaLaryListAdapter extends BaseAdapter {
    List<MediaGalaryList> mediaGalaryLists = new ArrayList<MediaGalaryList>();
    Context mContext;

    public MediaGaLaryListAdapter(Context mContext, List<MediaGalaryList> mMedialist) {
        this.mContext = mContext;
        this.mediaGalaryLists = mMedialist;

    }

    @Override
    public int getCount() {
        return mediaGalaryLists.size();
    }

    @Override
    public Object getItem(int position) {
        return mediaGalaryLists.get(position);
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
            holder.value_id = (TextView) convertView.findViewById(R.id.entityid);
            holder.file = (TextView) convertView.findViewById(R.id.entityname);
            holder.label = (TextView) convertView.findViewById(R.id.type_id);
            holder.position = (TextView) convertView.findViewById(R.id.price);
            holder.disabled = (TextView) convertView.findViewById(R.id.visibility);
            holder.entityidtext = (TextView) convertView.findViewById(R.id.entityidtext);
            holder.entityidtext.setText("Value Id");
            holder.entitynametext = (TextView) convertView.findViewById(R.id.entitynametext);
            holder.entitynametext.setText("File");
            holder.pricetext = (TextView) convertView.findViewById(R.id.pricetext);
            holder.pricetext.setText("Position");
            holder.typeiidtext = (TextView) convertView.findViewById(R.id.typeiidtext);
            holder.typeiidtext.setText("Label_Default");
            holder.visibilitytext = (TextView) convertView.findViewById(R.id.visibilitytext);
            holder.visibilitytext.setText("Position_default");
            holder.descritiponmtxt = (TextView) convertView.findViewById(R.id.descritiponmtxt);
            holder.descritiponmtxt.setVisibility(View.INVISIBLE);
            holder.description = (TextView) convertView.findViewById(R.id.description);
            holder.description.setVisibility(View.INVISIBLE);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        MediaGalaryList mproductdata = (MediaGalaryList) getItem(position);

        holder.value_id.setText(mproductdata.value_id);
        holder.file.setText(mproductdata.file);
        holder.label.setText(mproductdata.label);
        holder.position.setText(mproductdata.position);
        holder.disabled.setText(mproductdata.disabled);
        return convertView;
    }

    /*private view holder class*/
    static class ViewHolder {
        TextView value_id;
        TextView file;
        TextView label;
        TextView position;
        TextView disabled;
        TextView entityidtext;
        TextView entitynametext;
        TextView pricetext;
        TextView typeiidtext;
        TextView visibilitytext;
        TextView descritiponmtxt;
        TextView description;
    }

}