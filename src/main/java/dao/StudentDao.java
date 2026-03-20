package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Student;
import util.HibernateUtil;

public class StudentDao implements IStudentDao {

    // create
    @Override
    public void registerStudent(Student student) {
        Transaction tx = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            session.persist(student);

            tx.commit();
        } catch (Exception e) {
            safeRollback(tx);
            e.printStackTrace();
        } finally {
            safeClose(session);
        }
    }

    // update
    @Override
    public void updateStudent(Student student) {
        Transaction tx = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            session.merge(student);

            tx.commit();
        } catch (Exception e) {
            safeRollback(tx);
            e.printStackTrace();
        } finally {
            safeClose(session);
        }
    }

    // read by ID
    @Override
    public Student grtStudentById(int regNo) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(Student.class, regNo);
        }
    }

    //read all
    @Override
    public List<Student> getAllStudents() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Student", Student.class).getResultList();
        }
    }

    // delete by regNo
    @Override
    public int deleteStudent(int regNo) {
        Transaction tx = null;
        Session session = null;
        int deleted = 0;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            deleted = session.createMutationQuery(
                    "DELETE FROM Student WHERE regNo = :id")
                    .setParameter("id", regNo)
                    .executeUpdate();

            tx.commit();
        } catch (Exception e) {
            safeRollback(tx);
            e.printStackTrace();
        } finally {
            safeClose(session);
        }

        return deleted;
    }

    // delete by sNo
    @Override
    public void deleteStudentBySNo(String sNo) {
        Transaction tx = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            int deleted = session.createMutationQuery(
                    "DELETE FROM Student WHERE sNo = :sno")
                    .setParameter("sno", sNo)
                    .executeUpdate();

            tx.commit();
            System.out.println("Rows deleted: " + deleted);
        } catch (Exception e) {
            safeRollback(tx);
            e.printStackTrace();
        } finally {
            safeClose(session);
        }
    }

    // counts & read
    @Override
    public int countStudentByCenter(String center) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "SELECT COUNT(s) FROM Student s WHERE s.center = :center",
                    Long.class)
                    .setParameter("center", center)
                    .getSingleResult()
                    .intValue();
        }
    }

    @Override
    public int countStudentByProgram(String program) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "SELECT COUNT(s) FROM Student s WHERE s.program = :program",
                    Long.class)
                    .setParameter("program", program)
                    .getSingleResult()
                    .intValue();
        }
    }

    @Override
    public List<Student> getStudentsByCenter(String center) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "FROM Student s WHERE s.center = :center",
                    Student.class)
                    .setParameter("center", center)
                    .getResultList();
        }
    }


    private void safeRollback(Transaction tx) {
        try {
            if (tx != null) tx.rollback();
        } catch (Exception ignore) {}
    }

    private void safeClose(Session session) {
        try {
            if (session != null && session.isOpen()) session.close();
        } catch (Exception ignore) {}
    }
}