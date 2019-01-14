package ao.co.policia.policianacional;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import ao.co.policia.policianacional.modelos.Foragido;
import de.hdodenhof.circleimageview.CircleImageView;

public class Foragidos extends AppCompatActivity {

    private CardView publicar;
    private Toolbar toolbar;
    private CardView inserirImg;
    private DatabaseReference banco;
    private FirebaseAuth firebaseAuth;
    private EditText nome, descricao, crime, data;
    private CircleImageView imagem2;

    private FirebaseUser usuario;
    public String dd;
    public static String caminhoImg;

    private static final int GALLERIAS = 1;

    private StorageReference imageStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foragidos);
        toolbar = (Toolbar) findViewById(R.id.toolForagidos);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cadastrar Foragidos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        /** Referenciar as minhas Views no Java*/
        imageStorage = FirebaseStorage.getInstance().getReference();
        nome = (EditText) findViewById(R.id.fNome);
        descricao = (EditText) findViewById(R.id.fDescricao);
        crime = (EditText) findViewById(R.id.fCrime);
        data = (EditText) findViewById(R.id.fData);
        inserirImg = (CardView) findViewById(R.id.inserirImagem);
        publicar = (CardView) findViewById(R.id.publicar);
        imagem2 =(CircleImageView)findViewById(R.id.fImagem);


        /** Referenciar as minhas Views no Java*/
        usuario = FirebaseAuth.getInstance().getCurrentUser();
        String uId = usuario.getUid();

       /** Inicio do Evento Click no botão Publicar,Para quardar os foragidos na base de dados*/
        publicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nomeForagido = nome.getText().toString();
                String descricaoForagido = descricao.getText().toString();
                String crimeForagido = crime.getText().toString();
                String dataForagido = data.getText().toString();


                firebaseAuth = FirebaseAuth.getInstance();
                String id = firebaseAuth.getUid();

                dd = UUID.randomUUID().toString();
                banco = FirebaseDatabase.getInstance().getReference().child("foragidos").child(dd);
                HashMap<String, String> valores = new HashMap<>();
                valores.put("codigo", dd);
                valores.put("nome", nomeForagido);
                valores.put("descricao", descricaoForagido);
                valores.put("crime", crimeForagido);
                valores.put("data", dataForagido);
                valores.put("imagem", caminhoImg);
                banco.setValue(valores).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Intent fora = new Intent(Foragidos.this, MainActivity.class);
                            startActivity(fora);
                        } else {
                            Toast.makeText(Foragidos.this, "Erro de Coneção", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });


        /** Este metodo onClick vai permitir buscar uma imagem no pc para o faragido*/
        inserirImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent foto = new Intent();
                foto.setType("image/*");
                foto.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(foto, "Seleciona Imagem"), GALLERIAS);

            }
        });

    }


    /**
     * Estes metodos são responssaveis por cortar a imagem e guardar FireBase Storage na Pasta Imagem onde tera a Url da Imagem
     */



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
                final StorageReference filepath = imageStorage.child("imagem_foragido").child(random() + ".png");
                filepath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                caminhoImg=uri.toString();
                                Picasso.get().load(caminhoImg).into(imagem2);
                              /**  banco.child("imagem").setValue(uri.toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                          //  progressDialog.dismiss();

                                            Toast.makeText(Foragidos.this, "Imagem Salvo Com Sucesso ", Toast.LENGTH_LONG).show();
                                        } else {

                                            Toast.makeText(Foragidos.this, "erro grave", Toast.LENGTH_LONG).show();
                                          //  progressDialog.dismiss();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        //  getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }



}

