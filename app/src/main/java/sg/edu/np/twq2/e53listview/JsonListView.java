package sg.edu.np.twq2.e53listview;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonListView extends AppCompatActivity implements GetJson.CallBack{
    List<Song> data = new ArrayList<>();
    SongsAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_list_view);

        String s = "[{\"Artist\":\"Adele\",\"Duration\":\"4:47\",\"Title\":\"Someone Like You\"},{\"Artist\":\"Donny Osmond\",\"Duration\":\"3:18\",\"Title\":\"Be A Man\"},{\"Artist\":\"Shakira\",\"Duration\":\"3:23\",\"Title\":\"Try Everything\"}]";

        GetJson gj = new GetJson(this,"https://portfolio-1-179005.firebaseio.com/MADLv.json");
        gj.execute();

        Type listType = new TypeToken<ArrayList<Song>>(){}.getType();
        data = new Gson().fromJson(s, listType);
        itemsAdapter = new SongsAdapter(this, R.layout.item_song_layout, data);

        ListView listView = findViewById(R.id.lvJson);
        listView.setAdapter(itemsAdapter);

        // Set an item click listener for ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song selectedItem = (Song) parent.getItemAtPosition(position);
                new AlertDialog.Builder(JsonListView.this)
                        .setMessage("You have selected " + selectedItem.getTitle())
                        .show();

            }
        });
    }

    @Override
    public void onJsonRetrieved(String json)
    {
        Type listType = new TypeToken<ArrayList<Song>>(){}.getType();
        List<Song> newData = new Gson().fromJson(json, listType);
        data.clear();
        data.addAll(newData);
        itemsAdapter.notifyDataSetChanged();
    }
}
