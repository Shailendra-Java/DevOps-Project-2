package com.niit.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.dao.BlogDAO;
import com.niit.model.Blog;

@Repository
public class BlogDAOImpl implements BlogDAO{

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	public BlogDAOImpl(SessionFactory sessionFactory) {
		
		super();
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	@Override
	public boolean addBlog(Blog blog) {
		
		try {
			sessionFactory.getCurrentSession().save(blog);
			return true;
		}catch(Exception exception) {
			return false;
		}
	}

	@Transactional
	@Override
	public boolean deleteBlog(Blog blog) {
		
		try {
			sessionFactory.getCurrentSession().delete(blog);
			return true;
		}catch(Exception exception) {
			return false;
		}
	}

	@Transactional
	@Override
	public boolean updateBlog(Blog blog) {

		try {
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}catch(Exception exception) {
			return false;
		}
	}

	@Override
	public Blog getBlog(int blogId) {
		
		try {
			Session session = sessionFactory.openSession();
			Blog blog = session.get(Blog.class, blogId);
			return blog;
		}catch(Exception exception) {
			return null;
		}
	}

	@Transactional
	@Override
	public boolean approveBlog(Blog blog) {
		
		try {
			
			blog.setStatus("A");
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}catch(Exception exception) {
			return false;
		}
	}
	
	@Transactional
	@Override
	public boolean rejectBlog(Blog blog) {
		
		try {
			
			blog.setStatus("NA");
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}catch(Exception exception) {
			return false;
		}
	}
	
	@Override
	public List<Blog> lisBlog(String userName){
		
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			List<Blog> blogList = new ArrayList<Blog>();
			Query query = session.createQuery("FROM Blog where username=:username").setString("username",userName);
			blogList = query.list();
			return blogList;
		} catch (Exception e) {
			return null;
		}
	}
}