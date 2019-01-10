package ao.co.policia.policianacional;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.UUID;

public class Denucia extends AppCompatActivity {

    private Toolbar toolbar;
    private Button btn;
    private EditText nome, data, crime;
    private DatabaseReference banco;
    public String tableDenuncia = "denuncias";
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private Spinner spinner;
    private TextView  denuncia;

    private String[] festas = new String[]{"Seleciona o Tipo de Delito","Violençia C/Criança","Violençia C/Esposo","Combate aos Incendios","Manifestaçoes", "Violençia C/Mulheres", "Burladores", "Acidente de Viação", "Contactar Bombeiros"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fazer_denuncia);
        spinner=(Spinner)findViewById(R.id.box);

        toolbar = (Toolbar) findViewById(R.id.denunciaToolbar);
        btn = (Button) findViewById(R.id.btnDenunciar);
        nome = (EditText) findViewById(R.id.txtNome);
        data = (EditText) findViewById(R.id.txtData);
        denuncia = (TextView) findViewById(R.id.txtDeneuncia);
        crime = (EditText) findViewById(R.id.txtCrime);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog= new ProgressDialog(this);
        progressDialog.setMessage("Aguarde Por Favor....");
        progressDialog.setTitle("Denunciando");


        ArrayAdapter<String> listas1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, festas);
        listas1.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(listas1);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Denuncias");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = firebaseAuth.getCurrentUser().getUid();
                String uid = UUID.randomUUID().toString();
                banco = FirebaseDatabase.getInstance().getReference().child(tableDenuncia).child(uid);

                HashMap<String, String> denun = new HashMap<>();
                denun.put("codigo", uid);
                denun.put("nome","");
                denun.put("data", data.getText().toString());
                denun.put("denuncia", spinner.getSelectedItem().toString());
                denun.put("crime", crime.getText().toString());
                progressDialog.show();
                banco.setValue(denun).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            Intent denunciarAgora= new Intent(Denucia.this,MainActivity.class);
                            startActivity(denunciarAgora);
                        }else{
                            progressDialog.hide();
                            Toast.makeText(Denucia.this,"Erro de Conexão",Toast.LENGTH_SHORT).show();
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
