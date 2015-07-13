package riocatlog.mobimedia.com.templaterioproject.ui.utility;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import riocatlog.mobimedia.com.templaterioproject.ui.model.MediaGalaryList;

/**
 * Created by ram on 10/7/15.
 */
public class ReadJsonFromTextFromMediaGalary {
    public List<MediaGalaryList> ReadJsonFromExternal(String str) {
        List<MediaGalaryList> mListProductdata = new ArrayList<MediaGalaryList>();
        MediaGalaryList mMediaGalaryList;
        try {
            JSONObject jsonObj = new JSONObject(str);
            JSONObject jsonfirst = jsonObj.getJSONObject("categories");
            JSONArray childcategory = jsonfirst.getJSONArray("childcategories");
            for (int i = 0; i < childcategory.length(); i++) {
                JSONObject jsonfirstarrayobject = childcategory.getJSONObject(i);
                JSONArray productlistarray = jsonfirstarrayobject.getJSONArray("productlist");
                for (int j = 0; j < productlistarray.length(); j++) {
                    JSONObject jprductlistarray = productlistarray.getJSONObject(j);
                    JSONObject jobject = jprductlistarray.getJSONObject("productdata");
                    JSONObject mediagalary = jobject.getJSONObject("media_gallery");
                    JSONArray imagesarray = mediagalary.getJSONArray("images");
                    for (int k = 0; k < imagesarray.length(); k++) {
                        mMediaGalaryList = new MediaGalaryList();
                        JSONObject jimageobj = imagesarray.getJSONObject(k);
                        mMediaGalaryList.value_id = jimageobj.getString("value_id");
                        mMediaGalaryList.file = jimageobj.getString("file");
                        mMediaGalaryList.label = jimageobj.getString("label_default");
                        mMediaGalaryList.position = jimageobj.getString("position");
                        mMediaGalaryList.disabled = jimageobj.getString("position_default");
                        mMediaGalaryList.label_default = jimageobj.getString("label_default");
                        mMediaGalaryList.position_default = jimageobj.getString("position_default");
                        mMediaGalaryList.disabled_default = jimageobj.getString("disabled_default");
                        mListProductdata.add(mMediaGalaryList);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mListProductdata;
    }
}

