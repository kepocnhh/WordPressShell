package stan.wp.shell.core;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

public abstract class Unit
{
    public int ID;

    public ContentValues getCV()
    {
        ContentValues content = new ContentValues();
        content.put(BaseColumns._ID, ID);
        return getContentValues(content);
    }
    public abstract void setCursor(Cursor route);
    public abstract ContentValues getContentValues(ContentValues content);
}