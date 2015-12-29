package com.hshy41.mane.my.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.alertview.AlertView;
import com.hshy41.mane.R;
import com.hshy41.mane.adapter.recyclerviewadapter.AddPhoto;
import com.hshy41.mane.adapter.recyclerviewadapter.PhotoItemDecoration;
import com.hshy41.mane.base.BaseActivity;
import com.hshy41.mane.utils.FileUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * 公共报修单页面
 */
public class GoodsReleaseActivity extends BaseActivity implements View.OnClickListener, com.bigkoo.alertview.OnItemClickListener {
    Handler handler = new Handler();
    private String picPath;
    Bitmap bm;
    /***
     * 从Intent获取图片路径的KEY
     */
    public static String KEY_PHOTO_PATH = "photo_path";
    private Uri photoUri;
    private static final String TAG = "uploadImage";
    /**
     * 使用照相机拍照获取图片
     */
    public static final int SELECT_PIC_BY_TACK_PHOTO = 1;
    /***
     * 使用相册中的图片
     */
    public static final int SELECT_PIC_BY_PICK_PHOTO = 2;
    /**
     * 宝贝照片列表
     *
     * @return
     */
    RecyclerView rv_goods_release;

    /**
     * 照片列表
     *
     * @return
     */
    List<Bitmap> list_photo;
    /**
     * 添加照片列表适配器
     */
    AddPhoto adapter;

    /**
     * +号
     */
    BitmapDrawable bd_add;

    /**
     * 发布按钮
     *
     * @return
     */
    Button bt_release;

    /**
     * 图片列表下标
     *
     * @return
     */
    int i_photo_idex;

    @Override
    protected int setContent() {
        return R.layout.activity_goods_release;
    }

    @Override
    protected void initViews() {
        bt_release = (Button) findViewById(R.id.bt_goods_release_release);
        list_photo = new ArrayList<Bitmap>();
        //添加图片数据
        bd_add = (BitmapDrawable) getResources().getDrawable(R.drawable.jiatu);
        list_photo.add(bd_add.getBitmap());

        //预设RecyclerView的各种属性
        rv_goods_release = (RecyclerView) findViewById(R.id.rv_goods_release);
        adapter = new AddPhoto(this, list_photo);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_goods_release.setAdapter(adapter);
        rv_goods_release.setLayoutManager(llm);
        rv_goods_release.addItemDecoration(
                new PhotoItemDecoration(this, PhotoItemDecoration.HORIZONTAL_LIST));
        rv_goods_release.setItemAnimator(new DefaultItemAnimator());
        bt_release.setOnClickListener(this);
        //添加滑动移除监听 BUG 会跟我下面那个监听冲突
//        SwipeDismissRecyclerViewTouchListener verticalListener = new SwipeDismissRecyclerViewTouchListener.Builder(
//                rv_goods_release,
//                new SwipeDismissRecyclerViewTouchListener.DismissCallbacks() {
//                    @Override
//                    public boolean canDismiss(int position) {
////                        if (position == adapter.mData.size() - 1) {
////                            return false;
////                        }
//                        return true;
//                    }
//
//                    @Override
//                    public void onDismiss(View view) {
//                        int id = rv_goods_release.getChildPosition(view);
//                        adapter.mData.remove(id);
//                        adapter.notifyDataSetChanged();
//
////                        Toast.makeText(getBaseContext(), String.format("Delete item %d", id), Toast.LENGTH_LONG).show();
//                    }
//                }).setIsVertical(true).create();

//        rv_goods_release.setOnTouchListener(verticalListener);
        //添加item点击监听
        adapter.setOnItemClickLitener(new AddPhoto.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                i_photo_idex = position;
                new AlertView("上传头像", null, "取消", null,
                        new String[]{"拍照", "从相册中选择"},
                        GoodsReleaseActivity.this, AlertView.Style.ActionSheet,
                        GoodsReleaseActivity.this)
                        .show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                if (position != adapter.mData.size() - 1) {
//                    adapter.mData.remove(position);//删除图片
                    adapter.removeData(position);//删除动画
//                    adapter.notifyDataSetChanged();//通知视图
                }

            }
        });
    }

    @Override
    protected void initTitleBar() {
        rl_title_left = (RelativeLayout) findViewById(R.id.rl_title_left);
        rl_title_right = (RelativeLayout) findViewById(R.id.rl_title_right);
        iv_title_gps = (ImageView) findViewById(R.id.iv_title_gps);
        tv_title_text = (TextView) findViewById(R.id.tv_title_text);
        tv_title_right = (TextView) findViewById(R.id.tv_title_right);
        bt_title_right = (Button) findViewById(R.id.bt_title_right);
        bt_title_left = (Button) findViewById(R.id.bt_title_left);
        tv_title_right = (TextView) findViewById(R.id.tv_title_right);
    }

    @Override
    protected void setTitleBar() {
        rl_title_left.setVisibility(View.VISIBLE);
        rl_title_right.setVisibility(View.GONE);
        iv_title_gps.setVisibility(View.GONE);
        tv_title_text.setVisibility(View.VISIBLE);
        bt_title_right.setVisibility(View.GONE);
        bt_title_left.setVisibility(View.VISIBLE);
        tv_title_right.setVisibility(View.GONE);

        tv_title_text.setText(R.string.goods_release);

        bt_title_left.setOnClickListener(this);
        rl_title_left.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.rl_title_left:
            case R.id.bt_title_left:
                finish();
                break;
            case R.id.bt_goods_release_release:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(Object o, int position) {
        //判断是否是拓展窗口View，而且点击的是非取消按钮
//        if(o == mAlertViewExt && position != AlertView.CANCELPOSITION){
//            String name = etName.getText().toString();
//            if(name.isEmpty()){
//                Toast.makeText(this, "啥都没填呢", Toast.LENGTH_SHORT).show();
//            }
//            else{
//                Toast.makeText(this, "hello,"+name, Toast.LENGTH_SHORT).show();
//            }
//
//            return;
//        }
        switch (position) {
            case 0://拍照
                String SDState = Environment.getExternalStorageState();
                if (SDState.equals(Environment.MEDIA_MOUNTED)) {

                    Intent intent = new Intent(
                            MediaStore.ACTION_IMAGE_CAPTURE);// "android.media.action.IMAGE_CAPTURE"

                    /***
                     * 需要说明一下，以下操作使用照相机拍照，拍照后的图片会存放在相册中的
                     * 这里使用的这种方式有一个好处就是获取的图片是拍照后的原图
                     * 如果不实用ContentValues存放照片路径的话，拍照后获取的图片为缩略图不清晰
                     */
                    ContentValues values = new ContentValues();
                    photoUri = getContentResolver()
                            .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                    values);
                    intent.putExtra(
                            android.provider.MediaStore.EXTRA_OUTPUT,
                            photoUri);
                    /** ----------------- */
                    startActivityForResult(intent,
                            SELECT_PIC_BY_TACK_PHOTO);
                } else {
                    Toast.makeText(this, "内存卡不存在",
                            Toast.LENGTH_LONG).show();
                }
                break;
            case 1://从相册中选择
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setType("image/*");
                intent.setDataAndType(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        "image/*");
                startActivityForResult(intent, SELECT_PIC_BY_PICK_PHOTO);
                break;
        }
//        Toast.makeText(this, "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK
                && requestCode == SELECT_PIC_BY_PICK_PHOTO
                || requestCode == SELECT_PIC_BY_TACK_PHOTO) {
            doPhoto(requestCode, data);
        }
        if (resultCode == Activity.RESULT_OK && requestCode == 13) {
            Bundle extras = data.getExtras();
            Bitmap photo = extras.getParcelable("data");
            picPath = FileUtil.saveFile(this, "temp_head.jpg", photo);
            bm = BitmapFactory.decodeFile(picPath);
            if (i_photo_idex == adapter.mData.size() - 1) {
                Runnable runable = new Runnable() {
                    @Override
                    public void run() {
                        adapter.addData(bm, adapter.mData.size() - 1);//添加加入动画
                    }
                };
                handler.postDelayed(runable, 300);

            } else {
                adapter.removeData(i_photo_idex);
                Runnable runable = new Runnable() {
                    @Override
                    public void run() {
                        adapter.addData(bm, adapter.mData.size() - 1);//添加加入动画
                    }
                };
                handler.postDelayed(runable, 300);
            }

//            Log.v(TAG, "最终选择的图片=" + picPath);

//            imageView.setImageBitmap(bm);
            if (picPath != null) {
                Log.i(TAG, "最终选择的图片 picPath   = " + picPath);
//                handler.sendEmptyMessage(TO_UPLOAD_FILE);
            } else {
                Log.i(TAG, "上传的文件路径出错");
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 选择图片后，获取图片的路径
     *
     * @param requestCode
     * @param data
     */
    private void doPhoto(int requestCode, Intent data) {
        if (requestCode == SELECT_PIC_BY_PICK_PHOTO) // 从相册取图片，有些手机有异常情况，请注意
        {
            if (data == null) {
                Toast.makeText(this, "选择图片文件出错", Toast.LENGTH_LONG).show();
                return;
            }
            photoUri = data.getData();
            if (photoUri == null) {
                Toast.makeText(this, "选择图片文件出错", Toast.LENGTH_LONG).show();
                return;
            }
        }
        String[] pojo = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(photoUri, pojo, null, null, null);
        if (cursor != null) {
            int columnIndex = cursor.getColumnIndexOrThrow(pojo[0]);
            cursor.moveToFirst();
            picPath = cursor.getString(columnIndex);
            // cursor.close();
        }
        Log.i(TAG, "imagePath = " + picPath);
        if (picPath != null
                && (picPath.endsWith(".png") || picPath.endsWith(".PNG")
                || picPath.endsWith(".jpg") || picPath.endsWith(".JPG"))) {

            // handler.sendEmptyMessage(TO_UPLOAD_FILE);
            KEY_PHOTO_PATH = picPath;
            Log.i(TAG, "2  KEY_PHOTO_PATH = " + KEY_PHOTO_PATH);
            Intent intent = new Intent("com.android.camera.action.CROP");
            Uri uri;
            if (data == null) {
                uri = photoUri;
            } else {
                uri = data.getData();
            }
            intent.setDataAndType(uri, "image/*");
            intent.putExtra("crop", "true");// crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", 150);
            intent.putExtra("outputY", 150);
            intent.putExtra("return-data", true);
            startActivityForResult(intent, 13);

        } else {
            Toast.makeText(this, "选择图片文件不正确", Toast.LENGTH_LONG).show();
        }
    }
}
