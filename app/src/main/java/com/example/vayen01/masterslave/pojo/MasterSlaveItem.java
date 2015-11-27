package com.example.vayen01.masterslave.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * POJO to keep information of adapter items (e.g. {@link android.support.v4.view.PagerAdapter},
 * {@link android.support.v7.widget.RecyclerView.Adapter})
 *
 * @author ycagri
 */
public class MasterSlaveItem {

    /**
     * Item name
     */
    private String mMasterItemName;

    /**
     * List of sub-items.
     */
    private List<String> mSlaveItems;

    public MasterSlaveItem(String name){
        mMasterItemName = name;
        mSlaveItems = new ArrayList<>();
    }

    public String getMasterItemName() {
        return mMasterItemName;
    }

    public void setMasterItemName(String masterItemName) {
        mMasterItemName = masterItemName;
    }

    public List<String> getSlaveItems() {
        return mSlaveItems;
    }

    public void setSlaveItems(List<String> slaveItems) {
        mSlaveItems = slaveItems;
    }
}
