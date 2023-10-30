package vn.khanhann.webbansach_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nguoi_dung")
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_nguoi_dung")
    private int maNguoiDung;

    @Column(name = "ho_dem", length = 256)
    private String hoDem;

    @Column(name = "ten", length = 256)
    private String ten;

    @Column(name = "ten_dang_nhap", length = 256)
    private String tenDangNhap;

    @Column(name = "mat_khau", length = 512)
    private String matKhau;

    @Column(name = "gioi_tinh")
    private Character gioiTinh;

    @Column(name = "email", length = 256)
    private String email;

    @Column(name = "so_dien_thoai", length = 256)
    private String soDienThoai;

    @Column(name = "dia_chi_mua_hang", length = 256)
    private String diaChiMuaHang;

    @Column(name = "dia_chi_giao_hang", length = 256)
    private String diaChiGiaoHang;

    @Column(name = "da_kich_hoat")
    private boolean daKichHoat;

    @Column(name = "ma_kich_hoat")
    private String maKichHoat;


    @OneToMany(mappedBy = "nguoiDung", fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<SuDanhGia> danhSachSuDanhGia;

    @OneToMany(mappedBy = "nguoiDung", fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<SachYeuThich> danhSachSachYeuThich;

    @OneToMany(mappedBy = "nguoiDung", fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<DonHang> danhSachDonHang;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "nguoidung_quyen",
            joinColumns = @JoinColumn(name = "ma_nguoi_dung"),
            inverseJoinColumns = @JoinColumn(name = "ma_quyen")
    )
    private List<Quyen> danhSachQuyen;
}
