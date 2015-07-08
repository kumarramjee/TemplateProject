package riocatlog.mobimedia.com.templaterioproject.ui.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import riocatlog.mobimedia.com.templaterioproject.R;
import riocatlog.mobimedia.com.templaterioproject.ui.Adapter.ProductdataAdapter;
import riocatlog.mobimedia.com.templaterioproject.ui.model.MainCategories;
import riocatlog.mobimedia.com.templaterioproject.ui.model.ProductData;

public class NextActivity extends Activity {

    private ListView listeditem, prodctlist;
    private ProductdataAdapter mproductadapter;
    private Context mcontext;
    private List<ProductData> mproduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        SetUPUI();
        mproduct = new ArrayList<ProductData>();
        String jsonString = loadJSONFromAsset();
        mproduct = ReadJsonFromExternal(jsonString);
        Log.i("Next Actity", "==" + mproduct);

        mproductadapter = new ProductdataAdapter(mcontext, mproduct);
        prodctlist.setAdapter(mproductadapter);
    }

    private List<ProductData> ReadJsonFromExternal(String jsonString) {
        List<ProductData> mListProductdata = new ArrayList<ProductData>();

        Log.i("", "" + jsonString);
        ProductData productdata = new ProductData();

        try {

            MainCategories catitem = new MainCategories();
            JSONObject jsonObj = new JSONObject(jsonString);
            JSONObject jsonfirst = jsonObj.getJSONObject("categories");
            JSONArray childcategory = jsonfirst.getJSONArray("childcategories");

            for (int i = 0; i < childcategory.length(); i++) {
                Log.i("Length ", "of Child ==" + childcategory.length());
                JSONObject jsonfirstarrayobject = childcategory.getJSONObject(i);

                JSONArray productlistarray = jsonfirstarrayobject.getJSONArray("productlist");

                for (int j = 0; j < productlistarray.length(); j++) {

                    JSONObject jproductlistarray = productlistarray.getJSONObject(j);
                    productdata.entity_id = jproductlistarray.getInt("entity_id");
                    productdata.entity_type_id = jproductlistarray.getString("entity_type_id");
                    productdata.attribute_set_id = jproductlistarray.getString("attribute_set_id");
                    productdata.type_id = jproductlistarray.getString("type_id");
                    productdata.sku = jproductlistarray.getString("sku");
                    productdata.has_options = jproductlistarray.getString("has_options");
                    productdata.required_options = jproductlistarray.getString("required_options");
                    productdata.created_at = jproductlistarray.getString("created_at");
                    productdata.updated_at = jproductlistarray.getString("updated_at");
                    productdata.name = jproductlistarray.getString("name");
                    productdata.image = jproductlistarray.getString("image");
                    productdata.small_image = jproductlistarray.getString("small_image");
                    productdata.thumbnail = jproductlistarray.getString("thumbnail");
                    productdata.url_key = jproductlistarray.getString("url_key");
                    productdata.url_path = jproductlistarray.getString("url_path");
                    productdata.options_container = jproductlistarray.getString("options_container");
                    productdata.image_label = jproductlistarray.getString("image_label");

                    Log.i("Next Activtiy", "Product Data Values==" + productdata.entity_id + "," + productdata.attribute_set_id);

                    mListProductdata.add(productdata);
                    Log.i("Next Activity","Product data=="+mListProductdata.size());

                }


            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return mListProductdata;


    }

    private void SetUPUI() {
        listeditem = (ListView) findViewById(R.id.listeditem);
        prodctlist = (ListView) findViewById(R.id.prodctlist);
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
}