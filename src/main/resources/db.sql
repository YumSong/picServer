--A
create table t_admin(
id number PRIMARY key
,login_name NVARCHAR2(255)
,login_password NVARCHAR2(255)
);
create sequence S_t_admin
minvalue 1
maxvalue 999999999999
start with 1
increment by 1;

create table merchantDetail(
merchantDetail_id number PRIMARY key
,merchant_id NUMBER --商家ID
,idcard_num number --身份証號
,idcard_pic NVARCHAR2(255)  --身份証圖片
,merchant_name NVARCHAR2(255)  --商家姓名
,shop_id number --店鋪id
,status number --狀態：0-待處理、 1-審核通過（拉白）、 2-駁回 3、不同意（拉黑） 
);
create sequence S_merchantDetail
minvalue 1
maxvalue 999999999999
start with 1
increment by 1;


create table merchant(
merchant_id number PRIMARY key
,login_name NVARCHAR2(255) --店鋪登陸賬號
,login_password NVARCHAR2(255) --店鋪登陸密碼
);
create sequence S_merchant
minvalue 1
maxvalue 999999999999
start with 1
increment by 1;

--M
create table shop(
shop_id number primary key  --店鋪id
,shop_name NVARCHAR2(255)  --店名
,service_starttime TIMESTAMP  --服務開始時間
,service_endtime TIMESTAMP  --服務結束時間
,serviec_range number --配送範圍
,distribution_cost number --配送費用
,shop_pic NVARCHAR2(255) --店内圖片url拼接
,business_pic NVARCHAR2(255) --工商照片
,address NVARCHAR2(255)  --店鋪地址
);
create sequence S_shop
minvalue 1
maxvalue 999999999999
start with 1
increment by 1;


create table recipe(
re_id NUMBER PRIMARY KEY --菜品id
,re_name NVARCHAR2(255) --菜品的名字
,re_pic NVARCHAR2(255) --圖片的url
,detail NVARCHAR2(255)  --菜品的介紹
,price number --菜品的價格
,shop_id number --店鋪的ID
);
create sequence S_recipe
minvalue 1
maxvalue 999999999999
start with 1
increment by 1;