package com.example.w2d5_ex1;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements HomeFragment.HomeListener,
                                                               EntryFragment.BackButtonListener {

    FragmentManager fragManager;
    HomeFragment homeFrag;
    EntryFragment entryFrag;
    ListFragment listFrag;

    public static final String FILE_NAME = "contactList.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragManager = getSupportFragmentManager();
        homeFrag = new HomeFragment();

        fragManager.beginTransaction()
                .replace(R.id.container, homeFrag)
                .commit();
    }

    public void switchToEntry(){
        entryFrag = new EntryFragment();

        fragManager.beginTransaction()
                .replace(R.id.container, entryFrag)
                .commit();
    }

    public void switchToList(){
        listFrag = new ListFragment();

        fragManager.beginTransaction()
                .replace(R.id.container, listFrag)
                .commit();
    }

    public void switchToHome(){
        fragManager.beginTransaction()
                .replace(R.id.container, homeFrag)
                .commit();
    }
}
