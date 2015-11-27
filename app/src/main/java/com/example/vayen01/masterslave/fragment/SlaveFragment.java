package com.example.vayen01.masterslave.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vayen01.masterslave.warehouse.MasterSlaveWarehouse;
import com.example.vayen01.masterslave.R;

/**
 * Slave fragment that keeps a tab layout and a view pager to display sub-items.
 *
 * @author ycagri
 */
public class SlaveFragment extends Fragment {

    private ViewPager mSlaveVP;
    private TabLayout mSlaveSlidingTabs;

    private SlavePagerAdapter mSlavePagerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.fragment_slave, container, false);

        mSlaveVP = (ViewPager) parentView.findViewById(R.id.vp_slave);
        mSlavePagerAdapter = new SlavePagerAdapter(getActivity());
        mSlaveVP.setAdapter(mSlavePagerAdapter);

        mSlaveSlidingTabs = (TabLayout) parentView.findViewById(R.id.st_slave);
        mSlaveSlidingTabs.setupWithViewPager(mSlaveVP);

        return parentView;
    }

    public void notifyPositionChanged() {
        mSlavePagerAdapter.notifyDataSetChanged();
        mSlaveSlidingTabs.setupWithViewPager(mSlaveVP);
    }

    private class SlavePagerAdapter extends PagerAdapter {

        private LayoutInflater mInflater;

        public SlavePagerAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View parentView = mInflater.inflate(R.layout.item_slave_pager, container, false);
            TextView slaveTV = (TextView) parentView.findViewById(R.id.tv_slave_item);
            slaveTV.setText(MasterSlaveWarehouse.getInstance().getSelectedMasterItem().getSlaveItems().get(position));
            container.addView(parentView);
            return parentView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return MasterSlaveWarehouse.getInstance().getSelectedMasterItem().getSlaveItems().size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return MasterSlaveWarehouse.getInstance().getSelectedMasterItem().getSlaveItems().get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }
}
