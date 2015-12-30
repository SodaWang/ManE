package com.hshy41.mane.my.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.alertview.AlertView;
import com.hshy41.mane.MyApplication;
import com.hshy41.mane.R;
import com.hshy41.mane.base.BaseActivity;
import com.hshy41.mane.bean.ChangeHeadBaseBean;
import com.hshy41.mane.entity.ChangeHeadEntity;
import com.hshy41.mane.utils.Cons;
import com.hshy41.mane.utils.FileUtil;
import com.hshy41.mane.utils.ToastUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.client.*;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;


public class PersonalInfoSetActivity extends BaseActivity implements View.OnClickListener, com.bigkoo.alertview.OnItemClickListener, com.bigkoo.alertview.OnDismissListener {

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
    private AlertView mAlertView;//避免创建重复View，先创建View，然后需要的时候show出来，推荐这个做法
    private String picPath;
    /**
     * 头像布局
     *
     * @return
     */
    RelativeLayout rl_head_icon;

    /**
     * 昵称布局
     *
     * @return
     */
    RelativeLayout rl_nickname;

    /**
     * 昵称文字
     *
     * @return
     */
    TextView tv_nickname;

    /**
     * 头像图片
     *
     * @return
     */
    ImageView iv_head_icon;

    ChangeHeadEntity data;
    ChangeHeadBaseBean bean;

    /**
     * 头像bitmap
     *
     * @return
     */
    Bitmap bm_head;

    /**
     * 手机号
     *
     * @return
     */
    TextView tv_phone;

    @Override
    protected void onResume() {
        super.onResume();
        if (!MyApplication.user.getId().equals(0)) {
            //若已登录，从缓存中显示头像
            ImageLoader.getInstance().displayImage(MyApplication.user.getFace(), iv_head_icon, MyApplication.options);
            //设置昵称
            tv_nickname.setText(MyApplication.user.getNickname());
            //设置手机号
            tv_phone.setText(MyApplication.user.getPhone());
        }
    }

    @Override
    protected int setContent() {
        // TODO Auto-generated method stub
        return R.layout.activity_personal_info_set;
    }

    @Override
    protected void initViews() {
        // TODO Auto-generated method stub

        rl_head_icon = (RelativeLayout) findViewById(R.id.rl_personal_info_set_head_icon);
        rl_nickname = (RelativeLayout) findViewById(R.id.rl_personal_info_set_nickname);
        iv_head_icon = (ImageView) findViewById(R.id.iv_personal_info_set_head_icon);
        tv_nickname = (TextView) findViewById(R.id.tv_personal_info_set_nickname);
        tv_phone = (TextView) findViewById(R.id.tv_personal_info_set_phonenumber);


        rl_head_icon.setOnClickListener(this);
        rl_nickname.setOnClickListener(this);
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
    }

    @Override
    protected void setTitleBar() {
        rl_title_left.setVisibility(View.VISIBLE);
        rl_title_right.setVisibility(View.GONE);
        iv_title_gps.setVisibility(View.GONE);
        tv_title_text.setVisibility(View.VISIBLE);
        bt_title_right.setVisibility(View.GONE);
        bt_title_left.setVisibility(View.VISIBLE);

        tv_title_text.setText(R.string.personal_info_set);
        bt_title_left.setOnClickListener(this);
        rl_title_left.setOnClickListener(this);

    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        Intent intent;
        switch (arg0.getId()) {
            case R.id.rl_title_left:
            case R.id.bt_title_left:
                finish();
                break;
            case R.id.rl_personal_info_set_head_icon:
                if (MyApplication.user.getId().equals("0")) {
                    ToastUtil.showLongToast(this, "请先登录");
                } else {
                    new AlertView("上传头像", null, "取消", null,
                            new String[]{"拍照", "从相册中选择"},
                            this, AlertView.Style.ActionSheet, this).show();
                }
                break;
            case R.id.rl_personal_info_set_nickname:
                intent = new Intent(this, NickNameActivity.class);
                startActivityForResult(intent, Cons.REQUEST_SETNICKNAME);
                break;
            default:
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
    public void onDismiss(Object o) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case Cons.REQUEST_SETNICKNAME:
                switch (resultCode) {
                    case RESULT_OK:
                        String temp = data.getStringExtra(Cons.TAG_RESULT_NICKNAME_ACTIVITY);
                        if (temp != null &&
                                !temp.equals("")) {
                            tv_nickname.setText(temp);
                        }
                        break;
                }
            default:
                if (resultCode == Activity.RESULT_OK
                        && requestCode == SELECT_PIC_BY_PICK_PHOTO
                        || requestCode == SELECT_PIC_BY_TACK_PHOTO) {
                    doPhoto(requestCode, data);
                }
                if (resultCode == Activity.RESULT_OK && requestCode == 13) {
                    Bundle extras = data.getExtras();
                    Bitmap photo = extras.getParcelable("data");
                    picPath = FileUtil.saveFile(this, "temp_head.jpg", photo);

                    bm_head = BitmapFactory.decodeFile(picPath);
                    //上传头像
                    ChangeHead(FileUtil.returnFile(this, "", "temp_head.jpg", FileUtil.bitmapToBytes(bm_head)));
//                    iv_head_icon.setImageBitmap(bm);
//            Log.v(TAG, "最终选择的图片=" + picPath);
                    if (picPath != null) {
                        Log.i(TAG, "最终选择的图片 picPath   = " + picPath);
//                handler.sendEmptyMessage(TO_UPLOAD_FILE);
                    } else {
                        Log.i(TAG, "上传的文件路径出错");
                    }
                }
                break;
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


    /**
     * 更换头像线程
     */
    Runnable uploadImage = new Runnable() {
        @Override
        public void run() {

//            List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
//
//            params = new LinkedList<BasicNameValuePair>();
//            params.add(new BasicNameValuePair("uid", MyApplication.user.getId()));//增加参数1
//            params.add(new BasicNameValuePair("image", BitmapUtils.save(bm_head)));//增加参数2
//            HttpClient httpClient = new DefaultHttpClient();
//            try {
//                HttpPost postMethod = new HttpPost(Cons.DOMAIN + Cons.CHANGE_HEAD);//创建一个post请求
//                postMethod.setEntity(new UrlEncodedFormEntity(params, "utf-8")); //将参数填入POST Entity中
//                HttpResponse response = httpClient.execute(postMethod); //执行POST方法
//                Log.i(TAG, "resCode = " + response.getStatusLine().getStatusCode()); //获取响应码
////                Log.i(TAG, "result = " + EntityUtils.toString(response.getEntity(), "utf-8")); //获取响应内容
//                JSONObject json = new JSONObject(EntityUtils.toString(response.getEntity(), "utf-8"));
//                if (json.get("Result").equals("0")) {
//                    bean = MyApplication.gson.fromJson(json.toString(), ChangeHeadBaseBean.class);
//                    data = bean.data;
//                    MyApplication.user.setFace(data.getAvatar());
//                } else if (json.get("Result").equals("1")) {
//                    Looper.prepare();
//                    ToastUtil.showToast(PersonalInfoSetActivity.this, json.getString("Message"));
//                    Looper.loop();
//                }
//                bean = MyApplication.gson.fromJson(EntityUtils.toString(response.getEntity(), "utf-8"), ChangeHeadBaseBean.class);
//                data = bean.data;
//                //设置头像地址
//                MyApplication.user.setFace(data.getAvatar());
//                //显示之
//                ImageLoader.getInstance().displayImage(MyApplication.user.getFace(), iv_head_icon, MyApplication.options);
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            } catch (ClientProtocolException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (JSONException e) {
//                e.printStackTrace();
        }
    };


    private void ChangeHead(final File imageFile) {
        //实例化HttpUtils对象， 参数设置链接超时
        HttpUtils HTTP_UTILS = new HttpUtils(60 * 1000);
        //实例化RequestParams对象
        RequestParams requestParams = new RequestParams();
        requestParams.addBodyParameter("uid", MyApplication.user.getId());
        requestParams.addBodyParameter("image", imageFile, "image/png");

        String photoUrl = Cons.DOMAIN + Cons.CHANGE_HEAD;
        //通过HTTP_UTILS来发送post请求， 并书写回调函数
        HTTP_UTILS.send(HttpRequest.HttpMethod.POST, photoUrl, requestParams, new com.lidroid.xutils.http.callback.RequestCallBack<String>() {
            @Override
            public void onFailure(HttpException httpException, String arg1) {
                Looper.prepare();
                ToastUtil.showToast(PersonalInfoSetActivity.this, "链接错误");
                Looper.loop();
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                JSONObject json = null;
                try {
                    json = new JSONObject(responseInfo.result);
                    if (json.get("Result").equals("0")) {
                        bean = MyApplication.gson.fromJson(json.toString(), ChangeHeadBaseBean.class);
                        data = bean.data;
                        MyApplication.user.setFace(data.getAvatar());
                    } else if (json.get("Result").equals("1")) {
                        Looper.prepare();
                        ToastUtil.showToast(PersonalInfoSetActivity.this, json.getString("Message"));
                        Looper.loop();
                    }
                    bean = MyApplication.gson.fromJson(responseInfo.result, ChangeHeadBaseBean.class);
                    data = bean.data;
                    //设置头像地址
                    MyApplication.user.setFace(data.getAvatar());
                    //更新用户信息
                    MyApplication.updataUserInfo(context);
                    //显示之
                    ImageLoader.getInstance().displayImage(MyApplication.user.getFace(), iv_head_icon, MyApplication.options);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}