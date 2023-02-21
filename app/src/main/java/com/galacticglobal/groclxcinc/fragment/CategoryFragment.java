package com.galacticglobal.groclxcinc.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.galacticglobal.groclxcinc.R;
import com.galacticglobal.groclxcinc.activity.HomeScreenActivity;
import com.galacticglobal.groclxcinc.activity.ProductsDeatils;
import com.galacticglobal.groclxcinc.adapter.AllCategoryHome;
import com.galacticglobal.groclxcinc.adapter.SubCatProductAdapter;
import com.galacticglobal.groclxcinc.classes.ApiServices;
import com.galacticglobal.groclxcinc.classes.BaseFragment;
import com.galacticglobal.groclxcinc.classes.Constants;
import com.galacticglobal.groclxcinc.classes.HelperClass;
import com.galacticglobal.groclxcinc.interfaces.ApiInterfaces;
import com.galacticglobal.groclxcinc.interfaces.SubCatCallback;
import com.galacticglobal.groclxcinc.interfaces.SubCatProductsClick;
import com.galacticglobal.groclxcinc.model_classes.AllCateModel;
import com.galacticglobal.groclxcinc.model_classes.Datum;
import com.galacticglobal.groclxcinc.model_classes.sub_cat_products.SubCatModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryFragment extends BaseFragment implements SubCatCallback, SubCatProductsClick {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView recyclerView;
    private List<Datum> categoryList;
    private List<com.galacticglobal.groclxcinc.model_classes.sub_cat_products.Datum> subCatProducts;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ViewGroup menu;

    public CategoryFragment() {
        // Required empty public constructor
    }

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
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        init(view);
        return view;
    }

    private void init(View view) {

        recyclerView = view.findViewById(R.id.recycler_view);
        menu = view.findViewById(R.id.menu);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


//        LinearLayoutManager layoutManager_inner = new LinearLayoutManager(getContext());
//        layoutManager_inner.setOrientation(LinearLayoutManager.VERTICAL);
//        recycler_view_inner.setLayoutManager(layoutManager_inner);
        fetchAllCategories();

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeScreenActivity.openCloseDrawer();
            }
        });
    }


    public void fetchAllCategories() {
        if (HelperClass.getNetworkInfo(getActivity())) {

            try {
                ApiInterfaces apiInterfaces = ApiServices.apiService().create(ApiInterfaces.class);
                Call<AllCateModel> call = apiInterfaces.allCategories();
                call.enqueue(new Callback<AllCateModel>() {
                    @Override
                    public void onResponse(Call<AllCateModel> call, Response<AllCateModel> response) {
                        try {
                            if (response.isSuccessful() && response.body().getIsSuccess().equalsIgnoreCase(Constants.TRUE)) {

                                assert response.body() != null;

                                categoryList = response.body().getData();
                                recyclerView.setAdapter(new AllCategoryHome(getActivity(), categoryList, (position, recyclerView1) -> {
                                    onSubCatItemClick(position, recyclerView); }));

                                setMainAdapter();

                            } else {
                                Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {

                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<AllCateModel> call, Throwable t) {
                        Toast.makeText(getActivity(), "Please try again!", Toast.LENGTH_SHORT).show();

                    }
                });
            } catch (Exception e) {

                e.printStackTrace();
            }
        } else {
            Toast.makeText(getActivity(), "No Internet", Toast.LENGTH_SHORT).show();


        }
    }
    private void setMainAdapter() {
        recyclerView.setAdapter(new  AllCategoryHome(getActivity(), categoryList,this ));
    }

    private void subCatList(int cid, RecyclerView rec) {
        if (HelperClass.getNetworkInfo(getContext())) {
            showProgressDialog();
            try {
                ApiInterfaces apiInterfaces = ApiServices.apiService().create(ApiInterfaces.class);
                Call<SubCatModel> call = apiInterfaces.subCateWiseProduct(cid);
                call.enqueue(new Callback<SubCatModel>() {
                    @Override
                    public void onResponse(@NonNull Call<SubCatModel> call, @NonNull Response<SubCatModel> response) {
                        try {
                            assert response.body() != null;
                            if (response.isSuccessful() && response.body().getIsSuccess().equalsIgnoreCase(Constants.TRUE)) {
                                hideProgressDialog();
                                assert response.body() != null;
                                //launchActivity(response.body().getData());

                                subCatProducts = response.body().getData();
                                rec.setAdapter(new SubCatProductAdapter(getActivity(), subCatProducts,position -> onSubCatProductsClick(position)));


                            } else {
                                hideProgressDialog();

                            }
                        } catch (Exception e) {
                            hideProgressDialog();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<SubCatModel> call, @NonNull Throwable t) {
                        hideProgressDialog();
                        Toast.makeText(getContext(), R.string.try_again, Toast.LENGTH_LONG).show();

                    }
                });
            } catch (Exception e) {
                hideProgressDialog();
                Toast.makeText(getContext(), R.string.try_again, Toast.LENGTH_LONG).show();

                e.printStackTrace();
            }
        } else {
            hideProgressDialog();
            Toast.makeText(getContext(), R.string.no_internet, Toast.LENGTH_LONG).show();

        }
    }


    @Override
    public void onSubCatItemClick(int position, RecyclerView rec) {
        subCatList(categoryList.get(position).getCid(),rec);

        Log.d("sfsdfsdf", String.valueOf(categoryList.get(position).getCid()));

    }


    @Override
    public void onSubCatProductsClick(int position) {
        HelperClass.setSubCatName(subCatProducts.get(position).getCategoryName(),getActivity());
        subCatProducts.get(position).getCid();
        HelperClass.setSubClick( subCatProducts.get(position).getCid(),getActivity());
        Intent intent = new Intent(getActivity(), ProductsDeatils.class);
        startActivity(intent);
    }
}