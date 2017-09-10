package anwar.onlineshop.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import anwar.onlineshop.HomeActivity;
import anwar.onlineshop.Interface.OnItemClickListeners;
import anwar.onlineshop.Model.ProductModel;
import anwar.onlineshop.Adapter.productRecyclerAdapter;
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
 * {@link ProductFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductFragment extends Fragment implements OnItemClickListeners {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView productRecyclerView;
    private productRecyclerAdapter listadapter;
    private GridLayoutManager layoutManager;
    private int itemCount=0;
    private List<ProductModel> productList=new ArrayList<>();
    private List<ProductModel> rcList=new ArrayList<>();
    private FakeProducts fakeProducts=new FakeProducts();
    private Toolbar toolbar;
    private RequestQueue queue;
    private JSONParser parser=new JSONParser();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductFragment newInstance(String param1, String param2) {
        ProductFragment fragment = new ProductFragment();
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
        ((HomeActivity)getActivity()).settingUpActionBar(view,mParam2);
        productRecyclerView=(RecyclerView)view.findViewById(R.id.product_recycler_view);
        layoutManager=new GridLayoutManager(getActivity(),2);
        productList=fakeProducts.getProductList(mParam1,mParam2);
        listadapter=new productRecyclerAdapter(this);
        productRecyclerView.setLayoutManager(layoutManager);
        productRecyclerView.setAdapter(listadapter);
        addProduct();
        productRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(layoutManager.findLastVisibleItemPosition()==itemCount){
                    if (itemCount<productList.size())
                        addProduct();
                }

            }
        });
        return view;
    }

    private void addProduct() {
        List<ProductModel> temp=new ArrayList<>();
        for (int i = itemCount; i <productList.size() ; i++) {
            temp.add(productList.get(i));
            if(temp.size()==6){
                itemCount=i;
                break;
            }
        }
        listadapter.addProduct(temp);
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
    public void onClick(View v, int position) {
        switch (v.getId()){
            case R.id.product_recycler_view:
                ProductModel productmodel=productList.get(position);
                getActivity().getIntent().putExtra(SELECTED_PRODUCT,productmodel);
                ViewFragment viewFragment=new ViewFragment();
                FragmentManager fragmentManager =getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(Relative_layoutfor_fragments,
                        viewFragment, viewFragment.getTag()).addToBackStack(null).commit();
                break;
        }
    }

    @Override
    public void onLongClick(View v, int position) {

    }

    @Override
    public void onResume() {
        super.onResume();
        itemCount=0;
    }
    public void init(){
        queue = Volley.newRequestQueue(getActivity());
        String url = EndPoints.PRODUCTS_LIST+mParam1+"/"+mParam2;
       // String u=" https://api.androidhive.info/contacts/";
        JsonRequest req = new JsonRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                productList=parser.parseProductList(response);
                addProduct();
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
}
