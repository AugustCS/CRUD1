package com.debugia.crudinicial;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuPrincipal extends Fragment implements View.OnClickListener {
    Button b_familia;

    public MenuPrincipal() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*
        final Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Fragment fragment = new login();
                    replaceFragment(fragment);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //super.run();
            }
        };
        thread.start();
         */
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_menu_principal, container, false);
        b_familia= view.findViewById(R.id.b_familia);
        b_familia.setOnClickListener(this);
        return  view;
    }

    @Override
    public void onClick(View v) {
        Fragment fragment;
        switch (v.getId()){
            case (R.id.b_familia):
                fragment= new familia();
                CambiarFragment(fragment);
                break;
        }
    }

    public void CambiarFragment(Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frag_contenedor, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
