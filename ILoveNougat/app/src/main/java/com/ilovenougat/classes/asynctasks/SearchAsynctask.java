package com.ilovenougat.classes.asynctasks;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import com.ilovenougat.classes.R;
import com.ilovenougat.classes.dto.ProductDTO;
import com.ilovenougat.classes.utils.Constants;
import com.ilovenougat.classes.utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Advait on 9/10/2016.
 */
public class SearchAsynctask extends AsyncTask<Void, Void, Response> {
    private String searchQuery = "";
    //private URL newURL;
    private Context context;
    private List<ProductDTO> products = new ArrayList<>();
    Dialog dialog;
    ProgressDialog progressDialog;

    public SearchAsynctask(Context context, String searchQuery) {
        this.context = context;
        this.searchQuery = searchQuery;
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.price_compare_popup);
        dialog.setTitle("Price Comparision");

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
    }

    @Override
    protected Response doInBackground(Void... params) {
        StringBuffer buffer = new StringBuffer();
        String paramValues = "";
        try {

            // Simulate network access.
            final String newURL = Constants.SERVER_URL_6PM + searchQuery + Constants.SERVER_KEY_6PM;

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

        if (progressDialog.isShowing())
            progressDialog.dismiss();

        if (Utils.isDefined("" + s.getResults())) {
            //Toast.makeText(context, "" + s.getResults(), Toast.LENGTH_SHORT).show();

            try {

                JSONArray jsonArray = new JSONArray(Arrays.asList(s.getResults()));
                JSONArray jsonArray1 = jsonArray.getJSONArray(0);
                ProductDTO productDTO = new ProductDTO();
                products = new ArrayList<>();
                if (jsonArray1.length() > 0) {

                    JSONObject jsonObject = jsonArray1.getJSONObject(0);
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
                    //Toast.makeText(context, jsonObject.getString("price"), Toast.LENGTH_SHORT).show();
                    TextView compareTV = (TextView) dialog.findViewById(R.id.compareText);
                    compareTV.setText("This product is available on 6pm.com at " +jsonObject.getString("price"));
                    dialog.show();
                } else {
                    TextView compareTV = (TextView) dialog.findViewById(R.id.compareText);
                    compareTV.setText("This product is not available on 6pm.com");
                    dialog.show();
                }
            } catch (Exception e) {
                Toast.makeText(context, "Server is down, Please try again later!", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(context, "Server is down, Please try again later!", Toast.LENGTH_SHORT).show();
        }


    }
}
