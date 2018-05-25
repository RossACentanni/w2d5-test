package com.example.w2d5_ex1;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;


/**
 * A simple {@link Fragment} subclass.
 */
public class EntryFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = EntryFragment.class.getSimpleName()+"_TAG";
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    private EditText firstNameET;
    private EditText lastNameET;
    private EditText ageET;
    private EditText phoneET;
    private EditText emailET;

    private Button pictureBTN;
    private Button saveBTN;
    private Button backBTN;

    private BackButtonListener listener;

    public EntryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener = (BackButtonListener) getActivity();
        }
        catch (ClassCastException e){
            Log.d(TAG, "onAttach: Implement BackButtonListener in Main");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entry, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firstNameET = view.findViewById(R.id.firstNameET);
        lastNameET = view.findViewById(R.id.lastNameET);
        ageET = view.findViewById(R.id.ageET);
        emailET = view.findViewById(R.id.emailET);
        phoneET = view.findViewById(R.id.phoneET);

        pictureBTN = view.findViewById(R.id.pictureBTN);
        saveBTN = view.findViewById(R.id.saveBTN);
        backBTN = view.findViewById(R.id.backBTN);

        pictureBTN.setOnClickListener(this);
        saveBTN.setOnClickListener(this);
        backBTN.setOnClickListener(this);
    }

    public interface BackButtonListener{
        public void switchToHome();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pictureBTN:
                takePicture();
                break;
            case R.id.saveBTN:
                writeToFile();
                break;
            case R.id.backBTN:
                listener.switchToHome();
                break;
        }
    }

    private void takePicture(){
        PackageManager packMan = getContext().getPackageManager();
        boolean hasCamera = packMan.hasSystemFeature(PackageManager.FEATURE_CAMERA);
        if(!hasCamera){
            Toast.makeText(getContext(), "You don't have a camera!", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if((takePictureIntent.resolveActivity(packMan)) != null){
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    //Fires off when returning from camera.
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_IMAGE_CAPTURE){
            Log.d(TAG, "onActivityResult: Photo successfully taken.");
        }
    }

    private void writeToFile(){
        String firstName = firstNameET.getText().toString();
        String lastName = lastNameET.getText().toString();
        String age = ageET.getText().toString();
        String email = emailET.getText().toString();
        String phone = phoneET.getText().toString();

        String toWrite = firstName + " " + lastName + "\n"  + phone + "\n";
        try{
            OutputStreamWriter osw = new OutputStreamWriter(getContext().openFileOutput(
                    MainActivity.FILE_NAME, Context.MODE_APPEND));
            osw.write(toWrite);
            osw.close();
        }
        catch(IOException e) {
            Log.e(TAG, "writeToFile: File write failed.");
        }
    }

}
