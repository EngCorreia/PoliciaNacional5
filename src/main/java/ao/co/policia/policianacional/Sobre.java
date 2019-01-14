package ao.co.policia.policianacional;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import ao.co.policia.policianacional.modelos.Foragido;
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

    public static class ForagidoAdapter extends RecyclerView.Adapter<ForagidoAdapter.viewHolher> {
        final List<Foragido> listas;
        int id;
        List<Foragido> foragidos;

        public ForagidoAdapter(List<Foragido> listas) {
            this.listas = listas;
        }


        @NonNull
        @Override
        public viewHolher onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_foragido, parent, false);
            final viewHolher viewHolher = new viewHolher(view);
            foragidos = new ArrayList<>();
            viewHolher.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    id = viewHolher.getAdapterPosition();

                    String codigoF = listas.get(id).getCodigo();
                    String nomeF = listas.get(id).getNome();
                    String descricaoF = listas.get(id).getDescricao();
                    String crimeF = listas.get(id).getCrime();
                    String dataF = listas.get(id).getDatas();


                    Intent novo = new Intent(parent.getContext(), Apagar.class);
                    novo.putExtra("codigoF", codigoF);
                    novo.putExtra("nomeF", nomeF);
                    novo.putExtra("descricaoF", descricaoF);
                    novo.putExtra("crimeF", crimeF);
                    novo.putExtra("dataF", dataF);

                    parent.getContext().startActivity(novo);
                }
            });
            return viewHolher;
        }

        @Override
        public void onBindViewHolder(@NonNull viewHolher holder, int position) {


            holder.nome.setText(listas.get(position).getNome());
            holder.descricao.setText(listas.get(position).getDescricao());
            Picasso.get().load(listas.get(position).getImagem()).into(holder.imagens);
            holder.crime.setText(listas.get(position).getCrime());
            holder.datas.setText(listas.get(position).getDatas());
        }

        @Override
        public int getItemCount() {
            return listas.size();
        }


        public class viewHolher extends RecyclerView.ViewHolder {
            ImageView imagens;
            TextView nome, descricao, crime, datas, linha;
            LinearLayout linearLayout;


            public viewHolher(@NonNull View itemView) {
                super(itemView);

                nome = (TextView) itemView.findViewById(R.id.status);
                descricao = (TextView) itemView.findViewById(R.id.descricao);
                imagens = (ImageView) itemView.findViewById(R.id.imgAmigos);
                crime = (TextView) itemView.findViewById(R.id.crime);
                datas = (TextView) itemView.findViewById(R.id.datas);
                linha = (TextView) itemView.findViewById(R.id.linha);
                linearLayout = (LinearLayout) itemView.findViewById(R.id.forag);

            }
        }
    }
}
