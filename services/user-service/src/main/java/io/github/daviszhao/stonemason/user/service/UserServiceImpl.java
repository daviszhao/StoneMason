package io.github.daviszhao.stonemason.user.service;

import io.github.daviszhao.stonemason.api.base.PageData;
import io.github.daviszhao.stonemason.api.user.UserService;
import io.github.daviszhao.stonemason.busEvent.services.EventService;
import io.github.daviszhao.stonemason.busEvent.services.utils.EventUtils;
import io.github.daviszhao.stonemason.db.user.tables.UserTable;
import io.github.daviszhao.stonemason.db.user.tables.daos.UserDao;
import io.github.daviszhao.stonemason.exceptions.user.UserExistExption;
import io.github.daviszhao.stonemason.exceptions.user.UserStatusException;
import io.github.daviszhao.stonemason.exceptions.user.UsetNotExistException;
import io.github.daviszhao.stonemason.models.user.User;
import lombok.AllArgsConstructor;
import org.jooq.Condition;
import org.jooq.SortField;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.util.StringUtils.hasText;

@RestController
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private EventService eventService;
    private EventUtils eventUtils;

    @Override
    public List<User> queryAllUsers(String keyword, Boolean locked) {
        List<Condition> conditions = userDao.buildUserCondition(keyword, locked);
        SortField<LocalDateTime> sortField = UserTable.USER.CREATETIME.desc();
        return userDao.queryAllData(conditions, sortField);
    }

    @Override
    public PageData<User> queryUsers(String keyword, Boolean locked, int pageSize, int page) {
        assert pageSize > 0;
        assert page > 0;
        List<Condition> conditions = userDao.buildUserCondition(keyword, locked);
        return userDao.queryPageData(conditions, UserTable.USER.CREATETIME.desc(), pageSize, page);
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

    @Override
    public void testEvent() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", "daviszhao");
        payload.put("time", LocalDateTime.now());
        eventService.prepairNotifyEvent("event.user.test", eventUtils.serialize(payload));
    }

}
