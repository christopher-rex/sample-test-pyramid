package com.testpyramid.persistence;

import helpers.TestDataHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class UserRepositoryTest {

    private final UserRepository userRepository = new UserRepository();

    @Before
    public void setUp() throws Exception {
        TestDataHelper.createUser();
    }

    @After
    public void tearDown() throws Exception {
        TestDataHelper.cleanDb();
    }

    @Test
    public void findByEmailAndPasswordReturnsNullIfUserDoesNotExist() throws Exception {
        Map<String, String> user = userRepository.findByEmailAndPassword("nonexistant@nonexistant", "password");

        assertNull(user);
    }

    @Test
    public void findByEmailAndPasswordReturnsNullIfUserIsInactive() throws Exception {
        TestDataHelper.createUser("inactiveUser","inactiveUser@test.com","password", "false", "true");
        Map<String, String> user = userRepository.findByEmailAndPassword("inactiveUser@test.com", "password");

        assertNull(user);
    }

    @Test
    public void findByEmailAndPasswordReturnsUserDetailsIfUserExists() throws Exception {
        Map<String, String> user = userRepository.findByEmailAndPassword("test@test.com", "password");

        assertNotNull(user);
        assertEquals("Test User", user.get("name"));
    }
}