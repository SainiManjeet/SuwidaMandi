package com.galacticglobal.groclxcinc.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.galacticglobal.groclxcinc.R;
import com.galacticglobal.groclxcinc.activity.EditProfileActivity;
import com.galacticglobal.groclxcinc.activity.HomeScreenActivity;
import com.galacticglobal.groclxcinc.activity.LoginActivity;
import com.galacticglobal.groclxcinc.activity.ProductsDeatils;
import com.galacticglobal.groclxcinc.adapter.Buyproducts;
import com.galacticglobal.groclxcinc.adapter.CustomAdapter;
import com.galacticglobal.groclxcinc.adapter.DailyEssentialsAdapter;
import com.galacticglobal.groclxcinc.adapter.KitchenAdapter;
import com.galacticglobal.groclxcinc.adapter.StaplesCornerAdap;
import com.galacticglobal.groclxcinc.adapter.UperRecycler;
import com.galacticglobal.groclxcinc.classes.ApiServices;
import com.galacticglobal.groclxcinc.classes.BaseFragment;
import com.galacticglobal.groclxcinc.classes.Constants;
import com.galacticglobal.groclxcinc.classes.HelperClass;
import com.galacticglobal.groclxcinc.interfaces.ApiInterfaces;
import com.galacticglobal.groclxcinc.interfaces.BannerClickListner;
import com.galacticglobal.groclxcinc.interfaces.DailyItemClickListner;
import com.galacticglobal.groclxcinc.interfaces.ItemClick;
import com.galacticglobal.groclxcinc.interfaces.KitchenItemClickListner;
import com.galacticglobal.groclxcinc.interfaces.ListItemClickCallback;
import com.galacticglobal.groclxcinc.interfaces.PersonalItemClick;
import com.galacticglobal.groclxcinc.model_classes.add_to_cart.AddToCartModel;
import com.galacticglobal.groclxcinc.model_classes.home_products_model.BeautyStore;
import com.galacticglobal.groclxcinc.model_classes.home_products_model.DailyEssentialStore;
import com.galacticglobal.groclxcinc.model_classes.home_products_model.HomeBanner;
import com.galacticglobal.groclxcinc.model_classes.home_products_model.HomeBanner2;
import com.galacticglobal.groclxcinc.model_classes.home_products_model.HomeBanner3;
import com.galacticglobal.groclxcinc.model_classes.home_products_model.HomeKitchenStore;
import com.galacticglobal.groclxcinc.model_classes.home_products_model.HomeProductsModel;
import com.galacticglobal.groclxcinc.model_classes.home_products_model.HomeScreenProduct;
import com.galacticglobal.groclxcinc.model_classes.home_products_model.PersonalCare;
import com.galacticglobal.groclxcinc.util.UIUtility;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends BaseFragment implements ListItemClickCallback, ItemClick, BannerClickListner, KitchenItemClickListner,
        DailyItemClickListner, PersonalItemClick {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    final long DELAY_MS = 2000;
    final long PERIOD_MS = 3500;
    List<HomeScreenProduct> products;
    List<HomeBanner> banner_data_1;
    List<HomeBanner2> banner_data_2;
    List<HomeBanner3> banner_data_3;
    List<BeautyStore> beauty_store;
    List<PersonalCare> personalCares;

    PagerAdapter adapter;
    List<HomeKitchenStore> homeKitchenStores;
    List<DailyEssentialStore> dailyEssentialStores;
    RecyclerView product_buy;
    int currentPage = 0;
    Timer timer;
    RecyclerView recycler_home;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView, upper_recycle,recyclerStaples;
    private ViewGroup menu, edit_profile;
    private ImageView edit, banner_1, banner_2, banner_3;
    private ViewPager view_pager;
    private TextView address;
    //Stapal Corner
    private Integer[] drawableArrayStaple = {R.drawable.namkeen, R.drawable.atta_flour, R.drawable.dals,
            R.drawable.rice, R.drawable.cooking_oil, R.drawable.atta_flour};
    private String[] nameArrayStaple = {"Namkeens", "Atta & Flour", "Dals & Pulses",
            "Rice & Rice Products", "Cooking Oils", "Atta & Flour"};
    private Integer[] uperInt = {R.drawable.namkeen, R.drawable.atta_flour};
    private String[] uperString = {"Namkeens", "Atta & Flour"};

    public HomeFragment() {
        // Required empty public constructor
    }

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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        address = view.findViewById(R.id.address);
        edit = view.findViewById(R.id.edit);
        menu = view.findViewById(R.id.menu);
        recycler_home = view.findViewById(R.id.recycler_home);
        view_pager = view.findViewById(R.id.view_pager);
        edit_profile = view.findViewById(R.id.edit_profile);
        upper_recycle = view.findViewById(R.id.upper_recycle);
        banner_1 = view.findViewById(R.id.banner_1);
        banner_2 = view.findViewById(R.id.banner_2);
        banner_3 = view.findViewById(R.id.banner_3);


        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == 4 - 1) {
                    currentPage = 0;
                }
                view_pager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

         recyclerStaples = view.findViewById(R.id.recycler_staples);

        if (HelperClass.getAddress(getActivity()).isEmpty()) {
            address.setText("No Data Found");

        } else if (!HelperClass.getAddress(getActivity()).isEmpty()) {
            address.setText(HelperClass.getAddress(getActivity()));

        }

        edit_profile.setOnClickListener(v -> {
            if (HelperClass.getAddress(getActivity()).isEmpty()) {
                Intent in = new Intent(getActivity(), LoginActivity.class);
                address.setText("No Data Found");
                startActivity(in);
            } else if (!HelperClass.getAddress(getActivity()).isEmpty()) {
                Intent in = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(in);

            }
        });


        edit.setOnClickListener(v -> {
            if (HelperClass.getAddress(getActivity()).isEmpty()) {
                Intent in = new Intent(getActivity(), LoginActivity.class);
                address.setText("No Data Found");
                startActivity(in);
            } else if (!HelperClass.getAddress(getActivity()).isEmpty()) {
                Intent in = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(in);

            }
        });

        menu.setOnClickListener(v -> HomeScreenActivity.openCloseDrawer());

        banner_1.setOnClickListener(v -> {
            HelperClass.setSubClick(banner_data_2.get(0).getCid(), getActivity());
            HelperClass.setSubCatName(banner_data_2.get(0).getName(), getActivity());
            Intent in = new Intent(getActivity(), ProductsDeatils.class);
            startActivity(in);
        });

        banner_3.setOnClickListener(v -> {
            HelperClass.setSubClick(banner_data_3.get(0).getCid(), getActivity());
            HelperClass.setSubCatName(banner_data_3.get(0).getName(), getActivity());
            Intent in = new Intent(getActivity(), ProductsDeatils.class);
            startActivity(in);
        });
        // Get the products of home screen
        products();
        // productHome();

        recyclerView = view.findViewById(R.id.recycler_view);
        product_buy = view.findViewById(R.id.product_buy);
        // set a GridLayoutManager with 3 number of columns
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView


        GridLayoutManager gridLayoutManager3 = new GridLayoutManager(getActivity(), 2);
        gridLayoutManager3.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
        upper_recycle.setLayoutManager(gridLayoutManager3); // set LayoutManager to RecyclerView
        upper_recycle.setAdapter(new UperRecycler(getActivity(), uperInt, uperString, this::onListItemClick));

        /*Staples corner adapter*/
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getActivity(), 3);
        gridLayoutManager2.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
        recyclerStaples.setLayoutManager(gridLayoutManager2); // set LayoutManager to RecyclerView


        GridLayoutManager gridLayoutManager_kitchen = new GridLayoutManager(getActivity(), 3);
        gridLayoutManager_kitchen.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
        recycler_home.setLayoutManager(gridLayoutManager_kitchen); // set LayoutManager to RecyclerView

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        product_buy.setLayoutManager(layoutManager);
    }


    @Override
    public void onListItemClick(int position, String name) {
        Log.d("Position is: ", "" + position);

        // Intent in = new Intent(getActivity(), CategoryWiseBuyProduct.class);
        getData(name);
        Intent in = new Intent(getActivity(), ProductsDeatils.class);
        in.putExtra("name", name);
        startActivity(in);
    }

    @Override
    public void onBannerListItemClick(int position, String name) {
        HelperClass.setSubClick(banner_data_1.get(position).getCid(), getActivity());
        HelperClass.setSubCatName(banner_data_1.get(position).getName(), getActivity());
        Intent in = new Intent(getActivity(), ProductsDeatils.class);
        startActivity(in);
    }

    @Override
    public void onKitchenItemClickListner(int position, String name) {
        HelperClass.setSubClick(homeKitchenStores.get(position).getCid(), getActivity());
        HelperClass.setSubCatName(homeKitchenStores.get(position).getName(), getActivity());
        Intent in = new Intent(getActivity(), ProductsDeatils.class);
        startActivity(in);
    }

    @Override
    public void onPersonalItemClick(int position, String name) {
        HelperClass.setSubClick(personalCares.get(position).getCid(), getActivity());
        Log.d("sdcsadfd", String.valueOf(personalCares.get(position).getCid()));
        HelperClass.setSubCatName(personalCares.get(position).getName(), getActivity());
        Log.d("sdcsadfd",personalCares.get(position).getName());

        Intent in = new Intent(getActivity(), ProductsDeatils.class);
        startActivity(in);
    }

    @Override
    public void onDailyItemClickListner(int position, String name) {
        HelperClass.setSubClick(dailyEssentialStores.get(position).getCid(), getActivity());
        HelperClass.setSubCatName(dailyEssentialStores.get(position).getName(), getActivity());
        Intent in = new Intent(getActivity(), ProductsDeatils.class);
        startActivity(in);
    }

    private void getData(String name) {
        if (name.equalsIgnoreCase("Fruits & Vegetables")) {
            HelperClass.setSubClick(6, getActivity());
            HelperClass.setSubCatName("Fruits & Vegetables", getActivity());

        } else if (name.equalsIgnoreCase("Foodgrains,Oils & Masalas")) {
            HelperClass.setSubClick(8, getActivity());
            HelperClass.setSubCatName("Foodgrains,Oils & Masalas", getActivity());
        } else if (name.equalsIgnoreCase("Cleaning & Household")) {
            HelperClass.setSubClick(20, getActivity());
            HelperClass.setSubCatName("Cleaning & Household", getActivity());
        } else if (name.equalsIgnoreCase("Personal Care")) {
            //   getProduct("21");
            HelperClass.setSubClick(21, getActivity());
            HelperClass.setSubCatName("Personal Care", getActivity());
        } else if (name.equalsIgnoreCase("Snacks & Beverages")) {
            HelperClass.setSubClick(1, getActivity());
            HelperClass.setSubCatName("Snacks & Beverages", getActivity());
        } else if (name.equalsIgnoreCase("Bread,Dairy & Eggs")) {
            HelperClass.setSubClick(5, getActivity());
            HelperClass.setSubCatName("Bread,Dairy & Eggs", getActivity());
        }
        // Staples corner
        else if (name.equalsIgnoreCase("Namkeens")) {
            HelperClass.setSubClick(13, getActivity());
            HelperClass.setSubCatName("Namkeens", getActivity());
        } else if (name.equalsIgnoreCase("Atta & Flour")) {
            HelperClass.setSubClick(8, getActivity());
            HelperClass.setSubCatName("Atta & Flour", getActivity());
        } else if (name.equalsIgnoreCase("Dals & Pulses")) {
            HelperClass.setSubClick(24, getActivity());
            HelperClass.setSubCatName("Dals & Pulses", getActivity());
        } else if (name.equalsIgnoreCase("Rice & Rice Products")) {
            HelperClass.setSubClick(22, getActivity());
            HelperClass.setSubCatName("Rice & Rice Products", getActivity());
        } else if (name.equalsIgnoreCase("Atta & Flour")) {
            HelperClass.setSubClick(8, getActivity());
            HelperClass.setSubCatName("Atta & Flour", getActivity());
        } else {
            // Cooking
            HelperClass.setSubCatName("Cooking Oil", getActivity());
            HelperClass.setSubClick(10, getActivity());
        }
    }

    private void products() {

        if (HelperClass.getNetworkInfo(getActivity())) {
            try {
                ApiInterfaces apiInterfaces = ApiServices.apiService().create(ApiInterfaces.class);
                Call<HomeProductsModel> call = apiInterfaces.homeProduct();
                call.enqueue(new Callback<HomeProductsModel>() {
                    @Override
                    public void onResponse(@NonNull Call<HomeProductsModel> call, @NonNull Response<HomeProductsModel> response) {
                        try {
                            assert response.body() != null;
                            if (response.isSuccessful() && response.body().getIsSuccess().equalsIgnoreCase(Constants.TRUE)) {
                                hideProgressDialog();
                                assert response.body() != null;
                                products = response.body().getData().get(0).getHomeScreenProducts();

                                banner_data_1 = response.body().getData().get(0).getHomeBanner();
                                banner_data_3 = response.body().getData().get(0).getHomeBanner3();

                                personalCares = response.body().getData().get(0).getPersonalCare();
                                recyclerStaples.setAdapter(new StaplesCornerAdap(getActivity(),personalCares , (position, name) -> onPersonalItemClick(position, name)));
                                Picasso.get().load(banner_data_3.get(0).getImage()).into(banner_3);

                                banner_data_2 = response.body().getData().get(0).getHomeBanner2();
                                view_pager.setAdapter(new CustomAdapter(getActivity(), banner_data_1, (position, name) -> onBannerListItemClick(position, name)));

                                setBaneer_1();

                                beauty_store = response.body().getData().get(0).getBeautyStore();


                                // Daily Essential
                                dailyEssentialStores = response.body().getData().get(0).getDailyEssentialStore();
                                recyclerView.setAdapter(new DailyEssentialsAdapter(getActivity(), dailyEssentialStores, (position, name) -> onDailyItemClickListner(position, name)));
                                // TODO copy
                                homeKitchenStores = response.body().getData().get(0).getHomeKitchenStore();
                                recycler_home.setAdapter(new KitchenAdapter(getActivity(), homeKitchenStores, (position, name) -> onKitchenItemClickListner(position, name)));
                                product_buy.setAdapter(new Buyproducts(getActivity(), products, (position, qty, cost, attr) -> {
                                    onItemClick(position, qty, cost, attr);

                                }));

                                //setBuyProductAdapter();
                            } else {
                                hideProgressDialog();

                                Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                            }
                        } catch (Exception e) {
                            hideProgressDialog();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<HomeProductsModel> call, @NonNull Throwable t) {
                        hideProgressDialog();

                        Toast.makeText(getActivity(), R.string.try_again, Toast.LENGTH_SHORT).show();
                        Log.d("Home Error: ", "" + t.toString());
                        // Toast.makeText(getActivity(), ""+t.toString(), Toast.LENGTH_SHORT).show();

                    }
                });
            } catch (Exception e) {
                hideProgressDialog();
                e.printStackTrace();
            }
        } else {
            hideProgressDialog();
            Toast.makeText(getActivity(), R.string.no_internet, Toast.LENGTH_SHORT).show();


        }
    }

    private void setBaneer_1() {
        Picasso.get().load(banner_data_2.get(0).getImage()).into(banner_1);
    }

  private void addCart(int user_id, int product_id, int product_qty, float product_amt, float attr, String order_session) {

        if (HelperClass.getNetworkInfo(getActivity())) {
           // showProgressDialog();
            try {
                ApiInterfaces apiInterfaces = ApiServices.apiService().create(ApiInterfaces.class);
                Call<AddToCartModel> call = apiInterfaces.addCart(user_id, product_id, product_qty, product_amt, attr, order_session);
                call.enqueue(new Callback<AddToCartModel>() {
                    @Override
                    public void onResponse(@NonNull Call<AddToCartModel> call, @NonNull Response<AddToCartModel> response) {
                        try {
                            assert response.body() != null;
                            if (response.isSuccessful() && response.body().getIsSuccess().equalsIgnoreCase(Constants.TRUE)) {
                              //  hideProgressDialog();
                            } else {
                              //  hideProgressDialog();

                                //Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                            }
                        } catch (Exception e) {
                         //   hideProgressDialog();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<AddToCartModel> call, @NonNull Throwable t) {
                      //  hideProgressDialog();

                        Toast.makeText(getActivity(), R.string.try_again, Toast.LENGTH_SHORT).show();

                    }
                });
            } catch (Exception e) {
              //  hideProgressDialog();
                e.printStackTrace();
            }
        } else {
           // hideProgressDialog();
            Toast.makeText(getActivity(), R.string.no_internet, Toast.LENGTH_SHORT).show();


        }
    }

    @Override
    public void onItemClick(int position, int qty, float cost, float attr) {

        if (HelperClass.getUserId(getActivity()) != null && !HelperClass.getUserId(getActivity()).isEmpty()) {
            addCart(Integer.parseInt(HelperClass.getUserId(getActivity())), products.get(position).getPid(), 1, cost, attr, HelperClass.getOrderSession(getActivity()));
        } else {
            UIUtility.showDialog(getActivity(), "You need to Login first");
        }
    }



}
