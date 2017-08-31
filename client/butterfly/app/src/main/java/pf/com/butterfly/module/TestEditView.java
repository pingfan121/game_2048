package pf.com.butterfly.module;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Hashtable;

import pf.com.butterfly.MainActivity;
import pf.com.butterfly.R;

/**
 * Created by admin on 2017/2/26.
 */
public class TestEditView
{
    private static EditText et_img=null;
    public static Button btn_close=null;
    public static Button btn_tupian=null;
    private static View father;

    public static Hashtable<String,Bitmap> bitmaps =new Hashtable<String, Bitmap>();

    private static final int TEV_PICK=100;

    public static void Init()
    {
        View view= MainActivity.main.findViewById(R.id.layer1);
        father=view.findViewById(R.id.editView);

        et_img=(EditText)father.findViewById(R.id.et_test);

        btn_close=(Button) father.findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Hide();
            }
        });

        btn_tupian=(Button) father.findViewById(R.id.btn_tupian);
        btn_tupian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               getImage();
            }
        });

    }

    public static void Hide()
    {
        father.setVisibility(View.INVISIBLE);
    }
    public static void Show()
    {
        father.setVisibility(View.VISIBLE);
    }

    /**
     * 图文详情页面选择图片
     */
    public static void getImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        MainActivity.main.startActivityForResult(intent, TEV_PICK);
    }

    // 因为调用了Camera和Gally所以要判断他们各自的返回情况,他们启动时是这样的startActivityForResult
    public static void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode != Activity.RESULT_OK)
            return;

        switch (requestCode)
        {
            case TEV_PICK:
            {
                try {
                    //通知编辑
                    Uri uri = data.getData();
                    Bitmap bitmap = getOriginalBitmap(uri);
                    SpannableString ss = getBitmapMime(bitmap, uri);
                    insertIntoEditText(ss);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }



    /**
     * EditText中可以接收的图片(要转化为SpannableString)
     *
     * @param pic
     * @param uri
     * @return SpannableString
     */
    private static SpannableString getBitmapMime(Bitmap pic, Uri uri) {
        int imgWidth = pic.getWidth();
        int imgHeight = pic.getHeight();
        // 只对大尺寸图片进行下面的压缩，小尺寸图片使用原图
        if (imgWidth >= et_img.getWidth()) {
            float scale = (float) et_img.getWidth() / imgWidth;
            Matrix mx = new Matrix();
            mx.setScale(scale, scale);
            pic = Bitmap.createBitmap(pic, 0, 0, imgWidth, imgHeight, mx, true);
        }
        String smile = uri.getPath();
        SpannableString ss = new SpannableString(smile);
        ImageSpan span = new ImageSpan(MainActivity.main, pic);
        ss.setSpan(span, 0, smile.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }
    private static void insertIntoEditText(SpannableString ss) {
        // 先获取Edittext中原有的内容
        Editable et = et_img.getText();
        int start = et_img.getSelectionStart();
        // 设置ss要添加的位置
        et.insert(start,"\n");

        et.insert(start+1, ss);

        et.insert(start+1+ss.length(), "\n");

        // 把et添加到Edittext中
        et_img.setText(et);
        // 设置Edittext光标在最后显示
        et_img.setSelection(start + ss.length()+2);
    }
    private static Bitmap getOriginalBitmap(Uri photoUri) {
        if (photoUri == null) {
            return null;
        }
        Bitmap bitmap = null;
        try {
            ContentResolver conReslv = MainActivity.main.getContentResolver();
            // 得到选择图片的Bitmap对象
            bitmap = MediaStore.Images.Media.getBitmap(conReslv, photoUri);
        } catch (Exception e) {
            Log.e("测试", "Media.getBitmap failed", e);
        }
        return bitmap;
    }
}
