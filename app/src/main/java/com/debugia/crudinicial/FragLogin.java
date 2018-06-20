package com.debugia.crudinicial;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class FragLogin extends Fragment implements View.OnClickListener {
    Button b_ingresar;
    EditText et_ruc, et_usuario, et_clave;
    DrawerLayout drawer;

    public FragLogin() {
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
        et_ruc.setText("20600124782");
        et_usuario.setText("admin");
        et_clave.setText("12345678");
        return view;

    }

    @Override
    public void onClick(View v) {
        Fragment fragment;
        switch (v.getId()) {
            case (R.id.b_ingresar):
                if (BDUsuario.getLogin(et_ruc.getText().toString(), et_usuario.getText().toString(), et_clave.getText().toString())) {
                    et_ruc.setText("");
                    et_usuario.setText("");
                    et_clave.setText("");
                    drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                    fragment = new FragMenuPrincipal();
                    CambiarFragment(fragment);
                } else {
                    Toast.makeText(getActivity(), "Error en las credenciales", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void CambiarFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
        transaction.replace(R.id.frag_contenedor, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}