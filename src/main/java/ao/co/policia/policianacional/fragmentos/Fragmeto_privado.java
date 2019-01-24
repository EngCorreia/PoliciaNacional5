package ao.co.policia.policianacional.fragmentos;


import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ao.co.policia.policianacional.R;
import ao.co.policia.policianacional.adpteres.PrivadoAdapter;
import ao.co.policia.policianacional.modelos.Privados;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragmeto_privado extends Fragment {

    //:::::::::::::::
    private static final String ARG_ID = "id";
    private String id;

    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;
    private DatabaseReference referencia;
    List<Privados> mlista;
    private FirebaseUser usuarios;
    private String user;

    /*
    public Fragmeto_privado() {
        // Required empty public constructor
    }
    */
    //********************************************************************************
    public static Fragmeto_privado newInstance (String id_){
        Bundle bundle = new Bundle();
        bundle.putString(ARG_ID, id_);
        Fragmeto_privado pager =new Fragmeto_privado();
        pager.setArguments(bundle);
        return pager;
    }
    //***********************************************************************************************
    private void readBundle(Bundle bundle){
        if(bundle!=null){
            id = getArguments().getString(ARG_ID);
        }
    }
    //********************************************************************************

    public void modelo1() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Foragidos");
        progressDialog.setMessage("Por Favor Aguarde....");
        // progressDialog.show();

        //:::::::::::::::::::
        readBundle(getArguments());
        //FirebaseUser usuarios = FirebaseAuth.getInstance().getCurrentUser();
        //String id = usuarios.getUid();

        Log.i(id, "user!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        referencia = FirebaseDatabase.getInstance().getReference().child("denunciass").child("privado").child(id);
        referencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot dados : dataSnapshot.getChildren()) {

                    mlista.add(new Privados(dados.child("nome").getValue(String.class), dados.child("denuncia").getValue(String.class), dados.child("data").getValue(String.class), dados.child("imagem").getValue(String.class)));

                }
                // progressDialog.dismiss();
                PrivadoAdapter fora = new PrivadoAdapter(mlista);

                recyclerView.setAdapter(fora);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragmeto_chat, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyTelefone);
        mlista = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        modelo1();
        PrivadoAdapter denunciaPrivadas = new PrivadoAdapter(mlista);
        recyclerView.setAdapter(denunciaPrivadas);
        return view;
    }

}
