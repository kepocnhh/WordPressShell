package stan.wp.shell.helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.File;

public class AsyncPicturesLoader
        extends AsyncFilesLoader
{
    private ImageView imageView;
    private int errorImage;

    public AsyncPicturesLoader(ImageView iv, int ei)
    {
        this.imageView = iv;
        this.errorImage = ei;
    }

    @Override
    protected void onPostExecute(String path)
    {
        File f = new File(path);
        if(f.exists())
        {
            Bitmap myBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
            this.imageView.setImageBitmap(myBitmap);
        }
        else
        {
            this.imageView.setImageResource(this.errorImage);
        }
    }
}