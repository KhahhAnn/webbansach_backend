package vn.khanhann.webbansach_backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.khanhann.webbansach_backend.entity.NguoiDung;
import vn.khanhann.webbansach_backend.entity.Quyen;
import vn.khanhann.webbansach_backend.repository.NguoiDungRepository;
import vn.khanhann.webbansach_backend.repository.QuyenRepository;
import vn.khanhann.webbansach_backend.service.UserService;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private NguoiDungRepository nguoiDungRepository;
    private QuyenRepository quyenRepository;

    @Autowired
    public UserServiceImpl(NguoiDungRepository nguoiDungRepository, QuyenRepository quyenRepository) {
        this.nguoiDungRepository = nguoiDungRepository;
        this.quyenRepository = quyenRepository;
    }

    @Override
    public NguoiDung findByUserName(String tenDangNhap) {
        return this.nguoiDungRepository.findByTenDangNhap(tenDangNhap);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        NguoiDung nguoiDung = this.nguoiDungRepository.findByTenDangNhap(username);
        if(nguoiDung == null) {
            throw new UsernameNotFoundException("Tài khoản không tồn tại");
        }
        User user = new User(nguoiDung.getTenDangNhap(), nguoiDung.getMatKhau(), rolesToAuthorities(nguoiDung.getDanhSachQuyen()));
        return user;
    }
    private Collection<? extends GrantedAuthority> rolesToAuthorities(Collection<Quyen> quyens) {
        return quyens.stream().map(role -> new SimpleGrantedAuthority(role.getTenQuyen())).collect(Collectors.toList());
    }
}
