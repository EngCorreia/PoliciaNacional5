package ao.co.policia.policianacional.fragmentos;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import ao.co.policia.policianacional.adpteres.PublicoAdapter;
import ao.co.policia.policianacional.modelos.Denunciados;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragmento_denunciadoss extends Fragment {

    private RecyclerView recyclerView;
    ProgressDialog progressDialog;
    DatabaseReference referencia;
    List<Denunciados> mlista;
    private FirebaseUser usuario;
    private  FirebaseAuth firebaseAuth;


    public Fragmento_denunciadoss() {
        // Required empty public constructor
    }




    public void modelo1() {
        progressDialog= new ProgressDialog(getContext());
        progressDialog.setTitle("Foragidos");
        progressDialog.setMessage("Por Favor Aguarde....");
        progressDialog.show();
        usuario= FirebaseAuth.getInstance().getCurrentUser();
       // String uid = usuario.getUid();
       // String cod=UUID.randomUUID().toString();
        referencia = FirebaseDatabase.getInstance().getReference().child("denuncias").child("publica");
      //  referencia = FirebaseDatabase.getInstance().getReference().child("denuncias");
        referencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot dados : dataSnapshot.getChildren()) {

                    mlista.add(new Denunciados(dados.child("codigo").getValue(String.class), dados.child("nome").getValue(String.class), dados.child("data").getValue(String.class), dados.child("crime").getValue(String.class), dados.child("denuncia").getValue(String.class), dados.child("imagem").getValue(String.class)));

                }
                progressDialog.dismiss();
                PublicoAdapter fora = new PublicoAdapter(mlista);

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
        View view = inflater.inflate(R.layout.fragment_fragmento_pedido, container, false);

        recyclerView=(RecyclerView)view.findViewById(R.id.denunciados);
        mlista= new ArrayList<>();
        modelo1();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return view;
    }

}
