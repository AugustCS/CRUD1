package com.debugia.crudinicial;


import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragFiltros extends Fragment implements View.OnClickListener {

    Button b_xs, b_s, b_m, b_l, b_xl, b_xxl;

    public FragFiltros() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_filtros, container, false);
        b_xs = view.findViewById(R.id.b_xs);
        b_s = view.findViewById(R.id.b_s);
        b_m = view.findViewById(R.id.b_m);
        b_l = view.findViewById(R.id.b_l);
        b_xl = view.findViewById(R.id.b_xl);
        b_xxl = view.findViewById(R.id.b_xxl);


        b_xs.setOnClickListener(this);
        b_s.setOnClickListener(this);
        b_m.setOnClickListener(this);
        b_l.setOnClickListener(this);
        b_xl.setOnClickListener(this);
        b_xxl.setOnClickListener(this);
        /*
          GradientDrawable gd = new GradientDrawable();
        gd.setColor(0xFF00FF00); // Changes this drawbale to use a single color instead of a gradient
        gd.setCornerRadius(5);
        gd.setStroke(1, 0xFF000000);
        TextView tv = (TextView)findViewById(R.id.textView1);
        tv.setBackground(gd);
         */
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.b_xs):

                b_xs.setTextColor(Color.WHITE);
                b_xs.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.border_active));
                break;
            case (R.id.b_s):
                b_s.setTextColor(Color.WHITE);
                b_s.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.border_active));
                break;
            case (R.id.b_m):
                b_m.setTextColor(Color.WHITE);
                b_m.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.border_active));
                break;
            case (R.id.b_l):
                b_l.setTextColor(Color.WHITE);
                b_l.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.border_active));
                break;
            case (R.id.b_xl):
                b_xl.setTextColor(Color.WHITE);
                b_xl.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.border_active));
                break;
            case (R.id.b_xxl):
                b_xxl.setTextColor(Color.WHITE);
                b_xxl.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.border_active));
                break;
        }
    }
}