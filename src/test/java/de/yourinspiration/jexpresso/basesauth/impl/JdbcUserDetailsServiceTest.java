package de.yourinspiration.jexpresso.basesauth.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import de.yourinspiration.jexpresso.baseauth.UserDetails;
import de.yourinspiration.jexpresso.baseauth.UserNotFoundException;

/**
 * Test case for {@link JdbcUserDetailsService}.
 * 
 * @author Marcel Härle
 *
 */
public class JdbcUserDetailsServiceTest {

    private JdbcUserDetailsService userDetailsService;

    @Mock
    private Connection connection;
    @Mock
    private Statement statement;

    private final String userByUsernameQuery = "password, enabled from users where username = ?";
    private final String authoritiesByUsernameQuery = "select role from userroles where username = ?";

    @Before
    public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);

        Mockito.when(connection.createStatement()).thenReturn(statement);

        userDetailsService = new JdbcUserDetailsService(connection, userByUsernameQuery, authoritiesByUsernameQuery);
    }

    @Test
    public void testLoadUserByUsername() throws SQLException, UserNotFoundException {
        final ResultSet userResult = Mockito.mock(ResultSet.class);
        Mockito.when(userResult.first()).thenReturn(true);
        Mockito.when(userResult.getString(1)).thenReturn("1234");
        Mockito.when(userResult.getBoolean(2)).thenReturn(true);
        Mockito.when(statement.executeQuery("password, enabled from users where username = 'max'")).thenReturn(
                userResult);

        final ResultSet authorityResult = Mockito.mock(ResultSet.class);
        Mockito.when(authorityResult.next()).thenReturn(false);
        Mockito.when(statement.executeQuery("select role from userroles where username = 'max'")).thenReturn(
                authorityResult);

        final UserDetails userDetails = userDetailsService.loadUserByUsername("max");

        assertNotNull(userDetails);
        assertEquals("max", userDetails.getUsername());
        assertEquals("1234", userDetails.getPassword());
        assertEquals(0, userDetails.getAuthorities().size());
    }

    @Test(expected = UserNotFoundException.class)
    public void testLoadUserByUsernameForNotExistingUser() throws SQLException, UserNotFoundException {
        final ResultSet userResult = Mockito.mock(ResultSet.class);
        Mockito.when(userResult.first()).thenReturn(false);
        Mockito.when(statement.executeQuery("password, enabled from users where username = 'max'")).thenReturn(
                userResult);

        userDetailsService.loadUserByUsername("max");
    }

}
