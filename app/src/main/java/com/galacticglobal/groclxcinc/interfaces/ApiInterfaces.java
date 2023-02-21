package com.galacticglobal.groclxcinc.interfaces;

import com.galacticglobal.groclxcinc.model_classes.AllCateModel;
import com.galacticglobal.groclxcinc.model_classes.ForgotModel;
import com.galacticglobal.groclxcinc.model_classes.add_to_cart.AddToCartModel;
import com.galacticglobal.groclxcinc.model_classes.cart_items.CartItems;
import com.galacticglobal.groclxcinc.model_classes.cart_items.removeCartItems;
import com.galacticglobal.groclxcinc.model_classes.cate_wise_product.CateProduct;
import com.galacticglobal.groclxcinc.model_classes.change_pass.ChangePassModel;
import com.galacticglobal.groclxcinc.model_classes.checkout.Checkout;
import com.galacticglobal.groclxcinc.model_classes.edit_profile_model.EditProfileModel;
import com.galacticglobal.groclxcinc.model_classes.home_products_model.HomeProductsModel;
import com.galacticglobal.groclxcinc.model_classes.login.LoginModel;
import com.galacticglobal.groclxcinc.model_classes.order_history.OrderHistory;
import com.galacticglobal.groclxcinc.model_classes.search.SearchModel;
import com.galacticglobal.groclxcinc.model_classes.sign_up.SignUpModel;
import com.galacticglobal.groclxcinc.model_classes.sub_cat_products.SubCatModel;
import com.galacticglobal.groclxcinc.model_classes.sub_cat_products_model.SubCatProductsModel;
import com.galacticglobal.groclxcinc.model_classes.updateProduct.UpdateProductModel;
import com.galacticglobal.groclxcinc.model_classes.update_cart.UpdateCart;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterfaces {


    @GET("api.php?type=main_category_list")
    Call<AllCateModel> allCategories();

    @FormUrlEncoded
    @POST("api.php?type=registration")
    Call<SignUpModel> signUp(@Field("fname") String fname,
                             @Field("lname") String lname,
                             @Field("email") String email,
                             @Field("password") String password,
                             @Field("address") String address,
                             @Field("phone") String phone,
                             @Field("hno") String hno);


    @FormUrlEncoded
    @POST("api.php?type=edit_profile")
    Call<EditProfileModel> editProfile(@Field("user_id") int user_id,
                                       @Field("fname") String fname,
                                       @Field("lname") String lname,
                                       @Field("address") String address,
                                       @Field("phone_no") String phone_no,
                                       @Field("billing_address") String billing_address,
                                       @Field("user_bill_address") String user_bill_address);


    @FormUrlEncoded
    @POST("api.php?type=forget_password")
    Call<ForgotModel> forgotPass(
            @Field("email") String email);

    @FormUrlEncoded
    @POST("api.php?type=login")
    Call<LoginModel> login(
            @Field("username") String email,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("api.php?type=change_password")
    Call<ChangePassModel> pass(
            @Field("user_id") String user_id,
            @Field("old_password") String old_password,
            @Field("new_password") String new_password);

    @FormUrlEncoded
    @POST("api.php?type=category_wise_product")
    Call<CateProduct> cateWiseProduct(
            @Field("category_id") String category_id);

    @FormUrlEncoded
    @POST("api.php?type=order_history")
    Call<OrderHistory> orderHistory(
            @Field("user_id") String user_id);

    //
    @FormUrlEncoded
    @POST("api.php?type=cart_items")
    Call<CartItems> cartItems(
            @Field("order_session") String order_session);

    @FormUrlEncoded
    @POST("api.php?type=remove_item")
    Call<removeCartItems> removeItem(
            @Field("product_id") String product_id,
            @Field("product_attr_id") String product_attr_id,
            @Field("order_session") String order_session);

    @GET("api.php?type=home_scree_products")
    Call<HomeProductsModel> homeProduct();

    @FormUrlEncoded
    @POST("api.php?type=sub_category_list")
    Call<SubCatModel> subCateWiseProduct(@Field("cid") int cid);

    @FormUrlEncoded
    @POST("api.php?type=category_wise_product")
    Call<SubCatProductsModel> subCateWiseProductDetails(@Field("category_id") int category_id);


    @FormUrlEncoded
    @POST("api.php?type=addtocart")
    Call<AddToCartModel> addCart(@Field("user_id") int user_id,
                                 @Field("product_id") int product_id,
                                 @Field("product_qty") int product_qty,
                                 @Field("product_amt") float product_amt,
                                 @Field("product_attr") float product_attr,
                                 @Field("order_session") String order_session
                                 );

    @FormUrlEncoded
    @POST("api.php?type=checkout")
    Call<Checkout> checkout(
            @Field("user_id") String product_id,
            @Field("order_session") String order_session);

    @FormUrlEncoded
    @POST("api.php?type=update_cart")
    Call<UpdateCart> updateCart(
            @Field("order_id") String order_id,
            @Field("product_qty") String product_qty);

    //
    @FormUrlEncoded
    @POST("api.php?type=product_search")
    Call<SearchModel> searchProduct(
            @Field("keyword") String keyword);

    //
    @FormUrlEncoded
    @POST("api.php?type=update_cart_pid")
    Call<UpdateProductModel> updateCartUsingId(
            @Field("pid") String pid,
            @Field("order_session") String order_session,
            @Field("product_qty") String product_qty,
            @Field("p_attr") String p_attr);




}


