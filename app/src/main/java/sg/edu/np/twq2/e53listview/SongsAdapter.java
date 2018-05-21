package sg.edu.np.twq2.e53listview;

import android.content.Context;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SongsAdapter extends ArrayAdapter<Song> {
    private List<Song> dataList;
    private Context context;

    public SongsAdapter(Context src, int layout, List data)
    {
        super(src, layout, data);
        dataList = data;
        context = src;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("TAG", "Get View @ " + position + " for " + convertView);
        View itemView = convertView;
        if(itemView == null)
        {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_song_layout, parent, false);
        }

        //find the item
        Song currentData = dataList.get(position);

        //fill the view
        TextView title = (TextView)itemView.findViewById(R.id.tvTitle);
        title.setText(currentData.getTitle());

        TextView singer = (TextView)itemView.findViewById(R.id.tvArtist);
        singer.setText(currentData.getArtist());

        TextView duration = (TextView)itemView.findViewById(R.id.tvDuration);
        duration.setText(currentData.getDuration());

        return itemView;
    }
}
