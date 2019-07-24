package com.myfirebasetest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostActivity extends AppCompatActivity {

    EditText name, edt_title, edt_content;
    Button btn_post;
    RecyclerView recyclerView;

    //Firebase
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseRecyclerOptions<Post> options;
    FirebaseRecyclerAdapter<Post, MyRecyclerViewHolder> adapter;
    private EditText edt_dame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        name= findViewById(R.id.name);
        edt_title= (EditText)findViewById(R.id.edt_title);
        edt_dame= (EditText)findViewById(R.id.edt_dame);
        btn_post= (Button)findViewById(R.id.btn_post);
        recyclerView= (RecyclerView)findViewById(R.id.recyclerview);

        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference("herd");

        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postComment();
            }
        });
        displayContent();

    }

    private void postComment() {

        String name1 =  name.getText().toString();
        String title= edt_title.getText().toString();
        String content= edt_dame.getText().toString();

        Post post= new Post(name1, title, content);

        databaseReference.child(name1) //use this to set unique id
                .setValue(post);



        
    }

    private void displayContent() {
        options=
                new FirebaseRecyclerOptions.Builder<Post>()
                .setQuery(databaseReference, Post.class)
                .build();

        adapter=
                new FirebaseRecyclerAdapter<Post, MyRecyclerViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull MyRecyclerViewHolder holder, int position, @NonNull Post model) {
                        holder.name();
                        holder.txt_title.setText(model.getTitle());
                        holder.txt_content.setText(model.getContent());
                    }

                    @NonNull
                    @Override
                    public MyRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                        View itemView;
                        itemView = LayoutInflater. from(getBaseContext()).inflate(R.layout.post_item, viewGroup, false);
                        return new MyRecyclerViewHolder(itemView);
                    }
        };


      adapter.startListening();
      recyclerView.setAdapter(adapter);
    }


}
