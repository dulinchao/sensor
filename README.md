## 传感器数据采集模拟仿真系统

此项目为课程设计题目

以下为数据库建表代码：
```sql
create table INFORMATION
(
    ID                   INT auto_increment,
    NAME                 VARCHAR(100) not null,
    TEMPERATURE          INT,
    HUMIDITY             INT,
    PRESSURE             DOUBLE,
    CONCENTRATION_OF_CO2 INT,
    TIME                 DATETIME,
    constraint INFORMATION_PK
        primary key (ID)
);
```