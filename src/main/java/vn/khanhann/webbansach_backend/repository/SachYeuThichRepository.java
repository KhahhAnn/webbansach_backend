package vn.khanhann.webbansach_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import vn.khanhann.webbansach_backend.entity.SachYeuThich;
@RepositoryRestResource(path = "sach-yeu-thich")

public interface SachYeuThichRepository extends JpaRepository<SachYeuThich, Integer> {
}
