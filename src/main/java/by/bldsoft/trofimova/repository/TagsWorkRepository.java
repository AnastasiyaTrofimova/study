package by.bldsoft.trofimova.repository;

import by.bldsoft.trofimova.entity.TagWork;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagsWorkRepository extends JpaRepository<TagWork, Long> {
    @EntityGraph(value = "TagHome.message", type = EntityGraph.EntityGraphType.FETCH)
    List <TagWork> findAll();

}
