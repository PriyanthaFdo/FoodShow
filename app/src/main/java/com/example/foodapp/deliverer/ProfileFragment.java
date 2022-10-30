package com.example.foodapp.deliverer;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.classes.Deliveryman;
import com.example.foodapp.login.LoginMain;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {
    private Button logout, update, delete;
    private TextView name, mail, phone, password;
    private EditText edt_name, edt_mobile;
    private ProgressBar progressBar;
    private Boolean deleteConfirm = false;

    private final Deliveryman deliveryman = new Deliveryman();
    private final String uid = FirebaseAuth.getInstance().getUid();
    private final DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Deliveryman").child(uid);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.delivery_fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        logout = view.findViewById(R.id.btn_driv_logout);
        update = view.findViewById(R.id.btn_dri_update);
        delete = view.findViewById(R.id.dri_del);
        name = view.findViewById(R.id.lbl_dri_name);
        mail = view.findViewById(R.id.lbl_dri_mail);
        phone = view.findViewById(R.id.lbl_dri_mobile);
        password = view.findViewById(R.id.lbl_dri_pass);
        edt_name = view.findViewById(R.id.edt_dri_name);
        edt_mobile = view.findViewById(R.id.edt_dri_mobile);
        progressBar = getActivity().findViewById(R.id.dri_profile_progressbar);

        progressBar.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        db.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()) {
                    deliveryman.setUid(uid);
                    deliveryman.setName(snapshot.child("name").getValue().toString());
                    deliveryman.setMobile(snapshot.child("mobile").getValue().toString());
                    deliveryman.setEmail(snapshot.child("email").getValue().toString());
                    deliveryman.setPassword(snapshot.child("password").getValue().toString());

                    name.setText(deliveryman.getName());
                    mail.setText(deliveryman.getEmail());
                    phone.setText(deliveryman.getMobile());
                    password.setText(deliveryman.getPassword());
                }
                else{
                    Toast.makeText(getContext(), "Error in getting" + uid, Toast.LENGTH_SHORT).show();

                }
                progressBar.setVisibility(View.INVISIBLE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

        update.setOnClickListener(v -> {
            if(update.getText().equals("Update")) {
                name.setVisibility(View.INVISIBLE);
                phone.setVisibility(View.INVISIBLE);
                edt_name.setVisibility(View.VISIBLE);
                edt_mobile.setVisibility(View.VISIBLE);

                edt_name.setText(deliveryman.getName());
                edt_mobile.setText(deliveryman.getMobile());
                update.setText(R.string.save);
            }
            else if(update.getText().equals("Save")){
                progressBar.setVisibility(View.VISIBLE);
                getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                deliveryman.setName(edt_name.getText().toString());
                deliveryman.setMobile(edt_mobile.getText().toString());

                db.setValue(deliveryman);

                name.setVisibility(View.VISIBLE);
                phone.setVisibility(View.VISIBLE);
                edt_name.setVisibility(View.INVISIBLE);
                edt_mobile.setVisibility(View.INVISIBLE);
                update.setText(R.string.btn_dri_update);

                progressBar.setVisibility(View.INVISIBLE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        });

        logout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getContext(), LoginMain.class));
            getActivity().finish();
        });
/*
        delete.setOnClickListener(v -> {
            if(!deleteConfirm){
                deleteConfirm = true;
                delete.setText(R.string.confirm_delete);
            }
            else{
                progressBar.setVisibility(View.VISIBLE);
                getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                AuthCredential credential = EmailAuthProvider.getCredential(db.child("email").toString(), db.child("password").toString());
                user.reauthenticate(credential).addOnCompleteListener(e -> {

                    FirebaseAuth.getInstance().getCurrentUser().delete()
                            .addOnSuccessListener(x -> {
                                db.removeValue();
                                Toast.makeText(getContext(), "Account permanently Deleted", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getContext(), LoginMain.class));
                                getActivity().finish();

                                progressBar.setVisibility(View.INVISIBLE);
                                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            }).addOnFailureListener(c -> {
                                Toast.makeText(getContext(), "Error! "+c.getMessage(), Toast.LENGTH_LONG).show();
                                deleteConfirm = null;
                            });
                });
            }
        });*/
    }
}