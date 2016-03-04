package stan.wp.shell.helpers;

import android.os.AsyncTask;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncPicturesLoader
        extends AsyncTask<String, Void, String>
{
    @Override
    protected String doInBackground(String... arg0)
    {
        try
        {
            HttpURLConnection huc = (HttpURLConnection) new URL(arg0[0]).openConnection();
            huc.setConnectTimeout(5 * 1000);
            huc.connect();
            InputStream input = huc.getInputStream();
            OutputStream output = new FileOutputStream(arg0[1]);
            byte data[] = new byte[1024];
            int count;
            while((count = input.read(data)) != -1)
            {
                output.write(data, 0, count);
            }
            output.flush();
            output.close();
            input.close();
        }
        catch (java.net.SocketTimeoutException e)
        {
            Log.e("AsyncPicturesLoader", "open Stream - SocketTimeoutException " + e.getMessage());
        }
        catch(Exception e)
        {
            Log.e("AsyncPicturesLoader", e.getMessage());
        }
        return arg0[1];
    }
}