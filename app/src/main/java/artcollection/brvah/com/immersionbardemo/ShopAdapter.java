package artcollection.brvah.com.immersionbardemo;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by Administrator on 2019/9/26.
 */

public class ShopAdapter extends BaseQuickAdapter<ShopBean,BaseViewHolder>{
    public ShopAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ShopBean item) {

    }
}
