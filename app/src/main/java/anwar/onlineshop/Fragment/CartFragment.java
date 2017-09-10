package anwar.onlineshop.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xgc1986.ripplebutton.widget.RippleButton;

import java.util.ArrayList;

import anwar.onlineshop.Adapter.cartRecyclerAdapter;
import anwar.onlineshop.HomeActivity;
import anwar.onlineshop.Model.CartModel;
import anwar.onlineshop.R;
import anwar.onlineshop.storage.SharedPref;
import anwar.onlineshop.Interface.OnItemClickListeners;
import static anwar.onlineshop.R.id.Relative_layoutfor_fragments;
import static anwar.onlineshop.consts.CART_LIST;
import static anwar.onlineshop.consts.NAME;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CartFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment implements View.OnClickListener ,OnItemClickListeners{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private LinearLayout empety_layout,checkout_layout;
    private SharedPref sharedPref;
    private ArrayList<CartModel> cartModels=new ArrayList<>();
    private String Totalcost;
    private TextView text_cost;
    private RippleButton check_out,start_shoping;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private OnItemClickListeners listeners;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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
        View view=inflater.inflate(R.layout.fragment_cart, container, false);
        ((HomeActivity)getActivity()).ShowAndHide(0);
        ((HomeActivity)getActivity()).settingUpActionBar(view,"My Cart");
        checkout_layout=(LinearLayout)view.findViewById(R.id.check_out_layout);
        empety_layout=(LinearLayout)view.findViewById(R.id.empety_cart_layout);
        recyclerView=(RecyclerView)view.findViewById(R.id.cart_recyclerview);
        text_cost=(TextView)view.findViewById(R.id.text_totalcost);
        check_out=(RippleButton)view.findViewById(R.id.btn_check_out);
        start_shoping=(RippleButton)view.findViewById(R.id.start_shoping);
        sharedPref=new SharedPref(getActivity());
        cartModels =sharedPref.getCartItem();
        if(cartModels.size() ==0){
            empety_layout.setVisibility(View.VISIBLE);
            checkout_layout.setVisibility(View.GONE);
        }
        listeners=(OnItemClickListeners)this;
        Totalcost=String.valueOf(sharedPref.getTotalcost());
        adapter=new cartRecyclerAdapter(cartModels,listeners);
        layoutManager= new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        text_cost.setText(Totalcost+" Tk");
        check_out.setOnClickListener(this);
        start_shoping.setOnClickListener(this);
/*        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener
                .OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
            }

            @Override
            public void onItemLongClick(View v, int position) {
                Toast.makeText(getActivity(),"OnLongClick",Toast.LENGTH_SHORT).show();
            }
        }));*/
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

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
        switch (v.getId()){
            case  R.id.btn_check_out:
                ArrayList<CartModel>CartList=new ArrayList<>();
                CartList.addAll(cartModels);
                Intent intent=getActivity().getIntent();
                intent.putExtra(NAME,"CartFragment");
                intent.putExtra(CART_LIST,CartList);
                OrderFragment orderFragment=new OrderFragment();
                fragmentManager.beginTransaction().replace(Relative_layoutfor_fragments,
                        orderFragment, orderFragment.getTag()).commit();
                sharedPref.clearSharedPreference();
                cartModels.clear();
                break;
            case R.id.start_shoping:
                for (int i = 0; i <fragmentManager.getBackStackEntryCount() ; i++) {
                    fragmentManager.popBackStack();
                }
                HomeFragment homeFragment=new HomeFragment();
                fragmentManager.beginTransaction().replace(Relative_layoutfor_fragments,
                        homeFragment, homeFragment.getTag()).commit();
                break;
        }
    }

    @Override
    public void onClick(View v, int position) {
        switch (v.getId()){
            case R.id.cart_recyclerview:
                Toast.makeText(getActivity(),position +"single Click",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onLongClick(View v, int position) {
        switch (v.getId()){
            case R.id.cart_recyclerview:
                Toast.makeText(getActivity(),position +"Long Click",Toast.LENGTH_SHORT).show();
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

    @Override
    public void onResume() {
        super.onResume();
    }
    public void onPause() {
        super.onPause();
        ((HomeActivity)getActivity()).ShowAndHide(cartModels.size());
    }
}
