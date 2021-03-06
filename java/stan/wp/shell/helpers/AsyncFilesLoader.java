package stan.wp.shell.helpers;

import android.os.AsyncTask;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

public class AsyncFilesLoader
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
            while ((count = input.read(data)) != -1)
            {
                output.write(data, 0, count);
            }
            output.flush();
            output.close();
            input.close();
        }
        catch (ConnectException e)
        {
            Log.e("AsyncFilesLoader", "open Stream - ConnectException " + e.getMessage());
        }
        catch (SocketTimeoutException e)
        {
            Log.e("AsyncFilesLoader", "open Stream - SocketTimeoutException " + e.getMessage());
        }
        catch (Exception e)
        {
            Log.e("AsyncFilesLoader", e.getMessage());
        }
        return arg0[1];
    }
}