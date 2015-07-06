package riocatlog.mobimedia.com.templaterioproject.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import riocatlog.mobimedia.com.templaterioproject.R;
import riocatlog.mobimedia.com.templaterioproject.ui.Adapter.CategorylistAdapter;
import riocatlog.mobimedia.com.templaterioproject.ui.model.Categories;


public class CatlogRioMain extends Activity {
    TextView textshow;
    TextView rootcatname;
    TextView categoryid;
    TextView mediaurltext;
    List<Categories> catitems = new ArrayList<Categories>();
    ListView categorylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catlog_rio_main);
        SetupUI();
        String str = loadJSONFromAsset();
        catitems = ReadJsonFromExternal(str);

        Log.i("Categorty list ", "item==" + catitems);

        CategorylistAdapter catadapter = new CategorylistAdapter(CatlogRioMain.this, catitems);
        categorylist.setAdapter(catadapter);
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("jsontxt.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void SetupUI() {
        textshow = (TextView) findViewById(R.id.textshow);
        rootcatname = (TextView) findViewById(R.id.rootcatname);
        categoryid = (TextView) findViewById(R.id.categoryid);
        mediaurltext = (TextView) findViewById(R.id.mediaurl);
        categorylist = (ListView) findViewById(R.id.categorylist);
    }

    private List<Categories> ReadJsonFromExternal(String str) {
        List<Categories> categorylist = new ArrayList<Categories>();

        try {
            Categories catitem = new Categories();

            JSONObject jsonObj = new JSONObject(str);
            String timestanp = jsonObj.getString("timestamp");
            String rootname = jsonObj.getString("rootcategory_name");
            String rootid = jsonObj.getString("rootcategory_id");
            String mediaurlname = jsonObj.getString("mediaurl");

            //first jsonB=object is staring from here
            JSONObject jsonfirst = jsonObj.getJSONObject("categories");

            //starting first array from here

            JSONArray childcategory = jsonfirst.getJSONArray("childcategories");

            for (int i = 0; i < childcategory.length(); i++) {
                JSONObject jsonfirstarrayobject = childcategory.getJSONObject(i);

                String category_name = jsonfirstarrayobject.getString("category_name");
                String category_name_arabic = jsonfirstarrayobject.getString("category_name_arabic");
                String category_id = jsonfirstarrayobject.getString("category_id");
                String is_active = jsonfirstarrayobject.getString("is_active");
                String cat_position = jsonfirstarrayobject.getString("cat_position");
                catitem.categoriesid = category_id;
                catitem.categoryname = category_name;
                catitem.categorynamearebic = category_name_arabic;
                catitem.categoryposition = cat_position;
                catitem.is_active = is_active;
                categorylist.add(catitem);


                Log.i("Catlog Rio Main", "values==:" + category_name + "," + category_name_arabic + "," + category_id + "," + is_active + "," + cat_position);

                //Second internal array starts from here


            }


            SetJsonParseValuetToTextView(timestanp, rootname, rootid, mediaurlname);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return categorylist;

    }

    private void SetJsonParseValuetToTextView(String timestanp, String rootname, String rootid, String mediaurl) {
        textshow.setText("Timestamp :" + timestanp);
        rootcatname.setText("Category Name :" + rootname);
        categoryid.setText("Category Id :" + rootid);
        mediaurltext.setText("Url :" + mediaurl);
    }

}
