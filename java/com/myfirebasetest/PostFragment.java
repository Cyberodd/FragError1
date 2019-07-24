package com.myfirebasetest;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PostFragment extends Fragment {

    EditText edt_title;
    EditText edt_content;
    EditText edt_location;
    EditText edt_dame,edt_name;
    String edt_date;
    Button btn_post;
    RecyclerView recyclerView;

    //Firebase
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String name,title,content, location;
    View mview;
    FirebaseRecyclerOptions<Post> options;
    FirebaseRecyclerAdapter<Post, MyRecyclerViewHolder> adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mview= inflater.inflate(R.layout.activity_post, container, false);


        edt_name= mview.findViewById(R.id.name);
        edt_dame= mview.findViewById(R.id.edt_dame);
        edt_title= mview.findViewById(R.id.edt_title);
        btn_post= mview.findViewById(R.id.btn_post);
        recyclerView= mview.findViewById(R.id.recyclerview);

        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference("posts");

        btn_post.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                postComment();


            }
        });
        displayContent();
return mview;
    }

    private void postComment() {
        title= edt_title.getText().toString();
        content= edt_dame.getText().toString();
        name=edt_name.getText().toString();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Post post= new Post(name, title, content);

                databaseReference.child(title) //use this to set unique id
                        .setValue(post);
                adapter.notifyDataSetChanged();
                Toast.makeText(getActivity(),"Success",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        
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
                        holder.txt_title.setText(model.getTitle());
                        holder.txt_content.setText(model.getContent());
                    }

                    @NonNull
                    @Override
                    public MyRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                        View itemView;
                        itemView = LayoutInflater. from(getActivity()).inflate(R.layout.post_item, viewGroup, false);
                        return new MyRecyclerViewHolder(itemView);
                    }
                };


        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }


}
