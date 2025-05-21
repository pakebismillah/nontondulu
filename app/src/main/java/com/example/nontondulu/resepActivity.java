package com.example.nontondulu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.UUID;

public class resepActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageView ivPreview;
    private Uri imageUri;
    private EditText etNamaResep, etWaktu, etJenis, etBahan, etLangkah;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tambah_resep);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tambahresep), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etNamaResep = findViewById(R.id.etNamaResep);
        etWaktu = findViewById(R.id.etWaktu);
        etJenis = findViewById(R.id.etJenis);
        etBahan = findViewById(R.id.etBahan);
        etLangkah = findViewById(R.id.etLangkah);


        ivPreview = findViewById(R.id.imgPreview);
        Button btnGambar = findViewById(R.id.btnUploadImage);
        Button btnCancel = findViewById(R.id.btnCancel);
        Button btnSave = findViewById(R.id.btnSave);

        btnGambar.setOnClickListener(v -> openGallery());

        btnCancel.setOnClickListener(v -> {
            Intent intent = new Intent(this, HomeFragment.class);
            startActivity(intent);
            finish();
        });

        btnSave.setOnClickListener(v -> {
            if (imageUri != null) {
                uploadImageToFirebase(imageUri);
            } else {
                Toast.makeText(this, "Pilih gambar dulu", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            ivPreview.setImageURI(imageUri);
        }
    }

    private void uploadImageToFirebase(Uri uri) {
        String userId = FirebaseAuth.getInstance().getUid();
        if (userId == null) {
            Toast.makeText(this, "Kamu belum login", Toast.LENGTH_SHORT).show();
            return;
        }

        String fileName = "resep/" + UUID.randomUUID().toString();
        StorageReference ref = FirebaseStorage.getInstance().getReference(fileName);

        ref.putFile(uri)
                .addOnSuccessListener(taskSnapshot -> ref.getDownloadUrl().addOnSuccessListener(downloadUri -> {
                    saveToFirestore(downloadUri.toString(), userId);
                }))
                .addOnFailureListener(e -> Toast.makeText(this, "Upload gagal: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    private void saveToFirestore(String imageUrl, String userId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("reseps")
                .add(new Resep(userId, imageUrl))
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "Resep disimpan!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, HomeFragment.class));
                    finish();
                })
                .addOnFailureListener(e -> Toast.makeText(this, "Gagal simpan: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    // POJO class
    public static class Resep {
        public String userId;
        public String imageUrl;

        public Resep() {} // Needed by Firestore

        public Resep(String userId, String imageUrl) {
            this.userId = userId;
            this.imageUrl = imageUrl;
        }
    }
}