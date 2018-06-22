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
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragFiltros extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    ExpandableListAdapter listAdapter;
    ExpListViewAdapterWithCheckbox listAdapterCheckbox;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    BDFamilia bdFamilia = new BDFamilia();
    BDSubFamilia bdSubFamilia = new BDSubFamilia();
    BDConcepto bdConcepto = new BDConcepto();

    Button b_borrar, b_listo;

    Toolbar toolbar_filtro, toolbar;


    public FragFiltros() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filtros, container, false);

        expListView = view.findViewById(R.id.lvExp);

        prepareListData();

        listAdapter = new ExpandableListAdapter(getContext(), listDataHeader, listDataChild);
        listAdapterCheckbox = new ExpListViewAdapterWithCheckbox(getContext(), listDataHeader, listDataChild);

        expListView.setAdapter(listAdapterCheckbox);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });
        return view;
    }


    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding Header data
        listDataHeader.add("Familia");
        listDataHeader.add("Sub Familia");
        ArrayList<List<String>> Conceptos = BDConcepto.getListaConceptos();
        Integer conceptos = Conceptos.size();
        for (int i = 0; i < conceptos; i++) {
            listDataHeader.add(Conceptos.get(i).get(1));
        }

        // Adding child data
        List<String> list_familia = bdFamilia.getListaNombres("");
        List<String> list_subFamilia = bdSubFamilia.getListaNombres("");

        List<String> list_concepto1 = new ArrayList<>();
        List<String> list_concepto2 = new ArrayList<>();
        List<String> list_concepto3 = new ArrayList<>();
        List<String> list_concepto4 = new ArrayList<>();
        List<String> list_concepto5 = new ArrayList<>();
        List<String> list_concepto6 = new ArrayList<>();
        List<String> list_concepto7 = new ArrayList<>();
        try {
            CodigosGenerales.ConceptoElegido=1;
            list_concepto1 = bdConcepto.getListaNombres("");
            CodigosGenerales.ConceptoElegido=2;
            list_concepto2 = bdConcepto.getListaNombres("");
            CodigosGenerales.ConceptoElegido=3;
            list_concepto3 = bdConcepto.getListaNombres("");
            CodigosGenerales.ConceptoElegido=4;
            list_concepto4 = bdConcepto.getListaNombres("");
            CodigosGenerales.ConceptoElegido=5;
            list_concepto5 = bdConcepto.getListaNombres("");
            CodigosGenerales.ConceptoElegido=6;
            list_concepto6 = bdConcepto.getListaNombres("");
            CodigosGenerales.ConceptoElegido=7;
            list_concepto7 = bdConcepto.getListaNombres("");
        } catch (Exception e) {
            Log.d("Concentps",e.getMessage());
        }

        listDataChild.put(listDataHeader.get(0), list_familia);
        listDataChild.put(listDataHeader.get(1), list_subFamilia);
        listDataChild.put(listDataHeader.get(2), list_concepto1);
        listDataChild.put(listDataHeader.get(3), list_concepto2);
        listDataChild.put(listDataHeader.get(4), list_concepto3);
        listDataChild.put(listDataHeader.get(5), list_concepto4);
        listDataChild.put(listDataHeader.get(6), list_concepto5);
        listDataChild.put(listDataHeader.get(7), list_concepto6);
        listDataChild.put(listDataHeader.get(28), list_concepto7);
    }

    public void CambiarFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_contenedor, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (view.getId()) {
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