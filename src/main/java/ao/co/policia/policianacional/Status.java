package ao.co.policia.policianacional;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Status extends AppCompatActivity {


    private Toolbar toolbar;
    private EditText statusConta;
    private Button botao;
    private CardView cardView;

    private ProgressDialog progressDialog;

    // fire base

    private DatabaseReference statusDataBase;
    private FirebaseUser usuarioId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);


        String status_values = getIntent().getStringExtra("status_val");

        statusConta = (EditText) findViewById(R.id.imagemStatus);
        cardView = (CardView) findViewById(R.id.button);

        toolbar = findViewById(R.id.status1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("STATUS DO USUARIO");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        statusConta.setText(status_values);

        // fire base
        usuarioId = FirebaseAuth.getInstance().getCurrentUser();
        String id = usuarioId.getUid();
        statusDataBase = FirebaseDatabase.getInstance().getReference().child("usuarios").child(id);


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(Status.this);
                progressDialog.setTitle("Trocando seu Status");
                progressDialog.setMessage("Por Favor Aguarda..!!!");

                progressDialog.show();

                String stat = statusConta.getText().toString();
                statusDataBase.child("status").setValue(stat).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

                            progressDialog.dismiss();
                            Intent usuario = new Intent(Status.this, Usuario.class);
                            startActivity(usuario);
                        } else {
                            progressDialog.hide();
                            Toast.makeText(getApplicationContext(), "erro", Toast.LENGTH_SHORT).show();
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
