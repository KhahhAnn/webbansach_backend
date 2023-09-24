package vn.khanhann.webbansach_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "don_hang")
public class DonHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_don_hang")
    private int maDonHang;

    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "dia_chi_mua_hang", length = 512)
    private String diaChiMuaHang;

    @Column(name = "dia_chi_nhan_hang", length = 512)
    private String diaChiNhanHang;

    @Column(name = "tong_tien_san_pham")
    private double tonTienSanPham;

    @Column(name = "chi_phi_giao_hang")
    private double chiPhiGiaoHang;

    @Column(name = "chi_phi_thanh_toan")
    private double chiPhiThanhToan;

    @Column(name = "tong_tien")
    private double tongTien;

    @OneToMany(mappedBy = "donHang", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ChiTietDonHang> danhSachChiTietDonHang;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_nguoi_dung", nullable = false)
    private NguoiDung nguoiDung;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_hinh_thuc_thanh_toan")
    private HinhThucThanhToan hinhThucThanhToan;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_hinh_thuc_giao_hang")
    private HinhThucGiaoHang hinhThucGiaoHang;
}
