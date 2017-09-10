package anwar.onlineshop.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import anwar.onlineshop.Interface.OnItemClickListeners;
import anwar.onlineshop.Model.ProductModel;
import anwar.onlineshop.Model.RowItem;
import anwar.onlineshop.Adapter.categoryRecyclerAdapter;
import anwar.onlineshop.Adapter.recomRecyclerAdapter;
import anwar.onlineshop.HomeActivity;
import anwar.onlineshop.R;
import anwar.onlineshop.api.FakeProducts;

import static anwar.onlineshop.R.id.Relative_layoutfor_fragments;
import static anwar.onlineshop.consts.SELECTED_PRODUCT;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CategoryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragment extends Fragment implements OnItemClickListeners {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView categoryList;
    private RecyclerView categoryRecom;
    private RecyclerView.Adapter listadapter;
    private RecyclerView.Adapter recomAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.LayoutManager layoutManager1;
    private List<RowItem> cgList=new ArrayList<>();
    private List<ProductModel> productModels=new ArrayList<>();
    private ProductFragment vf;
    private OnItemClickListeners listeners=(OnItemClickListeners)this;
    private FakeProducts fakeProducts=new FakeProducts();

    private OnFragmentInteractionListener mListener;

    public CategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();
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
       View view= inflater.inflate(R.layout.fragment_category, container, false);
        ((HomeActivity)getActivity()).imageTransition(view,getActivity(),mParam1);
        categoryList=(RecyclerView) view.findViewById(R.id.category_list);
        categoryRecom=(RecyclerView) view.findViewById(R.id.category_recomList);
        cgList=fakeProducts.getProductType(mParam1);
        listadapter=new categoryRecyclerAdapter(cgList,listeners);
        layoutManager = new LinearLayoutManager(getActivity());
        categoryList.setLayoutManager(layoutManager);
        categoryList.setAdapter(listadapter);
        productModels=fakeProducts.getRecomProduct();
        recomAdapter=new recomRecyclerAdapter(productModels,listeners);
        layoutManager1 = new LinearLayoutManager(getActivity());
        categoryRecom.setLayoutManager(layoutManager1);
        categoryRecom.setAdapter(recomAdapter);
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
        switch (v.getId()){
            case R.id.category_list:
                if(cgList.get(position).getTotal() !="0"){
                    vf=new ProductFragment().newInstance(mParam1,cgList.get(position).getCatagory());
                    FragmentManager fragmentManager =getActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(Relative_layoutfor_fragments,
                            vf, vf.getTag()).addToBackStack(null).commit();
                }
                break;
            case R.id.category_recomList:
                getActivity().getIntent().putExtra(SELECTED_PRODUCT,productModels.get(position));
                ViewFragment vf=new ViewFragment();
                FragmentManager fragmentManager =getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(Relative_layoutfor_fragments,
                        vf, vf.getTag()).addToBackStack(null).commit();
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
