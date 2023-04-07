package thud.chiasehinhanh.giaodien;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import thud.chiasehinhanh.R;
import thud.chiasehinhanh.dulieu.BaiViet;
import thud.chiasehinhanh.xuly.BaiVietAdapter;

public class ThemBaiViet extends XuLyBaiViet {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btnXoa.setVisibility(View.GONE);
    }
    public void LuuBaiViet(View view) {
        String nDung = edtNoidung.getText().toString().trim();

        if (nDung.length() < 1) {
            edtNoidung.requestFocus();
            edtNoidung.selectAll();
            layoutNoidung.setError("Nội dung bài viết không được rỗng!");
            return;
        } else {
            layoutNoidung.setError(null);
        }

        if (bitmap == null) {
            layoutHinh.setError("Chưa chọn hình!");
            return;
        } else {
            layoutHinh.setError(null);
        }

        BaiVietAdapter baiVietAdapter = new BaiVietAdapter(this);

        SharedPreferences sharedPref = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        int idNguoiDung = sharedPref.getInt("idNguoiDung", -1);
        // Lưu tên ảnh vào cơ sở dữ liệu
        String tenAnh = "anh_" + System.currentTimeMillis() + ".jpg";
        BaiViet baiviet = new BaiViet(nDung, tenAnh, idNguoiDung);
        baiVietAdapter.insertBaiViet(baiviet);

        // Lưu ảnh vào thư mục
        try {
            FileOutputStream fos = openFileOutput(tenAnh, Context.MODE_PRIVATE);
            InputStream is = new ByteArrayInputStream(bitmapToByteArray(bitmap));
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
            fos.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(this, TrangChu.class);
        startActivity(intent);
        Toast.makeText(this, "Lưu bài viết thành công!", Toast.LENGTH_LONG).show();
        finish();
    }

//    EditText edtNoidung;
//    TextInputLayout layoutNoidung, layoutHinh;
//    ImageView imgHinh;
//    static Bitmap bitmap = null;
//
//    ActivityResultLauncher<Intent> runCamera;
//    ActivityResultLauncher<Intent> runGallery;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.baiviet);
//        Button btnXoa = findViewById(R.id.btn_xoa);
//        btnXoa.setVisibility(View.GONE);
//        edtNoidung = findViewById(R.id.edt_ndbaiviet);
//        imgHinh = findViewById(R.id.img_hinhanh);
//        layoutNoidung = findViewById(R.id.layout_ndbaiviet);
//        layoutHinh = findViewById(R.id.layout_hinhanh);
//
//        runCamera = registerForActivityResult(new
//                        ActivityResultContracts.StartActivityForResult(),
//                new ActivityResultCallback<ActivityResult>() {
//                    @Override
//                    public void onActivityResult(ActivityResult result) {
//                        if (result.getResultCode() == Activity.RESULT_OK) {
//                            bitmap = (Bitmap)
//                                    result.getData().getExtras().get("data");
//                            imgHinh.setImageBitmap(bitmap);
//                        } else
//                            Toast.makeText(ThemBaiViet.this,
//                                    "Lỗi chụp hình!", Toast.LENGTH_LONG).show();
//                    }
//                });
//        runGallery = registerForActivityResult(
//                new ActivityResultContracts.StartActivityForResult(),
//                new ActivityResultCallback<ActivityResult>() {
//                    @Override
//                    public void onActivityResult(ActivityResult result) {
//                        if (result.getResultCode() == Activity.RESULT_OK) {
//                            if (result.getData() != null) {
//                                Uri contentURI = result.getData().getData();
//                                try {
//                                    bitmap = MediaStore.Images.Media.
//                                            getBitmap(getContentResolver(),
//                                                    contentURI);
//                                    imgHinh.setImageBitmap(bitmap);
//                                } catch (IOException e) {
//                                }
//                            }
//                        } else
//                            Toast.makeText(ThemBaiViet.this, "Lỗi chọn hình!",
//                                    Toast.LENGTH_LONG).show();
//                    }
//                });
//    }
//
//    public void HinhAnh(View view) {
//        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
//        pictureDialog.setTitle("Thực hiện");
//        String[] pictureDialogItems = {
//                " Chọn hình có sẵn",
//                " Chụp hình mới"};
//        pictureDialog.setItems(pictureDialogItems,
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        switch (which) {
//                            case 0:
//                                Intent galleryIntent = new Intent(
//                                        Intent.ACTION_PICK,
//                                        android.provider.MediaStore.Images.
//                                                Media.EXTERNAL_CONTENT_URI);
//                                runGallery.launch(galleryIntent);
//                                break;
//                            case 1:
//                                Intent intent = new Intent(
//                                        android.provider.MediaStore.
//                                                ACTION_IMAGE_CAPTURE);
//                                runCamera.launch(intent);
//                                break;
//                        }
//                    }
//                });
//        pictureDialog.show();
//    }
//    public void LuuBaiViet(View view) {
//        String nDung = edtNoidung.getText().toString().trim();
//
//        if (nDung.length() < 1) {
//            edtNoidung.requestFocus();
//            edtNoidung.selectAll();
//            layoutNoidung.setError("Nội dung bài viết không được rỗng!");
//            return;
//        } else {
//            layoutNoidung.setError(null);
//        }
//
//        if (bitmap == null) {
//            layoutHinh.setError("Chưa chọn hình!");
//            return;
//        } else {
//            layoutHinh.setError(null);
//        }
//
//        BaiVietAdapter baiVietAdapter = new BaiVietAdapter(this);
//
//        SharedPreferences sharedPref = getSharedPreferences("loginPrefs", MODE_PRIVATE);
//        int idNguoiDung = sharedPref.getInt("idNguoiDung", -1);
//        // Lưu tên ảnh vào cơ sở dữ liệu
//        String tenAnh = "anh_" + System.currentTimeMillis() + ".jpg";
//        BaiViet baiviet = new BaiViet(nDung, tenAnh, idNguoiDung);
//        baiVietAdapter.insertBaiViet(baiviet);
//
//        // Lưu ảnh vào thư mục
//        try {
//            FileOutputStream fos = openFileOutput(tenAnh, Context.MODE_PRIVATE);
//            InputStream is = new ByteArrayInputStream(bitmapToByteArray(bitmap));
//            byte[] buffer = new byte[1024];
//            int length;
//            while ((length = is.read(buffer)) > 0) {
//                fos.write(buffer, 0, length);
//            }
//            fos.close();
//            is.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Intent intent = new Intent(this, TrangChu.class);
//        startActivity(intent);
//        Toast.makeText(this, "Lưu bài viết thành công!", Toast.LENGTH_LONG).show();
//        finish();
//    }
//
//    private byte[] bitmapToByteArray(Bitmap bitmap) {
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//        return stream.toByteArray();
//    }
//    public void DongActivity(View view) {
//        finish();
//    }
//    @Override
//    public void onBackPressed() {
//        Intent intent = new Intent(this, TrangChu.class);
//        startActivity(intent);
//        finish();
//    }
}