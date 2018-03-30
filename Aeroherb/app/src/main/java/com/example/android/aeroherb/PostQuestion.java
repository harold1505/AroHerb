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
        if(que.equals("My papaya plant has small, dark raised spots. What to do?")){
        Answer ansobj = new Answer("Bot","This is due to bacterial infection. Use R23 Pesticide");
        databaseReference=FirebaseDatabase.getInstance().getReference().child("FAQ").child(key).child("Answers");
        databaseReference.push().setValue(ansobj);}
        else if(que.equals("My banana tree has curly leaves. What to do?")){
            Answer ansobj = new Answer("Bot","Use proper fertilizer and remove the infected leaves.");
            databaseReference=FirebaseDatabase.getInstance().getReference().child("FAQ").child(key).child("Answers");
            databaseReference.push().setValue(ansobj);}
       else if(que.equals("The jasmine plant has stunned foliage. What to do?")){
            Answer ansobj = new Answer("Bot","Use fungicide to prevent spreading");
            databaseReference=FirebaseDatabase.getInstance().getReference().child("FAQ").child(key).child("Answers");
            databaseReference.push().setValue(ansobj);}
        else if(que.equals("Cucumber has yellow spots and streaks. What to do?")){
            Answer ansobj = new Answer("Bot","Remove the virus affected plant to prevent spreading.");
            databaseReference=FirebaseDatabase.getInstance().getReference().child("FAQ").child(key).child("Answers");
            databaseReference.push().setValue(ansobj);}
       else if(que.equals("Neem tree has dried leaf tips. What to do?")){
            Answer ansobj = new Answer("Bot","Remove infected leaves and avoid overwatering");
            databaseReference=FirebaseDatabase.getInstance().getReference().child("FAQ").child(key).child("Answers");
            databaseReference.push().setValue(ansobj);}
        else if(que.equals("Mango tree produces disformed fruit. What to do?")){
            Answer ansobj = new Answer("Bot","Add fertilizer regularly to produce healthy fruits");
            databaseReference=FirebaseDatabase.getInstance().getReference().child("FAQ").child(key).child("Answers");
            databaseReference.push().setValue(ansobj);}

    }
}
