
package vn.khanhann.webbansach_backend.security;

public class Endpoints {
    public static  final String front_end_host = "http://localhost:3000";
    public static  final String[] PUBLIC_GET_ENDPOINTS = {
            "/sach",
            "/sach/**",
            "/hinh-anh",
            "/hinh-anh/**",
            "/nguoi-dung/search/existsByTenDangNhap",
            "/nguoi-dung/search/existsByEmail",
            "/tai-khoan/kich-hoat"
    };
    public static  final String[] PUBLIC_POST_ENDPOINTS = {
            "/tai-khoan/dang-ky",
    };
    public static  final String[] ADMIN_GET_ENDPOINTS = {
            "/nguoi-dung",
            "/nguoi-dung/**"
    };
}
