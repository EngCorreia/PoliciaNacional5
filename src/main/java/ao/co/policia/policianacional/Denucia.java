package ao.co.policia.policianacional;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class Denucia extends AppCompatActivity {

    private Toolbar toolbar;
    private CardView btn;
    private EditText nome, data, crime;
    private DatabaseReference banco;
    public String tableDenuncia = "denuncias";
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private Spinner spinner;
    private TextView  denuncia;
    private CardView editar;
    private CircleImageView perfi;
    private static String imagem;
    private static final int GALLERIAS = 1;
    private FirebaseUser usuario;
    private StorageReference imageStorage;

    private String[] festas = new String[]{"SELECIONA O TIPO DO CRIME","Violençia C/Criança","Violençia C/Esposo","Combate aos Incendios","Manifestaçoes", "Violençia C/Mulheres", "Burladores", "Acidente de Viação", "Contactar Bombeiros"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fazer_denuncia);
        spinner=(Spinner)findViewById(R.id.box);

        toolbar = (Toolbar) findViewById(R.id.denunciaToolbar);
        editar =(CardView)findViewById(R.id.btnImag);
        perfi=(CircleImageView)findViewById(R.id.perfil);
        btn = (CardView) findViewById(R.id.btnDenunciar);
        nome = (EditText) findViewById(R.id.txtNome);
        data = (EditText) findViewById(R.id.txtData);
        crime = (EditText) findViewById(R.id.txtCrime);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog= new ProgressDialog(this);
        progressDialog.setMessage("Aguarde Por Favor....");
        progressDialog.setTitle("FAZER DENUNCIA");
        imageStorage = FirebaseStorage.getInstance().getReference();


        usuario = FirebaseAuth.getInstance().getCurrentUser();
        String uId = usuario.getUid();


        ArrayAdapter<String> listas1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, festas);
        listas1.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(listas1);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("FAZER DENUNCIAS");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent foto = new Intent();
                foto.setType("image/*");
                foto.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(foto, "Seleciona Imagem"), GALLERIAS);

            }
        });



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = firebaseAuth.getCurrentUser().getUid();
                String uid = UUID.randomUUID().toString();
                banco = FirebaseDatabase.getInstance().getReference().child(tableDenuncia).child(uid);

                HashMap<String, String> denun = new HashMap<>();
                denun.put("codigo", uid);
                denun.put("nome",nome.getText().toString());
                denun.put("data", data.getText().toString());
                denun.put("denuncia", spinner.getSelectedItem().toString());
                denun.put("crime", crime.getText().toString());
                denun.put("imagem",imagem);
               // progressDialog.show();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERIAS && resultCode == RESULT_OK) {
            //   progressDialog.show();
            Uri imageUri = data.getData();

            CropImage.activity(imageUri)
                    .setAspectRatio(1, 1)
                    .start(this);

        }


        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                Uri resultUri = result.getUri();

                String current_user_id = usuario.getUid();
                final StorageReference filepath = imageStorage.child("imagem_denunciados").child(random()+".png");
                filepath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                imagem = uri.toString();
                                Picasso.get().load(imagem).into(perfi);
                                /**  banco.child("imagem").setValue(uri.toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                progressDialog.dismiss();

                                Toast.makeText(Foragidos.this, "Imagem Salvo Com Sucesso ", Toast.LENGTH_LONG).show();
                                } else {

                                Toast.makeText(Foragidos.this, "erro grave", Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                                }
                                }
                                });*/
                            }
                        });
                    }
                });

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();

            }
        }
    }

    public static String random() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = generator.nextInt(20);
        char tempChar;
        for (int i = 0; i < randomLength; i++) {
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }






    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
