package vn.khanhann.webbansach_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.khanhann.webbansach_backend.entity.NguoiDung;
import vn.khanhann.webbansach_backend.service.TaiKhoanService;

@RestController
@RequestMapping("/tai-khoan")
public class TaiKhoanController {
    @Autowired
    private TaiKhoanService taiKhoanService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/dang-ky")
    public ResponseEntity<?> danKyNguoiDung(@Validated @RequestBody NguoiDung nguoiDung) {
        ResponseEntity<?> responseEntity = this.taiKhoanService.dangKyNguoiDung(nguoiDung);
        return responseEntity;
    }
}
