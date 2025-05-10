package in.co.rays.project_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.CollegeDTO;
import in.co.rays.project_3.dto.InitiativeDTO;
import in.co.rays.project_3.dto.InitiativeDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class InitiativeModelHibImp  implements InitiativeModelInt{

	@Override
	public long add(InitiativeDTO dto) throws ApplicationException, DuplicateRecordException {
		Session session = null;
		Transaction tx = null;
//		InitiativeDTO duplicateInitiativeName = fingByName(dto.getName());
//		if (duplicateInitiativeName != null) {
//			throw new DuplicateRecordException("Initiative name already exist");
//		}
		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.save(dto);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();

		}
			throw new ApplicationException("Exception in Initiative Add " + e.getMessage());
		} finally {
			session.close();
		}
		return dto.getId();
	}

	@Override
	public void delete(InitiativeDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.delete(dto);
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in Initiative Delete" + e.getMessage());
		} finally {
			session.close();
		}

		
	}

	@Override
	public void update(InitiativeDTO dto) throws ApplicationException, DuplicateRecordException {
		Session session = null;
		Transaction tx = null;
//		InitiativeDTO dtoExist = fingByName(dto.getName());

		// Check if updated College already exist
		/*
		 * if (dtoExist != null && dtoExist.getId() != dto.getId()) { throw new
		 * DuplicateRecordException("College is already exist"); }
		 */
		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			System.out.println("before update");
			
			session.saveOrUpdate(dto);
			System.out.println("after update");
			tx.commit();

		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in initiative update" + e.getMessage());
		} finally {
			session.close();
		}

		
	}

	@Override
	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return list(0, 0);
	}

	@Override
	public List list(int pageNo, int pageSize) throws ApplicationException {
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(InitiativeDTO.class);
			if (pageSize > 0) {
				pageNo = ((pageNo - 1) * pageSize) + 1;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = criteria.list();

		} catch (HibernateException e) {

			throw new ApplicationException("Exception : Exception in  initiative list");
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public List search(InitiativeDTO dto) throws ApplicationException {
		return search(dto, 0, 0);
	}

	@Override
	public List search(InitiativeDTO dto, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub

		Session session = null;
		ArrayList<InitiativeDTO> list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(InitiativeDTO.class);
			if (dto != null) {
				if (dto.getId() != null) {
					criteria.add(Restrictions.like("id", dto.getId()));
				}
				if (dto.getName() != null && dto.getName().length() > 0) {
					criteria.add(Restrictions.like("name", dto.getName() + "%"));
				}

					if (dto.getStartDate() != null && dto.getStartDate().getTime() > 0) {
					criteria.add(Restrictions.eq("dob", dto.getStartDate()));
				}    
					if (dto.getType() > 0) {
						criteria.add(Restrictions.eq("type", dto.getType()));
					} 
			}
			// if pageSize is greater than 0
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = (ArrayList<InitiativeDTO>) criteria.list();
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in user search");
		} finally {
			session.close();
		}

		return list;
	}
	@Override
	public InitiativeDTO findByPK(long pk) throws ApplicationException {
		System.out.println("======"+pk+"----------------------------------");
		Session session = null;
		InitiativeDTO dto = null;
		try {
			session = HibDataSource.getSession();

			dto = (InitiativeDTO) session.get(InitiativeDTO.class, pk);
			System.out.println(dto);
		} catch (HibernateException e) {

			throw new ApplicationException("Exception : Exception in getting initiaive by pk");
		} finally {
			session.close();
		}
		System.out.println("++++"+dto);
		return dto;
	}

}
