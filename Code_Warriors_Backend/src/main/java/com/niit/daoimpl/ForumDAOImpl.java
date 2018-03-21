package com.niit.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.dao.ForumDAO;
import com.niit.model.Blog;
import com.niit.model.Forum;

@Repository
public class ForumDAOImpl implements ForumDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public boolean addForum(Forum forum) {
		
		try {
			sessionFactory.getCurrentSession().save(forum);
			return true;
		}catch(Exception exception) {
			return false;
		}
	}

	@Transactional
	@Override
	public boolean deleteForum(Forum forum) {

		try {
			sessionFactory.getCurrentSession().delete(forum);
			return true;
		}catch(Exception exception) {
			return false;
		}
	}

	@Transactional
	@Override
	public boolean updateForum(Forum forum) {
		
		try {
			sessionFactory.getCurrentSession().update(forum);
			return true;
		}catch(Exception exception) {
			return false;
		}
	}

	@Override
	public Forum getForum(int forumId) {
		
		try {
			Session session = sessionFactory.openSession();
			Forum forum = session.get(Forum.class, forumId);
			return forum;
		}catch(Exception exception) {
			return null;
		}
	}

	@Override
	public List<Forum> lisForum(String userName) {
		
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			List<Forum> forumList = new ArrayList<Forum>();
			Query query = session.createQuery("FROM Forum where username=:username").setString("username",userName);
			forumList = query.list();
			return forumList;
		} catch (Exception e) {
			return null;
		}
	}

}