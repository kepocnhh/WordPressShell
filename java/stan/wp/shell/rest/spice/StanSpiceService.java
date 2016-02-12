package stan.wp.shell.rest.spice;

import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

import stan.wp.shell.rest.API;

public class StanSpiceService
        extends RetrofitGsonSpiceService
{
    public final static String BASE_URL = "https://public-api.wordpress.com/rest/v1.1/sites/redrumers.com/";

    @Override
    public void onCreate()
    {
        super.onCreate();
        addRetrofitInterface(API.class);
    }

    @Override
    protected String getServerUrl()
    {
        return BASE_URL;
    }

    @Override
    public int getThreadCount()
    {
        return 10;
    }
}