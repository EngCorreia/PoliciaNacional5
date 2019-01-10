package ao.co.policia.policianacional;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import ao.co.policia.policianacional.adpteres.DenunciaAdapters;
import ao.co.policia.policianacional.modelos.Denuncias;


public class MenuActivity extends AppCompatActivity {

    List<Denuncias> lista;
    RecyclerView recyclerView;
    DatabaseReference reference;
    ArrayList<Denuncias> listas;

    private Toolbar toolbar;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;


    public void nomes() {
      /*  mAuth.getCurrentUser();
        String user = mAuth.getUid();
        reference = FirebaseDatabase.getInstance().getReference().child("usuarios");


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dados : dataSnapshot.getChildren()) {

                    //  Denuncias denucia = dados.getValue(Denuncias.class);

                    lista.add(new Denuncias(dados.child("nome").getValue(String.class), dados.child("status").getValue(String.class), R.drawable.cmarido));
                    //  listas.add(denucia);

                }
                DenunciaAdapters dene = new DenunciaAdapters(lista);
                recyclerView.setAdapter(dene);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

 lista.add(new Denuncias("Violençia Contra Criança", "Saíba Mais", R.drawable.criancas));
 lista.add(new Denuncias("Violençia Contra Marido", "Saíba Mais", R.drawable.cmarido));
 lista.add(new Denuncias("Combate Incendios", "Saíba Mais", R.drawable.fogo));
 lista.add(new Denuncias("Anunciar Manifestaçoes", "Saíba Mais", R.drawable.manifestacaos));
 lista.add(new Denuncias("Violençia Contra Mulher", "Saíba Mais", R.drawable.cmulher));
 lista.add(new Denuncias("Denunçiar Burladores", "Saíba Mais", R.drawable.burladores));
 lista.add(new Denuncias("Acidente de Viação", "Saíba Mais", R.drawable.acidente));
 lista.add(new Denuncias("Fale Conosco", "Saíba Mais", R.drawable.unidade));


    }


    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mAuth = FirebaseAuth.getInstance();
        listas = new ArrayList<Denuncias>();
        toolbar = findViewById(R.id.tDenuncias);
        lista = new ArrayList<>();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Seleciona o seu Problema");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Declaracao das minhas views (RecycleViews)
        recyclerView = (RecyclerView) findViewById(R.id.recycleViews);

        nomes();
        recyclerView.setHasFixedSize(true);

         recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
       // recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DenunciaAdapters dene = new DenunciaAdapters(lista);
        recyclerView.setAdapter(dene);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}