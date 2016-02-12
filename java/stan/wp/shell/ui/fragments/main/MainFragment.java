package stan.wp.shell.ui.fragments.main;

import android.database.Cursor;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import stan.wp.shell.App;
import stan.wp.shell.R;
import stan.wp.shell.db.SQliteApi;
import stan.wp.shell.listeners.fragments.main.IMainFragmentListener;
import stan.wp.shell.rest.requests.posts.GetPostsFromPage;
import stan.wp.shell.rest.responses.StanResponse;
import stan.wp.shell.rest.responses.posts.PostsResponse;
import stan.wp.shell.ui.adapters.main.MainRecyclerAdapter;
import stan.wp.shell.ui.fragments.StanFragment;

public class MainFragment
        extends StanFragment
{

    //___________________VIEWS
    RecyclerView main_recycler;
    private SwipyRefreshLayout swipyrefreshlayout;
    View main_progress;

    //_______________FIELDS
    private MainRecyclerAdapter adapter;
    private int page = 0;
    private int postsCount = -1;

    static public MainFragment newInstance()
    {
        return new MainFragment();
    }

    public MainFragment()
    {
        super(R.layout.main_fragment, R.string.MainFragment);
    }

    @Override
    protected void findViews(View v)
    {
        super.findViews(v);
        main_progress = v.findViewById(R.id.main_progress);
        main_progress.setVisibility(View.VISIBLE);
        main_recycler = (RecyclerView) v.findViewById(R.id.main_recycler);
        swipyrefreshlayout = (SwipyRefreshLayout) v.findViewById(R.id.swipyrefreshlayout);
        initList();
        init();
    }

    private void init()
    {
        getPosts(new RequestListener<StanResponse>()
        {

            @Override
            public void onRequestFailure(SpiceException spiceException)
            {
                loadNewPosts();
                main_progress.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onRequestSuccess(StanResponse geekResponse)
            {
                PostsResponse postsResponse = (PostsResponse) geekResponse;
                if (postsResponse.posts != null && postsResponse.posts.size() > 0)
                {
                    loadNewPosts();
                }
                main_progress.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void getPosts(RequestListener<StanResponse> rl)
    {
        GetPostsFromPage request = new GetPostsFromPage(getActivity(), page + 1);
        App.spiceManager.execute(request, rl);
    }
    private void getPosts()
    {
        getPosts(new RequestListener<StanResponse>()
        {

            @Override
            public void onRequestFailure(SpiceException spiceException)
            {
                swipyrefreshlayout.setRefreshing(false);
                loadNewPosts();
            }

            @Override
            public void onRequestSuccess(StanResponse geekResponse)
            {
                swipyrefreshlayout.setRefreshing(false);
                PostsResponse postsResponse = (PostsResponse) geekResponse;
                if (postsResponse.posts != null && postsResponse.posts.size() > 0)
                {
                    loadNewPosts();
                }
            }
        });
    }

    private void initList()
    {
        adapter = new MainRecyclerAdapter(getActivity());
        main_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        main_recycler.setAdapter(adapter);
        swipyrefreshlayout.setColorSchemeResources(R.color.black, R.color.black, R.color.white);
        swipyrefreshlayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction)
            {
                if (direction == SwipyRefreshLayoutDirection.TOP)
                {
                    refreshTop();
                }
                else
                {
                    refreshBot();
                }
            }
        });
    }

    private void loadNewPosts()
    {
        Cursor c = null;
        c = SQliteApi.getPostSimpleFromPage(page + 1);
        adapter.swapCursor(c);
        int pc = c.getCount();
        if (pc != postsCount || page == 0)
        {
            postsCount = pc;
            page++;
        }
        Log.e("loadNewPosts", "postsCount = " + postsCount);
    }

    private void refreshTop()
    {
        page = 0;
        getPosts();
    }

    private void refreshBot()
    {
        getPosts();
    }

    private IMainFragmentListener getListener()
    {
        return (IMainFragmentListener) clickListener;
    }
}