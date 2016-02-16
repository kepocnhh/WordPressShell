package stan.wp.shell.ui.fragments.main;

import android.database.Cursor;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
//import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
//import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

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
    private SwipeRefreshLayout swiperefreshlayout;
    View main_progress;

    //_______________FIELDS
    private MainRecyclerAdapter adapter;
    private LinearLayoutManager layoutManager;
    private int page = 0;
    private int postsCount = -1;
    private boolean refreshing = false;

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
        swiperefreshlayout = (SwipeRefreshLayout) v.findViewById(R.id.swiperefreshlayout);
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
                if(postsResponse.posts != null && postsResponse.posts.size() > 0)
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

    private void initList()
    {
        adapter = new MainRecyclerAdapter(getActivity());
        layoutManager = new LinearLayoutManager(getActivity());
        main_recycler.setLayoutManager(layoutManager);
        main_recycler.setAdapter(adapter);
        main_recycler.setOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE)
                {
                    if (layoutManager.findLastVisibleItemPosition() >= adapter.getItemCount() - 1 && !refreshing)
                    {
                        refreshing = true;
                        main_progress.setVisibility(View.VISIBLE);
                        Log.e("onScrollStateChanged","refreshBot");
                        refreshBot();
                    }
                }
            }
        });
        swiperefreshlayout.setColorSchemeResources(R.color.black, R.color.black, R.color.white);
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                refreshTop();
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
        getPosts(new RequestListener<StanResponse>()
        {
            @Override
            public void onRequestFailure(SpiceException spiceException)
            {
                swiperefreshlayout.setRefreshing(false);
                loadNewPosts();
            }
            @Override
            public void onRequestSuccess(StanResponse geekResponse)
            {
                swiperefreshlayout.setRefreshing(false);
                PostsResponse postsResponse = (PostsResponse) geekResponse;
                if (postsResponse.posts != null && postsResponse.posts.size() > 0)
                {
                    loadNewPosts();
                }
            }
        });
    }

    private void refreshBot()
    {
        getPosts(new RequestListener<StanResponse>()
        {
            @Override
            public void onRequestFailure(SpiceException spiceException)
            {
                refreshing = false;
                main_progress.setVisibility(View.INVISIBLE);
                loadNewPosts();
            }
            @Override
            public void onRequestSuccess(StanResponse geekResponse)
            {
                refreshing = false;
                main_progress.setVisibility(View.INVISIBLE);
                PostsResponse postsResponse = (PostsResponse) geekResponse;
                if (postsResponse.posts != null && postsResponse.posts.size() > 0)
                {
                    loadNewPosts();
                }
            }
        });
    }

    private IMainFragmentListener getListener()
    {
        return (IMainFragmentListener) clickListener;
    }
}