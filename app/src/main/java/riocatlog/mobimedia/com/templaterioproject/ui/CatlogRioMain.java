package riocatlog.mobimedia.com.templaterioproject.ui;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import riocatlog.mobimedia.com.templaterioproject.R;
import riocatlog.mobimedia.com.templaterioproject.ui.Adapter.CategorylistAdapter;
import riocatlog.mobimedia.com.templaterioproject.ui.Adapter.ExpandableListAdapter;
import riocatlog.mobimedia.com.templaterioproject.ui.model.Categories;
import riocatlog.mobimedia.com.templaterioproject.ui.model.ProductData;
import riocatlog.mobimedia.com.templaterioproject.ui.ui.NotiFicationActivity;


public class CatlogRioMain extends Activity implements TextView.OnClickListener {
    private TextView textshow;
    private TextView rootcatname;
    private TextView categoryid;
    private TextView mediaurltext;
    private List<Categories> catitems;
    private ListView categorylist;
    private CategorylistAdapter catadapter;
    private String jsonstring;
    private TextView notification;
    private String notificationtitle = "Rio Catlog";
    private String subject = "Rio Catlog Update";
    private String body = "MobiMedia is a mobile led digital solutions organization. Our focus is empowerment, enhancement and enrichment of consumer experience with a brand. To achieve this goal, our offerings are divided into Product Platforms and Bespoke Consumer Engagement Applications Services. Though we develop products, we believe in delivering them as Services to our clients. We are committed to work with our clients as partners and help them achieve their business goals.";
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    Bitmap bm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catlog_rio_main);
        SetupUI();
        jsonstring = loadJSONFromAsset();
        catitems = new ArrayList<Categories>();
        catitems = ReadJsonFromExternal(jsonstring);
        catadapter = new CategorylistAdapter(CatlogRioMain.this, catitems);
        categorylist.setAdapter(catadapter);
        prepareListData();


        notification.setOnClickListener(this);
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        listDataHeader.add("Rio Catlog Product");
        List<String> rioproduct = new ArrayList<String>();
        rioproduct.add("Product Name");
        rioproduct.add("Product Id");
        rioproduct.add("Product Categories");
        rioproduct.add("Product Data");
        rioproduct.add("Product List");
        listDataChild.put(listDataHeader.get(0), rioproduct);
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
        notification = (TextView) findViewById(R.id.notification);
        expListView = (ExpandableListView) findViewById(R.id.expendiblelistview);
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

            SetJsonParseValuetToTextView(timestanp, rootname, rootid);

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
                JSONArray productlistarray = jsonfirstarrayobject.getJSONArray("productlist");
                ProductData productdata = new ProductData();

                List<ProductData> mListProductdata = new ArrayList<ProductData>();

                for (int j = 0; j < productlistarray.length(); j++) {
                    //this is second internal array
                    JSONObject jproductlistarray = productlistarray.getJSONObject(j);

                    productdata.entity_id = jproductlistarray.getString("entity_id");
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

                    /*
                    Add media galary and image url

                    */
                    mListProductdata.add(productdata);


                }
                //Log.i("Catlog Rio Main", "values==:" + category_name + "," + category_name_arabic + "," + category_id + "," + is_active + "," + cat_position);

                //Second internal array starts from here


            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return categorylist;

    }

    private void SetJsonParseValuetToTextView(String timestanp, String rootname, String rootid) {
        textshow.setText("Timestamp :" + timestanp);
        rootcatname.setText("Category Name :" + rootname);
        categoryid.setText("Category Id :" + rootid);

    }

    private Bitmap getCircleBitmap(Bitmap bitmap) {
        final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final int color = Color.RED;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawOval(rectF, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        bitmap.recycle();

        return output;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.notification:
                showNotification();

                break;
            default:
                break;
        }
    }

    private void showNotification() {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(R.drawable.avatar1, notificationtitle, System.currentTimeMillis());


        Intent notifintent = new Intent(this, NotiFicationActivity.class);

        PendingIntent pending = PendingIntent.getActivity(getApplicationContext(), 0, notifintent, 0);
        notification.setLatestEventInfo(getApplicationContext(), subject, body, pending);
        mNotificationManager.notify(0, notification);


    }
}
