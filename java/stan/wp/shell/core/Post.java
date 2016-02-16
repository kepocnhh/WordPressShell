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
    public String format;

    @Override
    public ContentValues getContentValues(ContentValues content)
    {
        content.put(Tables.PostSimple.date_COLUMN, date);
        content.put(Tables.PostSimple.excerpt_COLUMN, excerpt);
//        content.put(Tables.PostSimple_featured_image_COLUMN, featured_image.guid);
        content.put(Tables.PostSimple.featured_image_COLUMN, featured_image);
        content.put(Tables.PostSimple.title_COLUMN, title);
        content.put(Tables.PostSimple.format_COLUMN, format);
        return content;
    }

    @Override
    public void setCursor(Cursor route)
    {
        ID = route.getInt(route.getColumnIndex(BaseColumns._ID));
        date = route.getString(route.getColumnIndex(Tables.PostSimple.date_COLUMN));
        excerpt = route.getString(route.getColumnIndex(Tables.PostSimple.excerpt_COLUMN));
//        featured_image = new FeaturedImage(route.getString(route.getColumnIndex(Tables.PostSimple_featured_image_COLUMN)));
        featured_image = route.getString(route.getColumnIndex(Tables.PostSimple.featured_image_COLUMN));
        title = route.getString(route.getColumnIndex(Tables.PostSimple.title_COLUMN));
        format = route.getString(route.getColumnIndex(Tables.PostSimple.format_COLUMN));
    }
}