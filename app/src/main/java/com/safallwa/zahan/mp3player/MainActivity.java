package com.safallwa.zahan.mp3player;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ScanMusic scanMusic;
    private ArrayList<HashMap<String, String>> musicList = new ArrayList<HashMap<String, String>>();
    ArrayAdapter<String> adapter;
    ArrayList<String> songs=new ArrayList<>();
    ArrayList<String> paths=new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        listView=(ListView) findViewById(R.id.listView);
        scanMusic = new ScanMusic();
        musicList=scanMusic.getSong();

            for (int i = 0; i < musicList.size(); i++) {
                songs.add(i, musicList.get(i).get("songTitle"));
                paths.add(i, musicList.get(i).get("songPath"));
            }

            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songs);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent player = new Intent(MainActivity.this, PlayerClass.class);
                    player.putExtra("songindex", position);
                    startActivity(player);
                }
            });




    }



    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
