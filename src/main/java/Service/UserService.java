package Service;

import Data.*;
import Data.Cuisine;
import Repo.UserRepository;
import Utils.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;


public class UserService implements UserRepository {

    SaltHash saltHash = new SaltHash();
    Hash hash = new Hash();

    CompareChar compareChar = new CompareChar();
    private SessionFactory sessionFactory;


    public UserService(){
        new UserService(HibernateUtil.getSessionFactory());
    }

    public UserService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }



    public boolean createUser(String userName, String email, String confirmEmail, char[] password, char[] confirmPassword, String dob) {

        System.out.println(compareChar.compareCharArrays(password,confirmPassword));
        if (email.equals(confirmEmail) && compareChar.compareCharArrays(password,confirmPassword)) {
            try (Session session = sessionFactory.openSession()) {
                Transaction transaction = session.beginTransaction();

                User user = new User();
                //salt hash password
                Optional<String> salt = saltHash.generateSalt(100);
                Optional<char[]> securePassword = hash.hashPassword(password,salt);
                // Set user properties
                user.setUserName(userName);
                user.setEmail(email);
                user.setPassword(securePassword.get());
                user.setDoB(LocalDate.parse(dob));

                session.save(user);

                transaction.commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (userName.isEmpty() || email.isEmpty() || password.length == 0) {
            return false;
        } else {
            return false;
        }

        return false;
    }

    public Optional<User> findUserByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {
            String query = "FROM User WHERE userName = :username";
            return session.createQuery(query, User.class)
                    .setParameter("username", username)
                    .uniqueResultOptional();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public Optional<User> findUserByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            String query = "FROM User WHERE email = :email";
            return session.createQuery(query, User.class)
                    .setParameter("email", email)
                    .uniqueResultOptional();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public boolean updateUsername(int userId, String newUsername) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            User user = session.get(User.class, userId);
            if (user != null) {
                user.setUserName(newUsername);
                session.update(user);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateEmail(int userId, String newEmail) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            User user = session.get(User.class, userId);
            if (user != null) {
                user.setEmail(newEmail);
                session.update(user);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updatePassword(int userId, char[] newPassword) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            User user = session.get(User.class, userId);
            if (user != null) {
                user.setPassword(newPassword);
                session.update(user);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateDoB(int userId, LocalDate newDoB) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            User user = session.get(User.class, userId);
            if (user != null) {
                user.setDoB(newDoB);
                session.update(user);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateLocation(int userId, Locale newLocation) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            User user = session.get(User.class, userId);
            if (user != null) {
                user.setCurrentLocation(newLocation);
                session.update(user);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateFavoriteMeals(int userId, List<Meal> newFavoriteMeals) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            User user = session.get(User.class, userId);
            if (user != null) {
                user.setFavoriteMeals(newFavoriteMeals);
                session.update(user);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateFavoriteCuisine(int userId, List<Cuisine> newFavoriteCuisine) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            User user = session.get(User.class, userId);
            if (user != null) {
                user.setFavoriteCuisine(newFavoriteCuisine);
                session.update(user);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateMealsToDate(int userId, List<Meal> newMealsToDate) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            User user = session.get(User.class, userId);
            if (user != null) {
                user.setMealsToDate(newMealsToDate);
                session.update(user);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateAllergies(int userId, List<Allergy> newAllergies) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            User user = session.get(User.class, userId);
            if (user != null) {
                user.setAllergies(newAllergies);
                session.update(user);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(int userId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, userId);
            if (user != null){
                session.delete(user);
                transaction.commit();
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> query = builder.createQuery(User.class);
            Root<User> root = query.from(User.class);
            query.select(root);

            return session.createQuery(query).getResultList();
        }
    }
}

