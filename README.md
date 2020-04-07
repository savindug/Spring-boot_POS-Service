Spring-boot_POS-Service

1. configure your db
create a database on phpmyadmin
  
  
add db config to application.properties

  \src\main\resources\application.properties\
  
//running the port of the service  
server.port=8090 

spring.jpa.hibernate.ddl-auto=update
//spring-boot = your db name,
//don't change unicode and timezones
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/spring-boot?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC 
spring.datasource.username=root
spring.datasource.password=

spring.jpa.properties.hibernate.show_sql = true
spring.jpa.properties.hibernate.Use_sql_comments = true
spring.jpa.properties.hibernate.format_sql = true
