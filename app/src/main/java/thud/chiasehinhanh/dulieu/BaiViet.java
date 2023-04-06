package thud.chiasehinhanh.dulieu;

public class BaiViet {
    private int id;
    private String tieuDe;
    private String noiDung;
    private int idNguoiDung;

    public BaiViet(int id, String tieuDe, String noiDung, int idNguoiDung) {
        this.id = id;
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
        this.idNguoiDung = idNguoiDung;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public int getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(int idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }
}
