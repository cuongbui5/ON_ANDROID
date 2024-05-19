package com.example.myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcv;

    private MyDB myDB;

    private CustomAdapter customAdapter;
    private SearchView searchView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        searchView=findViewById(R.id.sv);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                try {
                    if(newText.isEmpty()){
                        customAdapter.search(0);
                    }else {
                        customAdapter.search(Double.parseDouble(newText));
                    }
                    customAdapter.notifyDataSetChanged();

                }catch (Exception e){
                    Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });


        myDB=new MyDB(this, "MYDB", null, 1);
        //myDB.initData();




        rcv=findViewById(R.id.rcv);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        customAdapter =new CustomAdapter(myDB.getAll());
        customAdapter.sort();
        rcv.setAdapter(customAdapter);






    }





}