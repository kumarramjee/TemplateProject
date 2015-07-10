package riocatlog.mobimedia.com.templaterioproject.ui.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import riocatlog.mobimedia.com.templaterioproject.R;
import riocatlog.mobimedia.com.templaterioproject.ui.Adapter.MediaGaLaryListAdapter;
import riocatlog.mobimedia.com.templaterioproject.ui.Adapter.ProductdataAdapter;
import riocatlog.mobimedia.com.templaterioproject.ui.model.MediaGalaryList;
import riocatlog.mobimedia.com.templaterioproject.ui.model.ProductData;
import riocatlog.mobimedia.com.templaterioproject.ui.utility.ReadJsonFromText;
import riocatlog.mobimedia.com.templaterioproject.ui.utility.ReadJsonFromTextFromMediaGalary;

public class NextActivity extends Activity {

    private ListView listeditem, prodctlist;
    private ProductdataAdapter mproductadapter;
    private Context mcontext = this;
    List<ProductData> mproduct;
    List<MediaGalaryList> mMediagalrylist;
    ReadJsonFromText mReadJsonFromExternal;
    MediaGaLaryListAdapter mMediaGaLaryListAdapter;
    ReadJsonFromTextFromMediaGalary mreadJsonFromTextFromMediaGalary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        SetUPUI();
        String jsonString = loadJSONFromAsset();
        mproduct = new ArrayList<ProductData>();
        mMediagalrylist = new ArrayList<MediaGalaryList>();
        mReadJsonFromExternal = new ReadJsonFromText();
        mproduct = mReadJsonFromExternal.ReadJsonFromExternal(jsonString);
        mproductadapter = new ProductdataAdapter(mcontext, mproduct);
        prodctlist.setAdapter(mproductadapter);
        mreadJsonFromTextFromMediaGalary = new ReadJsonFromTextFromMediaGalary();
        mMediagalrylist = mreadJsonFromTextFromMediaGalary.ReadJsonFromExternal(jsonString);
        mMediaGaLaryListAdapter = new MediaGaLaryListAdapter(mcontext, mMediagalrylist);
        listeditem.setAdapter(mMediaGaLaryListAdapter);

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