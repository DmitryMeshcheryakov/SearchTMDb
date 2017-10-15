package by.insight.searchtmdb.tools;

import android.app.Activity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by Dmitry on 14.10.2017.
 */

public class InitUtil {

    public static void initToolbar(Toolbar toolbar, Activity activity) {
        ((AppCompatActivity) activity).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
