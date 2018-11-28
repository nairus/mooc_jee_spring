package fr.eservices.drive.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import fr.eservices.drive.model.Article;
import fr.eservices.drive.model.Category;
import fr.eservices.drive.model.Perishable;

public class CatalogDaoJPAImpl implements CatalogDao {

    private EntityManager em;

    public CatalogDaoJPAImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Category> getCategories() throws DataException {
        String query = "FROM Category cat ORDER BY cat.orderIdx ASC";
        return em.createQuery(query, Category.class).getResultList();
    }

    @Override
    public List<Category> getArticleCategories(int id) throws DataException {
        try {
            String query = "SELECT art FROM Article art LEFT JOIN art.categories cat WHERE art.id = :id";
            Article article = em.createQuery(query, Article.class).setParameter("id", id).getSingleResult();

            return article.getCategories();
        } catch (NoResultException e) {
            throw new DataException("The article [" + id + "] does not exist", e);
        }
    }

    @Override
    public List<Article> getCategoryContent(int categoryId) throws DataException {
        try {
            // Check if the category exists.
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Category> cq = cb.createQuery(Category.class);
            Root<Category> cat = cq.from(Category.class);
            cq.where(cb.equal(cat.get("id"), categoryId));
            Category category = em.createQuery(cq).getSingleResult();

            // Return the list of articles linked to the current category.
            String query = "SELECT art FROM Article art JOIN art.categories cat WHERE cat.id = :id";
            return em.createQuery(query, Article.class).setParameter("id", category.getId()).getResultList();
        } catch (NoResultException e) {
            throw new DataException("The category [" + categoryId + "] does not exist.", e);
        }
    }

    @Override
    public List<Perishable> getPerished(Date day) throws DataException {
        String query = "SELECT per FROM Perishable per WHERE per.bestBefore < :date";
        List<Perishable> perishables = em.createQuery(query, Perishable.class).setParameter("date", day)
                .getResultList();

        if (perishables.size() > 200) {
            throw new DataException("The perishable articles are more than 200");
        }

        return perishables;
    }

}
