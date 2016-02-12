package stan.wp.shell.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import stan.wp.shell.listeners.fragments.IStanFragmentListener;

public abstract class StanFragment
        extends Fragment
{
    protected View container;
    private String fragmentTag;
    private int fragmentTagId;

    public String getFragmentTag()
    {
        return fragmentTag;
    }

    protected IStanFragmentListener clickListener;

    public StanFragment(int lay, int id)
    {
        Bundle args = new Bundle();
        args.putInt("layout", lay);
        setArguments(args);
        fragmentTagId = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(getArguments().getInt("layout", 0), container, false);
        findViews(v);
        return v;
    }
    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        clickListener = (IStanFragmentListener) activity;
        fragmentTag = getActivity().getResources().getString(fragmentTagId);
    }

    public void setVisibility(boolean visibility)
    {
        if(visibility)
        {
            container.setVisibility(View.VISIBLE);
        }
        else
        {
            container.setVisibility(View.INVISIBLE);
        }
    }

    protected void findViews(View v)
    {
        container = v;
    }

}