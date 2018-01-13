package com.shortbrodemo.pch.shortbrodemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shortbrodemo.pch.shortbrodemo.R;
import com.shortbrodemo.pch.shortbrodemo.model.Edges;
import com.shortbrodemo.pch.shortbrodemo.model.Node;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<Edges> {

    public ListViewAdapter(Context context, List<Edges> edges) {
        super(context, 0, edges);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Node node = getItem(position).getNode();

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.basic_image_view, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.basic_image_view_iv);
        Picasso.with(getContext()).load(node.getThumbnail_src()).into(imageView);

        TextView textView = convertView.findViewById(R.id.basic_image_view_tv);
        try {
            textView.setText(node.getEdge_media_to_caption().getEdges().get(0).getNode().getText());
        } catch (NullPointerException e){
            Log.e("Bad", e.getLocalizedMessage());
        } catch (ArrayIndexOutOfBoundsException e){
            Log.e("Bad", e.getLocalizedMessage());
        }

        return convertView;
    }
}
