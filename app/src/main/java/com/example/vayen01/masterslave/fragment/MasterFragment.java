package com.example.vayen01.masterslave.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vayen01.masterslave.util.DividerItemDecoration;
import com.example.vayen01.masterslave.listener.MasterSlaveListener;
import com.example.vayen01.masterslave.warehouse.MasterSlaveWarehouse;
import com.example.vayen01.masterslave.R;

/**
 * Master fragment that keeps a single recycler view to show master items.
 *
 * @author ycagri
 */
public class MasterFragment extends Fragment {

    private MasterSlaveListener mListener;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (MasterSlaveListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement MasterSlaveListener!");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView masterRV = (RecyclerView) inflater.inflate(R.layout.fragment_master, container, false);
        masterRV.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        masterRV.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        masterRV.setAdapter(new MasterRecyclerViewAdapter(getActivity()));

        return masterRV;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private class MasterRecyclerViewAdapter extends RecyclerView.Adapter<MasterRecyclerViewAdapter.MasterItemHolder> {

        private LayoutInflater mInflater;

        public MasterRecyclerViewAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }


        @Override
        public MasterItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.item_master_list, parent, false);
            return new MasterItemHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MasterItemHolder holder, final int position) {
            holder.mItemNameTV.setText(MasterSlaveWarehouse.getInstance().getItems().get(position).getMasterItemName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemSelected(position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return MasterSlaveWarehouse.getInstance().getItems().size();
        }

        public class MasterItemHolder extends RecyclerView.ViewHolder {

            private TextView mItemNameTV;

            public MasterItemHolder(View itemView) {
                super(itemView);

                mItemNameTV = (TextView) itemView;
            }
        }
    }
}
