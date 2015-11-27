package com.example.vayen01.masterslave.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.vayen01.masterslave.R;
import com.example.vayen01.masterslave.fragment.MasterFragment;
import com.example.vayen01.masterslave.fragment.SlaveFragment;
import com.example.vayen01.masterslave.listener.MasterSlaveListener;
import com.example.vayen01.masterslave.warehouse.MasterSlaveWarehouse;

public class MainActivity extends AppCompatActivity implements MasterSlaveListener {

    private SlaveFragment mSlaveFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSlaveFragment = (SlaveFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_slave);

        if (mSlaveFragment == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new MasterFragment())
                    .commit();
        }
    }

    @Override
    public void onItemSelected(int position) {
        MasterSlaveWarehouse.getInstance().setSelectedMasterPosition(position);
        if (mSlaveFragment != null) {
            mSlaveFragment.notifyPositionChanged();
        } else {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, new SlaveFragment());
            ft.addToBackStack(null);
            ft.commit();
        }
    }
}
