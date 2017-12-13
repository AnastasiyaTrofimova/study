package by.bldsoft.trofimova.repository;

import by.bldsoft.trofimova.entity.TagWork;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagsWorkRepository extends JpaRepository<TagWork, Long> {

    List <TagWork> findAll();

}
