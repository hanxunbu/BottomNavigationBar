package com.huiyi.resume_fan.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huiyi.resume_fan.R;

/**
 * Created by LW on 2017/12/1.
 */
public class HomeFragment extends Fragment{

    public static HomeFragment newInstance(String paraml){
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("agres1",paraml);
        fragment.setArguments(args);
        return fragment;
    }

    public HomeFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me,container,false);
        Bundle bundle = getArguments();
        String agrs1 = bundle.getString("agres1");
        TextView tv = (TextView) view.findViewById(R.id.tv_me);
        tv.setText(agrs1);
        return view;
    }
}
