package anwar.onlineshop.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import anwar.onlineshop.Adapter.spinnerAdapter;
import anwar.onlineshop.HomeActivity;
import anwar.onlineshop.R;

import static anwar.onlineshop.R.id.Relative_layoutfor_fragments;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OrderFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ListPopupWindow popupWindow;
    private Spinner spColor,spSize;
    private spinnerAdapter spinneradapter;
    private LinearLayout menuLayout;
    private EditText name,address,city,email,phone;
    private  Button spinnerOk,confrom_order;
    private spinnerAdapter spinnerAdapter;
    private String colorList[]=new String[]{"Select Color","RED","GREEN","BLUE","WHITE","BLACK"};
    private String sizeList[]=new String[]{"Select Size","SMALL","MEDIUM","LARGE","EXTRA"};
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public OrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderFragment newInstance(String param1, String param2) {
        OrderFragment fragment = new OrderFragment();
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
        final View view= inflater.inflate(R.layout.fragment_order, container, false);
        name=(EditText)view.findViewById(R.id.text_name);
        address=(EditText)view.findViewById(R.id.text_addre);
        city=(EditText)view.findViewById(R.id.text_city);
        email=(EditText)view.findViewById(R.id.text_email);
        phone=(EditText)view.findViewById(R.id.text_phone);
        confrom_order=(Button) view.findViewById(R.id.confrom_order);
        confrom_order.setOnClickListener(this);
        Handler h=new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                ListDialog(view);
            }
        },200);

        return view;
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
/*        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.confrom_order:
                if(name.getText().length() >=6 && address.getText().length()>=12 && city.getText().length()>=4){
                    if(phone.getText().length()>10){
                        FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                        for (int i = 0; i <fragmentManager.getBackStackEntryCount() ; i++) {
                            fragmentManager.popBackStack();
                        }
                        HomeFragment homeFragment=new HomeFragment();
                        fragmentManager.beginTransaction().replace(Relative_layoutfor_fragments,
                                homeFragment, homeFragment.getTag()).commit();
                    }else Toast.makeText(getActivity()," Make sure your phone is correct",Toast.LENGTH_SHORT).show();
                }else Toast.makeText(getActivity()," Make sure your information is correct",Toast.LENGTH_SHORT).show();
                break;
            case R.id.spinner_ok:
                if(!spColor.getSelectedItem().equals("Select Color") && !spSize.getSelectedItem().equals("Select Size"))
                    popupWindow.dismiss();
                else Toast.makeText(getActivity()," Please choose color and size",Toast.LENGTH_SHORT).show();
                break;
        }
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
    private void ListDialog(View view){
        popupWindow = new ListPopupWindow(getActivity());
        LayoutInflater inflater= getActivity().getLayoutInflater();
        View v=inflater.inflate(R.layout.ch_spinner, null);
        spColor=(Spinner)v.findViewById(R.id.spinner_color);
        spSize=(Spinner)v.findViewById(R.id.spinner_size);
        spinnerOk=(Button)v.findViewById(R.id.spinner_ok);
        addItemOnSpinner();
        spinnerOk.setOnClickListener(this);
        popupWindow.setPromptView(v);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnchorView(view.findViewById(R.id.texxxxxxxxx));
        popupWindow.setWidth(250);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.color.cardview_light_background));
        popupWindow.show();
    }
    private void addItemOnSpinner() {
/*        spinneradapter = new spinnerAdapter(getActivity(), R.layout.custom_spinner_item);
        spinneradapter.addAll(colorList);
        spinneradapter.add("Select Blood Group");
        spinneradapter.setDropDownViewResource(R.layout.custom_spinner_item);
        spColor.setAdapter(spinneradapter);
        spColor.setSelection(spinneradapter.getCount());*/
        ArrayAdapter adapter=new ArrayAdapter<>(getActivity(),R.layout.custom_spinner_item,colorList);
        adapter.setDropDownViewResource(R.layout.custom_spinner_item);
        spColor.setAdapter(adapter);
        ArrayAdapter adapter1=new ArrayAdapter<>(getActivity(),R.layout.custom_spinner_item,sizeList);
        adapter1.setDropDownViewResource(R.layout.custom_spinner_item);
        spSize.setAdapter(adapter1);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void onResume() {
        super.onResume();
        //((HomeActivity)getActivity()).ShowAndHide();
    }

    @Override
    public void onPause() {
        super.onPause();
        popupWindow.dismiss();
    }
}
