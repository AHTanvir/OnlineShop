package anwar.onlineshop.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.xgc1986.ripplebutton.widget.RippleButton;

import anwar.onlineshop.HomeActivity;
import anwar.onlineshop.Model.ProductModel;
import anwar.onlineshop.Model.CartModel;
import anwar.onlineshop.R;
import anwar.onlineshop.storage.SharedPref;

import static anwar.onlineshop.R.id.Relative_layoutfor_fragments;
import static anwar.onlineshop.consts.NAME;
import static anwar.onlineshop.consts.SELECTED_PRODUCT;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
//    int buttonColor = getResources().getColor(android.R.color.holo_red_light);
  //  int rippleColor = getResources().getColor(android.R.color.holo_blue_light);
    private ListPopupWindow popupWindow;
    private SpinnerAdapter  spinneradapter;
    private Spinner spColor,spSize;
    private LinearLayout menuLayout;
    private RippleButton addToCart,orderBtn;
    private TextView brand_name,product_id,size,color,price;
    private Button backBtn,spinnerOk;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView image;
    private String colorList[]=new String[]{"Select Color","RED","GREEN","BLUE","WHITE","BLACK"};
    private String sizeList[]=new String[]{"Select Size","SMALL","MEDIUM","LARGE","EXTRA"};
    private SharedPref sharedPref;
    private ProductModel productModel;
    private String clickfor;
    private View viewvv;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewFragment newInstance(String param1, String param2) {
        ViewFragment fragment = new ViewFragment();
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
        Intent i=getActivity().getIntent();
        productModel=(ProductModel)i.getSerializableExtra(SELECTED_PRODUCT);
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_view, container, false);
        viewvv=view;
        sharedPref=new SharedPref(getActivity());
        collapsingToolbarLayout=(CollapsingToolbarLayout)view.findViewById(R.id.collapsingtool);
        collapsingToolbarLayout.setTitle(productModel.getName());
        image=(ImageView)view.findViewById(R.id.toolbart_product_img);
        image.setImageResource(Integer.parseInt(productModel.getUrl()));
        backBtn=(Button) view.findViewById(R.id.view_backBtn);
        backBtn.setVisibility(View.VISIBLE);
        brand_name=(TextView)view.findViewById(R.id.brand_name);
        product_id=(TextView)view.findViewById(R.id.product_id);
        price=(TextView)view.findViewById(R.id.price) ;
        size=(TextView)view.findViewById(R.id.size);
        color=(TextView)view.findViewById(R.id.color);
        orderBtn=(RippleButton) view.findViewById(R.id.order);
        addToCart=(RippleButton)view.findViewById(R.id.add_to_cart);
        orderBtn.setOnClickListener(this);
        brand_name.setText(productModel.getName());
        product_id.setText(productModel.getProduct_id());
        size.setText(productModel.getSize());
        color.setText(productModel.getColor());
        price.setText(productModel.getPrice());
        backBtn.setOnClickListener(this);
        addToCart.setOnClickListener(this);
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
      /*  if (context instanceof OnFragmentInteractionListener) {
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
            case R.id.add_to_cart:
                clickfor=addToCart.getText().toString();
                ListDialog(v);
                break;
            case R.id.spinner_ok:
                if(!spColor.getSelectedItem().equals("Select Color") && !spSize.getSelectedItem().equals("Select Size")) {
                    popupWindow.dismiss();
                    CartModel cartmodel=new CartModel(productModel.getProduct_id(),productModel.getName(), spSize.getSelectedItem().toString(),
                            spColor.getSelectedItem().toString(),productModel.getPrice(),"1",productModel.getUrl());
                    if(clickfor.equals(addToCart.getText().toString())){
                        System.out.println("CLICK "+clickfor);
                        int itm=sharedPref.putCartItem(cartmodel);
                        ((HomeActivity)getActivity()).ShowAndHide(itm);
                        //((HomeActivity)getActivity()).onBackPressed();
                    }else if(clickfor.equals(orderBtn.getText().toString())) {
                        System.out.println("CLICK2 "+clickfor);
                        Intent intent=getActivity().getIntent();
                        intent.putExtra(NAME,"ViewFragment");
                        intent.putExtra(SELECTED_PRODUCT,cartmodel);
                        OrderFragment orderFragment=new OrderFragment();
                        FragmentManager fragmentManager =getActivity().getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(Relative_layoutfor_fragments,
                                orderFragment, orderFragment.getTag()).commit();
                    }
                    clickfor="task";
                } else Toast.makeText(getActivity()," Please choose color and size",Toast.LENGTH_SHORT).show();
                break;
            case R.id.view_backBtn:
                ((HomeActivity)getActivity()).onBackPressed();
                break;
            case R.id.order:
                clickfor=orderBtn.getText().toString();
                ListDialog(v);
                break;
        }
/*        OrderFragment orderFragment=new OrderFragment();
        FragmentManager fragmentManager =getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(Relative_layoutfor_fragments,
                orderFragment, orderFragment.getTag()).addToBackStack(null).commit();*/
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
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if(popupWindow !=null && popupWindow.isShowing())
            popupWindow.dismiss();

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
        popupWindow.setAnchorView(viewvv.findViewById(R.id.tv_for_dialog));
        popupWindow.setWidth(270);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.color.lightgrey));
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
}
