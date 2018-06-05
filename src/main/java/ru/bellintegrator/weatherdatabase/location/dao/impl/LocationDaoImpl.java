package ru.bellintegrator.weatherdatabase.location.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.weatherdatabase.location.dao.LocationDao;
import ru.bellintegrator.weatherdatabase.location.model.Location;
import ru.bellintegrator.weatherdatabase.location.view.LocationView;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class LocationDaoImpl implements LocationDao {
    private final EntityManager em;

    @Autowired
    public LocationDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Location location) {
        em.persist(location);
    }

    /**
     * Загрузка Location по city и country
     * @param locView
     * @return Location
     */
    @Override
    public List<Location> loadByParams(LocationView locView) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Location> q = cb.createQuery(Location.class);
        Root<Location> c = q.from(Location.class);

        Predicate p = cb.conjunction();

        p = cb.equal((c.get("city")), locView.getCity());

        if(locView.getCountry() != null) {
            p = cb.and(p, cb.equal((c.get("country")), locView.getCountry()));
        }

        q.select(c).where(p);

        TypedQuery<Location> query = em.createQuery(q);

        //Location location = query.getSingleResult();
        List<Location> locList = query.getResultList();

        return locList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Location> all() {
        List<Location> locList = em.createQuery("from Location").getResultList();
        return locList;
    }
}