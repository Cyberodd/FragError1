package com.myfirebasetest;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_gen:

                    mTextMessage.setText(R.string.title_home);
                    mTextMessage.setVisibility(View.GONE);
                    Fragment frag = new PostFragment();
                    if (frag != null) {

                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.container, fragment, frag.getTag());
                        ft.commit();

                    }
                    return true;
                case R.id.navigation_dashboard:

                    mTextMessage.setText(R.string.title_dashboard);
                    mTextMessage.setVisibility(View.GONE);
                    fragment = new GoatFragment();
                    if (frag != null) {

                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.container, frag, frag.getTag());
                        ft.commit();

                        return true;}
                 case R.id.navigation_notifications:
                            mTextMessage.setText(R.string.title_notifications);
                            return true;
                    return false;
            }
        }

        ;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            BottomNavigationView navView = findViewById(R.id.nav_view);
            mTextMessage = findViewById(R.id.message);
            navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        }


    }
}