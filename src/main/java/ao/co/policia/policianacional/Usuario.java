package ao.co.policia.policianacional;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.net.URL;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class Usuario extends AppCompatActivity {

    private TabLayout tabLayout;
    private Toolbar toolbar;
    private FirebaseUser usuario;
    private DatabaseReference userDataBase;
    CircleImageView imageConta;
    TextView nomeConta, statusConta;
    Button novaConta, imagemStatus;
    ProgressDialog progressDialog;

    private static final int GALLERIA = 1;

    //store fire base
    private StorageReference imageStorage;

    private ProgressDialog processo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        toolbar = (Toolbar) findViewById(R.id.tooUsuario);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Conta do Usuario");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageStorage = FirebaseStorage.getInstance().getReference();
        tabLayout = findViewById(R.id.tabLayout);
        imageConta = (CircleImageView) findViewById(R.id.profile_image);
        nomeConta = (TextView) findViewById(R.id.nome);
        statusConta = (TextView) findViewById(R.id.status);
        novaConta = (Button) findViewById(R.id.criarConta);
        imagemStatus = (Button) findViewById(R.id.imagens);

        progressDialog = new ProgressDialog(Usuario.this);
        progressDialog.setMessage("Aguardando Resposta.....");
        progressDialog.setTitle("Processando seu Status");


        usuario = FirebaseAuth.getInstance().getCurrentUser();
        String uId = usuario.getUid();
        userDataBase = FirebaseDatabase.getInstance().getReference().child("usuarios").child(uId);

        progressDialog.show();

        userDataBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String nome = dataSnapshot.child("nome").getValue().toString();
                    String status = dataSnapshot.child("status").getValue().toString();
                    String imagem = dataSnapshot.child("imagem").getValue().toString();
                    String polegada = dataSnapshot.child("imagem_tam").getValue().toString();

                    progressDialog.dismiss();

                    nomeConta.setText("Usuario : " + nome);
                    statusConta.setText("Status : " + status);
                    Picasso.get().load(imagem).into(imageConta);
                    //Picasso.get().load(imagem).into(imageConta);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        imagemStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent fotos = new Intent();
                fotos.setType("image/*");
                fotos.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(fotos, "Seleciona Imagem"), GALLERIA);


                /**
                 // start picker to get image for cropping and then use the image in cropping activity
                 CropImage.activity()
                 .setGuidelines(CropImageView.Guidelines.ON)
                 .start(Usuario.this);      */


            }
        });


        novaConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status_valor = statusConta.getText().toString();
                Intent status = new Intent(Usuario.this, Status.class);

                status.putExtra("status_val", status_valor);
                startActivity(status);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERIA && resultCode == RESULT_OK) {
            //  progressDialog.show();
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

                final StorageReference filepath = imageStorage.child("imagem_perfil").child(current_user_id + ".jpg");
                filepath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                userDataBase.child("imagem").setValue(uri.toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            progressDialog.dismiss();

                                            Toast.makeText(Usuario.this, "Imagem Salvo Com Sucesso ", Toast.LENGTH_LONG).show();
                                        } else {

                                            Toast.makeText(Usuario.this, "erro grave", Toast.LENGTH_LONG).show();
                                            progressDialog.dismiss();
                                        }
                                    }
                                });
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

        if (item.getItemId() == R.id.menu2) {
            Intent lista2 = new Intent(Usuario.this, MenuActivity.class);
            startActivity(lista2);
        } else if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


}
