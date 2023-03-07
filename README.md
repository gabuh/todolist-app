# ToDo List App

<p>todo list app made with java, standard swing and mysql </p>

### Requirements
- JDK (Java 17)
- MySQL


### Build and run
1. create a <strong>db.properties</strong> file in the root dir.
2. follow the pattern below: 

```console
user=<your_mysql_user>
password=<your_password>
dburl=jdbc:mysql://localhost:<mysql_port>/<name_of_ur_database>
useSSL=false
```
><p>note: by default mysql uses root as user, no password, and port 3306.</p>

3. execute the [sql script](https://github.com/kevindotklein/todolist-app/blob/main/setup/setup.sql).

<br>

### Todo
- [ ] automate the setup process.







## Database Schema

```mermaid
---
title: Category and Task schema
---
erDiagram

Category {
Integer id PK "AUTO_INCREMENT"
varchar cname "bigger enough"
}

Task{
Integer id
varchar tname
Integer id_category FK "just"
}


Task }|--|| Category : contem
```

## Project class diagram

```mermaid
classDiagram
direction LR;
class CategoryDao{
	<<Interface>>
	findAll() List< Category >
	findByName(String name) Category
	add(Category category) void
	delete(Integer id) void
}

class Category{
	- id : Integer
	- name : String
	
	+getters()
	+setters()
	+toString() String
	+hashCode() int
	+equals() boolean
}

class CategoryDaoJDBC {
	- conn : Connection
}

CategoryDaoJDBC <.. java_sql_Connection : uses

Category <-- CategoryDaoJDBC : instantiate

CategoryDaoJDBC --|> CategoryDao : implements
```





```mermaid
classDiagram
direction LR;
class TaskDao{
	<<Interface>>
	findAll() List< Task >
	findByName(String name) Task
	add(Task Task) void
	delete(Integer id) void
}

class Task{
	- id : Integer
	- label : String
	
	+getters()
	+setters()
	+toString() String
	+hashCode() int
	+equals() boolean
}

class TaskDaoJDBC {
	- conn : Connection
}

TaskDaoJDBC <.. java_sql_Connection : uses

Task <-- TaskDaoJDBC : instantiate

TaskDaoJDBC --|> TaskDao : implements
```

