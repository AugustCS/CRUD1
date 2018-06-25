package com.debugia.crudinicial;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragListArtiDescripcion extends Fragment implements View.OnClickListener {

    ArrayList<List<String>> listArrayList = new ArrayList<>();
    Button b_cerrar_popup;
    View popupView;
    PopupWindow popupWindow;

    LayoutInflater layoutInflater;
    ImageButton ib_info, b_share,ib_carritoPedidos;
    TextView et_nom_family, et_stock, et_precio;
    ImageView iv_imagen;
    String Cod_Producto;
    EditText et_cantidad;
    TextView tv_origen_popup;

    public FragListArtiDescripcion() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_articulos_desc, container, false);
        listArrayList = CodigosGenerales.getArticulosDescripcion();
        et_nom_family = view.findViewById(R.id.et_nom_family);
        et_stock = view.findViewById(R.id.et_stock);
        et_precio = view.findViewById(R.id.et_precio);
        iv_imagen = view.findViewById(R.id.iv_imagen);
        ib_info = view.findViewById(R.id.ib_info);
        b_share = view.findViewById(R.id.b_share);
        et_cantidad= view.findViewById(R.id.et_cantidad);
        ib_carritoPedidos=view.findViewById(R.id.ib_carritoPedidos);
        tv_origen_popup=view.findViewById(R.id.tv_origen_popup);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.polo);
        RoundedBitmapDrawable mDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        mDrawable.setCircular(true);
        iv_imagen.setImageDrawable(mDrawable);

        try {
            et_nom_family.setText(CodigosGenerales.nom_categoria + " - " + listArrayList.get(0).get(0));
            Cod_Producto=listArrayList.get(0).get(0);
            et_stock.setText(listArrayList.get(0).get(1));
            et_precio.setText(listArrayList.get(0).get(2));
        } catch (Exception e) {
            et_nom_family.setText("");
            Log.d("OnCreateView", e.getMessage());
        }

        b_share.setOnClickListener(this);
        iv_imagen.setOnClickListener(this);
        ib_info.setOnClickListener(this);
        ib_carritoPedidos.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case (R.id.ib_info):
                layoutInflater =(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                popupView = layoutInflater.inflate((R.layout.popup_info), null);
                popupWindow = new PopupWindow(popupView,RadioGroup.LayoutParams.MATCH_PARENT,
                        RadioGroup.LayoutParams.MATCH_PARENT);

                popupWindow.setOutsideTouchable(true);
                popupWindow.setFocusable(true);

                b_cerrar_popup = popupView.findViewById(R.id.id_cerrar);
                b_cerrar_popup.setOnClickListener(new Button.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }});

                popupWindow.showAsDropDown(tv_origen_popup, 0, 0);
                break;
            case(R.id.iv_imagen):
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                View mView = getLayoutInflater().inflate(R.layout.dialog_zoom, null);
                PhotoView photoView = mView.findViewById(R.id.imageView);
                photoView.setImageResource(R.drawable.polo);
                mBuilder.setView(mView);
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
                break;
            case (R.id.b_share):
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBodyText = "Envia un mensaje";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Titulo");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
                startActivity(Intent.createChooser(sharingIntent, "Compartir este producto"));
                break;
            case (R.id.ib_carritoPedidos):
                try{
                   // BDCarritoPedidos.AgregarProducto(Cod_Producto, String.valueOf(Integer.parseInt(et_cantidad.getText().toString())));
                    if(BDCarritoPedidos.GuardarProductoDeseado( String.valueOf(Integer.parseInt(et_cantidad.getText().toString())),Cod_Producto))
                        Toast.makeText(getActivity(),"Artículo guardado en la lista de deseos.",Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getActivity(),"No se pudo añadir el artículo.",Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Log.d("ib_carritoPedidos",e.getMessage());
                }

                break;
        }
    }
}