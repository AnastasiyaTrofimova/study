package by.bldsoft.trofimova.repository;

import by.bldsoft.trofimova.entity.TagHome;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagsHomeRepository extends JpaRepository<TagHome, Long> {
    @EntityGraph(value = "TagHome.message", type = EntityGraph.EntityGraphType.FETCH)
    List<TagHome> findAll();

 }
