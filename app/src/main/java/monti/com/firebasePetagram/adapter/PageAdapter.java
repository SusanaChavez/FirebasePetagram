package monti.com.firebasePetagram.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Susana on 05/10/2016.
 */
public class PageAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;

    public PageAdapter(FragmentManager fm, ArrayList<Fragment> fragments){
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    public void setItem(int posicion, Fragment fragment) {
        fragments.remove(posicion);
        fragments.add(fragment);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
