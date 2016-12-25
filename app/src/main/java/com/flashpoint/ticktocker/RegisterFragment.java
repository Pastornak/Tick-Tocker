package com.flashpoint.ticktocker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by User on 25.12.2016.
 */

public class RegisterFragment extends Fragment {

    private EditText emailEditText;
    private EditText passwordEditText;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.registr_fragment, container, false);
        getActivity().setTitle("Registration");
        emailEditText = (EditText) view.findViewById(R.id.editTextEmail);
        mAuth = FirebaseAuth.getInstance();
        passwordEditText = (EditText) view.findViewById(R.id.editTextPassword);
        view.findViewById(R.id.buttonRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doRegisterAction(getUserEmail(), getUserPassword());
            }
        });
        return view;
    }

    @NonNull
    private String getUserEmail() {
        return emailEditText.getText().toString().trim();
    }

    @NonNull
    private String getUserPassword() {
        return passwordEditText.getText().toString().trim();
    }

    private void doRegisterAction(String email, String password) {
        if (validateEmailAndPassword(email, password)) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                showCalendarFragment();
                            } else {
                                showToast("Authentification failed bye bitch");
                            }
                        }
                    });
        }


    }

    private boolean validateEmailAndPassword(String email, String password) {
        //Якщо валідація true, то повернути імейл та пароль
        return validateFuckingEmail(email) && validatePassword(password);
    }

    private boolean validateFuckingEmail(String email) {
        //Якщо імейл відповідає шаблону для імейлів
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true;
        }
        showYOUREmailError();
        return false;
    }

    private void showYOUREmailError() {
        showToast("Sorry your email is not good ,go to hell");
    }

    private boolean validateYourIdiotikPassword(String password) {

        if (TextUtils.isEmpty(password)) {
            //Показує повідомлення про помилку
            showPasswordError();
            return false;
        }
        return true;

    }

    private boolean validatePassword(String password) {
        if (TextUtils.isEmpty(password)) {
            showPasswordError();
            return false;
        }
        return true;

    }

    private void showPasswordError() {
        showToast("You make a mistake when you try to write this ******* password");
    }

    private void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    private void showCalendarFragment() {
        FragmentActivity activity = getActivity();
        MainActivity mainActivity = (MainActivity) activity;
        mainActivity.showFragment(new CalendarFragment());
    }


}







