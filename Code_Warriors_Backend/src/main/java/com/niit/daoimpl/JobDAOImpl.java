package com.niit.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.dao.JobDAO;
import com.niit.model.Job;

@Repository
public class JobDAOImpl implements JobDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public boolean addJob(Job job) {
		
		try {
			sessionFactory.getCurrentSession().save(job);
			return true;
		}catch(Exception exception) {
			return false;
		}
	}

	@Transactional
	@Override
	public boolean deleteJob(Job job) {
		
		try {
			sessionFactory.getCurrentSession().delete(job);
			return true;
		}catch(Exception exception) {
			return false;
		}
	}

	@Transactional
	@Override
	public boolean updateJob(Job job) {
		
		try {
			sessionFactory.getCurrentSession().update(job);
			return true;
		}catch(Exception exception) {
			return false;
		}
	}

	@Override
	public Job getJob(int jobId) {
		
		try {
			Session session = sessionFactory.openSession();
			Job job = session.get(Job.class, jobId);
			return job;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Job> lisJob(int jobId) {
		
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			List<Job> jobList = new ArrayList<Job>();
			Query query = session.createQuery("FROM Job where jobId=:jobId").setInteger("jobId",jobId);
			jobList = query.list();
			return jobList;
		} catch (Exception e) {
			return null;
}
	}

	@Override
	public boolean applyJob(Job job) {
		// TODO Auto-generated method stub
		return false;
	}

}
