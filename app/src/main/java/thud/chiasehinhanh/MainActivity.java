package thud.chiasehinhanh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import thud.chiasehinhanh.giaodien.DangKy;
import thud.chiasehinhanh.giaodien.DangNhap;

public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mnu_dangnhap) {
            dangXuat();
            return true;
        } else if (id == R.id.mnu_dangky) {
            Intent intent = new Intent(MainActivity.this, DangKy.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void dangXuat() {
        // Xoá thông tin đăng nhập trong SharedPreferences
        SharedPreferences preferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("tenDangNhap");
        editor.remove("matKhau");
        editor.apply();

        // Chuyển về màn hình đăng nhập
        Intent intent = new Intent(MainActivity.this, DangNhap.class);
        startActivity(intent);
        finish();
    }
}
