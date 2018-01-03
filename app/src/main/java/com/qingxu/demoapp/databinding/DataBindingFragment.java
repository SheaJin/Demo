package com.qingxu.demoapp.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qingxu.demoapp.R;

import java.util.ArrayList;
import java.util.List;


public class DataBindingFragment extends Fragment {

    private FragmentDataBindingBinding binding;
    private RecyclerView mRv;
    private List<UserInfo> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_data_binding,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.setUser(new UserInfo("name","12"));

        mRv = view.findViewById(R.id.recyclerView);
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));

        list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(new UserInfo("jahsasasjsa", "14564345"));
        }
        mRv.setAdapter(new DataBindingAdapter(getActivity(), list));
    }
}

