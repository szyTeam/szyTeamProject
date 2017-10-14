package bean.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youth.banner.Banner;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import bean.BaseBean;
import bean.Image;
import bean.NewsBean;
import shizhaoyu1506b20170825.project_two.R;
import utils.HttpUils;
import utils.ResultData;

/**
 * 类用途：
 * 作者：史曌宇
 */

public class PageFragment extends Fragment {


    private static String ARGS_PAGE = "aaa";
    private int mPage;

    private Banner banner;
    private View zuixin;

    public static Fragment newInstance(int page) {

        Bundle args = new Bundle();
        args.putInt(ARGS_PAGE, page);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPage = getArguments().getInt(ARGS_PAGE);//用于显示界面的变量
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        setBannerData();
    }

    private void initView() {
        banner = (Banner) zuixin.findViewById(R.id.ban);


    }

    private void setBannerData() {
        //设置Banner的数据
        final ArrayList<String> banner_list_image = new ArrayList<>();
        final ArrayList<String> banner_list_title = new ArrayList<>();
        String Banner_Url = "http://news-at.zhihu.com/api/4/news/latest";

        HttpUils.getHttpUtils(getActivity()).get(Banner_Url, NewsBean.class, new ResultData() {


            @Override
            public void onSuccess(BaseBean basebean) {

                NewsBean newsBean = (NewsBean) basebean;
                List<NewsBean.TopStoriesBean> top_stories = newsBean.getTop_stories();
                for (int i = 0; i < top_stories.size(); i++) {
                    String image = top_stories.get(i).getImage();
                    String title = top_stories.get(i).getTitle();
                    banner_list_image.add(image);
                    banner_list_title.add(title);

                }
                banner_list_image.size();
                banner_list_title.size();

            setData(banner_list_image,banner_list_title);
            }



        });


    }

    private void setData(ArrayList<String> banner_list_image, ArrayList<String> banner_list_title) {
        banner.setImageLoader(new Image());
        banner.setBannerTitles(banner_list_title);
        banner.setImages(banner_list_image);
        banner.setDelayTime(1000);
        banner.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //if (mPage == 1) {
        zuixin = inflater.inflate(R.layout.fragment_zuixin_news, container, false);
        return zuixin;
        // }

        //return null;//默认返回Null
    }
}
