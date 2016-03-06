package stan.wp.shell.ui.adapters.main;

import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import stan.wp.shell.R;

public class PostRecyclerAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    protected Context mContext;
    protected Cursor mCursor;

    public PostRecyclerAdapter(Context context)
    {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.post_vertical, parent, false);
        return initVerticalHolder(v);
    }
    private RecyclerView.ViewHolder initHorizontalHolder(View v)
    {
//        return new PostHorizontalHolder(v);
        return null;
    }
    private RecyclerView.ViewHolder initVerticalHolder(View v)
    {
//        return new PostVerticalHolder(v);
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i)
    {
        if (mCursor.moveToPosition(i))
        {
            initView(holder, i);
        }
    }

    protected void initView(RecyclerView.ViewHolder h, int i)
    {
    }

    @Override
    public long getItemId(int position)
    {
        mCursor.moveToPosition(position);
        return mCursor.getInt(mCursor.getColumnIndex(BaseColumns._ID));
    }

    @Override
    public int getItemCount()
    {
        if(mCursor == null)
        {
            return 0;
        } else
        {
            return mCursor.getCount();
        }
    }

    protected String getString(String cn)
    {
        return mCursor.getString(mCursor.getColumnIndex(cn));
    }

    public void swapCursor(Cursor c)
    {
        if(mCursor!=null)
        {
            mCursor.close();
        }
        mCursor = c;
        notifyDataSetChanged();
    }
}