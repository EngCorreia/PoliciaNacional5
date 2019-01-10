package ao.co.policia.policianacional;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class Foragidos extends AppCompatActivity {

    private Toolbar toolbar;
    private Button publicar;
    private DatabaseReference banco;
    private FirebaseAuth firebaseAuth;
    private EditText nome, descricao, crime, data;
    private CircleImageView imagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foragidos);
        toolbar=(Toolbar)findViewById(R.id.toolForagidos);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cadastrar Foragidos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nome = (EditText) findViewById(R.id.fNome);
        descricao = (EditText) findViewById(R.id.fDescricao);
        crime = (EditText) findViewById(R.id.fCrime);
        data = (EditText) findViewById(R.id.fData);

        publicar = (Button) findViewById(R.id.publicar);

        publicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nomeForagido = nome.getText().toString();
                String descricaoForagido = descricao.getText().toString();
                String crimeForagido = crime.getText().toString();
                String dataForagido = data.getText().toString();


                firebaseAuth = FirebaseAuth.getInstance();
                String id = firebaseAuth.getUid();

                String dd= UUID.randomUUID().toString();
                banco = FirebaseDatabase.getInstance().getReference().child("foragidos").child(dd);
                HashMap<String, String> valores = new HashMap<>();
                valores.put("codigo",dd);
                valores.put("nome", nomeForagido);
                valores.put("descricao", descricaoForagido);
                valores.put("crime", crimeForagido);
                valores.put("data", dataForagido);
                valores.put("imagem", "default");
                banco.setValue(valores).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Intent fora = new Intent(Foragidos.this, MainActivity.class);
                            startActivity(fora);
                        } else {
                            Toast.makeText(Foragidos.this,"Erro de Coneção",Toast.LENGTH_SHORT).show();

                        }

                    }
                });


            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }


        return super.onOptionsItemSelected(item);
    }
}

