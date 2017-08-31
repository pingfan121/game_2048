package pf.com.butterfly.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import pf.com.butterfly.MainActivity;
import pf.com.butterfly.R;
import pf.com.butterfly.module.ShowPhoto;

/**
 * Created by admin on 2017/2/25.
 */
public class PhotoChoose

{
    /*用来标识请求照相功能的activity*/
    private static final int CAMERA_WITH_DATA = 3023;

    /*用来标识请求gallery的activity*/
    private static final int PHOTO_PICKED_WITH_DATA = 3021;

    /*拍照的照片存储位置*/
    private static final File PHOTO_DIR = new File(Environment.getExternalStorageDirectory() + "/DCIM/Camera");

    private static File mCurrentPhotoFile;//照相机拍照得到的图片

    public static void doPickPhotoAction() {
        Context context = MainActivity.main;

        // Wrap our context to inflate list items using correct theme
        final Context dialogContext = new ContextThemeWrapper(context,
                android.R.style.Theme_Light);
        String cancel="返回";
        String[] choices;
        choices = new String[2];
        choices[0] = MainActivity.main.getString(R.string.take_photo);  //拍照
        choices[1] = MainActivity.main.getString(R.string.pick_photo);  //从相册中选择
        final ListAdapter adapter = new ArrayAdapter<String>(dialogContext,
                android.R.layout.simple_list_item_1, choices);

        final AlertDialog.Builder builder = new AlertDialog.Builder(
                dialogContext);
        builder.setTitle(R.string.attachToContact);
        builder.setSingleChoiceItems(adapter, -1,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        switch (which) {
                            case 0:{
                                String status= Environment.getExternalStorageState();
                                if(status.equals(Environment.MEDIA_MOUNTED)){//判断是否有SD卡
                             //       doTakePhoto();// 用户点击了从照相机获取
                                }
                                else{
                                  //  showToast("没有SD卡");
                                    Toast.makeText(MainActivity.main, R.string.nocard,
                                            Toast.LENGTH_LONG).show();
                                }
                                break;

                            }
                            case 1:
                                doPickPhotoFromGallery();// 从相册中去获取
                                break;
                        }
                    }
                });
        builder.setNegativeButton(cancel, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }

        });
        builder.create().show();
    }
    /**
     * 拍照获取图片
     *
     */
    protected void doTakePhoto() {
        try {
            // Launch camera to take photo for selected contact
            PHOTO_DIR.mkdirs();// 创建照片的存储目录
            mCurrentPhotoFile = new File(PHOTO_DIR, getPhotoFileName());// 给新照的照片文件命名
            final Intent intent = getTakePickIntent(mCurrentPhotoFile);
            MainActivity.main.startActivityForResult(intent, CAMERA_WITH_DATA);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(MainActivity.main, R.string.photoPickerNotFoundText,
                    Toast.LENGTH_LONG).show();
        }
    }

    public static Intent getTakePickIntent(File f) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
        return intent;
    }

    /**
     * 用当前时间给取得的图片命名
     *
     */
    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "'IMG'_yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date) + ".jpg";
    }

    // 请求Gallery程序
    protected static void doPickPhotoFromGallery() {
        try {
            // Launch picker to choose photo for selected contact
            final Intent intent = getPhotoPickIntent();
            MainActivity.main.startActivityForResult(intent, PHOTO_PICKED_WITH_DATA);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(MainActivity.main, R.string.photoPickerNotFoundText1,
                    Toast.LENGTH_LONG).show();
        }
    }

    // 封装请求Gallery的intent
    public static Intent getPhotoPickIntent() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
        intent.setType("image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 80);
        intent.putExtra("outputY", 80);
        intent.putExtra("return-data", true);
        return intent;
    }

    // 因为调用了Camera和Gally所以要判断他们各自的返回情况,他们启动时是这样的startActivityForResult
    public static void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode != Activity.RESULT_OK)
            return;

        switch (requestCode) {
            case PHOTO_PICKED_WITH_DATA: {// 调用Gallery返回的

                ContentResolver resolver = MainActivity.main.getContentResolver();
                Bitmap bm=null;
                try {

                    Uri uri = data.getData();
                   // Uri uri=geturi(data);
                    String path = FileOper.getImageAbsolutePath(MainActivity.main,uri);
                    bm = BitmapFactory.decodeStream(resolver.openInputStream(uri));
                    ShowPhoto.getInstance().ShowPhoto(bm,path);

                }catch (IOException e) {

                   // Log.e(TAG,e.toString());

                }


                break;
            }
            case CAMERA_WITH_DATA: {// 照相机程序返回的,再次调用图片剪辑程序去修剪图片
                doCropPhoto(mCurrentPhotoFile);
                break;
            }
        }
    }

    public static String getRealPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = MainActivity.main.getContentResolver().query(contentUri, proj, null, null, null);
        if(cursor.moveToFirst()){;
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }
    private static String getRealPath(Uri fileUrl){
        String fileName = null;
        Uri filePathUri = fileUrl;
        if(fileUrl!= null){
            if (fileUrl.getScheme().toString().compareTo("content")==0)           //content://开头的uri
            {
                String[] proj = { MediaStore.Images.Media.DATA };
                Cursor cursor = MainActivity.main.getContentResolver().query(fileUrl, proj, null, null, null);
                if (cursor != null && cursor.getCount()==1 && cursor.moveToFirst())
                {
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    fileName = cursor.getString(column_index);          //取出文件路径
                    if(!fileName.startsWith("/mnt")){
//检查是否有”/mnt“前缀

                        fileName = "/mnt" + fileName;
                    }
                    cursor.close();
                }
            }else if (fileUrl.getScheme().compareTo("file")==0)         //file:///开头的uri
            {
                fileName = filePathUri.toString();
                fileName = filePathUri.toString().replace("file://", "");
//替换file://
                if(!fileName.startsWith("/mnt")){
//加上"/mnt"头
                    fileName += "/mnt";
                }
            }
        }
        return fileName;
    }

    private static void doCropPhoto(File f) {
        try {
            // 启动gallery去剪辑这个照片
            final Intent intent = getCropImageIntent(Uri.fromFile(f));
            MainActivity.main.startActivityForResult(intent, PHOTO_PICKED_WITH_DATA);
        } catch (Exception e) {
            Toast.makeText(MainActivity.main, R.string.photoPickerNotFoundText,
                    Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Constructs an intent for image cropping. 调用图片剪辑程序
     */
    public static Intent getCropImageIntent(Uri photoUri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(photoUri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 80);
        intent.putExtra("outputY", 80);
        intent.putExtra("return-data", true);
        return intent;
    }
}
