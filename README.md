
# Introduction
This is a straightforward web platform designed for creating a blog. It utilizes Java, follows the MVC (Model-View-Controller) architecture, incorporates IoC (Inversion of Control), includes a DAO (Data Access Object) layer, and is built on a basic custom framework.

# Install
1. Install GlassFish 4
2. Make db from the dump file
3. Create connect pool, and JDBC resources (jdbc/mysql)
4. Create a security realm (jdbc-realm)
```
./asadmin create-auth-realm   --classname com.sun.enterprise.security.auth.realm.jdbc.JDBCRealm   
--property user-name-column=user_name:password-column=password:group-name-column=group_name:jaas-context=jdbcRealm:datasource-jndi="jdbc/mysql":group-table=groups:user-table=users:digest-algorithm=MD5:digestrealm-password-enc-algorithm=MD5   jdbc-realm
jdbc:mysql://localhost:3306/blogj?useUnicode=true&characterEncoding=UTF-8
```
