package stan.wp.shell;

import android.app.Application;
import android.content.Context;

import java.io.File;

import stan.wp.shell.db.SQliteApi;
import stan.wp.shell.rest.spice.StanSpiceManager;

public class App
        extends Application
{
    public static StanSpiceManager spiceManager = new StanSpiceManager();
    public static Context app_context;

    @Override
    public void onCreate()
    {
        super.onCreate();
        app_context = getApplicationContext();
        SQliteApi.createDb(app_context);
        new File(app_context.getFilesDir().getAbsolutePath() + "/" + "featuredImages").mkdirs();
    }
}