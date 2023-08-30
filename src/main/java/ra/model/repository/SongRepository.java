package ra.model.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;
import ra.model.entity.Song;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SongRepository implements ISongRepository{
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;
    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.config.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Song> findAll() {
        List<Song> list = new ArrayList<>();
        String hql = "SELECT s FROM Song AS s";
        // sử dụng các phương thức của đối tượng 1 cách tự động
        TypedQuery<Song> type = entityManager.createQuery(hql, Song.class);
        // lấy về danh sách
        list = type.getResultList();
        return list;
    }

    @Override
    public Song findByID(Long id) {
        // sử dụng các phương thức của đối tượng 1 cách tự động
        TypedQuery<Song> type = entityManager.createQuery("SELECT s FROM Song AS s WHERE s.id=:id", Song.class);
        type.setParameter("id",id);
        // lấy về 1 đối tương
        Song s = type.getSingleResult();
        return s;
    }

    @Override
    public void save(Song s) {
        // Khởi tạo các đối tượng để quản lí giao dịch
        Session session = null;
        Transaction transaction = null;
        try {
            // khởi tạo session (phiên)
            session = sessionFactory.openSession();
            // bắt đầu 1 giao dịch
            transaction = session.beginTransaction();
            if(s.getId()==null){
                // chức năng thêm mới
                session.save(s);
            }else {
                // chức năng update (liên quan đến copy)
                // lấy đối tượng cũ cần sửa ra
                Song old = findByID(s.getId());
                if(s.getMP3()==null){
                    s.setMP3(old.getMP3());
                }
                old.copy(s);
                session.saveOrUpdate(old);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.isActive();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void delete(Long id) {
        Session session =null;
        Transaction transaction = null;
        try{
            session = sessionFactory.openSession();
            transaction= session.beginTransaction();
            session.delete(findByID(id));
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            if(transaction!=null){
                transaction.isActive();
            }
        }finally {
            if (session!=null){
                session.close();
            }
        }
    }
}
