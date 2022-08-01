package com.example.demo.repo;

import com.example.demo.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByOrderByModifiedAtDesc();
    List<Board> findByWriter(String writer);

    @Query(value = "SELECT b FROM Board b WHERE b.title LIKE %:searchWord% OR b.content LIKE %:searchWord% ORDER BY b.modifiedAt DESC")
    List<Board> findAllSearch(String searchWord);

    Page<Board> findAll(Pageable pageable);
}
