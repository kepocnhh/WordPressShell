package stan.wp.shell.core;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import stan.wp.shell.db.Tables;

public class Post
    extends Unit
{
    public String title;
    public String excerpt;
    public String date;
//    public FeaturedImage featured_image;
    public String featured_image;

    @Override
    public ContentValues getContentValues(ContentValues content)
    {
        content.put(Tables.PostSimple_date_COLUMN, date);
        content.put(Tables.PostSimple_excerpt_COLUMN, excerpt);
//        content.put(Tables.PostSimple_featured_image_COLUMN, featured_image.guid);
        content.put(Tables.PostSimple_featured_image_COLUMN, featured_image);
        content.put(Tables.PostSimple_title_COLUMN, title);
        return content;
    }

    @Override
    public void setCursor(Cursor route)
    {
        ID = route.getInt(route.getColumnIndex(BaseColumns._ID));
        date = route.getString(route.getColumnIndex(Tables.PostSimple_date_COLUMN));
        excerpt = route.getString(route.getColumnIndex(Tables.PostSimple_excerpt_COLUMN));
//        featured_image = new FeaturedImage(route.getString(route.getColumnIndex(Tables.PostSimple_featured_image_COLUMN)));
        featured_image = route.getString(route.getColumnIndex(Tables.PostSimple_featured_image_COLUMN));
        title = route.getString(route.getColumnIndex(Tables.PostSimple_title_COLUMN));
    }
}