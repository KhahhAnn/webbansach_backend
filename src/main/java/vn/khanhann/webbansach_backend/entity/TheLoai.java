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
@Table(name = "the_loai")
public class TheLoai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_the_loai")
    private int maTheLoai;

    @Column(name = "ten_the_loai", length = 256)
    private String tenTheLoai;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "sach_theloai",
            joinColumns = @JoinColumn(name = "ma_the_loai"),
            inverseJoinColumns = @JoinColumn(name = "ma_sach")
    )
    private List<Sach> danhSachQuyenSach;
}
