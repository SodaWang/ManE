package com.hshy41.mane.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hshy41.mane.R;
import com.hshy41.mane.adapter.recyclerviewadapter.Tour;
import com.hshy41.mane.adapter.recyclerviewadapter.ZhouBianShangHu;
import com.hshy41.mane.base.BaseFragment;
import com.hshy41.mane.my.activity.ProductInfoActivity;
import com.hshy41.mane.utils.Cons;

/**
 * Created by Administrator on 2015/12/22.
 */
public class TourFragment extends Fragment {
    private int mIndex;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView rv = (RecyclerView) inflater.inflate(
                R.layout.recyclerview, container, false);
        setupRecyclerView(rv);

        return rv;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("index", mIndex);
        Log.d("test", "call onSaveInstanceState:" + mIndex);
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        Tour adapter = new Tour(getActivity());
        adapter.setOnItemClickLitener(new Tour.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("activity", Cons.ACTIVITY_TOUR);
                Intent intent = new Intent(getActivity(), ProductInfoActivity.class);
                intent.putExtras(bundle);
                getActivity().startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }

}
