package riocatlog.mobimedia.com.templaterioproject.ui.utility;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import riocatlog.mobimedia.com.templaterioproject.ui.model.ChildCategory;
import riocatlog.mobimedia.com.templaterioproject.ui.model.MainCategories;
import riocatlog.mobimedia.com.templaterioproject.ui.model.ProductData;

/**
 * Created by ram on 8/7/15.
 */
public class ReadJsonFromText {
    public List<ProductData> ReadJsonFromExternal(String str) {
        List<ProductData> mListProductdata = new ArrayList<ProductData>();
        ProductData productdata;
        try {
            JSONObject jsonObj = new JSONObject(str);
            JSONObject jsonfirst = jsonObj.getJSONObject("categories");
            JSONArray childcategory = jsonfirst.getJSONArray("childcategories");
            for (int i = 0; i < childcategory.length(); i++) {
                JSONObject jsonfirstarrayobject = childcategory.getJSONObject(i);
                JSONArray productlistarray = jsonfirstarrayobject.getJSONArray("productlist");
                for (int j = 0; j < productlistarray.length(); j++) {
                    productdata = new ProductData();
                    JSONObject jproductlistarray = productlistarray.getJSONObject(j);
                    JSONObject jobject = jproductlistarray.getJSONObject("productdata");
                    productdata.entity_id = jobject.getString("entity_id");
                    productdata.entity_type_id = jobject.getString("entity_type_id");
                    productdata.type_id = jobject.getString("type_id");
                    productdata.price = jobject.getString("price");
                    productdata.visibility=jobject.getString("visibility");
                    productdata.description=jobject.getString("description");
                    mListProductdata.add(productdata);
                    /*
                    productdata.sku = jobject.getString("sku");
                    productdata.has_options = jobject.getString("has_options");
                    productdata.required_options = jobject.getString("required_options");
                    productdata.created_at = jobject.getString("created_at");
                    productdata.updated_at = jobject.getString("updated_at");
                    productdata.name = jobject.getString("name");
                    productdata.image = jobject.getString("image");
                    productdata.small_image = jobject.getString("small_image");
                    productdata.thumbnail = jobject.getString("thumbnail");
                    productdata.url_key = jobject.getString("url_key");
                    productdata.url_path = jobject.getString("url_path");
                    productdata.options_container = jobject.getString("options_container");
                    productdata.image_label = jobject.getString("image_label");*/


                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mListProductdata;
    }

}

