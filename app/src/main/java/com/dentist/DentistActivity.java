package com.dentist;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.Application.MyApp;
import com.fragment.MainMenuFragment;
import com.fragment.NewsFragment;

public class DentistActivity extends AppCompatActivity implements NewsFragment.OnFragmentInteractionListener{
    private MainMenuFragment mainMenuFragment;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dentist);
        mToolbar=(Toolbar)findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);
        mainMenuFragment= (MainMenuFragment)getFragmentManager().findFragmentById(R.id.fragment_drawer);
        mainMenuFragment.setup(R.id.fragment_drawer,(DrawerLayout)findViewById(R.id.activity_dentist),mToolbar);

        NewsFragment newsFragment=new NewsFragment();
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.container,newsFragment);
        transaction.commit();

    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        switch(requestCode){
            case 1:
                if(resultCode==RESULT_OK){
                    mainMenuFragment.changeStatue(((MyApp)getApplication()).getUser().getName());
                    Log.d("ll","success");
                }
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onResume(){
       super.onResume();
        if(((MyApp)getApplication()).getUser().getName()!=null)
        mainMenuFragment.changeStatue(((MyApp)getApplication()).getUser().getName());
    }
}
