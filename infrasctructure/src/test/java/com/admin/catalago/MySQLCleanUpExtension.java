package com.admin.catalago;

import com.admin.catalago.infrasctructure.category.persistense.CategoryRepository;
import com.admin.catalago.infrasctructure.castmember.persistence.CastMemberRepository;
import com.admin.catalago.infrasctructure.genre.persistence.GenreRepository;
import com.admin.catalago.infrasctructure.video.persistence.VideoRepository;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;
import java.util.List;

public class MySQLCleanUpExtension implements BeforeEachCallback {
    @Override
    public void beforeEach(final ExtensionContext context) {
        final var appContext = SpringExtension.getApplicationContext(context);

        cleanUp(List.of(
                appContext.getBean(VideoRepository.class),
                appContext.getBean(CastMemberRepository.class),
                appContext.getBean(GenreRepository.class),
                appContext.getBean(CategoryRepository.class)
        ));
    }

    private void cleanUp(final Collection<CrudRepository> repositories){
        repositories.forEach(CrudRepository::deleteAll);
    }
}