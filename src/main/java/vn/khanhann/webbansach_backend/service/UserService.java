package vn.khanhann.webbansach_backend.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import vn.khanhann.webbansach_backend.entity.NguoiDung;

@Service
public interface UserService extends UserDetailsService {
    public NguoiDung findByUserName(String tenDangNhap);
}
