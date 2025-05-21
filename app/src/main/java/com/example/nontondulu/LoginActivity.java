package com.example.nontondulu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin, tvRegister;

    private FirebaseAuth auth; // Firebase Auth

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inisialisasi Firebase Auth
        auth = FirebaseAuth.getInstance();

        // Binding View
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);

        // Tombol Login
        btnLogin.setOnClickListener(v -> loginUser());

        // Tombol ke halaman Register
        tvRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void loginUser() {
        String email = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Isi semua data dulu!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Login pakai Firebase
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Ambil nama dari Firebase User kalau ada, atau pakai email saja
                        String name = auth.getCurrentUser().getDisplayName(); // optional
                        if (name == null || name.isEmpty()) {
                            name = "User"; // fallback default
                        }

                        SessionManager sessionManager = new SessionManager(this);
                        sessionManager.saveUser(name, email);

                        Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }else {
                        Toast.makeText(this, "Login gagal: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
