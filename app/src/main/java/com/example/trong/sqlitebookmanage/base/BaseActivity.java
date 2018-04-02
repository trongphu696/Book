package com.example.trong.sqlitebookmanage.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by trong on 3/8/2018.
 */

public abstract class BaseActivity extends AppCompatActivity {
   protected abstract int getLayoutResources();
   protected abstract void initVariables(Bundle saveInstanceState);
   protected abstract void initData(Bundle saveInstanceState);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResources());
        initVariables(savedInstanceState);
        initData(savedInstanceState);
    }
}
