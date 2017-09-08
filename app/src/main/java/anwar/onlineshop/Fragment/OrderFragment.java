package anwar.onlineshop.Fragment;

import android.content.Context;
import android.content.Intent;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import anwar.onlineshop.Adapter.spinnerAdapter;
import anwar.onlineshop.HomeActivity;
import anwar.onlineshop.Model.AddressModel;
import anwar.onlineshop.Model.CartModel;
import anwar.onlineshop.Model.ProductModel;
import anwar.onlineshop.R;
import anwar.onlineshop.api.JsonObjectUtil;

import static anwar.onlineshop.R.id.Relative_layoutfor_fragments;
import static anwar.onlineshop.consts.CART_LIST;
import static anwar.onlineshop.consts.NAME;
import static anwar.onlineshop.consts.SELECTED_PRODUCT;

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
    private  ArrayList<CartModel> cartList=new ArrayList<>();
    private spinnerAdapter spinneradapter;
    private CartModel cartModel;
    private LinearLayout menuLayout;
    private EditText name,address,city,email,phone;
    private  Button spinnerOk,confrom_order;
    private spinnerAdapter spinnerAdapter;
    ArrayList<AddressModel> addressModels=new ArrayList<>();
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
        getExtraParam();
        name=(EditText)view.findViewById(R.id.text_name);
        address=(EditText)view.findViewById(R.id.text_addre);
        city=(EditText)view.findViewById(R.id.text_city);
        email=(EditText)view.findViewById(R.id.text_email);
        phone=(EditText)view.findViewById(R.id.text_phone);
        confrom_order=(Button) view.findViewById(R.id.confrom_order);
        confrom_order.setOnClickListener(this);
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
                if(address.getText().length() >=0 && phone.getText().length()>=0 ){
                    AddressModel addressModel=new AddressModel(name.getText().toString(),address.getText().toString(),
                            city.getText().toString(),email.getText().toString(),phone.getText().toString());
                    JsonObjectUtil objectUtil=  new JsonObjectUtil();
                    JSONObject jsonObject=objectUtil.CreateOrderObject(cartList,addressModel);
                    try{
                        JSONArray ja=jsonObject.getJSONArray("Address");
                        for (int i = 0; i <ja.length() ; i++) {
                            JSONObject j=ja.getJSONObject(i);
                            System.out.println("parse Address "+j.getString("name")+" "+j.getString("address")+" "+j.getString("city")
                                    +" "+j.getString("email")+" "+j.getString("phone") );
                        }
                        ja=jsonObject.getJSONArray("Order");
                        for (int i = 0; i <ja.length() ; i++) {
                            JSONObject j=ja.getJSONObject(i);
                            System.out.println("parse Order "+j.getString("productid")+" "+j.getString("name")+" "+j.getString("size")
                                    +" "+j.getString("color")+" "+j.getString("price")+" "+j.getString("quantity")+" "+j.getString("imageurl") );
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                ((HomeActivity)getActivity()).onBackPressed();
                break;
        }
    }

    public void getExtraParam() {
        Intent intent=getActivity().getIntent();
        if(!intent.equals(null)){
            if(intent.getStringExtra(NAME).equals("CartFragment")){
                cartList=intent.getParcelableArrayListExtra(CART_LIST);
                System.out.println("param "+intent.getParcelableArrayListExtra(CART_LIST));
                for (int i = 0; i <cartList.size() ; i++) {
                    System.out.println("Id "+cartList.get(i).getProductid()+"\n"+"Name "+cartList.get(i).getName());
                }
            }
            else if (intent.getStringExtra(NAME).equals("ViewFragment")){
                cartModel=(CartModel)intent.getParcelableExtra(SELECTED_PRODUCT);
                cartList.add(cartModel);
                System.out.println("Id "+cartModel.getProductid()+"\n"+"Name "+cartModel.getName()+"\n"+"price "+cartModel.getPrice());
            }
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
    }
}
