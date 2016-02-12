package stan.wp.shell.rest.requests.posts;

import android.content.Context;

import stan.wp.shell.db.SQliteApi;
import stan.wp.shell.rest.requests.StanRequest;
import stan.wp.shell.rest.responses.StanResponse;
import stan.wp.shell.rest.responses.posts.PostsResponse;

public class GetPostsFromPage
        extends StanRequest
{
    private int page;
    public GetPostsFromPage(Context context, int p)
    {
        super(context);
        page = p;
    }

    @Override
    public StanResponse loadDataFromNetwork() throws Exception
    {
        super.loadDataFromNetwork();
        PostsResponse response = getService().getPostsFromPage(page);
        SQliteApi.startTransaction();
        for(int i=0; i<response.posts.size(); i++)
        {
            SQliteApi.insertPostSimple(response.posts.get(i).getCV());
        }
        SQliteApi.endTransaction();
        return response;
    }
}