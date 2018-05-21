package sg.edu.np.twq2.e53listview;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CustomLayoutActivity extends AppCompatActivity {
    List<Song> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_layout);

        for (int i = 0; i < 20; i++) {
            data.add(new Song("T" + i, "a" + i, "0:" + i));
        }

        SongsAdapter itemsAdapter = new SongsAdapter(this, R.layout.item_song_layout, data);

        ListView listView = findViewById(R.id.lvCustom);
        listView.setAdapter(itemsAdapter);

        // Set an item click listener for ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song selectedItem = (Song) parent.getItemAtPosition(position);
                new AlertDialog.Builder(CustomLayoutActivity.this)
                        .setMessage("You have selected " + selectedItem.getTitle())
                        .show();

            }
        });
    }
}
