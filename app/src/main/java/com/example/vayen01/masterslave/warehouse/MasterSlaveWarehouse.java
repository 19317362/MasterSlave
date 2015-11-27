package com.example.vayen01.masterslave.warehouse;

import com.example.vayen01.masterslave.pojo.MasterSlaveItem;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is implemented using Singleton pattern. Whole master-slave design pattern access
 * adapter items via this class. Since this is a simple project, no synchronization is implemented.
 *
 * @author ycagri
 */
public class MasterSlaveWarehouse {

    private static MasterSlaveWarehouse sInstance;

    private int mItemCount = 3;
    private int mSubItemCount = 3;
    private List<MasterSlaveItem> mItems;
    private int mSelectedMasterPosition = 0;

    public static MasterSlaveWarehouse getInstance() {
        if (sInstance == null) {
            sInstance = new MasterSlaveWarehouse();
        }

        return sInstance;
    }

    private MasterSlaveWarehouse() {
        mItems = new ArrayList<>();

        for (int i = 0; i < mItemCount; i++) {
            MasterSlaveItem item = new MasterSlaveItem("Position " + (i + 1));
            for (int j = 0; j < mSubItemCount; j++) {
                item.getSlaveItems().add("Sub (" + (i + 1) + "-" + (j + 1) + ")");
            }

            mItems.add(item);
        }
    }

    public List<MasterSlaveItem> getItems() {
        return mItems;
    }

    public void setSubItemCount(int subItemCount) {
        mSubItemCount = subItemCount;
    }

    public void setItemCount(int itemCount) {
        mItemCount = itemCount;
    }

    public int getSelectedMasterPosition() {
        return mSelectedMasterPosition;
    }

    public void setSelectedMasterPosition(int selectedMasterPosition) {
        mSelectedMasterPosition = selectedMasterPosition;
    }

    public MasterSlaveItem getSelectedMasterItem() {
        return mItems.get(mSelectedMasterPosition);
    }
}
