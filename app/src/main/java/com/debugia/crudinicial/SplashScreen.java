package com.debugia.crudinicial;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class SplashScreen extends Fragment {
    public SplashScreen() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Fragment fragment = new Login();
                    CambiarFragment(fragment);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //super.run();
            }
        };
        thread.start();
        return inflater.inflate(R.layout.fragment_splash_screen, container, false);
    }

    public void CambiarFragment(Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frag_contenedor, fragment);
        fragmentTransaction.commit();
    }
}
