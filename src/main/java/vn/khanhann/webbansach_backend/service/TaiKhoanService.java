package vn.khanhann.webbansach_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.khanhann.webbansach_backend.entity.NguoiDung;
import vn.khanhann.webbansach_backend.entity.ThongBao;
import vn.khanhann.webbansach_backend.repository.NguoiDungRepository;

@Service
public class TaiKhoanService {
    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    public ResponseEntity<?> dangKyNguoiDung(NguoiDung nguoiDung) {
        if (this.nguoiDungRepository.existsByTenDangNhap(nguoiDung.getTenDangNhap())) {
            return ResponseEntity.badRequest().body(new ThongBao("Tên đăng nhập đã tồn tại"));
        }
        if (this.nguoiDungRepository.existsByEmail(nguoiDung.getEmail())) {
            return ResponseEntity.badRequest().body(new ThongBao("Email đã tồn tại"));
        }
        NguoiDung nguoiDungMoi =this.nguoiDungRepository.saveAndFlush(nguoiDung);
        return ResponseEntity.ok("Đăng ký thành công");
    }
}
