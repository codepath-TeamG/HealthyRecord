package com.example.zen.healthyrecord.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.zen.healthyrecord.AddItemActivity;
import com.example.zen.healthyrecord.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentAddItemPage.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentAddItemPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAddItemPage extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView txtDatePicker;
    TextView txtTimePicker;


    private FloatingActionButton btnOpenCamera;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private OnFragmentInteractionListener mListener;

    public FragmentAddItemPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentAddItemPage.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentAddItemPage newInstance(String param1, String param2) {
        FragmentAddItemPage fragment = new FragmentAddItemPage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_item_page, container, false);

        txtDatePicker = (TextView)v.findViewById(R.id.txtDatePicker);
        txtTimePicker = (TextView)v.findViewById(R.id.txtTimePicker);



        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat tf = new SimpleDateFormat("HH:mm");
        String formattedDate = df.format(c.getTime());
        String formattedTime = tf.format(c.getTime());

        Spinner spinner = (Spinner) v.findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(),R.layout.spinner_item){
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = LayoutInflater.from(getContext()).inflate(R.layout.spinner_item,parent,false);
                ((TextView)v.findViewById(android.R.id.text1)).setText(getItem(position));
                return v;
            }
        };

        adapter.add("Fruit");
        adapter.add("Softdrink");
        spinner.setAdapter(adapter);
        txtDatePicker.setText(formattedDate);
        txtTimePicker.setText(formattedTime);

        return v;


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnOpenCamera = (FloatingActionButton) view.findViewById(R.id.open_camera);
        btnOpenCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((AddItemActivity) getActivity()).camera(view);
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
