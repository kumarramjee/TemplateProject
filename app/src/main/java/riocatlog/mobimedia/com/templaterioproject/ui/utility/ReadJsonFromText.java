package riocatlog.mobimedia.com.templaterioproject.ui.utility;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import riocatlog.mobimedia.com.templaterioproject.ui.model.Images;
import riocatlog.mobimedia.com.templaterioproject.ui.model.MainCategories;
import riocatlog.mobimedia.com.templaterioproject.ui.model.ProductData;

/**
 * Created by ram on 7/7/15.
 */
public class ReadJsonFromText {
Context mContext;

    /*public String loadJSONFromAsset() {
        String json = null;
        try {
            byte[] buffer;
            try (InputStream is = mContext.getAssets().open("jsontxt.txt")) {
                int size = is.available();
                buffer = new byte[size];
                is.read(buffer);
                is.close();
            }
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
*/



    public List<ProductData> ReadJsonFromExternal(String str) {
        List<ProductData> mListProductdata = new ArrayList<ProductData>();

        List<MainCategories> categorylist = new ArrayList<MainCategories>();

        try {
            MainCategories catitem = new MainCategories();
            JSONObject jsonObj = new JSONObject(str);
            String timestanp = jsonObj.getString("timestamp");
            String rootname = jsonObj.getString("rootcategory_name");
            String rootid = jsonObj.getString("rootcategory_id");
            String mediaurlname = jsonObj.getString("mediaurl");


             JSONObject jsonfirst = jsonObj.getJSONObject("categories");
            JSONArray childcategory = jsonfirst.getJSONArray("childcategories");

            for (int i = 0; i < childcategory.length(); i++) {
                Log.i("Length ", "of Child ==" + childcategory.length());
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

                JSONArray productlistarray = jsonfirstarrayobject.getJSONArray("productlist");
                ProductData productdata = new ProductData();


                for (int j = 0; j < productlistarray.length(); j++) {
                    //this is second internal array
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


                    String prod_position = jproductlistarray.getString("prod_position");
                    String is_in_stock = jproductlistarray.getString("is_in_stock");
                    String is_salable = jproductlistarray.getString("is_salable");
                    String tier_price_changed = jproductlistarray.getString("tier_price_changed");



                    mListProductdata.add(productdata);

                    Log.i("Cat", "Main Rio Product==" + mListProductdata.size());
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return mListProductdata;

    }

}
