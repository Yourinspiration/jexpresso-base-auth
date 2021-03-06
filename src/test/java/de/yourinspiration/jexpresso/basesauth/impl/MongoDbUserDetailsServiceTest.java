package de.yourinspiration.jexpresso.basesauth.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

import de.yourinspiration.jexpresso.baseauth.UserDetails;
import de.yourinspiration.jexpresso.baseauth.UserNotFoundException;

/**
 * Test case for {@link MongoDbUserDetailsService}.
 * 
 * @author Marcel Härle
 *
 */
public class MongoDbUserDetailsServiceTest {

    private MongoDbUserDetailsService userDetailsService;

    @Mock
    private Mongo mongo;
    @Mock
    private DB db;
    @Mock
    private DBCollection dbCollection;

    private final String database = "testdb";
    private final String userCollection = "users";
    private final String usernameField = "username";
    private final String passwordField = "password";
    private final String enabledField = "enabled";
    private final String authoritiesField = "roles";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        Mockito.when(mongo.getDB(database)).thenReturn(db);
        Mockito.when(db.getCollection(userCollection)).thenReturn(dbCollection);

        userDetailsService = new MongoDbUserDetailsService(mongo, database, userCollection, usernameField,
                passwordField, enabledField, authoritiesField);
    }

    @Test
    public void testLoadUserByUsername() throws UserNotFoundException {
        final String username = "max";
        final String password = "1234";

        final BasicDBList authorityDBList = new BasicDBList();
        authorityDBList.add("USER");
        final BasicDBObject userResult = new BasicDBObject(usernameField, username).append(passwordField, password)
                .append(enabledField, "true").append(authoritiesField, authorityDBList);

        Mockito.when(dbCollection.findOne(new BasicDBObject().append(usernameField, username))).thenReturn(userResult);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertEquals(password, userDetails.getPassword());
        assertEquals(1, userDetails.getAuthorities().size());
    }

    @Test(expected = UserNotFoundException.class)
    public void testLoadUserForNotExistingUser() throws UserNotFoundException {
        final String username = "max";

        Mockito.when(dbCollection.findOne(new BasicDBObject().append(usernameField, username))).thenReturn(null);

        userDetailsService.loadUserByUsername(username);
    }

}
