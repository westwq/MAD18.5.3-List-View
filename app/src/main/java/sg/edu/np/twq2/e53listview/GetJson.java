package sg.edu.np.twq2.e53listview;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import static android.content.ContentValues.TAG;

public class GetJson extends AsyncTask<String, Void, String> {
    private ProgressDialog pDialog;
    Context context;
    private String sUrl;

    public GetJson(Context context, String url)
    {
        this.context = context;
        this.sUrl = url;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        //show progress dialog
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    protected String doInBackground(String... arg0)
    {
        //make a request to url and get response
        String jsonStr = "";

        try
        {
            URL url = new URL(sUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");

            //read the repsonse
            InputStream inputStream = new BufferedInputStream(conn.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();

            String line;
            while((line = reader.readLine()) != null)
            {
                sb.append(line).append('\n');
            }

            jsonStr = sb.toString();
            inputStream.close();
        }
        catch(MalformedURLException e)
        {
            Log.e(TAG,"MalformedURLException: " + e.getMessage());
        }
        catch(ProtocolException e)
        {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        }
        catch(IOException e)
        {
            Log.e(TAG,"IOException: " + e.getMessage());
        }
        catch(Exception e)
        {
            Log.e(TAG, "Exception: " + e.getMessage());
        }

        return jsonStr;
    }

    @Override
    protected void onPostExecute(String result)
    {
        super.onPostExecute(result);

        //dismiss progress dialog
        if(pDialog.isShowing())
            pDialog.dismiss();

        ((CallBack)context).onJsonRetrieved(result);
    }

    interface CallBack
    {
        void onJsonRetrieved(String json);
    }
}
