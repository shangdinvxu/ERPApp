package tarce.testnew.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import tarce.testnew.R;
import tarce.testnew.Utils.AvatarHelper;
import tarce.testnew.Utils.BitmapUtils;
import tarce.testnew.Utils.MyLog;
import tarce.testnew.Utils.SelectPicPopupWindow;
import tarce.testnew.greendao.GreendaoUtils.SaveInventroyUtils;
import tarce.testnew.greendao.greendaoBeans.SaveInventory;
import tarce.testnew.http.RetrofitClient;
import tarce.testnew.http.api.MRPApi;
import tarce.testnew.http.bean.requestBean.findProductByConditionRequest;
import tarce.testnew.http.bean.responseBean.FindProductByConditionResponse;
import tarce.testnew.http.bean.responseBean.GetAreaListResponse;

public class SaveActivity extends AppCompatActivity {
    private  String TAG = this.getClass().getSimpleName();
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.partNO)
    TextView partNO;
    @InjectView(R.id.partNOText)
    TextView partNOText;
    @InjectView(R.id.location)
    TextView location;
    @InjectView(R.id.locationText)
    TextView locationText;
    @InjectView(R.id.theoretical_qty)
    TextView theoreticalQty;
    @InjectView(R.id.theoretical_qtyText)
    TextView theoreticalQtyText;
    @InjectView(R.id.product_qty)
    TextView productQty;
    @InjectView(R.id.product_qtyText)
    EditText productQtyText;
    @InjectView(R.id.content_save)
    LinearLayout contentSave;
    @InjectView(R.id.imageView)
    ImageView imageView;
    private MRPApi mrpApi;
    private FindProductByConditionResponse.ResultBean.ResDataBean.ProductBean product;
    private FindProductByConditionResponse.ResultBean.ResDataBean res_data;
    private SaveInventroyUtils saveInventroyUtils;
    private boolean startBypostion  = false;

    private PopupWindow menuWindow = null;

    // 修改头像的临时文件存放路径（头像修改成功后，会自动删除之）
    private String __tempImageFileLocation = null;
    /** 回调常量之：拍照 */
    private static final int TAKE_BIG_PICTURE = 991;
    /** 回调常量之：拍照后裁剪 */
    private static final int CROP_BIG_PICTURE = 993;
//	/** 回调常量之：从相册中选取 */
//	private static final int CHOOSE_BIG_PICTURE = 995;
    /** 回调常量之：从相册中选取2 */
    private static final int CHOOSE_BIG_PICTURE2 = 996;
    /** 图像保存大小（微信的也是这个大小） */
    private static final int AVATAR_SIZE = 640;
    private View view;
    private SaveInventory saveInventory;
    private int databaseSwitch = 0;
    private int areaId = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        view = LayoutInflater.from(SaveActivity.this).inflate(R.layout.activity_save, null);
        ButterKnife.inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mrpApi = RetrofitClient.getInstance(SaveActivity.this).create(MRPApi.class);
        saveInventroyUtils = new SaveInventroyUtils();
        initCheckIntent();

    }

    private void initCheckIntent() {
        String result = getIntent().getStringExtra("result");
        if (result!=null&&result.length()>0){
            initIntentResult();
        }
        int postion = getIntent().getIntExtra("postion",-1);
        if (postion!=-1){
            initIntentPostion(postion);
        }

    }

    private void initIntentPostion(int postion) {
        List<SaveInventory> saveInventories = saveInventroyUtils.searchALL();
        startBypostion = true ;
        saveInventory = saveInventories.get(postion);
        partNOText.setText(saveInventory.getProduct_name());
        productQtyText.setText(saveInventory.getProduct_qty()+"");
        theoreticalQtyText.setText(saveInventory.getTheoretical_qty()+"");
        imageView.setImageBitmap(BitmapUtils.base64ToBitmap(saveInventory.getImage_medium()));
    }


    @OnClick(R.id.locationText)
    void getLocation(View v){
        Observable<GetAreaListResponse> areaList = mrpApi.getAreaList(new HashMap());
        final AlertDialog.Builder builder = new AlertDialog.Builder(SaveActivity.this);
        builder.setTitle("选择位置");
        builder.setIcon(android.R.drawable.ic_menu_more);
        builder.setCancelable(true);
        areaList.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Subscriber<GetAreaListResponse>() {
                            @Override
                            public void onCompleted() {
                                MyLog.e(TAG,"onCompleted");
                            }

                            @Override
                            public void onError(Throwable e) {
                                MyLog.e(TAG, e.toString());
                                MyLog.e(TAG,"onError");
                            }
                            @Override
                            public void onNext(GetAreaListResponse getAreaListResponse) {
                                MyLog.e(TAG,"onNext");
                                final List<GetAreaListResponse.ResultBean.ResDataBean> areaList = getAreaListResponse.getResult().getRes_data();
                                final List<String> strings = new ArrayList<String>();
                                for (GetAreaListResponse.ResultBean.ResDataBean resDataBean : areaList){
                                    String name = resDataBean.getName();
                                    strings.add(name);
                                }
                                final String[] databaseArr = strings.toArray(new String[strings.size()]);
                                builder.setSingleChoiceItems(databaseArr, databaseArr.length, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        databaseSwitch = i;
                                        int id = areaList.get(databaseSwitch).getId();
                                        areaId = id ;
                                    }
                                });
                                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        SaveActivity.this.locationText.setText(strings.get(databaseSwitch));
                                    }
                                });
                                AlertDialog dialog = builder.create();
                                dialog.setCanceledOnTouchOutside(true);
                                dialog.show();
                                MyLog.e(TAG,"dialogshow");
                            }
                        }
                );

    }

    private void initIntentResult() {
        String result = getIntent().getStringExtra("result");

        final List<SaveInventory> saveInventories = saveInventroyUtils.searchALL();
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("default_code", result);
        Call<FindProductByConditionResponse> productByCondition = mrpApi.findProductByCondition(new findProductByConditionRequest(stringStringHashMap));
        productByCondition.enqueue(new Callback<FindProductByConditionResponse>() {
            @Override
            public void onResponse(Call<FindProductByConditionResponse> call, Response<FindProductByConditionResponse> response) {
                if (response.body().getResult().getRes_code()==-1){
                    Toast.makeText(SaveActivity.this,"未找到该产品",Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    if (saveInventories!=null&&saveInventories.size()>0){
                        for (SaveInventory saveInventory :saveInventories){
                            if (response.body().getResult().getRes_data().getProduct().getProduct_name().equals(saveInventory.getProduct_name())){
                                Toast.makeText(SaveActivity.this,"该库存已存在",Toast.LENGTH_SHORT).show();
                                finish();
                                return;
                            }
                        }
                    }
                    res_data = response.body().getResult().getRes_data();
                    product = response.body().getResult().getRes_data().getProduct();
                    partNOText.setText(product.getProduct_name());
                    productQtyText.setText(response.body().getResult().getRes_data().getProduct_qty()+"");
                    theoreticalQtyText.setText(response.body().getResult().getRes_data().getTheoretical_qty()+"");
                    locationText.setText(response.body().getResult().getRes_data().getProduct().getArea().getName()+"");
                    areaId = (int) response.body().getResult().getRes_data().getProduct().getArea().getId();
                    Glide.with(SaveActivity.this)
                            .load(response.body().getResult().getRes_data().getProduct().getImage_medium())
                            .asBitmap()
                            .into(new SimpleTarget<Bitmap>() {
                                @Override
                                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                    imageView.setImageBitmap(resource);
                                }
                            });
                }
            }

            @Override
            public void onFailure(Call<FindProductByConditionResponse> call, Throwable t) {

            }
        });


    }

    @OnClick(R.id.imageView)
    void chooseImageView(){
        initpopwindow();
    }

    private void initpopwindow() {
        menuWindow = new SelectPicPopupWindow(SaveActivity.this, itemsOnClick);
        menuWindow.showAtLocation(view.findViewById(R.id.imageView),
                Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);


    }
    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            menuWindow.dismiss();
            switch (v.getId()) {
                // 拍照
                case R.id.takePhotoBtn:
                    //进入拍照
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//action is capture
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, getTempImageFileUri());
                    startActivityForResult(intent, TAKE_BIG_PICTURE);
                    break;
                // 相册选择图片
                case R.id.pickPhotoBtn:
                    startPhoto();//相册
                    break;
                default:
                    break;
            }
        }
    };

    /*进入相册*/
    private void startPhoto (){
        Intent intent = new Intent("android.intent.action.PICK");
        intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", AVATAR_SIZE);
        intent.putExtra("outputY", AVATAR_SIZE);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getTempImageFileUri());
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        startActivityForResult(intent, CHOOSE_BIG_PICTURE2);
    }

    private Bitmap decodeUriAsBitmap(Uri uri){
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(SaveActivity.this.getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }

    /**
     * 拍照后裁剪图片方法实现
     *
     */
    public void startPhotoZoom(Uri uri, int outputX, int outputY, int requestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 获得临时文件存放地址的Uri(此地址存在与否并不代表该文件一定存在哦).
     *
     * @return 正常获得uri则返回，否则返回null
     */
    private Uri getTempImageFileUri()
    {
        String tempImageFileLocation = getTempImageFileLocation();
        if(tempImageFileLocation != null)
        {
            return Uri.parse("file://" + tempImageFileLocation);
        }
        return null;
    }
    /**
     * 获得临时文件存放地址(此地址存在与否并不代表该文件一定存在哦).
     *
     * @return 正常获得则返回，否则返回null
     */
    private String getTempImageFileLocation()
    {
        try
        {
            if(__tempImageFileLocation == null)
            {
                String avatarTempDirStr = AvatarHelper.getUserAvatarSavedDir(SaveActivity.this);
                File avatarTempDir = new File(avatarTempDirStr);
                if(avatarTempDir != null)
                {
                    // 目录不存在则新建之
                    if(!avatarTempDir.exists())
                        avatarTempDir.mkdirs();
                    // 临时文件名
                    __tempImageFileLocation = avatarTempDir.getAbsolutePath()+"/"+"local_avatar_temp.jpg";
                }
            }
        }
        catch (Exception e)
        {
            Log.e(TAG, "【ChangeAvatar】读取本地用户的头像临时存储路径时出错了，" + e.getMessage(), e);
        }

        Log.d(TAG, "【ChangeAvatar】正在获取本地用户的头像临时存储路径：" + __tempImageFileLocation);

        return __tempImageFileLocation;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final Uri imagePhotoUri = getTempImageFileUri();
        switch (requestCode) {
            case TAKE_BIG_PICTURE:// 拍照完成则新拍的文件将会存放于指定的位置（即uri、tempImaheLocation所表示的地方）
            {
                if (resultCode == RESULT_OK) {
                    //从相机拍摄保存的Uri中取出图片，调用系统剪裁工具
                    if (imagePhotoUri != null) {
                        startPhotoZoom(imagePhotoUri, AVATAR_SIZE, AVATAR_SIZE, CROP_BIG_PICTURE);
                    } else {
//                        MyToast.showShort(this, "没有得到拍照图片");
                    }
                } else if (resultCode == RESULT_CANCELED) {

//                        MyToast.showShort(this, "取消拍照");

                } else {

//                   MyToast.showShort(this, "拍照失败");

                }
                break;
            }
            //裁切完成后的处理（上传头像）
            case CROP_BIG_PICTURE://from crop_big_picture
            {
                if (resultCode == RESULT_OK) {
                    MyLog.i("裁剪完成");
                    Bitmap bitmap = decodeUriAsBitmap(imagePhotoUri);
                    imageView.setImageBitmap(bitmap);
                }
                break;
            }
            case CHOOSE_BIG_PICTURE2:// 图片选取完成时，其实该图片还有原处，如要裁剪则应把它复制出来一份（以免裁剪时覆盖原图)
            {

                if (resultCode == RESULT_OK) {
                    MyLog.i("选择图片,裁剪完成");
                    Bitmap bitmap = decodeUriAsBitmap(imagePhotoUri);
                    imageView.setImageBitmap(bitmap);
                }
            }
            break;
        }
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SaveActivity.this);
        builder.setMessage("是否保存本次操作")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        String s = productQtyText.getText().toString();
                        int productQty = Integer.parseInt(s);
                        Bitmap bitmapFromView = BitmapUtils.getBitmapFromView(imageView);
                        String bitmapString = BitmapUtils.bitmapToBase64(bitmapFromView);
                        if (product!=null){
                            saveInventroyUtils.insertSaveInventroy(new SaveInventory(res_data.getTheoretical_qty(),
                                    productQty,bitmapString,product.getId(),product.getProduct_name(),location.getText().toString(),areaId));
                        }
                        if (startBypostion){
                            saveInventroyUtils.insertSaveInventroy(new SaveInventory(saveInventory.getTheoretical_qty(),
                                    productQty,bitmapString,saveInventory.getId(),saveInventory.getProduct_name(),locationText.getText().toString(),areaId));
                        }


                        finish();
                    }
                }).show();

        return super.onOptionsItemSelected(item);
    }


}
