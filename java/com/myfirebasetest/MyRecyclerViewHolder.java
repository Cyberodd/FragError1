package com.myfirebasetest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

public class MyRecyclerViewHolder extends ViewHolder {

    public String name;
    TextView txt_title, txt_content;
    public MyRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        txt_content= (TextView)itemView.findViewById(R.id.txt_content);
        txt_title= (TextView)itemView.findViewById(R.id.txt_title);
    }

    public void name() {
    }
}
