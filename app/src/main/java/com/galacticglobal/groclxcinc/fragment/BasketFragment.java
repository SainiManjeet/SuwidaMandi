package com.galacticglobal.groclxcinc.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.galacticglobal.groclxcinc.R;
import com.galacticglobal.groclxcinc.activity.HomeScreenActivity;
import com.galacticglobal.groclxcinc.adapter.CartItemAdap;
import com.galacticglobal.groclxcinc.classes.ApiServices;
import com.galacticglobal.groclxcinc.classes.BaseFragment;
import com.galacticglobal.groclxcinc.classes.Constants;
import com.galacticglobal.groclxcinc.classes.HelperClass;
import com.galacticglobal.groclxcinc.interfaces.ApiInterfaces;
import com.galacticglobal.groclxcinc.interfaces.QtyClickCallback;
import com.galacticglobal.groclxcinc.interfaces.cartItemClickCallback;
import com.galacticglobal.groclxcinc.model_classes.cart_items.CartItems;
import com.galacticglobal.groclxcinc.model_classes.cart_items.Datum;
import com.galacticglobal.groclxcinc.model_classes.cart_items.removeCartItems;
import com.galacticglobal.groclxcinc.model_classes.checkout.Checkout;
import com.galacticglobal.groclxcinc.model_classes.update_cart.UpdateCart;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BasketFragment extends BaseFragment implements cartItemClickCallback, QtyClickCallback {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static String LOG_TAG = "BasketFragment";
    CartItemAdap adapter;
    ArrayList<Integer> itemQty = new ArrayList<>();
    int mOrderId, mQyt;
    LinearLayout menu;
    private RecyclerView recyclerCart;
    private List<Datum> cratItemList;
    private LinearLayout  placeOrder, update, subtotalContainer, emptyBasket, startShopping;
    private String subtotal;
    Button checkout;
    private int itemQuantity, orderId;
    private TextView itemSubtotal;

    public BasketFragment() {
        // Required empty public constructor
    }

    public static BasketFragment newInstance(String param1, String param2) {
        BasketFragment fragment = new BasketFragment();
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
        // View view =  inflater.inflate(R.layout.fragment_basket, container, false);
        View view = inflater.inflate(R.layout.basket, container, false);
        recyclerCart = view.findViewById(R.id.recycler_cart);

        Log.d(LOG_TAG, " session: " + HelperClass.getOrderSession(getActivity()));

        menu = view.findViewById(R.id.menu);
        menu.setOnClickListener(v -> HomeScreenActivity.openCloseDrawer());

        subtotalContainer = view.findViewById(R.id.subtotal_container);
        itemSubtotal = view.findViewById(R.id.subtotal);
        checkout = view.findViewById(R.id.checkout);

        //  update = view.findViewById(R.id.update);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerCart.setLayoutManager(layoutManager);
        emptyBasket = view.findViewById(R.id.empty_basket);
        startShopping = view.findViewById(R.id.start_shopping);
        Log.d("session onCreate: ", "" + HelperClass.getOrderSession(getActivity()));
        cartItems(HelperClass.getOrderSession(getActivity()));



       startShopping.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent in = new Intent(getActivity(), HomeScreenActivity.class);
               startActivity(in);
           }
       });
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Log.d(LOG_TAG, " orderId: " + orderId + " itemQuantity: " + Constants.updateQuantity);

                checkout(HelperClass.getUserId(getActivity()), HelperClass.getOrderSession(getActivity()));

            }
        });
       /* update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Update cart : Before that do calculation
               // Log.d(LOG_TAG, " orderId: " + orderId + " itemQuantity: " + Constants.updateQuantity);
                 //updateCart(String.valueOf(orderId), String.valueOf(itemQuantity));

            }
        });*/
        return view;
    }

    private void getItemTotal() {
        double total = 0;
        for (int i = 0; i < cratItemList.size(); i++) {


            double myNum = Double.parseDouble(cratItemList.get(i).getSubTotal());
            //   String[] separated = cratItemList.get(i).getSubTotal().split(".");
            // Log.d(LOG_TAG," after split: "+separated[1]);

            total = myNum + total;
        }
        itemSubtotal.setText("Rs." + total);
    }

    // Show Cart Item API Call
    private void cartItems(String orderSession) {
        if (HelperClass.getNetworkInfo(getActivity())) {
            // showProgressDialog();
            try {
                ApiInterfaces apiInterfaces = ApiServices.apiService().create(ApiInterfaces.class);
                Call<CartItems> call = apiInterfaces.cartItems(orderSession);
                call.enqueue(new Callback<CartItems>() {
                    @Override
                    public void onResponse(@NonNull Call<CartItems> call, @NonNull Response<CartItems> response) {
                        try {
                            assert response.body() != null;
                            if (response.isSuccessful() && response.body().getIsSuccess().equalsIgnoreCase(Constants.TRUE)) {
                                hideProgressDialog();
                                assert response.body() != null;
                                cratItemList = response.body().getData();
                                itemQuantity = response.body().getData().get(0).getQty();
                                orderId = response.body().getData().get(0).getOid();

                                getItemTotal();

                                emptyBasket.setVisibility(View.GONE);
                                recyclerCart.setVisibility(View.VISIBLE);
                                setAdapter();

                            } else {
                                hideProgressDialog();
                               // Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                if (cratItemList != null && cratItemList.size() > 0) {
                                    cratItemList.clear();
                                }
                                subtotalContainer.setVisibility(View.GONE);
                                recyclerCart.setVisibility(View.GONE);
                                emptyBasket.setVisibility(View.VISIBLE);

                            }
                        } catch (Exception e) {
                            hideProgressDialog();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<CartItems> call, @NonNull Throwable t) {
                        hideProgressDialog();
                        Toast.makeText(getActivity(), R.string.try_again, Toast.LENGTH_SHORT).show();
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


    private void setAdapter() {
        if (cratItemList != null && cratItemList.size() > 0) {
            subtotalContainer.setVisibility(View.VISIBLE);
            //
            emptyBasket.setVisibility(View.GONE);
            recyclerCart.setVisibility(View.VISIBLE);
        } else {

            subtotalContainer.setVisibility(View.GONE);
            //
            emptyBasket.setVisibility(View.VISIBLE);
            recyclerCart.setVisibility(View.GONE);

        }
        adapter = new CartItemAdap(getActivity(), cratItemList, this, this);
        recyclerCart.setAdapter(adapter);
    }

    private void removeItem(Integer pos, Integer product_id, Integer product_attr_id, String order_session) {
        Log.d("API call: ", "product_id: " + product_id + " attr: " + product_attr_id + " order_session: " + order_session);
        if (HelperClass.getNetworkInfo(getActivity())) {
            //   showProgressDialog();
            try {
                ApiInterfaces apiInterfaces = ApiServices.apiService().create(ApiInterfaces.class);
                Call<removeCartItems> call = apiInterfaces.removeItem(String.valueOf(product_id), String.valueOf(product_attr_id), order_session);
                call.enqueue(new Callback<removeCartItems>() {
                    @Override
                    public void onResponse(@NonNull Call<removeCartItems> call, @NonNull Response<removeCartItems> response) {
                        try {
                            assert response.body() != null;
                            if (response.isSuccessful() && response.body().getIsSuccess().equalsIgnoreCase(Constants.TRUE)) {
                              //  hideProgressDialog();
                                assert response.body() != null;
                               // Toast.makeText(getActivity(), "Item removed successfully", Toast.LENGTH_SHORT).show();

                                // adapter.notifyDataSetChanged();

                                adapter.notifyItemRangeChanged(pos, cratItemList.size());

                                cartItems(HelperClass.getOrderSession(getActivity()));


                            } else {
                              //  hideProgressDialog();
                                //Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                           // hideProgressDialog();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<removeCartItems> call, @NonNull Throwable t) {
                       // hideProgressDialog();
                        Toast.makeText(getActivity(), R.string.try_again, Toast.LENGTH_SHORT).show();
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

    @Override
    public void onListItemClick(Integer pos, Integer pId, Integer attr, String orderSession) {

        removeItem(pos, pId, attr, orderSession);

    }

    // Checkout
    private void checkout(String userId, String order_session) {
        Log.d("checkout param:--", "userId: " + userId + "  order_session" + order_session);
        if (HelperClass.getNetworkInfo(getActivity())) {
            showProgressDialog();
            try {
                ApiInterfaces apiInterfaces = ApiServices.apiService().create(ApiInterfaces.class);
                Call<Checkout> call = apiInterfaces.checkout(userId, order_session);
                call.enqueue(new Callback<Checkout>() {
                    @Override
                    public void onResponse(@NonNull Call<Checkout> call, @NonNull Response<Checkout> response) {
                        try {
                            assert response.body() != null;
                            if (response.isSuccessful() && response.body().getIsSuccess().equalsIgnoreCase(Constants.TRUE)) {
                                hideProgressDialog();
                                assert response.body() != null;
                                Toast.makeText(getActivity(), "Your Oder has been placed Successfully!", Toast.LENGTH_SHORT).show();
                                HelperClass.setOrderSession("", getActivity()); // Clear Order Session
                                HomeScreenActivity.navigation.setSelectedItemId(R.id.action_home);
                                Log.d("Session aftr check=", "" + HelperClass.getOrderSession(getActivity()));
                                recyclerCart.setAdapter(null);



                                subtotalContainer.setVisibility(View.GONE);

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
                    public void onFailure(@NonNull Call<Checkout> call, @NonNull Throwable t) {
                        hideProgressDialog();
                        Toast.makeText(getActivity(), R.string.try_again, Toast.LENGTH_SHORT).show();
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

    // Update Cart
    private void updateCart(String order_id, String product_qty,ProgressBar progressBar,TextView txt) {
        if (HelperClass.getNetworkInfo(getActivity())) {
            progressBar.setVisibility(View.VISIBLE);
            txt.setVisibility(View.GONE);

            try {
                ApiInterfaces apiInterfaces = ApiServices.apiService().create(ApiInterfaces.class);
                Call<UpdateCart> call = apiInterfaces.updateCart(order_id, product_qty);
                call.enqueue(new Callback<UpdateCart>() {
                    @Override
                    public void onResponse(@NonNull Call<UpdateCart> call, @NonNull Response<UpdateCart> response) {
                        try {
                            assert response.body() != null;
                            if (response.isSuccessful() && response.body().getIsSuccess().equalsIgnoreCase(Constants.TRUE)) {
                               // hideProgressDialog();
                                assert response.body() != null;

                               // Toast.makeText(getActivity(), "Cart updated Successfully!", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                txt.setVisibility(View.VISIBLE);
                                cartItems(HelperClass.getOrderSession(getActivity()));// TODO Check n remove
                            } else {
                               // hideProgressDialog();
                             //   Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                txt.setVisibility(View.VISIBLE);
                            }
                        } catch (Exception e) {
                            hideProgressDialog();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<UpdateCart> call, @NonNull Throwable t) {
                       // hideProgressDialog();
                        Toast.makeText(getActivity(), R.string.try_again, Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        txt.setVisibility(View.VISIBLE);
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


    @Override
    public void onUpdateListItemClick(int orderId, String qty, ProgressBar progressBar,TextView txt,float attr) {
        updateCart(String.valueOf(orderId), String.valueOf(qty),progressBar,txt);
    }
}