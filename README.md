# java-simple-blog
it is simple web platform for ability to create blog. Used java, mvc, ioc, dao and simple custom framework


INSTALL


1 GlassFish 4
2 Make db from dump file
3 Create connect pool, and JDBC resources (jdbc/mysql)
4 Create security realm (jdbc-realm)
./asadmin create-auth-realm   --classname com.sun.enterprise.security.auth.realm.jdbc.JDBCRealm   
--property user-name-column=user_name:password-column=password:group-name-column=group_name:jaas-context=jdbcRealm:datasource-jndi="jdbc/mysql":group-table=groups:user-table=users:digest-algorithm=MD5:digestrealm-password-enc-algorithm=MD5   jdbc-realm
jdbc:mysql://localhost:3306/blogj?useUnicode=true&characterEncoding=UTF-8
