package ao.co.policia.policianacional;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ao.co.policia.policianacional.adpteres.TodosUsuarioAdapter;
import ao.co.policia.policianacional.modelos.ModeloUsuarios;

public class Sobre extends AppCompatActivity {

    private DatabaseReference referencia;
    private RecyclerView recyclerView;
    private FirebaseAuth firebaseAuth;
    List<ModeloUsuarios> mLista;
    Toolbar toolbar;


    public void modelo() {
/*
        referencia = FirebaseDatabase.getInstance().getReference().child("usuarios");
        referencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot lista:dataSnapshot.getChildren()){

                    modeloUsuarios.add(new ModeloUsuarios(lista.child("nome").getValue(String.class),lista.child("status").getValue(String.class),R.drawable.correia));
                }*/

        referencia = FirebaseDatabase.getInstance().getReference().child("usuarios");


        referencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dados : dataSnapshot.getChildren()) {

                    mLista.add(new ModeloUsuarios(dados.child("nome").getValue(String.class), dados.child("status").getValue(String.class), R.drawable.user3));

                }

                TodosUsuarioAdapter us = new TodosUsuarioAdapter(mLista);
                recyclerView.setAdapter(us);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);
        toolbar=(Toolbar)findViewById(R.id.toolUser);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Meus Usuarios");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mLista = new ArrayList<>();
        /**
         * Aqui começa o nosso recyclerViews
         * */
        recyclerView = (RecyclerView) findViewById(R.id.recycleViews1);


        /**
         * Inicio dos Codigos para a nossa activity Sobre
         * */

        firebaseAuth = FirebaseAuth.getInstance();

        /**
         * Esta Linha me traz todos os dados do FireDataBase
         * reference = FirebaseDatabase.getInstance().getReference().child("usuarios");
         * */
        referencia = FirebaseDatabase.getInstance().getReference().child("usuarios");

        modelo();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }


    @Override
    protected void onStart() {
        super.onStart();


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
