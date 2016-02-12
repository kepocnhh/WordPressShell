package stan.wp.shell.rest.spice;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.request.SpiceRequest;
import com.octo.android.robospice.request.listener.RequestListener;

public class StanSpiceManager
        extends SpiceManager
{

    public StanSpiceManager()
    {
        super(StanSpiceService.class);
    }

    @Override
    public <T> void execute(final SpiceRequest<T> request, final RequestListener<T> requestListener)
    {
        execute(request, null, DurationInMillis.ONE_SECOND, requestListener);
    }
}