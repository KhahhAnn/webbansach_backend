package vn.khanhann.webbansach_backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.khanhann.webbansach_backend.entity.NguoiDung;
import vn.khanhann.webbansach_backend.entity.ThongBao;
import vn.khanhann.webbansach_backend.repository.NguoiDungRepository;
import vn.khanhann.webbansach_backend.service.EmailService;

import java.util.UUID;

@Service
public class TaiKhoanService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    private EmailService emailService;

    public ResponseEntity<?> dangKyNguoiDung(NguoiDung nguoiDung) {
        if (this.nguoiDungRepository.existsByTenDangNhap(nguoiDung.getTenDangNhap())) {
            return ResponseEntity.badRequest().body(new ThongBao("Tên đăng nhập đã tồn tại"));
        }
        if (this.nguoiDungRepository.existsByEmail(nguoiDung.getEmail())) {
            return ResponseEntity.badRequest().body(new ThongBao("Email đã tồn tại"));
        }
        String encryptPassword = passwordEncoder.encode(nguoiDung.getMatKhau());
        nguoiDung.setMatKhau(encryptPassword);
        nguoiDung.setMaKichHoat(taoMaKichHoat());
        nguoiDung.setDaKichHoat(false);


        NguoiDung nguoiDungMoi =this.nguoiDungRepository.saveAndFlush(nguoiDung);

        guiMailKichHoat(nguoiDung.getEmail(), nguoiDung.getMaKichHoat());
        return ResponseEntity.ok("Đăng ký thành công");
    }

    private String taoMaKichHoat() {
        return UUID.randomUUID().toString();
    }

    private void guiMailKichHoat(String email, String maKichHoat) {
        String subject = "Kích hoạt tài khoản của bạn tại WebBanSach";
        String text = "Vui lòng sử dụng đoạn mã sau để kích hoạt tài khoản < " + email + ">:<html><body><br/><h1>"+ maKichHoat +"</h1></body></html>";
        text+= "<br/> Click vào link để kích hoạt tài khoản: ";
        String url = "http://localhost:3000/kich-hoat/"+ email + "/" + maKichHoat;
        text+= ("<br/> <a href="+url+">"+ url +"</a>");

        this.emailService.sendEmail("khanhanbui2003@gmail.com", email, subject, text);
    }

    public ResponseEntity<?> kichHoatTaiKhoan(String email, String maKichHoat) {
       NguoiDung nguoiDung = this.nguoiDungRepository.findByEmail(email);
       if(nguoiDung == null) {
           return ResponseEntity.badRequest().body(new ThongBao("Người dùng không tồn tại"));
       }
       if(nguoiDung.isDaKichHoat()) {
           return ResponseEntity.badRequest().body(new ThongBao("Tài khoản đã được kích hoạt!"));
       }
       if(maKichHoat.equals(nguoiDung.getMaKichHoat())) {
           nguoiDung.setDaKichHoat(true);
           nguoiDungRepository.save(nguoiDung);
           return ResponseEntity.ok("Kích hoạt thành công!");
       } else {
           return ResponseEntity.badRequest().body(new ThongBao("Mã kích hoạt không chính xác!"));
       }
    }
}
