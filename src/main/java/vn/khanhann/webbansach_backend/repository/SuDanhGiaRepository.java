package vn.khanhann.webbansach_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import vn.khanhann.webbansach_backend.entity.SuDanhGia;
@RepositoryRestResource(path = "su-danh-gia")

public interface SuDanhGiaRepository extends JpaRepository<SuDanhGia, Long> {
}
