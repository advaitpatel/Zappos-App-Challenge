package com.ilovenougat.classes.otheractivities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ilovenougat.classes.R;
import com.ilovenougat.classes.asynctasks.Response;
import com.ilovenougat.classes.asynctasks.SearchAsynctask;
import com.ilovenougat.classes.dto.ProductDTO;
import com.ilovenougat.classes.utils.Constants;
import com.ilovenougat.classes.utils.MyRecyclerAdapter;
import com.ilovenougat.classes.utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private Context context;
    private String searchQuery;
    private RecyclerView recyclerView;
    private List<ProductDTO> products = new ArrayList<>();
    private ProgressDialog progressDialog;
    String newURL = "";
    private TextView noSearchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        context = SearchActivity.this;

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Please Wait");
        progressDialog.show();

        noSearchResult = (TextView) findViewById(R.id.noSearchResult);
        recyclerView = (RecyclerView) findViewById(R.id.searchResult);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (getIntent() != null && getIntent().getExtras() != null
                || getIntent().getExtras().getString("searchQuery") != null) {
            searchQuery = getIntent().getExtras().getString("searchQuery");
        }

        newURL = Constants.SERVER_URL + searchQuery + Constants.SERVER_KEY;
        new SearchAsynctask(context, searchQuery).execute();

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public class SearchAsynctask extends AsyncTask<Void, Void, Response> {
        private String searchQuery = "";
        //private URL newURL;
        private Context context;


        public SearchAsynctask(Context context, String searchQuery) {
            this.context = context;
            this.searchQuery = searchQuery;
        }

        @Override
        protected Response doInBackground(Void... params) {
            StringBuffer buffer = new StringBuffer();
            String paramValues = "";
            try {

                // Simulate network access.
                //final String newURL = Constants.SERVER_URL + searchQuery + Constants.SERVER_KEY;

                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Response response = restTemplate.getForObject(newURL, Response.class);

                return response;
                //Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
                //return false;
            }

            return null;
        }

        @Override
        protected void onPostExecute(Response s) {
            super.onPostExecute(s);

            if (Utils.isDefined("" + s.getResults())) {
                //Toast.makeText(context, "" + s.getResults(), Toast.LENGTH_SHORT).show();
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                try {

                    JSONArray jsonArray = new JSONArray(Arrays.asList(s.getResults()));
                    JSONArray jsonArray1 = jsonArray.getJSONArray(0);
                    ProductDTO productDTO = new ProductDTO();
                    products = new ArrayList<>();

                    for (int i=0; i<jsonArray1.length(); i++) {
                        JSONObject jsonObject = jsonArray1.getJSONObject(i);
                        productDTO = new ProductDTO();

                        productDTO.setBrandName(jsonObject.getString("brandName"));
                        productDTO.setThumbnailImageUrl(jsonObject.getString("thumbnailImageUrl"));
                        productDTO.setProductId(jsonObject.getString("productId"));
                        productDTO.setOriginalPrice(jsonObject.getString("originalPrice"));
                        productDTO.setStyleId(jsonObject.getString("styleId"));
                        productDTO.setColorId(jsonObject.getString("colorId"));
                        productDTO.setPrice(jsonObject.getString("price"));
                        productDTO.setPercentOff(jsonObject.getString("percentOff"));
                        productDTO.setProductUrl(jsonObject.getString("productUrl"));
                        productDTO.setProductName(jsonObject.getString("productName"));

                        products.add(productDTO);

                    }

                    if (products.size() > 0) {
                        MyRecyclerAdapter adapter = new MyRecyclerAdapter(context, products);
                        recyclerView.setAdapter(adapter);
                        noSearchResult.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    } else {
                        noSearchResult.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    }

                    //Toast.makeText(context, jsonObject.getString("brandName"), Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(context, "Server is down, Please try again later!", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(context, "Server is down, Please try again later!", Toast.LENGTH_SHORT).show();
            }


        }
    }

}
