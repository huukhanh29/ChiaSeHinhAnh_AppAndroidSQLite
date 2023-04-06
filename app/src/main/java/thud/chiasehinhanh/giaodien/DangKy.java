package thud.chiasehinhanh.giaodien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import thud.chiasehinhanh.MainActivity;
import thud.chiasehinhanh.R;
import thud.chiasehinhanh.dulieu.NguoiDung;
import thud.chiasehinhanh.xuly.NguoiDungAdapter;

public class DangKy extends AppCompatActivity {

    TextInputLayout layoutTaiKhoan, layoutMatKhau, layoutNhapLaiMatKhau;
    TextInputEditText edtTenDangNhap, edtMatKhau, edtNhapLaiMatKhau;
    NguoiDungAdapter nguoiDungAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangky);

        // Khởi tạo đối tượng NguoiDungAdapter
        nguoiDungAdapter = new NguoiDungAdapter(this);

        // Ánh xạ các thành phần giao diện
        layoutTaiKhoan = findViewById(R.id.layout_taikhoan);
        layoutMatKhau = findViewById(R.id.layout_matkhau);
        layoutNhapLaiMatKhau = findViewById(R.id.layout_xacnhanmatkhau);
        edtTenDangNhap = findViewById(R.id.edt_taikhoan);
        edtMatKhau = findViewById(R.id.edt_matkhau);
        edtNhapLaiMatKhau = findViewById(R.id.edt_xacnhanmatkhau);
    }
    public void KiemTraDangKy(View view){
        // Lấy thông tin người dùng nhập vào
        String tenDangNhap = edtTenDangNhap.getText().toString().trim();
        String matKhau = edtMatKhau.getText().toString().trim();
        String nhapLaiMatKhau = edtNhapLaiMatKhau.getText().toString().trim();

        // Kiểm tra thông tin nhập vào có hợp lệ hay không
        if (tenDangNhap.isEmpty()) {
            layoutTaiKhoan.setError("Vui lòng nhập tên đăng nhập");
            edtTenDangNhap.requestFocus();
            return;
        } else if (nguoiDungAdapter.kiemTraDangKy(tenDangNhap)) {
            layoutTaiKhoan.setError("Tên đăng nhập đã tồn tại");
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

        if (nhapLaiMatKhau.isEmpty()) {
            layoutNhapLaiMatKhau.setError("Vui lòng nhập lại mật khẩu");
            edtNhapLaiMatKhau.requestFocus();
            return;
        } else if (!matKhau.equals(nhapLaiMatKhau)) {
            layoutNhapLaiMatKhau.setError("Mật khẩu không khớp");
            edtNhapLaiMatKhau.requestFocus();
            return;
        } else {
            layoutNhapLaiMatKhau.setError(null);
        }

        // Nếu thôngtin nhập vào hợp lệ, tiến hành đăng ký tài khoản
        if (!tenDangNhap.isEmpty() && !matKhau.isEmpty() && !nhapLaiMatKhau.isEmpty()
                && !nguoiDungAdapter.kiemTraDangKy(tenDangNhap) && matKhau.equals(nhapLaiMatKhau)) {
            NguoiDung nguoiDung = new NguoiDung(tenDangNhap, matKhau);
            nguoiDungAdapter.insertNguoiDung(nguoiDung);
            Intent intent = new Intent(DangKy.this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(DangKy.this, "Đăng ký tài khoản thành công", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

}
