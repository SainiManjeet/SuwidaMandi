package com.galacticglobal.groclxcinc.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.galacticglobal.groclxcinc.R;
import com.galacticglobal.groclxcinc.activity.HomeScreenActivity;
import com.galacticglobal.groclxcinc.activity.ProductsDeatils;
import com.galacticglobal.groclxcinc.adapter.AllCategoryHome;
import com.galacticglobal.groclxcinc.adapter.ProductBuyAdapter;
import com.galacticglobal.groclxcinc.adapter.SearchProductAdap;
import com.galacticglobal.groclxcinc.classes.ApiServices;
import com.galacticglobal.groclxcinc.classes.Constants;
import com.galacticglobal.groclxcinc.classes.HelperClass;
import com.galacticglobal.groclxcinc.interfaces.AddMainProductClick;
import com.galacticglobal.groclxcinc.interfaces.ApiInterfaces;
import com.galacticglobal.groclxcinc.interfaces.QtyClickCallback;
import com.galacticglobal.groclxcinc.model_classes.add_to_cart.AddToCartModel;
import com.galacticglobal.groclxcinc.model_classes.search.Datum;
import com.galacticglobal.groclxcinc.model_classes.search.SearchModel;
import com.galacticglobal.groclxcinc.model_classes.updateProduct.UpdateProductModel;
import com.galacticglobal.groclxcinc.model_classes.update_cart.UpdateCart;
import com.galacticglobal.groclxcinc.util.UIUtility;

import java.security.SecureRandom;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class SearchFragment extends Fragment implements AddMainProductClick, QtyClickCallback {
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static final String NoString = "0123456789";
    static SecureRandom rnd = new SecureRandom();
    RecyclerView recyclerView;
    ViewGroup menu;
    List<Datum> productsModels;
    private EditText search;

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
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
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        init(view);
        return view;
    }

    private void init(View view) {

        search = view.findViewById(R.id.edt_search);
        recyclerView = view.findViewById(R.id.recycler_view);
        menu = view.findViewById(R.id.menu);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeScreenActivity.openCloseDrawer();
            }
        });
        search.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                Log.d("hfgdihgu", " " + v.getText().toString());
                hideKeybaord(v);
                searchProduct(v.getText().toString());
                return true;
            }
            return false;
        });
    }
    private void hideKeybaord(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
    }

    public void searchProduct(String keyword) {
        if (HelperClass.getNetworkInfo(getActivity())) {

            try {
                ApiInterfaces apiInterfaces = ApiServices.apiService().create(ApiInterfaces.class);
                Call<SearchModel> call = apiInterfaces.searchProduct(keyword);
                call.enqueue(new Callback<SearchModel>() {
                    @Override
                    public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                        try {
                            if (response.isSuccessful() && response.body().getIsSuccess().equalsIgnoreCase(Constants.TRUE)) {

                                assert response.body() != null;


                                productsModels = response.body().getData();
                                recyclerView.setAdapter(new SearchProductAdap(getActivity(), productsModels, (position, value, cost, attr, progressBar) -> {
                                    onAddMainProductClick(position, value, cost, attr, progressBar);
                                },

                                        (order, qty, progress, txt, attr) -> {
                                            onUpdateListItemClick(order, qty, progress, txt, attr);
                                        }));


                            } else {
                                // No record Found
                               // Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {

                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<SearchModel> call, Throwable t) {
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

    @Override
    public void onUpdateListItemClick(int orderId, String qty, ProgressBar progressBar, TextView txt, float attr) {
        updateCartItem(String.valueOf(orderId), String.valueOf(qty), progressBar, txt, attr);

    }

    @Override
    public void onAddMainProductClick(int position, int value, float cost, float attr, ProgressBar progressBar) {




        if (HelperClass.getUserId(getActivity()) != null && !HelperClass.getUserId(getActivity()).isEmpty()) {

            String orderSession = HelperClass.getOrderSession(getActivity());
            if (orderSession != null && !orderSession.isEmpty()) {
                // Do Nothing
            } else {
                HelperClass.setOrderSession(randomNo(10) + "_" + randomString(32), getActivity());

            }
            addCart(Integer.valueOf(HelperClass.getUserId(getActivity())), productsModels.get(position).getPid(), value,  cost,  attr, HelperClass.getOrderSession(getActivity())
                    , progressBar);
        } else {
            UIUtility.showDialog(getActivity(), "You need to Login first");
        }
    }

    private void updateCartItem(String order_id, String product_qty, ProgressBar progressBar, TextView txt, float attr) {
        Log.d("updateCartItem  Id" + order_id + "", " product_qty :" + product_qty + " attr: " + attr+ " session: "+ HelperClass.getOrderSession(getActivity()));
        if (HelperClass.getNetworkInfo(getActivity())) {
            progressBar.setVisibility(View.VISIBLE);
            txt.setVisibility(View.GONE);

            try {
                ApiInterfaces apiInterfaces = ApiServices.apiService().create(ApiInterfaces.class);
                Call<UpdateProductModel> call = apiInterfaces.updateCartUsingId(order_id, HelperClass.getOrderSession(getActivity()), product_qty, String.valueOf(attr));
                call.enqueue(new Callback<UpdateProductModel>() {
                    @Override
                    public void onResponse(@NonNull Call<UpdateProductModel> call, @NonNull Response<UpdateProductModel> response) {
                        try {
                            assert response.body() != null;
                            if (response.isSuccessful() && response.body().getIsSuccess().equalsIgnoreCase(Constants.TRUE)) {
                                // hideProgressDialog();
                                assert response.body() != null;

                               // Toast.makeText(getActivity(), "Cart updated Successfully!", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                txt.setVisibility(View.VISIBLE);


                            } else {
                                // hideProgressDialog();
                                Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                txt.setVisibility(View.VISIBLE);
                            }
                        } catch (Exception e) {
                            //  hideProgressDialog();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<UpdateProductModel> call, @NonNull Throwable t) {
                        //hideProgressDialog();
                        Toast.makeText(getActivity(), R.string.try_again, Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        txt.setVisibility(View.VISIBLE);
                    }
                });
            } catch (Exception e) {
                // hideProgressDialog();
                e.printStackTrace();
            }
        } else {
            // hideProgressDialog();
            Toast.makeText(getActivity(), R.string.no_internet, Toast.LENGTH_SHORT).show();
        }
    }


    public String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    public String randomNo(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(NoString.charAt(rnd.nextInt(NoString.length())));
        return sb.toString();
    }

    private void addCart(Integer user_id, int product_id, int product_qty, float product_amt, float attr, String order_session, ProgressBar progressBar) {
        Log.d("AddCart ","user_id"+user_id+" product_id: "+product_id+" product_qty: "+product_qty+ " product_amt: "+product_amt+ " order_session: "+order_session );
        if (HelperClass.getNetworkInfo(getActivity())) {
            progressBar.setVisibility(View.VISIBLE);

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
                                progressBar.setVisibility(View.GONE);

                            } else {
                                // hideProgressDialog();
                                progressBar.setVisibility(View.GONE);

                                Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                            }
                        } catch (Exception e) {
                            // hideProgressDialog();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<AddToCartModel> call, @NonNull Throwable t) {
                        //hideProgressDialog();
                        progressBar.setVisibility(View.GONE);

                        // Toast.makeText(activity, R.string.try_again, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getActivity(), "" + t.toString(), Toast.LENGTH_SHORT).show();

                    }
                });
            } catch (Exception e) {
                //  hideProgressDialog();
                progressBar.setVisibility(View.GONE);

                e.printStackTrace();
            }
        } else {
            //  hideProgressDialog();
            progressBar.setVisibility(View.GONE);

            Toast.makeText(getActivity(), R.string.no_internet, Toast.LENGTH_SHORT).show();


        }
    }
}