package stan.wp.shell.helpers;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import stan.wp.shell.App;

public class PicturesLoader
{
    private static PicturesLoader instance;

    private Picasso picasso;

    private PicturesLoader(Context c)
    {
        picasso = new Picasso.Builder(c).downloader(new OkHttpDownloader(new OkHttpClient())).build();
    }
    public static PicturesLoader getInstance(Context context)
    {
        if(instance == null)
        {
            instance = new PicturesLoader(context);
        }
        return instance;
    }
    public Picasso getPicasso()
    {
        return picasso;
    }

    public static void loadImage(ImageView iv, String url)
    {
        getInstance(App.app_context)
                .getPicasso()
                .with(App.app_context)
                .load(url)
                .into(iv);
    }
}