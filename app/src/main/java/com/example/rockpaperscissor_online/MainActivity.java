package com.example.rockpaperscissor_online;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase db=FirebaseDatabase.getInstance();
    DatabaseReference rootRef=db.getReference();
    DatabaseReference gameRef=rootRef.child("game");

    TextView textView;
    Button rock,paper,scissor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        rootRef.setValue("cool");
//        rootRef.child("Users").setValue("shaf");
//         rootRef.child("Users").child("01").child("Email").setValue("sha@gmail.com");
//        rootRef.child("Users").child("01").child("Name").setValue("Shaf");

        textView=findViewById(R.id.textView);
        rock=findViewById(R.id.rock);
        paper=findViewById(R.id.paper);
        scissor=findViewById(R.id.scissor);

        rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameRef.setValue("Rock");
            }
        });

        paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameRef.setValue("Paper");
            }
        });
        scissor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameRef.setValue("Scissor");
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        gameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               //for getting value from the database
                String value=dataSnapshot.getValue(String.class);
                textView.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("TAG","Something missing");
            }
        });
    }
}
