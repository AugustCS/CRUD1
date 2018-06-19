package com.debugia.crudinicial;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;


public class Login extends Fragment implements View.OnClickListener {
    Button b_ingresar;
    EditText et_ruc, et_usuario, et_clave;
    ConexionSQL conexionSQL = new ConexionSQL();
    DrawerLayout drawer;

    public Login() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        b_ingresar = view.findViewById(R.id.b_ingresar);
        et_ruc = view.findViewById(R.id.et_ruc);
        et_usuario = view.findViewById(R.id.et_usuario);
        et_clave = view.findViewById(R.id.et_clave);
        drawer = getActivity().findViewById(R.id.drawer_layout);
        b_ingresar.setOnClickListener(this);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        return view;

    }

    @Override
    public void onClick(View v) {
        Fragment fragment;
        switch (v.getId()) {
            case (R.id.b_ingresar):
                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                fragment = new MenuPrincipal();
                CambiarFragment(fragment);
                /*
                if (conexionSQL.getLogin(et_ruc.getText().toString(), et_usuario.getText().toString(), et_clave.getText().toString())) {
                    et_ruc.setText("");
                    et_usuario.setText("");
                    et_clave.setText("");
                    drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                    fragment = new MenuPrincipal();
                    CambiarFragment(fragment);
                } else {
                    Toast.makeText(getActivity(), "Error en las credenciales", Toast.LENGTH_SHORT).show();
                }*/
                break;
        }
    }

    public void CambiarFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frag_contenedor, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}