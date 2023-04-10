package com.travel.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.model.Comment;
import com.travel.model.Trip;


public interface CommentDao extends JpaRepository<Comment, Integer> {
	List<Comment> findByTrip(Trip trip);
}
