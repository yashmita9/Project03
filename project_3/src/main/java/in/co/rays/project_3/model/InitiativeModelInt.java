package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.InitiativeDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface InitiativeModelInt {

	public long add(InitiativeDTO dto)throws ApplicationException,DuplicateRecordException;
	public void delete(InitiativeDTO dto)throws ApplicationException;
	public void update(InitiativeDTO dto)throws ApplicationException,DuplicateRecordException;
	public List list()throws ApplicationException;
	public List list(int pageNo,int pageSize)throws ApplicationException;
	public List search(InitiativeDTO dto)throws ApplicationException;
	public List search(InitiativeDTO dto,int pageNo,int pageSize)throws ApplicationException;
	public InitiativeDTO findByPK(long pk)throws ApplicationException;


	
}
