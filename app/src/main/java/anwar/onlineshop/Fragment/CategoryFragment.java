package anwar.onlineshop.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import anwar.onlineshop.Interface.OnItemClickListeners;
import anwar.onlineshop.Model.ProductCategoryModel;
import anwar.onlineshop.Model.ProductModel;
import anwar.onlineshop.Model.RowItem;
import anwar.onlineshop.Adapter.ProductCategoryRecyclerAdapter;
import anwar.onlineshop.Adapter.RecomRecyclerAdapter;
import anwar.onlineshop.HomeActivity;
import anwar.onlineshop.R;
import anwar.onlineshop.api.FakeProducts;
import anwar.onlineshop.api.JSONParser;
import anwar.onlineshop.api.JsonRequest;

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
    private ProductCategoryRecyclerAdapter listadapter;
    private RecomRecyclerAdapter recomAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private LinearLayoutManager layoutManager1;
    private List<ProductCategoryModel> cgList=new ArrayList<>();
    private List<ProductModel> productModels=new ArrayList<>();
    private ProductFragment vf;
    private FakeProducts fakeProducts=new FakeProducts();
    private int itemCount=0;
    private RequestQueue queue;
    private JSONParser parser=new JSONParser();
    private OnFragmentInteractionListener mListener;
    private AppBarLayout appBar;

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
        setHasOptionsMenu(true);
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
        appBar=(AppBarLayout)view.findViewById(R.id.appbar_layout);
        categoryList=(RecyclerView) view.findViewById(R.id.category_list);
        categoryRecom=(RecyclerView) view.findViewById(R.id.category_recomList);
        cgList=fakeProducts.getProductType(mParam1);
       // listadapter=new ProductCategoryRecyclerAdapter(this);
        listadapter=new ProductCategoryRecyclerAdapter(cgList,this);
        layoutManager = new LinearLayoutManager(getActivity());
        categoryList.setLayoutManager(layoutManager);
        categoryList.setAdapter(listadapter);
        productModels=fakeProducts.getRecomProduct();
        recomAdapter=new RecomRecyclerAdapter(this);
        layoutManager1 = new LinearLayoutManager(getActivity());
        categoryRecom.setLayoutManager(layoutManager1);
        categoryRecom.setAdapter(recomAdapter);
        addProduct();
        categoryRecom.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(layoutManager1.findLastVisibleItemPosition()==itemCount){
                    if (itemCount<productModels.size())
                        addProduct();
                }

            }
        });
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
                    vf=new ProductFragment().newInstance(mParam1,cgList.get(position).getName());
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.home, menu);
        MenuItem searchVie = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        //searchView.setBackgroundColor(Color.parseColor("#80FFFFFF"));
        searchView.setQueryHint("Product Name");
        searchVie.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                appBar.setExpanded(false, true);
                return false;
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                listadapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                listadapter.getFilter().filter(s);
                // Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
    public void init(final int id,String url){
        queue = Volley.newRequestQueue(getActivity());
        // String u=" https://api.androidhive.info/contacts/";
        JsonRequest req = new JsonRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if(id==categoryRecom.getId()){
                    productModels=parser.parseProductList(response);
                    addProduct();
                }
                else if(id== categoryList.getId()){
                    cgList=parser.parseProductCategory(response);
                    listadapter.addProducts(cgList);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("volleyError = "+error);

            }
        });
        req.setShouldCache(false);
        queue.add(req);
    }
    private void addProduct() {
        List<ProductModel> temp=new ArrayList<>();
        for (int i = itemCount; i <productModels.size() ; i++) {
            temp.add(productModels.get(i));
            if(temp.size()==6){
                itemCount=i;
                break;
            }
        }
        recomAdapter.addProducts(temp);
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
        itemCount=0;
    }
}
