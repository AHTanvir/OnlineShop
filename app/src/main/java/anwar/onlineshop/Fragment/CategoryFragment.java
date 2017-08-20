package anwar.onlineshop.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import anwar.onlineshop.Adapter.RecyclerItemClickListener;
import anwar.onlineshop.Adapter.RowItem;
import anwar.onlineshop.Adapter.categoryRecyclerAdapter;
import anwar.onlineshop.Adapter.recomListAdapter;
import anwar.onlineshop.Adapter.recomRecyclerAdapter;
import anwar.onlineshop.HomeActivity;
import anwar.onlineshop.R;

import static anwar.onlineshop.R.id.Relative_layoutfor_fragments;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CategoryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragment extends Fragment{
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
    private List<RowItem> rcList=new ArrayList<>();
    private productFragment productfragment;

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
        ((HomeActivity)getActivity()).imageTransition(view);
        categoryList=(RecyclerView) view.findViewById(R.id.category_list);
        categoryRecom=(RecyclerView) view.findViewById(R.id.category_recomList);
        for (int i = 0; i <10 ; i++) {
            RowItem r=new RowItem("Category","100");
            cgList.add(r);
        }
        listadapter=new categoryRecyclerAdapter(cgList);
        layoutManager = new LinearLayoutManager(getActivity());
        categoryList.setLayoutManager(layoutManager);
        categoryList.setAdapter(listadapter);
        RowItem ro0 = new RowItem(R.drawable.w6,"T-SHART","descriptio"," Tk 100");
        rcList.add(ro0);
        RowItem ro11 = new RowItem(R.drawable.women4,"WOMEN"," descriptio"," Tk 100");
        rcList.add(ro11);
        RowItem ro2 = new RowItem(R.drawable.watch,"MEN"," descriptio"," Tk 100");
        rcList.add(ro2);
        recomAdapter=new recomRecyclerAdapter(rcList);
        layoutManager1 = new LinearLayoutManager(getActivity());
        categoryRecom.setLayoutManager(layoutManager1);
        categoryRecom.setAdapter(recomAdapter);
        categoryList.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(),categoryList, new RecyclerItemClickListener
                .OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                productfragment=new productFragment();
                FragmentManager fragmentManager =getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(Relative_layoutfor_fragments,
                        productfragment, productfragment.getTag()).addToBackStack(null).commit();
            }
            @Override
            public void onItemLongClick(View v, int position) {
            }
        }));
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
