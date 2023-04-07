package thud.chiasehinhanh.giaodien;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import thud.chiasehinhanh.R;
import thud.chiasehinhanh.xuly.HashPassword;
import thud.chiasehinhanh.xuly.NguoiDungAdapter;

public class DangNhap extends AppCompatActivity {
    TextInputLayout layoutTaiKhoan, layoutMatKhau;
    TextInputEditText edtTenDangNhap, edtMatKhau;
    NguoiDungAdapter nguoiDungAdapter;
    String tenDangNhap, matKhau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangnhap);

        // Khởi tạo đối tượng NguoiDungAdapter
        nguoiDungAdapter = new NguoiDungAdapter(this);

        // Ánh xạ các thành phần giao diện
        layoutTaiKhoan = findViewById(R.id.layout_taikhoan);
        layoutMatKhau = findViewById(R.id.layout_matkhau);
        edtTenDangNhap = findViewById(R.id.edt_taikhoan);
        edtMatKhau = findViewById(R.id.edt_matkhau);

        // Lấy thông tin tài khoản đăng nhập từ SharedPreferences
        SharedPreferences preferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        tenDangNhap = preferences.getString("tenDangNhap", null);
        matKhau = preferences.getString("matKhau", null);

        // Kiểm tra nếu đã lưu thông tin đăng nhập trong SharedPreferences, tự động đăng nhập
        if (tenDangNhap != null && matKhau != null) {
            if (nguoiDungAdapter.kiemTraDangNhap(tenDangNhap, matKhau)) {
                xacThucDangNhap();
            } else {
                // Xoá thông tin đăng nhập nếu không đăng nhập thành công
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("tenDangNhap");
                editor.remove("matKhau");
                editor.apply();
            }
        }
    }
    public void KiemTraDangNhap(View view){
        tenDangNhap = edtTenDangNhap.getText().toString().trim();
        matKhau = edtMatKhau.getText().toString().trim();
        if (tenDangNhap.isEmpty()) {
            layoutTaiKhoan.setError("Vui lòng nhập tên đăng nhập");
            edtTenDangNhap.requestFocus();
            return;
        } else {
            layoutTaiKhoan.setError(null);
        }

        if (matKhau.isEmpty()) {
            layoutMatKhau.setError("Vui lòng nhập mật khẩu");
            edtMatKhau.requestFocus();
            return;
        } else {
            layoutMatKhau.setError(null);
        }
        // Kiểm tra đăng nhập
        if (nguoiDungAdapter.kiemTraDangNhap(tenDangNhap, HashPassword.hash(matKhau))) {
            // Nếu đăng nhập thành công, lưu thông tin đăng nhập vào SharedPreferences
            int id = nguoiDungAdapter.getIdNguoiDung(tenDangNhap);
            SharedPreferences preferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("tenDangNhap", tenDangNhap);
            editor.putString("matKhau", HashPassword.hash(matKhau));
            editor.putInt("idNguoiDung", id);
            editor.apply();
            xacThucDangNhap();
        } else {
            Toast.makeText(DangNhap.this,
                    "Tên đăng nhập hoặc mật khẩu không chính xác",
                    Toast.LENGTH_SHORT).show();
        }
    }
    private void xacThucDangNhap() {
        // Chuyển sang màn hình chính
        Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(DangNhap.this, TrangChu.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, TrangChu.class);
        startActivity(intent);
        finish();
    }
}