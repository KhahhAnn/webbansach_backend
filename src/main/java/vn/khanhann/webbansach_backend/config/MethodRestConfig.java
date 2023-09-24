package vn.khanhann.webbansach_backend.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import vn.khanhann.webbansach_backend.entity.NguoiDung;
import vn.khanhann.webbansach_backend.entity.TheLoai;

@Configuration
public class MethodRestConfig implements RepositoryRestConfigurer {
    private String url = "http://localhost:8080";
    @Autowired
    private EntityManager entityManager;
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(
                entityManager.getMetamodel()
                        .getEntities()
                        .stream().map(Type::getJavaType)
                        .toArray(Class[]::new)
        );

        HttpMethod[] chanCacPhuongThuc = {
                HttpMethod.DELETE,
                HttpMethod.POST,
                HttpMethod.PATCH,
                HttpMethod.PUT
        };

        HttpMethod[] chanDelete = {
                HttpMethod.DELETE,
                HttpMethod.POST,
                HttpMethod.PATCH,
                HttpMethod.PUT
        };
        blockHttpMethods(TheLoai.class, config, chanCacPhuongThuc);
        blockHttpMethods(NguoiDung.class, config, chanDelete);

    }
    private void blockHttpMethods(Class c, RepositoryRestConfiguration configuration, HttpMethod[] methods) {
        configuration.getExposureConfiguration()
                .forDomainType(c)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable((methods)))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(methods));
    }
}
