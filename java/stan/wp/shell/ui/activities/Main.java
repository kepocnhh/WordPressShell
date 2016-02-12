package stan.wp.shell.ui.activities;

import stan.wp.shell.R;
import stan.wp.shell.listeners.fragments.main.IMainFragmentListener;
import stan.wp.shell.ui.fragments.main.MainFragment;

public class Main
        extends StanActivity
        implements IMainFragmentListener
{
    //_______________VIEWS

    //_______________FRAGMENTS

    //_______________FIELDS

    public Main()
    {
        super(R.layout.main, R.id.main_frame);
    }

    @Override
    protected void initFragments()
    {
    }

    @Override
    protected void initViews()
    {
        initList();
    }

    private void initList()
    {
    }

    @Override
    protected void init()
    {
        initRequest();
        addFragment(MainFragment.newInstance());
    }

    private void initRequest()
    {
    }
}