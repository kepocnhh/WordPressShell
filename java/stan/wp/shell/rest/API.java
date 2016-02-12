package stan.wp.shell.rest;

import retrofit.http.GET;
import retrofit.http.Query;
import stan.wp.shell.rest.responses.posts.PostsResponse;

public interface API
{
    //_____________POSTS_______________//

    @GET("/posts")
    PostsResponse getPostsFromPage(@Query("page") int page);

    //_____________CATEGORIES_______________//
	
}