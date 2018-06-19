package com.debugia.crudinicial;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuPrincipal extends Fragment implements View.OnClickListener {
    Button b_familia, b_sub_familia;

    public MenuPrincipal() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_menu_principal, container, false);
        b_familia= view.findViewById(R.id.b_familia);
        b_sub_familia= view.findViewById(R.id.b_sub_familia);
        b_familia.setOnClickListener(this);
        b_sub_familia.setOnClickListener(this);
        try {
            ((AppCompatActivity)getActivity()).getSupportActionBar().show();
        } catch (Exception e) {
            Log.d("getSupportActionBar",e.getMessage());
        }
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
            case (R.id.b_sub_familia):
                fragment= new sub_familia();
                CambiarFragment(fragment);
                break;
        }
    }

    public void CambiarFragment(Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
        fragmentTransaction.replace(R.id.frag_contenedor, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
