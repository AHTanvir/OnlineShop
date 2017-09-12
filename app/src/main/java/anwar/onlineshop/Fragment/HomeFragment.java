package anwar.onlineshop.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import anwar.onlineshop.Adapter.HomeRecyclerAdapter;
import anwar.onlineshop.Interface.OnItemClickListeners;
import anwar.onlineshop.Model.CategoryModel;
import anwar.onlineshop.Model.ProductModel;
import anwar.onlineshop.Model.RowItem;
import anwar.onlineshop.Adapter.SellerRecyclerAdapter;
import anwar.onlineshop.HomeActivity;
import anwar.onlineshop.R;
import anwar.onlineshop.api.EndPoints;
import anwar.onlineshop.api.FakeProducts;
import anwar.onlineshop.api.JSONParser;
import anwar.onlineshop.api.JsonRequest;

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
    private HomeRecyclerAdapter recycleradapter;
    private SellerRecyclerAdapter sellerAdapter;
    private GridLayoutManager layoutManager;
    private LinearLayoutManager layoutManager1;
    private List<CategoryModel> categoryModel = new ArrayList<>();
    private List<ProductModel> productModels = new ArrayList<>();
    private OnFragmentInteractionListener mListener;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView collapsing_image;
    private FakeProducts fakeProducts=new FakeProducts();
    private int itemCount=0;
    private RequestQueue queue;
    private JSONParser parser=new JSONParser();
    Handler h=new Handler();
    private Thread thread;
    private LinearLayout progressBar1,progressBar2;

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
       View view= inflater.inflate(R.layout.fragment_home, container, false);
        ((HomeActivity)getActivity()).imageTransition(view,getActivity(),"Online Shop");
        recyclerView=(RecyclerView)view.findViewById(R.id.home_recyclerView);
        progressBar1=(LinearLayout)view.findViewById(R.id.dot_progrees1);
        progressBar2=(LinearLayout)view.findViewById(R.id.dot_progrees2);
        bestSellerRecyclerView=(RecyclerView)view.findViewById(R.id.home_bestseller_list);
        progressBar1.setVisibility(View.VISIBLE);
        progressBar2.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                categoryModel=fakeProducts.getHomeCategories();
                recycleradapter = new HomeRecyclerAdapter(categoryModel,HomeFragment.this);
                layoutManager = new GridLayoutManager(getActivity(),2);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(recycleradapter);
                progressBar1.setVisibility(View.GONE);
            }
        },2000);
        //////////////////////////////
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                productModels=fakeProducts.getBestSellerProducts();
                sellerAdapter = new SellerRecyclerAdapter(HomeFragment.this);
                layoutManager1 = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
                bestSellerRecyclerView.setLayoutManager(layoutManager1);
                bestSellerRecyclerView.setAdapter(sellerAdapter);
                addProduct();
                progressBar2.setVisibility(View.GONE);
            }
        },4000);
        bestSellerRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
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
    //MenuItem item = menu.findItem(R.id.addAction);
    @Override
    public void onClick(View v, int position) {
        CategoryFragment categoryFragment=new CategoryFragment().newInstance(categoryModel.get(position).getName()," ");
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.home, menu);
        MenuItem searchView = menu.findItem(R.id.action_search);
        searchView.setVisible(false);
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
        sellerAdapter.addProducts(temp);
    }
    public void init(final int id,String url){
        queue = Volley.newRequestQueue(getActivity());
        // String u=" https://api.androidhive.info/contacts/";
        JsonRequest req = new JsonRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if(id==R.id.home_bestseller_list){
                    productModels=parser.parseProductList(response);
                    addProduct();
                    progressBar2.setVisibility(View.GONE);
                }
                else if(id==R.id.home_recyclerView){
                    categoryModel=parser.parseCategory(response);
                    recycleradapter.addProducts(categoryModel);
                    progressBar1.setVisibility(View.GONE);
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
