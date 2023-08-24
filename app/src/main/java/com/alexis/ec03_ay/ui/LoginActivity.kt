package com.alexis.ec03_ay.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import com.alexis.ec03_ay.R
import com.alexis.ec03_ay.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = Firebase.auth

        if (firebaseAuth.currentUser != null) {
            goToMainActivity()
            return
        }
        googleLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == RESULT_OK){
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    val account = task.getResult(ApiException::class.java)
                    authenticateWithFirebase(account.idToken!!)
                }catch (e:Exception){

                }
            }
        }

        binding.tilEmail.editText?.addTextChangedListener {text ->
            binding.btnLogin.isEnabled = validateEmailPassword(text.toString(),binding.tilPassword.editText?.text.toString())
        }
        binding.tilPassword.editText?.addTextChangedListener {text ->
            binding.btnLogin.isEnabled = validateEmailPassword(binding.tilEmail.editText?.text.toString(),text.toString())
        }

        binding.btnLogin.setOnClickListener{
        //    miToast("Bienvenido")
        //   val intent = Intent(this, MainActivity::class.java)
        //    startActivity(intent)
        //    finish()
            val password = binding.tilPassword.editText?.text.toString()
            val email = binding.tilEmail.editText?.text.toString()
            loginWithEmailAndPassword(email,password)
        }

        binding.btnGoogle.setOnClickListener {
            loginWhitGoogle()
        }

        binding.btnRegistrate.setOnClickListener {
            val password = binding.tilPassword.editText?.text.toString()
            val email = binding.tilEmail.editText?.text.toString()
            singUpWithEmailAndPassword(email,password)
        }
    }

    private fun loginWithEmailAndPassword(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) { task ->
                if(task.isSuccessful){
                    val user = firebaseAuth.currentUser
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    miToast("Usuario No Se Encuentra Registrado")
                }
            }

    }

    private fun singUpWithEmailAndPassword(email: String, password: String) {
        if(validateEmailPassword(email,password)){
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        miToast("Usuario Registrado Correctamente")
                    }
                }
        }
        miToast("No Son Credenciales Validas")
    }

    private fun authenticateWithFirebase(idToken: String?) {
        val authCredentials = GoogleAuthProvider.getCredential(idToken,null)
        firebaseAuth.signInWithCredential(authCredentials)
            .addOnCompleteListener(this) { task->
                if(task.isSuccessful){
                    val user = firebaseAuth.currentUser
                    miToast("Bienvenido")
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun loginWhitGoogle(){
        val googleSignInOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail()
            .build()
        val googleClient = GoogleSignIn.getClient(this,googleSignInOption)
        val intent = googleClient.signInIntent
        googleLauncher.launch(intent)
    }

    private fun validateEmailPassword(email:String, password:String):Boolean{
        val isEmailValid = email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordValid = password.isNotEmpty()
        return isEmailValid && isPasswordValid
    }

    private fun miToast(message:String) {
        val miToast = Toast.makeText(this,message, Toast.LENGTH_LONG)
        miToast.show()
    }
}