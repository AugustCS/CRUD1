package com.debugia.crudinicial;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragFiltros extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {


    BDFamilia bdFamilia=new BDFamilia();
    BDSubFamilia bdSubFamilia=new BDSubFamilia();
    BDConcepto bdConcepto= new BDConcepto();


    ArrayList arrayList = new ArrayList<String>();
    ArrayAdapter<String> adapterFamilia, adapterSubFamilia;
    ListView lv_familia, lv_subfamilia;
    Toolbar toolbar_filtro, toolbar;

    Button b_borrar, b_listo;

    public FragFiltros() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_filtros, container, false);

        lv_familia = view.findViewById(R.id.lv_familia);
        lv_subfamilia = view.findViewById(R.id.lv_subfamilia);

        toolbar_filtro = getActivity().findViewById(R.id.toolbar_filtro);
        toolbar = getActivity().findViewById(R.id.toolbar);
        b_borrar = getActivity().findViewById(R.id.b_borrar);
        b_listo = getActivity().findViewById(R.id.b_listo);

        toolbar_filtro.setVisibility(View.VISIBLE);
        toolbar.setVisibility(View.GONE);

        try {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar_filtro);
        } catch (Exception e) {
            Log.d("getSupportActionBar", e.getMessage());
        }

        adapterFamilia = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_multiple_choice, bdFamilia.getListaNombres(""));
        adapterSubFamilia = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_multiple_choice, bdSubFamilia.getListaNombres(""));

        lv_familia.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lv_subfamilia.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        lv_familia.setAdapter(adapterFamilia);
        lv_subfamilia.setAdapter(adapterSubFamilia);

        lv_familia.setOnItemClickListener(this);
        lv_subfamilia.setOnItemClickListener(this);

        b_listo.setOnClickListener(this);
        b_borrar.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.b_listo):
                ArrayList Fam_Selected=new ArrayList();
                int len = lv_familia.getCount();
                SparseBooleanArray checked = lv_familia.getCheckedItemPositions();

                for (int i = 0; i < len; i++) {
                    if (checked.get(i))
                        Fam_Selected.add(i);
                }


                break;
            case (R.id.b_borrar):
                try {
                    lv_familia.clearChoices();
                    lv_subfamilia.clearChoices();
                    for (int i = 0; i < lv_familia.getCount(); i++) {
                        lv_familia.setItemChecked(i, false);
                    }
                    for (int i = 0; i < lv_subfamilia.getCount(); i++) {
                        lv_subfamilia.setItemChecked(i, false);
                    }
                } catch (Exception e) {
                    Log.d("Error", e.getMessage());
                }
                break;
        }
    }

    private void getData() {
        arrayList = CodigosGenerales.getListFiltros();
        //arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, arrayList);
        //lv_items.setAdapter(arrayAdapter);
    }

    public void CambiarFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_contenedor, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (view.getId()) {
            case (R.id.lv_familia):
                lv_familia.setItemChecked(2, true);
                break;
            case (R.id.lv_subfamilia):
                break;
        }
    }

    @Override
    public void onDetach() {
        toolbar_filtro.setVisibility(View.GONE);
        toolbar.setVisibility(View.VISIBLE);
        try {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        } catch (Exception e) {
            Log.d("getSupportActionBar", e.getMessage());
        }
        super.onDetach();
    }
}