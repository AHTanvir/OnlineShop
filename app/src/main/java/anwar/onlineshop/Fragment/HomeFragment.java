package anwar.onlineshop.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import anwar.onlineshop.Adapter.homeRecyclerAdapter;
import anwar.onlineshop.Interface.OnItemClickListeners;
import anwar.onlineshop.Model.ProductModel;
import anwar.onlineshop.Model.RowItem;
import anwar.onlineshop.Adapter.sellerRecyclerAdapter;
import anwar.onlineshop.HomeActivity;
import anwar.onlineshop.R;
import anwar.onlineshop.api.FakeProducts;

import static anwar.onlineshop.R.id.Relative_layoutfor_fragments;
import static anwar.onlineshop.consts.SELECTED_PRODUCT;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements OnItemClickListeners {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private RecyclerView bestSellerRecyclerView;
    private RecyclerView.Adapter recycleradapter;
    private RecyclerView.Adapter sellerAdapter;
    private GridLayoutManager layoutManager;
    private RecyclerView.LayoutManager layoutManager1;
    private List<RowItem> rowitem = new ArrayList<>();
    private List<ProductModel> productModels = new ArrayList<>();
    private OnFragmentInteractionListener mListener;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private OnItemClickListeners listeners=(OnItemClickListeners)this;
    private ImageView collapsing_image;
    private FakeProducts fakeProducts=new FakeProducts();
    private Thread thread;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
       View view= inflater.inflate(R.layout.fragment_home, container, false);
        ((HomeActivity)getActivity()).imageTransition(view,getActivity(),"Online Shop");
        recyclerView=(RecyclerView)view.findViewById(R.id.home_recyclerView);
        bestSellerRecyclerView=(RecyclerView)view.findViewById(R.id.home_bestseller_list);
        rowitem=fakeProducts.getHomeCategories();
        recycleradapter = new homeRecyclerAdapter(rowitem,this);
        layoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recycleradapter);
        //////////////////////////////
        productModels=fakeProducts.getBestSellerProducts();
        sellerAdapter = new sellerRecyclerAdapter(productModels,this);
        layoutManager1 = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        bestSellerRecyclerView.setLayoutManager(layoutManager1);
        bestSellerRecyclerView.setAdapter(sellerAdapter);
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
   /*     if (context instanceof OnFragmentInteractionListener) {
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
    public void onClick(View v, int position) {
        CategoryFragment categoryFragment=new CategoryFragment().newInstance(rowitem.get(position).getCatagory()," ");
        FragmentManager fragmentManager =getActivity().getSupportFragmentManager();
        switch (v.getId()){
            case R.id.home_recyclerView:
                fragmentManager.beginTransaction().replace(Relative_layoutfor_fragments,
                        categoryFragment, categoryFragment.getTag()).addToBackStack(null).commit();
                break;
            case R.id.home_bestseller_list:
                ProductModel productmodel=productModels.get(position);
                getActivity().getIntent().putExtra(SELECTED_PRODUCT,productmodel);
                ViewFragment viewFragment=new ViewFragment();
                fragmentManager =getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(Relative_layoutfor_fragments,
                        viewFragment, viewFragment.getTag()).addToBackStack(null).commit();
                break;
        }
    }

    @Override
    public void onLongClick(View v, int position) {

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
