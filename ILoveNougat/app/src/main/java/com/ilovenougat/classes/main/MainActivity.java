package com.ilovenougat.classes.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ilovenougat.classes.R;
import com.ilovenougat.classes.otheractivities.SearchActivity;
import com.ilovenougat.classes.utils.Utils;

public class MainActivity extends AppCompatActivity {
    private EditText searchET;
    private Button searchBTN;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = MainActivity.this;

        searchBTN = (Button) findViewById(R.id.searchBTN);
        searchET = (EditText) findViewById(R.id.searchET);

        searchBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.Online(context)) {
                    String query = searchET.getText().toString();

                    if (Utils.isDefined(query)) {
                        searchQuery(query);
                    } else {
                        Toast.makeText(context, "Please enter proper keyword!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Please connect to internet, then try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void searchQuery(String query) {
        Intent searchIntent = new Intent(context, SearchActivity.class);
        searchIntent.putExtra("searchQuery", query);
        startActivity(searchIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
