## About

This library provides basic functionality for authentication.

## UserDetailsService

A UserDetailsService loads user details by username.

### Implementations

* JdbcUserDetailsService: Retrieves user data from a JDBC connection
* MemoryUserDetailsService: Retrieves user data from memory
* MongoDbUserDetailsService: Retrieves user data froma MongoDB database

## PasswordEncoder

A PasswordEncoder encodes and checks plain text password.

### Implementations

* MD5PasswordEncoder: MD5 algorithm
* PBKDF2PasswordEncoder: PDKDF2 algorithm
* SHA256PasswordEncoder: sha-256 algorithm
* SHA512PasswordEncoder: sha-512 algorithm
* BCryptPasswordEncoder: Bcrypt algorithm
* PlainTextPasswordEncoder: no algorithm just plaintext
