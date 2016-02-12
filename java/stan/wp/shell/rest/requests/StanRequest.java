package stan.wp.shell.rest.requests;

import android.content.Context;
import android.util.Log;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import stan.wp.shell.rest.API;
import stan.wp.shell.rest.policies.NotRetryPolicy;
import stan.wp.shell.rest.responses.StanResponse;

public class StanRequest
        extends RetrofitSpiceRequest<StanResponse, API>
{
    protected Context mContext;

    public StanRequest(Context context)
    {
        super(StanResponse.class, API.class);
        this.setRetryPolicy(NotRetryPolicy.factory());
        mContext = context;
    }

    @Override
    public StanResponse loadDataFromNetwork() throws Exception
    {
        Log.e("Exec", "****************************************Request*************************************************");
        return null;
    }
}