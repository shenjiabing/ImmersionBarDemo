package artcollection.brvah.com.immersionbardemo;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2019/9/24.
 */

public class AFragment extends BaseFragment {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    Unbinder unbinder;
    @BindView(R.id.mRefreshLayout)
    SmartRefreshLayout mRefreshLayout;

    private ShopAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.d_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAdapter = new ShopAdapter(R.layout.item_shopcar);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.gray_EEEEEE));
        for (int i = 0; i < 20; i++) {
            ShopBean item = new ShopBean();
            item.setTitle("测试标题" + i);
            mAdapter.addData(item);
        }

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showShort("" + mAdapter.getData().get(position).getTitle());
            }
        });
        mRecyclerView.setNestedScrollingEnabled(false);

        setThemeColor(R.color.orange_FF5530,R.color.orange_FF5530);
//        setThemeColor(android.R.color.holo_green_light, android.R.color.holo_green_dark);

    }
    private void setThemeColor(int colorPrimary, int colorPrimaryDark) {

        mRefreshLayout.setPrimaryColorsId(colorPrimary, android.R.color.white);
        if (Build.VERSION.SDK_INT >= 21) {
            getActivity().getWindow().setStatusBarColor(ContextCompat.getColor(getContext(), colorPrimaryDark));

        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
