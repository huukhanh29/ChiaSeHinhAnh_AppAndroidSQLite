package thud.chiasehinhanh.xuly;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;

import thud.chiasehinhanh.R;
import thud.chiasehinhanh.dulieu.BaiViet;

public class CustomGridViewAdapter extends BaseAdapter {

    private Context context;
    private List<BaiViet> baiVietList;
    private NguoiDungAdapter nguoiDungAdapter;

    public CustomGridViewAdapter(Context context, List<BaiViet> baiVietList) {
        this.context = context;
        this.baiVietList = baiVietList;
        this.nguoiDungAdapter = new NguoiDungAdapter(context);
    }

    @Override
    public int getCount() {
        return baiVietList.size();
    }

    @Override
    public Object getItem(int position) {
        return baiVietList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_gridview, null);
            holder = new ViewHolder();
            holder.imageView = view.findViewById(R.id.imageView);
            holder.textNoiDung = view.findViewById(R.id.textNoiDung);
            holder.textHoTen = view.findViewById(R.id.textHoten);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        BaiViet baiViet = baiVietList.get(position);
        String tenNguoiDung = nguoiDungAdapter.getTenNguoiDungById(baiViet.getIdNguoiDung());
        holder.textNoiDung.setText(baiViet.getNoiDung());
        holder.textHoTen.setText(tenNguoiDung);
        Picasso.get().load(context.getFileStreamPath(baiViet.getImage())).into(holder.imageView);
        return view;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView textNoiDung, textHoTen;
    }
}


