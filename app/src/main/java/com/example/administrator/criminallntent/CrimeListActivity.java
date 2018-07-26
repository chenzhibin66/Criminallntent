package com.example.administrator.criminallntent;

import android.support.v4.app.Fragment;

public class CrimeListActivity extends SingFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
