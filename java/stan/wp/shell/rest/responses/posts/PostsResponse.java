package stan.wp.shell.rest.responses.posts;

import java.util.List;

import stan.wp.shell.core.Post;
import stan.wp.shell.rest.responses.StanResponse;

public class PostsResponse
        extends StanResponse
{
    public List<Post> posts;
}