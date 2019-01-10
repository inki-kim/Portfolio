package com.project.inki.fragmentexam;

import android.support.v7.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MainFragment extends Fragment {
    Button button1,button2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);

        Toast.makeText(getActivity().getApplicationContext(), "onCreateView호출", Toast.LENGTH_SHORT).show();
        button1 = viewGroup.findViewById(R.id.button1);
        button2 = viewGroup.findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity) getActivity();
                activity.onFragmentChanged(0);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity) getActivity();
                ActionBar actionBar=activity.getSupportActionBar();

                actionBar.setLogo(R.drawable.home);
                actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP|ActionBar.DISPLAY_SHOW_HOME|ActionBar.DISPLAY_USE_LOGO);


            }
        });

        return viewGroup;
    }

    @Override
    public void onAttach(Context context) {
        Toast.makeText(getActivity().getApplicationContext(), "onAttach호출", Toast.LENGTH_SHORT).show();
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Toast.makeText(getActivity().getApplicationContext(), "onCreate호출", Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Toast.makeText(getActivity().getApplicationContext(), "onActivityCreated호출", Toast.LENGTH_SHORT).show();
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        Toast.makeText(getActivity().getApplicationContext(), "onStart호출", Toast.LENGTH_SHORT).show();
        super.onStart();
    }

    @Override
    public void onResume() {
        Toast.makeText(getActivity().getApplicationContext(), "onResume호출", Toast.LENGTH_SHORT).show();
        super.onResume();
    }

    @Override
    public void onPause() {
        Toast.makeText(getActivity().getApplicationContext(), "onPause호출", Toast.LENGTH_SHORT).show();
        super.onPause();
    }

    @Override
    public void onStop() {
        Toast.makeText(getActivity().getApplicationContext(), "onStop호출", Toast.LENGTH_SHORT).show();
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Toast.makeText(getActivity().getApplicationContext(), "onDestroyView호출", Toast.LENGTH_SHORT).show();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Toast.makeText(getActivity().getApplicationContext(), "onDestroy호출", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Toast.makeText(getActivity().getApplicationContext(), "onDetach호출", Toast.LENGTH_SHORT).show();
        super.onDetach();
    }
}
