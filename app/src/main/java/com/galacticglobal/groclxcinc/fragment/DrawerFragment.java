package com.galacticglobal.groclxcinc.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.galacticglobal.groclxcinc.R;
import com.galacticglobal.groclxcinc.activity.ChangePassword;
import com.galacticglobal.groclxcinc.activity.HomeScreenActivity;
import com.galacticglobal.groclxcinc.activity.LoginActivity;
import com.galacticglobal.groclxcinc.activity.MyOrderActivity;
import com.galacticglobal.groclxcinc.activity.ShareActivity;
import com.galacticglobal.groclxcinc.activity.SignupActivity;
import com.galacticglobal.groclxcinc.adapter.AllCategoryAdap;
import com.galacticglobal.groclxcinc.classes.ApiServices;
import com.galacticglobal.groclxcinc.classes.Constants;
import com.galacticglobal.groclxcinc.classes.HelperClass;
import com.galacticglobal.groclxcinc.interfaces.ApiInterfaces;
import com.galacticglobal.groclxcinc.model_classes.AllCateModel;
import com.galacticglobal.groclxcinc.model_classes.Datum;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.galacticglobal.groclxcinc.activity.HomeScreenActivity.drawer;
import static com.galacticglobal.groclxcinc.activity.HomeScreenActivity.openCloseDrawer;


public class DrawerFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ProgressDialog progressDialog;
    RecyclerView categoryListView;
    LinearLayout viewContainer;
    View.OnClickListener listener = new View.OnClickListener() {
        public void onClick(View view) {
            Fragment fragment = null;
            if (view == view.findViewById(R.id.home)) {
                fragment = new HomeFragment();
                HomeScreenActivity.navigation.setSelectedItemId(R.id.action_home);
            } else if (view == view.findViewById(R.id.category)) {
                fragment = new CategoryFragment();
                HomeScreenActivity.navigation.setSelectedItemId(R.id.action_category);
            } else if (view == view.findViewById(R.id.my_order)) {
                fragment = new BasketFragment();
                HomeScreenActivity.navigation.setSelectedItemId(R.id.action_basket);
            } else {
                fragment = new HomeFragment();
            }
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.frame_layout, fragment);
            transaction.commit();
            HomeScreenActivity.openCloseDrawer();

            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }

        }
    };
    private List<Datum> categoryList;
    private LinearLayout mainLayout;
    private ViewGroup sign_login;
    private TextView home, address, log_out, changePass;
    private ImageView jobs_down;
    private TextView myOrder, category, referFriend,orderHistory;
    private RecyclerView cateListView;
    private int mVal = 0;

    public DrawerFragment() {
        // Required empty public constructor
    }

    public static DrawerFragment newInstance(String param1, String param2) {
        DrawerFragment fragment = new DrawerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_drawer, container, false);

        log_out = view.findViewById(R.id.log_out);
        changePass = view.findViewById(R.id.change_pass);
        home = view.findViewById(R.id.home);
        sign_login = view.findViewById(R.id.sign_login);
        address = view.findViewById(R.id.address);
        home = view.findViewById(R.id.home);
        category = view.findViewById(R.id.category);
        category.setOnClickListener(listener);
        referFriend = view.findViewById(R.id.refer_frd);
        orderHistory = view.findViewById(R.id.order_history);

        if (HelperClass.getUserId(getActivity()) != null && !HelperClass.getUserId(getActivity()).isEmpty()) {
            log_out.setVisibility(View.VISIBLE);
            changePass.setVisibility(View.VISIBLE);
            orderHistory.setVisibility(View.VISIBLE);
        } else {
            log_out.setVisibility(View.GONE);
            changePass.setVisibility(View.GONE);
            orderHistory.setVisibility(View.GONE);
        }

        orderHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCloseDrawer();
                Intent in = new Intent(getActivity(),MyOrderActivity.class);
                startActivity(in);
            }
        });

        referFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCloseDrawer();
                Intent in = new Intent(getActivity(), ShareActivity.class);
                startActivity(in);
            }
        });


        mainLayout = view.findViewById(R.id.main_layout);


        LinearLayout login = view.findViewById(R.id.login);
        LinearLayout signUp = view.findViewById(R.id.sign_up);


        myOrder = view.findViewById(R.id.my_order);
        myOrder.setOnClickListener(listener);


        if (HelperClass.getAddress(getActivity()).isEmpty()) {

            Log.d("asdcascdasc", HelperClass.getAddress(getActivity()));
            sign_login.setVisibility(View.VISIBLE);
            address.setText("No Data Found");

        } else if (!HelperClass.getAddress(getActivity()).isEmpty()) {
            sign_login.setVisibility(View.GONE);
            address.setText(HelperClass.getAddress(getActivity()));

        }

        home.setOnClickListener(listener);

        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCloseDrawer();
                Intent in = new Intent(getActivity(), ChangePassword.class);
                startActivity(in);
            }
        });

        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelperClass.LogOut(getActivity());
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCloseDrawer();
                Intent in = new Intent(getActivity(), LoginActivity.class);
                startActivity(in);
                // Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();

            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCloseDrawer();
                Intent in = new Intent(getActivity(), SignupActivity.class);
                startActivity(in);
            }
        });
        return view;
    }


    public void showProgressDialog() {
        progressDialog = ProgressDialog.show(getActivity(), null, "Loading...", true);
        progressDialog.setCancelable(false);
    }

    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
//        if (id == categoryContainer.getId()) {
//            if (mVal == 0) {
//                searchProduct();
//            }
//            if (viewContainer.getVisibility() == View.GONE) {
//                jobs_down.animate().rotation(180).start();
//                viewContainer.setVisibility(View.VISIBLE);
//            } else {
//                viewContainer.setVisibility(View.GONE);
//                jobs_down.animate().rotation(0).start();
//                viewContainer.setVisibility(View.GONE);
//            }
//
//        }
//        if (id==myOrder.getId()){
//            Intent in = new Intent(getActivity(), MyOrderActivity.class);
//            startActivity(in);
//        }
    }
}