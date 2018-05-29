package io.github.daviszhao.stonemason.user.service;

import io.github.daviszhao.stonemason.api.base.PageData;
import io.github.daviszhao.stonemason.api.user.UserService;
import io.github.daviszhao.stonemason.db.user.tables.UserTable;
import io.github.daviszhao.stonemason.db.user.tables.daos.UserDao;
import io.github.daviszhao.stonemason.exceptions.user.UserExistExption;
import io.github.daviszhao.stonemason.exceptions.user.UserStatusException;
import io.github.daviszhao.stonemason.exceptions.user.UsetNotExistException;
import io.github.daviszhao.stonemason.models.user.User;
import org.jooq.Condition;
import org.jooq.SortField;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@RestController
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public List<User> queryAllUsers(String keyword, Boolean locked) {
        List<Condition> conditions = userDao.buildUserCondition(keyword, locked);
        SortField<LocalDateTime> sortField = UserTable.table.CREATETIME.desc();
        return userDao.queryAllData(conditions, sortField);
    }

    @Override
    public PageData<User> queryUsers(String keyword, Boolean locked, int pageSize, int page) {
        assert pageSize > 0;
        assert page > 0;
        List<Condition> conditions = userDao.buildUserCondition(keyword, locked);
        return userDao.queryPageData(conditions, UserTable.table.CREATETIME.desc(), pageSize, page);
    }


    @Override
    public void changePassword(String userName, String password) {
        User user = getUser(userName);
        if (user == null) throw new UsetNotExistException(userName);
        user.setPassword(User.encryptPassword(password, user.getSalt()));
        userDao.update(user);

    }

    @Override
    public void switchLock(String userName, boolean toLock) {
        User user = getUser(userName);
        if (user == null) throw new UsetNotExistException(userName);
        if (user.getLocked() != null) {
            if (user.getLocked() == toLock) throw new UserStatusException();
        }
        user.setLocked(toLock);
        userDao.update(user);
    }

    @Override
    public void create(String userName, String password) {
        assert hasText(userName);
        assert hasText(password);
        User user = userDao.fetchOneByUsername(userName);
        if (user != null) throw new UserExistExption();
        user = new User(userName, password);
        userDao.insert(user);
    }

    @Override
    public User getUser(String userName) {
        assert hasText(userName);
        return userDao.fetchOneByUsername(userName);
    }

    @Override
    public User getUser(int userID) {

        return userDao.fetchOneById(userID);
    }

    @Override
    public void deleteUser(String userName) {
        assert hasText(userName);
        User user = getUser(userName);
        if (user == null) throw new UsetNotExistException(userName);

        userDao.delete(user);
    }

    @Override
    public void deleteUser(int userID) {
        User user = getUser(userID);
        if (user == null) throw new UsetNotExistException(userID);
        userDao.delete(user);
    }

}
