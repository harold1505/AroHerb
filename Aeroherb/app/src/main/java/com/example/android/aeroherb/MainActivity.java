package com.example.android.aeroherb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.content.Intent;
import com.google.firebase.database.*;
import com.firebase.ui.database.*;


public class MainActivity extends AppCompatActivity {


    private DatabaseReference databaseReference;
    android.support.v7.widget.RecyclerView recyclerView;
    Query query;
    FirebaseRecyclerOptions<Question> options;
    FirebaseRecyclerAdapter adapter;

    public MainActivity(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
        query= databaseReference.child("FAQ");
        options = new FirebaseRecyclerOptions.Builder<com.example.android.aeroherb.Question>()
                        .setQuery(query, com.example.android.aeroherb.Question.class)
                        .build();


        class QuestionHolder extends android.support.v7.widget.RecyclerView.ViewHolder {

            TextView question, name,key;
            android.support.v7.widget.CardView cardView;
            public QuestionHolder(android.view.View itemView) {
                super(itemView);
                question = itemView.findViewById(com.example.android.aeroherb.R.id.questionText);
                name = itemView.findViewById(com.example.android.aeroherb.R.id.submittedText);
                key=itemView.findViewById(com.example.android.aeroherb.R.id.keyText);
                cardView = itemView.findViewById(R.id.cardlayout);
            }
        }
        adapter = new FirebaseRecyclerAdapter<Question, QuestionHolder>(options) {
            @Override
            public QuestionHolder onCreateViewHolder(android.view.ViewGroup parent, int viewType) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.message for each item
                android.view.View view = android.view.LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_layout, parent, false);

                return new QuestionHolder(view);
            }

            @Override
            protected void onBindViewHolder(QuestionHolder holder, int position, final com.example.android.aeroherb.Question model) {
                final String key=model.getKey();
                final String question = model.getQue();
                final String name=model.getName();
                holder.question.setText(question);
                holder.question.setTypeface(android.graphics.Typeface.DEFAULT_BOLD);
                holder.question.setTextColor(getResources().getColor(R.color.colorPrimary));
                String n="Posted by: "+name;
                holder.name.setText(n);
                holder.key.setText(key);
                holder.cardView.setOnClickListener(new android.view.View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        Intent i = new Intent(MainActivity.this, PostAnswer.class);
                        i.putExtra("KEY",key);
                        i.putExtra("QUESTION",question);
                        i.putExtra("NAME",name);
                        startActivity(i);
                    }
            });

            }

        };


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button=(Button) findViewById(com.example.android.aeroherb.R.id.button1);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new android.support.v7.widget.LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        button.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent= new Intent(MainActivity.this,PostQuestion.class);
                startActivity(intent);
            }
        });

    }

    @Override protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }



}

