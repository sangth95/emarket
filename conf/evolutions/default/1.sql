# --- First database schema

# --- !Ups

CREATE TABLE branch (
    id integer NOT NULL,
    name character varying(255),
    description character varying
);


CREATE TABLE category (
    id integer NOT NULL,
    name character varying(255),
    description character varying,
    branch integer
);


CREATE SEQUENCE company_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;






CREATE SEQUENCE computer_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;






CREATE TABLE e_user (
    id integer NOT NULL,
    username character varying,
    hash_password character varying
);






CREATE TABLE id_gen (
    gen_name character varying(50) NOT NULL,
    gen_val integer NOT NULL
);






CREATE TABLE manufacturer (
    id integer NOT NULL,
    name character varying(255),
    description character varying
);






CREATE TABLE product (
    id integer NOT NULL,
    name character varying(255),
    manufacturer integer,
    short_description character varying(1000),
    price integer,
    information_detail character varying(2000),
    description character varying(5000),
    category integer,
    warranty character varying(255),
    pictures character varying(255),
    avatar character varying(100)
);






CREATE SEQUENCE product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;






ALTER SEQUENCE product_id_seq OWNED BY product.id;



CREATE TABLE role (
    id integer NOT NULL,
    role_name character varying
);






CREATE TABLE shopping_cart (
    id integer NOT NULL,
    user_id character varying(50),
    date character varying(10),
    complete integer
);





CREATE TABLE shopping_cart_detail (
    id integer NOT NULL,
    item_id integer,
    quatity integer NOT NULL,
    price integer NOT NULL,
    cart_id integer
);




CREATE SEQUENCE shopping_cart_detail_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;






ALTER SEQUENCE shopping_cart_detail_id_seq OWNED BY shopping_cart_detail.id;




CREATE TABLE user_role (
    e_user integer,
    role integer
);





ALTER TABLE ONLY product ALTER COLUMN id SET DEFAULT nextval('product_id_seq'::regclass);



ALTER TABLE ONLY shopping_cart_detail ALTER COLUMN id SET DEFAULT nextval('shopping_cart_detail_id_seq'::regclass);




INSERT INTO branch VALUES (1, 'Điện tử', NULL);
INSERT INTO branch VALUES (2, 'Thời trang nữ', NULL);
INSERT INTO branch VALUES (3, 'Thời trang nam', NULL);
INSERT INTO branch VALUES (4, 'Đồ gia dụng', NULL);
INSERT INTO branch VALUES (5, 'Đồ chơi trẻ em', NULL);
INSERT INTO branch VALUES (6, 'Thể thao và du lịch', NULL);




INSERT INTO category VALUES (1, 'Cameras', NULL, 1);
INSERT INTO category VALUES (2, 'Computers, Tablets & laptop', NULL, 4);
INSERT INTO category VALUES (3, 'Mobile Phone', NULL, NULL);
INSERT INTO category VALUES (4, 'Sound & Vision', NULL, NULL);




SELECT pg_catalog.setval('company_seq', 1000, false);




SELECT pg_catalog.setval('computer_seq', 1000, false);




INSERT INTO e_user VALUES (1, 'admin', 'd82494f05d6917ba02f7aaa29689ccb444bb73f20380876cb05d1f37537b7892');
INSERT INTO e_user VALUES (2, 'an', 'd93d5781b174c083352b9395daa1bf04777a235a6e263a00ec5957665d38efc5');
INSERT INTO e_user VALUES (3, 'sang', 'be0d509e3e4e9aefc8c4f15bde7721b8561a4e3af48b22b735e059186ba6008d');




INSERT INTO id_gen VALUES ('shoppingcartdetail_gen', 1);
INSERT INTO id_gen VALUES ('Product', 401);
INSERT INTO id_gen VALUES ('shopping_cart_detail', 1401);
INSERT INTO id_gen VALUES ('shopping_cart', 1601);


INSERT INTO manufacturer VALUES (1, 'ASUS', NULL);
INSERT INTO manufacturer VALUES (2, 'Hitachi', NULL);
INSERT INTO manufacturer VALUES (3, 'Samsung', NULL);
INSERT INTO manufacturer VALUES (4, 'Apple', NULL);
INSERT INTO manufacturer VALUES (5, 'Sony', NULL);


SELECT pg_catalog.setval('product_id_seq', 4, true);



INSERT INTO role VALUES (1, 'admin');
INSERT INTO role VALUES (2, 'manager');
INSERT INTO role VALUES (3, 'accountant');




INSERT INTO shopping_cart VALUES (1, 'abc', '2017/05/14', 1);
INSERT INTO shopping_cart VALUES (902, 'truonghongsanglk95@gmail.com', '2017/05/24', 2);
INSERT INTO shopping_cart VALUES (2, 'truonghongsanglk95@gmail.com', '2017/05/14', 2);
INSERT INTO shopping_cart VALUES (1002, NULL, NULL, 0);
INSERT INTO shopping_cart VALUES (1003, NULL, NULL, 0);
INSERT INTO shopping_cart VALUES (1102, NULL, NULL, 0);
INSERT INTO shopping_cart VALUES (1202, NULL, NULL, 0);
INSERT INTO shopping_cart VALUES (1302, NULL, NULL, 0);
INSERT INTO shopping_cart VALUES (1402, NULL, NULL, 0);



INSERT INTO shopping_cart_detail VALUES (402, 12, 1, 3590000, 1);
INSERT INTO shopping_cart_detail VALUES (602, 11, 1, 3590000, 1);
INSERT INTO shopping_cart_detail VALUES (204, 3, 1, 16478000, 1);
INSERT INTO shopping_cart_detail VALUES (704, 103, 1, 20380000, 1);
INSERT INTO shopping_cart_detail VALUES (802, 103, 1, 20380000, 1);
INSERT INTO shopping_cart_detail VALUES (902, 103, 1, 20380000, 2);
INSERT INTO shopping_cart_detail VALUES (1002, 2, 1, 13673000, 2);
INSERT INTO shopping_cart_detail VALUES (1102, 11, 1, 3590000, 902);
INSERT INTO shopping_cart_detail VALUES (1103, 14, 1, 3849000, 902);
INSERT INTO shopping_cart_detail VALUES (1203, 5, 1, 4850000, 1003);



SELECT pg_catalog.setval('shopping_cart_detail_id_seq', 46, true);




INSERT INTO user_role VALUES (1, 1);
INSERT INTO user_role VALUES (1, 2);
INSERT INTO user_role VALUES (2, 1);
INSERT INTO user_role VALUES (3, 2);




ALTER TABLE ONLY branch
    ADD CONSTRAINT branch_pkey PRIMARY KEY (id);




ALTER TABLE ONLY category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);



ALTER TABLE ONLY id_gen
    ADD CONSTRAINT id_gen_pkey PRIMARY KEY (gen_name);



ALTER TABLE ONLY manufacturer
    ADD CONSTRAINT manufacturer_pkey PRIMARY KEY (id);




ALTER TABLE ONLY product
    ADD CONSTRAINT pk_product PRIMARY KEY (id);




ALTER TABLE ONLY role
    ADD CONSTRAINT role_pk PRIMARY KEY (id);



ALTER TABLE ONLY shopping_cart_detail
    ADD CONSTRAINT shopping_cart_detail_pkey PRIMARY KEY (id);




ALTER TABLE ONLY shopping_cart
    ADD CONSTRAINT shopping_cart_pkey PRIMARY KEY (id);



ALTER TABLE ONLY e_user
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);



CREATE INDEX fki_cart_product_item_pk ON shopping_cart_detail USING btree (item_id);




ALTER TABLE ONLY shopping_cart_detail
    ADD CONSTRAINT cart_product_item_pk FOREIGN KEY (item_id) REFERENCES product(id);



ALTER TABLE ONLY category
    ADD CONSTRAINT category_branch_fkey FOREIGN KEY (branch) REFERENCES branch(id);

# --- !Downs


