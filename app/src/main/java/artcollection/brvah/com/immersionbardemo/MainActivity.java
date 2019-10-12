package artcollection.brvah.com.immersionbardemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.ToastUtils;

public class MainActivity extends AppCompatActivity {

    private Button[] mTabs;// 底部切换fragment 的按钮数组集合
    private Fragment[] fragments;
    private int index;//被点击的tab的下标
    private int currentTabIndex;// 当前tab下标

    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        findViewById();
        AFragment aFragment=new AFragment();
        BFragment bFragment=new BFragment();
        CFragment cFragment=new CFragment();
        DFragment dFragment=new DFragment();
        fragments = new Fragment[] { aFragment,bFragment, cFragment,dFragment};

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, aFragment).show(aFragment)
                .commit();
    }



    /**
     * init views
     */
    private void findViewById() {
        mTabs = new Button[4];
        mTabs[0] = (Button) findViewById(R.id.btn1);
        mTabs[1] = (Button) findViewById(R.id.btn2);
        mTabs[2] = (Button) findViewById(R.id.btn4);
        mTabs[3] = (Button) findViewById(R.id.btn5);
//		mTabs[4] = (Button) findViewById(R.id.btn5);
//		btn3 =(Button) findViewById(R.id.btn3);
        // select first tab
        mTabs[0].setSelected(true);
    }


    public void onAddTabClicked(View view){
        ToastUtils.showShort("发布");
    }

    /**
     * on tab clicked
     *
     * @param view
     */
    public void onTabClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                index = 0;
                break;
            case R.id.btn2:
                index = 1;
                break;
            case R.id.btn4:
                index = 2;
                break;
            case R.id.btn5:
                index = 3;
                break;
        }

        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                trx.add(R.id.fragment_container, fragments[index]);
            }
            trx.show(fragments[index]).commit();
        }
        mTabs[currentTabIndex].setSelected(false);
        // set current tab selected
        mTabs[index].setSelected(true);
        currentTabIndex = index;

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
                moveTaskToBack(false);
                ToastUtils.cancel();
            } else {
                TOUCH_TIME = System.currentTimeMillis();
                ToastUtils.showShort("再按一次退出");
            }

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
