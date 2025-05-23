FROM openjdk:17
COPY "./target/club-1.jar" "app.jar"
EXPOSE "3306"
ENTRYPOINT [ "java", "-jar","app.jar"  ]
