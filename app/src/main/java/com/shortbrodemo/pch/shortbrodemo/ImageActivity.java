package com.shortbrodemo.pch.shortbrodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.shortbrodemo.pch.shortbrodemo.model.Node;
import com.shortbrodemo.pch.shortbrodemo.session.SessionData;
import com.shortbrodemo.pch.shortbrodemo.utils.UtilsImage;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageActivity extends AppCompatActivity {

    @BindView(R.id.activity_image_iv)
    ImageView imageView;
    @BindView(R.id.activity_image_tv)
    TextView textView;
    Node node = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        int number = bundle.getInt(MainActivity.EXTRA_IMAGE_POSITION, -1);
        if (number > -1) {
            doShowingImage(number);
        } else {
            doErrorNoImage();
        }

        UtilsImage.saveLocally(node.getDisplay_url());
    }

    private void doErrorNoImage() {
        textView.setText(R.string.image_error_loading);
    }

    private void doShowingImage(int number) {
        node = SessionData.getInstance().getBase().getGraphQl().getHashtag().getEdge_hashtag_to_media().getEdges().get(number).getNode();

        if (UtilsImage.isImageAlreadyLocal(UtilsImage.gettingImageName(node.getDisplay_url()))) {
            Picasso.with(getApplicationContext()).load(UtilsImage.getLocally(UtilsImage.gettingImageName(node.getDisplay_url()))).into(imageView);
        } else {
            Picasso.with(getApplicationContext()).load(node.getDisplay_url()).into(imageView);
        }

        try {
            textView.setText(node.getEdge_media_to_caption().getEdges().get(0).getNode().getText());
        } catch (NullPointerException e){
            Log.e("Bad", e.getLocalizedMessage());
        } catch (ArrayIndexOutOfBoundsException e){
            Log.e("Bad", e.getLocalizedMessage());
        }
    }
}
