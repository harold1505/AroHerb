package com.example.android.aeroherb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.database.*;
import android.widget.*;


public class PostQuestion extends AppCompatActivity {

    private DatabaseReference databaseReference;
    EditText ename;
    EditText eque;
    Button bsub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.android.aeroherb.R.layout.activity_post_question);
        databaseReference=FirebaseDatabase.getInstance().getReference().child("FAQ").push();

        ename= (EditText) findViewById(com.example.android.aeroherb.R.id.edittextName);
        eque = (EditText) findViewById(com.example.android.aeroherb.R.id.edittextQuestion);
        bsub = (Button) findViewById(com.example.android.aeroherb.R.id.buttonSubmit);

        bsub.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                postQuestion();
                Toast.makeText(getApplicationContext(),"Question Posted Successfully", android.widget.Toast.LENGTH_SHORT).show();
                ename.setText("");
                eque.setText("");
            }
        });




    }

    private void postQuestion(){
        String name = ename.getText().toString().trim();
        String que = eque.getText().toString().trim();
        String key = databaseReference.getKey();
        Question question = new Question(name,que,key);
        databaseReference.setValue(question);
    }
}
