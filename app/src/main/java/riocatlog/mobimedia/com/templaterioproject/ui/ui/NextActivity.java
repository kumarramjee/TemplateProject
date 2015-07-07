package riocatlog.mobimedia.com.templaterioproject.ui.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import riocatlog.mobimedia.com.templaterioproject.R;
import riocatlog.mobimedia.com.templaterioproject.ui.Adapter.ProductdataAdapter;
import riocatlog.mobimedia.com.templaterioproject.ui.model.ProductData;
import riocatlog.mobimedia.com.templaterioproject.ui.utility.ReadJsonFromText;

public class NextActivity extends Activity {
    ListView listeditem, prodctlist;
    ProductdataAdapter mproductadapter;
    Context mcontext;
    List<ProductData> mproduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        SetUPUI();
        mproduct = new ArrayList<ProductData>();
        ReadJsonFromText mreadfromjson = new ReadJsonFromText();
        String jsonString = loadJSONFromAsset();
        mproduct = mreadfromjson.ReadJsonFromExternal(jsonString);
        Log.i("Next activity", "values==" + mproduct.size());

        mproductadapter = new ProductdataAdapter(mcontext, mproduct);
        prodctlist.setAdapter(mproductadapter);
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
