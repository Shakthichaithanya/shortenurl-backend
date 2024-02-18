package com.shortenurl.shortenurl.repository;

import com.shortenurl.shortenurl.model.Shortenurl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ShortenurlRepository extends JpaRepository<Shortenurl, UUID> {
}
