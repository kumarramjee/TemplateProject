package riocatlog.mobimedia.com.templaterioproject.ui.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import riocatlog.mobimedia.com.templaterioproject.R;
import riocatlog.mobimedia.com.templaterioproject.ui.model.MainCategories;

/**
 * Created by ram on 6/7/15.
 */
public class CategorylistAdapter extends BaseAdapter {
    Context mcontext;
    List<MainCategories> mCategories = new ArrayList<MainCategories>();

    public CategorylistAdapter(Context mContext, List<MainCategories> catitems) {
        this.mcontext = mContext;
        this.mCategories = catitems;
    }

    @Override
    public int getCount() {
        Log.i("Category", "Adapter==" + mCategories.size());
        return mCategories.size();
    }

    @Override
    public Object getItem(int position) {
        return mCategories.get(position);
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
            LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.categorylistitem, null);
            holder.categoriesid = (TextView) convertView.findViewById(R.id.catidvalues);
            holder.categoryname = (TextView) convertView.findViewById(R.id.catnamevalues);
            holder.categorynamearebic = (TextView) convertView.findViewById(R.id.catnamearebicvalues);
            holder.categoryposition = (TextView) convertView.findViewById(R.id.catpositionsvalues);
            holder.is_active = (TextView) convertView.findViewById(R.id.catisactivevalues);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        MainCategories mcategories = (MainCategories) getItem(position);
        holder.categoriesid.setText(mcategories.categoriesid);
        holder.categoryname.setText(mcategories.categoryname);
        holder.categorynamearebic.setText(mcategories.categorynamearebic);
        holder.categoryposition.setText(mcategories.categoryposition);
        String y = "Yes";
        String n = "NO";
        if ((mcategories.is_active).equals("1")) {
            holder.is_active.setText(y);
        } else {
            holder.is_active.setText(n);
        }
        return convertView;
    }

    public class ViewHolder {
        public TextView categoriesid;
        public TextView categoryname;
        public TextView categorynamearebic;
        public TextView categoryposition;
        public TextView is_active;
    }
}
