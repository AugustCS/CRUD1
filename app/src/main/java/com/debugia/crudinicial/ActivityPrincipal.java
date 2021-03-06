package com.debugia.crudinicial;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;

public class ActivityPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    DrawerLayout drawer;
    Button b_carrito;
    ImageView iv_logo;
    PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        Toolbar toolbar = findViewById(R.id.toolbar);
        Toolbar toolbar_filtro = findViewById(R.id.toolbar_filtro);
        toolbar_filtro.setVisibility(View.GONE);

        setSupportActionBar(toolbar);
        toolbar.setTitle("");

        iv_logo = findViewById(R.id.iv_logo);
        b_carrito = findViewById(R.id.b_carrito);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        try {
            getSupportActionBar().hide();
        } catch (Exception e) {
            Log.d("getSupportActionBar", e.getMessage());
        }

        try {
            iv_logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drawer.openDrawer(GravityCompat.START);
                }
            });
        } catch (Exception e) {
        }

        if (CodigosGenerales.Iniciar) {
            CodigosGenerales.Iniciar = false;
            Fragment fragment = new FragSplashScreen();
            CambiarFragment(fragment);
        }
        b_carrito.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 1) {
            new AlertDialog.Builder(this)
                    .setTitle("Cerrar sesión")
                    .setMessage("¿Esstá seguro de cerrar sesión?")
                    .setNegativeButton(android.R.string.cancel, null) // dismisses by default
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            BDUsuario.getExitLogin();
                            getSupportActionBar().hide();
                            CodigosGenerales.Iniciar = true;
                            ActivityPrincipal.super.onBackPressed();
                        }
                    })
                    .create()
                    .show();
        } else if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment;
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_catalogo) {
            fragment = new Catalogo();
            CambiarFragmentDrawer(fragment);
        } else if (id == R.id.nav_clientes) {
            fragment = new Clientes();
            CambiarFragmentDrawer(fragment);
        } else if (id == R.id.nav_pedidos) {
            fragment = new Pedidos();
            CambiarFragmentDrawer(fragment);
        } else if (id == R.id.nav_otros) {
            fragment = new Otros();
            CambiarFragmentDrawer(fragment);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void CambiarFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction().
                replace(R.id.frag_contenedor, fragment)
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
                .commit();
    }

    public void CambiarFragmentDrawer(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frag_contenedor, fragment)
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case (R.id.b_carrito):
                Fragment fragment=new FragCarritoPedidos();
                CambiarFragmentDrawer(fragment);
                break;
        }
    }
}