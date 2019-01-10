package com.project.inki.fragmentexam;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class ImageFragment extends Fragment {
    ImageView imageView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootview=(ViewGroup)inflater.inflate(R.layout.fragment_view,container,false);

        imageView=rootview.findViewById(R.id.imageView);

        return rootview;
    }

    public void setImage(int res) {
        imageView.setImageResource(res);
    }
}
