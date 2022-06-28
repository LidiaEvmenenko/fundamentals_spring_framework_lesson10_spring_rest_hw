create table if not exists categories
(
    id    bigserial primary key,
    title varchar(255)
);
insert into categories (title)
values ('Продукты'),
 ('Молочные продукты'),
 ('Хлебные изделия');

 create table cart
 (
     id          bigserial primary key,
     title       varchar(255),
     price       int,
     amount      int
 );

create table if not exists products
(
    id          bigserial primary key,
    title       varchar(255),
    price       int,
    category_id bigint references categories (id)
);
insert into products (title, price, category_id)
values ('Bread', 25, 3),
('Milk', 80, 2),
('ketchup', 65, 1),
('Pasta', 75, 3),
('Tomato', 99, 1),
('Cucumber', 78, 1),
('Sour cream', 80, 2),
('Water', 40, 1),
('Chocolate', 59, 1),
('Candies', 280, 1),
('Cottage cheese', 150, 2),
('Sausage', 80, 1),
('Meat', 380, 1),
('Mushrooms', 98, 1),
('Ice cream', 54, 1),
('Crisps', 40, 1),
('Coca Cola', 80, 1),
('Baton', 83, 3),
('Apple', 79, 1),
('Apple', 179, 1),
('Apple', 279, 1),
('Cheese', 450, 2);

