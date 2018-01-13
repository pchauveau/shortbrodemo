package com.shortbrodemo.pch.shortbrodemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.shortbrodemo.pch.shortbrodemo.adapter.ListViewAdapter;
import com.shortbrodemo.pch.shortbrodemo.event.GetAllImageEvent;
import com.shortbrodemo.pch.shortbrodemo.event.WebserviceEvent;
import com.shortbrodemo.pch.shortbrodemo.model.Edges;
import com.shortbrodemo.pch.shortbrodemo.model.Node;
import com.shortbrodemo.pch.shortbrodemo.session.SessionData;
import com.shortbrodemo.pch.shortbrodemo.utils.UtilsImage;
import com.shortbrodemo.pch.shortbrodemo.webservices.GetBroccoli;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.activity_main_lv)
    ListView listView;
    @BindView(R.id.activity_main_btn)
    Button button;

    public static final String EXTRA_IMAGE_POSITION = "com.shortbrodemo.pch.shortbrodemo.IMAGE_POSITION";


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(WebserviceEvent event) {
        if (event.isOk()) {
            listView.setAdapter(new ListViewAdapter(getApplicationContext(), SessionData.getInstance().getBase().getGraphQl().getHashtag().getEdge_hashtag_to_media().getEdges()));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(getApplicationContext(), ImageActivity.class);
                    intent.putExtra(EXTRA_IMAGE_POSITION, i);
                    startActivity(intent);
                }
            });
        } else {
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(GetAllImageEvent event) {
        if (event.isOk()) {
            Toast.makeText(this, R.string.image_downloaded, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, R.string.image_downloaded_error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ButterKnife.bind(this);

        requestPermission();

        GetBroccoli getBroccoli = new GetBroccoli();
        getBroccoli.GetBroccoli();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAllImage();
            }
        });
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        GetBroccoli getBroccoli = new GetBroccoli();
        getBroccoli.GetBroccoli();
    }

    /**
     * Method to get all image in SessionData and calling the UtilsImage function to save them locally
     */
    private void getAllImage() {
        if (SessionData.getInstance().getBase() != null) {
            List<String> strings = new ArrayList<>();

            for(Edges edge : SessionData.getInstance().getBase().getGraphQl().getHashtag().getEdge_hashtag_to_media().getEdges()){
                strings.add(edge.getNode().getDisplay_url());
            }

            UtilsImage.saveAllLocally(strings, this);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    public void requestPermission() {
        List<String> strings = new ArrayList<>();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            strings.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            strings.add(Manifest.permission.INTERNET);
        }

        String[] stringsTab = new String[strings.size()];
        for (int i = 0; i < strings.size(); i++) {
            stringsTab[i] = strings.get(i);
        }

        if (stringsTab.length > 0) {
            ActivityCompat.requestPermissions(this, stringsTab, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    requestPermission();
                }
                return;
            }
        }
    }
}
