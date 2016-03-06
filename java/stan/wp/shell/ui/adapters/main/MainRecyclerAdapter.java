package stan.wp.shell.ui.adapters.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.File;

import stan.wp.shell.R;
import stan.wp.shell.db.Tables;
import stan.wp.shell.helpers.AsyncFilesLoader;
import stan.wp.shell.helpers.AsyncPicturesLoader;
import stan.wp.shell.helpers.PicturesLoader;
import stan.wp.shell.ui.adapters.StanRecyclerAdapter;
import stan.wp.shell.ui.holders.adapters.main.MainRecyclerHolder;


public class MainRecyclerAdapter
        extends StanRecyclerAdapter
{
    public MainRecyclerAdapter(Context context)
    {
        super(context, R.layout.post);
    }

    @Override
    protected RecyclerView.ViewHolder initHolder(View v)
    {
        return new MainRecyclerHolder(v);
    }

    @Override
    protected void initView(RecyclerView.ViewHolder h, int i)
    {
        getHolder(h).post_title.setText(getString(Tables.PostSimple.title_COLUMN));
        getHolder(h).post_excerpt.setText(getString(Tables.PostSimple.excerpt_COLUMN));
//        getHolder(h).post_image.setImageDrawable(null);
        String format = getString(Tables.PostSimple.format_COLUMN);
        if(format == null)
        {

        } else if(format.equals("standard"))
        {
            String imgPath = mContext.getFilesDir().getAbsolutePath() + "/" + "featuredImages" + "/" + getItemId(i) + ".jpg";
            File f = new File(imgPath);
            if(f.exists())
            {
                Bitmap myBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
                if(myBitmap.getWidth() > myBitmap.getHeight())
                {
                    getHolder(h).post_container.setOrientation(LinearLayout.VERTICAL);
                }
                else
                {
                    getHolder(h).post_container.setOrientation(LinearLayout.HORIZONTAL);
                }
                getHolder(h).post_image.setImageBitmap(myBitmap);
            }
            else
            {
                PicturesLoader.loadImage(getHolder(h).post_image, getString(Tables.PostSimple.featured_image_COLUMN));
                new AsyncFilesLoader().execute(getString(Tables.PostSimple.featured_image_COLUMN), imgPath);
            }
        } else if(format.equals("video"))
        {
            getHolder(h).post_image.setImageResource(R.drawable.youtube);
        }
    }

    MainRecyclerHolder getHolder(RecyclerView.ViewHolder holder)
    {
        return (MainRecyclerHolder) holder;
    }
}