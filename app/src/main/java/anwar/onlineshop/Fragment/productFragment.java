package anwar.onlineshop.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import anwar.onlineshop.Adapter.RecyclerItemClickListener;
import anwar.onlineshop.Adapter.RowItem;
import anwar.onlineshop.Adapter.productRecyclerAdapter;
import anwar.onlineshop.Adapter.recomRecyclerAdapter;
import anwar.onlineshop.HomeActivity;
import anwar.onlineshop.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link productFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link productFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class productFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView productRecyclerView;
    private RecyclerView.Adapter listadapter;
    private GridLayoutManager layoutManager;
    private List<RowItem> productList=new ArrayList<>();
    private List<RowItem> rcList=new ArrayList<>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public productFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment productFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static productFragment newInstance(String param1, String param2) {
        productFragment fragment = new productFragment();
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
        View view=inflater.inflate(R.layout.fragment_product, container, false);
        ((HomeActivity)getActivity()).imageTransition(view);
        productRecyclerView=(RecyclerView)view.findViewById(R.id.product_recycler_view);
        layoutManager=new GridLayoutManager(getActivity(),2);
        RowItem ro0 = new RowItem(R.drawable.w6,"T-SHART","descriptio"," Tk 100");
        productList.add(ro0);
        RowItem ro11 = new RowItem(R.drawable.women4,"WOMEN"," descriptio"," Tk 100");
        productList.add(ro11);
        RowItem ro2 = new RowItem(R.drawable.watch,"MEN"," descriptio"," Tk 100");
        productList.add(ro2);
        RowItem ro00 = new RowItem(R.drawable.pant2,"T-SHART","descriptio"," Tk 100");
        productList.add(ro00);
        RowItem ro111 = new RowItem(R.drawable.women4,"WOMEN"," descriptio"," Tk 100");
        productList.add(ro111);
        RowItem ro22 = new RowItem(R.drawable.pant1,"MEN"," descriptio"," Tk 100");
        productList.add(ro22);
        listadapter=new productRecyclerAdapter(productList);
        productRecyclerView.setLayoutManager(layoutManager);
        productRecyclerView.setAdapter(listadapter);
        productRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), productRecyclerView, new RecyclerItemClickListener
                .OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

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
