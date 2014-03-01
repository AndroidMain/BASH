package ru.proglive.android1.lesson4.alyoshin.bash_reader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import org.apache.http.protocol.HTTP;

public class MainActivity extends Activity {
    ListView listView;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        listView = (ListView) findViewById(R.id.listView);

        String[] quotes = getResources().getStringArray(R.array.quotes);


//        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, quotes);
        MyListAdapter stringArrayAdapter = new MyListAdapter(this, quotes);

        listView.setAdapter(stringArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, QuoteActivity.class);
                intent.putExtra("id", position);
                intent.putExtra("timestamp", System.currentTimeMillis());
                startActivity(intent);
            }
        });

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "text");
        sendIntent.setType(HTTP.PLAIN_TEXT_TYPE); // "text/plain" MIME type

        // Verify that the intent will resolve to an activity
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
//            startActivity(sendIntent);
            Log.d("ResolveIntent", "Intent with ACTION_SEND is resolved");
        } else {
            Log.d("ResolveIntent", "Intent with ACTION_SEND is NOT resolved");
        }


    }
}
