package itesloscabos.com.hotelapp.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import itesloscabos.com.hotelapp.Models.Hotel;
import itesloscabos.com.hotelapp.Models.datos;
import itesloscabos.com.hotelapp.Models.hotelResult;
import itesloscabos.com.hotelapp.Models.indexImagenes;
import itesloscabos.com.hotelapp.R;

/**
 * Created by croni on 05/06/2017.
 */

public class viewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<indexImagenes>lista;

    public viewPagerAdapter(@NonNull Context context , @NonNull List<indexImagenes> lis) {
        this.context = context;
        this.lista = lis;
    }


    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        final indexImagenes item=lista.get(position);

        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v =layoutInflater.inflate(R.layout.custom_layout,null);

        ImageView img =(ImageView)v.findViewById(R.id.imageView);

        Glide.with(context)
                .load(item.getUrl())
               .centerCrop()
               .crossFade()
               .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(img);

        ViewPager vp =(ViewPager)container;
        vp.addView(v,0);
        return v;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp =(ViewPager)container;
        View view=(View)object;
        vp.removeView(view);

    }
}
