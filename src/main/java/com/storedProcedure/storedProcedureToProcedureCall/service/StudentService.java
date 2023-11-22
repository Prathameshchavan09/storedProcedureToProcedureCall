package com.storedProcedure.storedProcedureToProcedureCall.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storedProcedure.storedProcedureToProcedureCall.dao.StudentDao;
import com.storedProcedure.storedProcedureToProcedureCall.model.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;

@Service
public class StudentService {

	@Autowired
	private StudentDao studentDao;

	@Autowired
	@PersistenceContext
	private EntityManager em;

	public Student createStudent(Student student, String Type, Long id, String city, String name,
			int salary) {
		System.out.println("Service is called");
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("CrudCallProcedureFromProcedure")
				.setParameter("Type", Type)
				.setParameter("id", id)
				.setParameter("name", name)
				.setParameter("city", city)
				.setParameter("salary", salary);

		query.execute();
		return studentDao.save(student);

	}

	public List<Student> getAllStudent(Student student, String Type, Long id, String city, String name,
			int salary) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("CrudCallProcedureFromProcedure")
		.setParameter("Type",Type)
		.setParameter("id", id)
		.setParameter("name", name)
		.setParameter("city", city)
		.setParameter("salary", salary);
		

		query.execute();
		return studentDao.findAll();

	}

	public Student getStudentById(Student student, String Type, Long id, String city, String name,int salary) 
	{
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("CrudCallProcedureFromProcedure")
				.setParameter("Type",Type)
				.setParameter("id", id)
				.setParameter("name", name)
				.setParameter("city", city)
				.setParameter("salary", salary);


		query.execute();
		Optional<Student> optionalUser = studentDao.findById(id);
		return optionalUser.get();

	}

	@Transactional
	public int deleteStudent(Student student, String Type, Long id, String city, String name,int salary) {

		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("CrudCallProcedureFromProcedure")
				.setParameter("Type",Type)
				.setParameter("id", id)
				.setParameter("name", name)
				.setParameter("city", city)
				.setParameter("salary", salary);
	
		
		query.execute();

		int rowsAffectedProcedure = query.executeUpdate();
		studentDao.deleteById(id);

		return rowsAffectedProcedure;

	}

	@Transactional
	public Student updateStudent(Student student,String Type,Long id ,String name, String city, int salary) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("CrudCallProcedureFromProcedure")
				.setParameter("Type", Type)
				.setParameter("id", id)
				.setParameter("name", name)
				.setParameter("city", city)
				.setParameter("salary", salary);

		query.execute();

		Student existingStudent = studentDao.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));

		
		existingStudent.setId(id);
		existingStudent.setName(name);
		existingStudent.setCity(city);
		existingStudent.setSalary(salary);

		return studentDao.save(existingStudent);
	}

}
