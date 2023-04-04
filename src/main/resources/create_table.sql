create table product (id int primary key auto_increment not null, title varchar(255) not null, price double not null);
create table cart (id int primary key auto_increment not null);
create table cart_product (cart_id int references cart(id), product_id int references product(id), constraint cart_product_key primary key (cart_id, product_id));