package com.galacticglobal.groclxcinc.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.galacticglobal.groclxcinc.R;
import com.galacticglobal.groclxcinc.interfaces.BannerClickListner;
import com.galacticglobal.groclxcinc.model_classes.home_products_model.HomeBanner;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends PagerAdapter {


    List<HomeBanner> homeBanners;
    BannerClickListner onBannerListItemClick;

    FragmentActivity activity;
    public CustomAdapter(FragmentActivity activity, List<HomeBanner> homeBanners, BannerClickListner onBannerListItemClick) {
        this.activity= activity;
        this.homeBanners= homeBanners;
        this.onBannerListItemClick =  onBannerListItemClick;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater inflater = ((FragmentActivity)activity).getLayoutInflater();

        View viewItem = inflater.inflate(R.layout.image_item, container, false);
        ImageView imageView =  viewItem.findViewById(R.id.imageView);
        Picasso.get().load(homeBanners.get(position).getImage()).into(imageView);
        ((ViewPager)container).addView(viewItem);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onBannerListItemClick != null)
                    onBannerListItemClick.onBannerListItemClick(position,homeBanners.get(position).getName());

            }
        });

        return viewItem;
    }

    @Override
    public int getCount() {
        return homeBanners.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((View)object);
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        ((ViewPager) container).removeView((View) object);
    }
}
