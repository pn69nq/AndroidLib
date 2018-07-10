package com.android.fresco.demo.photo;

import android.view.View;

import com.facebook.fresco.helper.R;
import com.facebook.fresco.helper.photoview.PictureBrowseActivity;
import com.facebook.fresco.helper.photoview.entity.PhotoInfo;

/**
 * 查看大图
 * Created by android_ls on 2017/1/20.
 */

public class PhotoBrowseActivity extends PictureBrowseActivity {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_photo_browse;
    }

    @Override
    protected void setupViews() {
        super.setupViews();
        findViewById(R.id.rl_top_deleted).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PhotoInfo photoInfo = mItems.get(mPhotoIndex);

            }
        });
    }

    @Override
    public boolean onLongClick(View view) {
        PhotoInfo photoInfo = getCurrentPhotoInfo();
        return super.onLongClick(view);
    }

}
