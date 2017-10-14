package bean;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.youth.banner.loader.ImageLoaderInterface;

import shizhaoyu1506b20170825.project_two.R;

/**
 * 类用途：
 * 作者：史曌宇
 */

public class Image   extends ImageLoader implements ImageLoaderInterface {

    private Context context;
    private ImageLoader mImageLoader;

    private  void initImageLoader() {

        DisplayImageOptions options=new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .showImageOnLoading(R.mipmap.ic_launcher)
                .build();
        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(context)
                .defaultDisplayImageOptions(options)
                .build();
        mImageLoader = ImageLoader.getInstance();
        mImageLoader.init(configuration);

    }



    public   ImageLoader setImage(){

        return mImageLoader;
    }


    @Override
    public void displayImage(Context context, Object path, View imageView) {

        ImageView image = (ImageView) imageView;

        Glide.with(context).load(path).into(image);

        //Picasso 加载图片简单用法
        //   Picasso.with(context).load(path).into(imageView);

        //用fresco加载图片简单用法，记得要写下面的createImageView方法
        Uri uri = Uri.parse((String) path);
        image.setImageURI(uri);


    }

    @Override
    public View createImageView(Context context) {
        return null;
    }
}

