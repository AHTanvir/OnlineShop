package anwar.onlineshop.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.xgc1986.ripplebutton.widget.RippleButton;
import com.xgc1986.ripplebutton.widget.RippleImageButton;

import java.util.ArrayList;
import java.util.List;

import anwar.onlineshop.HomeActivity;
import anwar.onlineshop.R;

import static anwar.onlineshop.R.id.Relative_layoutfor_fragments;

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
    private PopupWindow popupWindow;
    private Spinner spColor,spSize;
    private SpinnerAdapter  spinneradapter;
    private LinearLayout menuLayout;
    private RippleButton addToCart,orderBtn;
    private TextView brand_name,product_id,size,color,price;
    private Button backBtn;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView image;
    private String colorList[]=new String[]{"RED","GREEN","BLUE","WHITE","BLACK"};
    private String sizeList[]=new String[]{"SMALL","MEDIUM","LARGE","EXTRA"};

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
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_view, container, false);
        collapsingToolbarLayout=(CollapsingToolbarLayout)view.findViewById(R.id.collapsingtool);
        collapsingToolbarLayout.setTitle("Fragment");
        image=(ImageView)view.findViewById(R.id.toolbart_product_img);
        image.setImageResource(R.drawable.women);
        backBtn=(Button) view.findViewById(R.id.view_backBtn);
        backBtn.setVisibility(View.VISIBLE);
        brand_name=(TextView)view.findViewById(R.id.brand_name);
        product_id=(TextView)view.findViewById(R.id.product_id);
        size=(TextView)view.findViewById(R.id.size);
        color=(TextView)view.findViewById(R.id.color);
        orderBtn=(RippleButton) view.findViewById(R.id.order);
        addToCart=(RippleButton)view.findViewById(R.id.add_to_cart);
        orderBtn.setOnClickListener(this);
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

    }

}
