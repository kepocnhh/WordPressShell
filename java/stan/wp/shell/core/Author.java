package stan.wp.shell.core;

import android.content.ContentValues;
import android.database.Cursor;

public class Author
        extends Unit
{
    public String username;
    public String name;
    public String first_name;
    public String last_name;
    public String nickname;
    public String avatar;

    @Override
    public ContentValues getContentValues(ContentValues content)
    {
        return content;
    }

    @Override
    public void setCursor(Cursor route)
    {

    }
}