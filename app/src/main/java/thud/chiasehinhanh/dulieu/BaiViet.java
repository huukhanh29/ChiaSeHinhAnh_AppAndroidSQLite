package thud.chiasehinhanh.dulieu;

public class BaiViet {
    private int id;
    private String noiDung;
    private String image;
    private int idNguoiDung;

    public BaiViet() {}

    public BaiViet(String noiDung, String image, int idNguoiDung) {
        this.noiDung = noiDung;
        this.image = image;
        this.idNguoiDung = idNguoiDung;
    }
    public BaiViet(int id, String noiDung, String image, int idNguoiDung) {
        this.id =id;
        this.noiDung = noiDung;
        this.image = image;
        this.idNguoiDung = idNguoiDung;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(int idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }
}
