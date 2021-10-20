package ru.studyit.demoonlineshop.dao;

import org.junit.jupiter.api.Test;
import ru.studyit.demoonlineshop.config.CConfigHibernate;
import ru.studyit.demoonlineshop.model.CUser;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CTestDAOUsers {

    @Test
    void checkIfGetWorksProperly()
    {
        CDAOUsers daoUsers = new CDAOUsers(CConfigHibernate.getSessionFactory());
        CUser user = daoUsers.get(UUID.fromString("80c99edf-46bc-4669-9fb3-848755883d82"));
        assertNotNull(user, "Метод daoUsers.get не нашёл пользователя по идентификатору");
        assertEquals("sidorov", user.getLogin(), "Загружается не тот пользователь, идентификатор которого указан");
    }
}