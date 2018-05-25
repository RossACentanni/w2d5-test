package com.example.w2d5_ex1;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = HomeFragment.class.getSimpleName()+"_TAG";
    private Button createContactBTN;
    private Button browseListBTN;

    private HomeListener listener;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener = (HomeListener) getActivity();
        }
        catch (ClassCastException e){
            Log.d(TAG, "onAttach: Declare HomeListener in Main");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createContactBTN = view.findViewById(R.id.createContactBTN);
        browseListBTN = view.findViewById(R.id.browseListBTN);

        createContactBTN.setOnClickListener(this);
        browseListBTN.setOnClickListener(this);
    }

    public interface HomeListener{
        public void switchToEntry();
        public void switchToList();
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.createContactBTN:
                listener.switchToEntry();
                break;
            case R.id.browseListBTN:
                listener.switchToList();
                break;
        }
    }
}
