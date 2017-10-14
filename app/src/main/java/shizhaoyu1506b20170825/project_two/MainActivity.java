package shizhaoyu1506b20170825.project_two;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import bean.fragment.PageFragment;

public class MainActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();


        setTab();







    }

    private void setTab() {
        //设置TabLayout的值
        FragmentViewPagerAdapter adapter = new FragmentViewPagerAdapter(getSupportFragmentManager(),
                this);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

    }


    private void initView() {
    //找到控件
         tabLayout = (TabLayout) findViewById(R.id.tablayout);
         viewPager = (ViewPager) findViewById(R.id.viewpager);
    }


    class FragmentViewPagerAdapter extends FragmentPagerAdapter{

        private String[] titles = new String[]{"最新日报", "专栏", "热门", "主题日报"};
        private final Context context;

        public FragmentViewPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context=context;

        }

        @Override
        public Fragment getItem(int position) {
            return PageFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

}
