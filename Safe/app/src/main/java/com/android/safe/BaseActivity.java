package com.android.safe;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

/**
 * This class sets some rules for all the activity in this app
 * such as hide the action bar from the activity
 */
public class BaseActivity extends ActionBarActivity {
    /**
     * @param savedInstanceState
     * expands onCreate of ActionBarActivity by add function
     * that hides the action bar
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide the action bar from the activity
        setActionBar();
    }

    /**
     * This function hide the action bar from the activity
     */
    private void setActionBar() {
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.hide();

    }
}
