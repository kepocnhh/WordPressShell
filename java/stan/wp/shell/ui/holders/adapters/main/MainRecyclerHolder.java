package stan.wp.shell.ui.holders.adapters.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import stan.wp.shell.R;

public class MainRecyclerHolder
        extends RecyclerView.ViewHolder
{
    public ImageView post_image;
    public TextView post_title;
    public TextView post_excerpt;
    public LinearLayout post_container;

    public MainRecyclerHolder(View v)
    {
        super(v);
        post_image = (ImageView)v.findViewById(R.id.post_image);
        post_title = (TextView)v.findViewById(R.id.post_title);
        post_excerpt = (TextView)v.findViewById(R.id.post_excerpt);
        post_container = (LinearLayout)v.findViewById(R.id.post_container);
    }
}