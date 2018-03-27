package com.example.android.aeroherb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PostAnswer extends AppCompatActivity {
    android.widget.TextView textViewquestion,textViewname;
    com.google.firebase.database.DatabaseReference databaseReference;
    android.widget.EditText ename,eans;
    android.widget.Button bsub;


    public PostAnswer(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_answer);
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("NAME");
        String n = "Posted by: "+name;
        textViewquestion=findViewById(com.example.android.aeroherb.R.id.textView2);
        textViewname=findViewById(com.example.android.aeroherb.R.id.textView3);
        String question = "Question:"+bundle.getString("QUESTION");
        textViewquestion.setText(question);
        textViewname.setText(n);

        databaseReference= com.google.firebase.database.FirebaseDatabase.getInstance().getReference().child("FAQ").child(bundle.getString("KEY")).child("Answers");

        ename= (android.widget.EditText) findViewById(com.example.android.aeroherb.R.id.nameedittext);
        eans = (android.widget.EditText) findViewById(com.example.android.aeroherb.R.id.ansedittext);
        bsub = (android.widget.Button) findViewById(com.example.android.aeroherb.R.id.ansbutton);

        bsub.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                postAnswer();
                android.widget.Toast.makeText(getApplicationContext(),"Answer Posted Successfully", android.widget.Toast.LENGTH_SHORT).show();
                ename.setText("");
                eans.setText("");
            }
        });




    }

    @Override protected void onStart() {
        super.onStart();
        android.support.v7.widget.RecyclerView recyclerView;
        com.google.firebase.database.Query query;
        com.firebase.ui.database.FirebaseRecyclerOptions<com.example.android.aeroherb.Answer> options;
        com.firebase.ui.database.FirebaseRecyclerAdapter adapter;
        com.google.firebase.database.DatabaseReference databaseReferencea = com.google.firebase.database.FirebaseDatabase.getInstance().getReference();
        Bundle bundle = getIntent().getExtras();
        String key = bundle.getString("KEY");
        query= databaseReferencea.child("FAQ").child(key).child("Answers");
        options = new com.firebase.ui.database.FirebaseRecyclerOptions.Builder<com.example.android.aeroherb.Answer>()
                .setQuery(query, Answer.class)
                .build();


        class AnswerHolder extends android.support.v7.widget.RecyclerView.ViewHolder {

            android.widget.TextView answer, name;
            public AnswerHolder(android.view.View itemView) {
                super(itemView);
                answer = itemView.findViewById(com.example.android.aeroherb.R.id.questionText);
                name = itemView.findViewById(com.example.android.aeroherb.R.id.submittedText);
            }
        }
        adapter = new com.firebase.ui.database.FirebaseRecyclerAdapter<com.example.android.aeroherb.Answer, AnswerHolder>(options) {
            @Override
            public AnswerHolder onCreateViewHolder(android.view.ViewGroup parent, int viewType) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.message for each item
                android.view.View view = android.view.LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_layout, parent, false);

                return new AnswerHolder(view);
            }

            @Override
            protected void onBindViewHolder(AnswerHolder holder, int position, final com.example.android.aeroherb.Answer model) {
                final String answer = model.getAns();
                final String name=model.getName();
                holder.answer.setText(answer);
                String n="Answered by: "+name;
                holder.name.setText(n);

            }

        };
        recyclerView = findViewById(R.id.ansrecyclerview);
        recyclerView.setLayoutManager(new android.support.v7.widget.LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    private void postAnswer(){
        String name = ename.getText().toString().trim();
        String ans = eans.getText().toString().trim();
        com.example.android.aeroherb.Answer answer = new Answer(name,ans);
        databaseReference.push().setValue(answer);
    }

}
