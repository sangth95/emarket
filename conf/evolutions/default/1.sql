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


INSERT INTO product VALUES (1, 'Laptop ASUS P550LNV-XO582D i7 4510U 15.6 inch (Đen) - Hãng Phân phối chính thức  ', 1, 'Màn hình    15.6", 1366 x 768 pixels
Đồ hoạ    Nvidia Geforce GT840M 2GB
Hệ điều hành    Free OS
CPU    Core i7 4510U 2.0Ghz , Max Turbo Frequency 3.1Ghz
RAM    4GB DDR3 1600MHz
Bộ nhớ trong    1TB
Camera    có
Pin    4 Cell
Trọng lượng    2.2kg
Khác    Ổ đĩa quang DVD RW', 15190000, 'SKU	AS082ELAA1W531VNAMZ-3206541
Kích thước màn hình	15.6
Hard Drive Capacity	1TB
Mẫu mã	Tablet Plaza (Tp.HCM)-Laptop ASUS P550LNV-XO582D 15.6 inch (Đen)
Kích thước sản phẩm (D x R x C cm)	38.14 x 25.15 x 2.76
Trọng lượng (KG)	2
Thời gian bảo hành	2 năm
Loại hình bảo hành	Bằng Phiếu bảo hành và Hóa đơn', 'Giới thiệu về Laptop Asus GL552VX-DM070D i7-6700HQ 15.6 inch (Đen) - Hãng phân phối chính thức

Máy tính xách tay ASUS GL552VX-DM070D sở hữu hiệu năng chơi game tối ưu, thiết kế chi tiết, chất lượng hình ảnh hoàn hảo và nâng cấp tức thời. Laptop ROG GL552VX-DM070D luôn sẵn sàng cho hoạt động giải trí.

Hiệu năng ấn tượng cho chơi game và xử lý dữ liệu



Máy tính xách tay ASUS GL552VX-DM070D với cấu hình lên đến bộ vi xử lý Intel® Core™ i7 lõi tứ thế hệ thứ 4 và đồ họa NVIDIA® GeForce® GTX™ chuyên dụng cùng hỗ trợ Microsoft DirectX 12, Laptop được thiết kế dành cho game thủ và cũng rất thích hợp cho các công việc sáng tạo. Trải nghiệm bất cứ game nào hay chạy bất kỳ ứng dụng nào với chất lượng hình ảnh sắc nét hơn và hiệu năng vượt trội vượt trên cả kỳ vọng của bạn.

Máy tính xách tay ASUS GL552VX-DM070D có thiết kế vừa bền bỉ vừa phong cách, lấy cảm hứng từ máy bay tàng hình F-22 và chi tiết Mayan tinh xảo trên một bàn phím đẹp mắt có lắp đèn nền.

Bàn phím tinh chỉnh giúp bạn điều khiển cuộc chơi



Những phím gọn, dễ bấm của ASUS GL552VX-DM070D cho khoảng cách bấm phím 1,8mm hoàn hảo về độ nhạy; nhóm phím WASD được đánh dấu đặc biệt và bộ phím số chuyên dụng cho phép bạn kiểm soát nhanh, toàn diện. Một bàn phím xây dựng nguyên khối vững chắc được chiếu sáng bởi đèn nền ROG màu đỏ giúp dễ nhìn ở mọi hướng và mọi thời điểm.

Khả năng lưu trữ và tốc độ đáng kinh ngạc

Với ổ cứng lên đến 1 TB 7200rpm và ổ lưu trữ thể rắn (SSD) M.2 lên đến 256GB, GL552VX-DM070D luôn có thừa khả năng lưu trữ và tốc độ ấn tượng. Lưu trữ tất cả những nội dung mà bạn cần và thỏa mãn đam mê tốc độ của bạn.



Nâng cấp tức thì một cách dễ dàng

Rất đơn giản để nâng tầm chiếc GL552VX-DM070D của bạn. Tấm nâng cấp có thể được trượt ra để tiếp cận trực tiếp khay nâng cấp bên trong, để bạn có thể lắp thêm ổ SSD M.2, một ổ cứng lớn hơn hoặc thêm bộ nhớ tùy nhu cầu của mình.

Frag siêu nhanh, luôn dẫn đầu

Công nghệ GameFirst III trên laptop GL552VX-DM070D ưu tiên lưu lượng dữ liệu game trên toàn hệ thống mạng của bạn. Trải nghiệm chơi game sẽ được mượt hơn, và hiện tượng lag chỉ còn là dĩ vãng — để chỉ số frag của bạn luôn được đặt lên đầu.

Sự tinh tế của màn hình Full HD, không hề gây nhức mắt



Màn hiển thị 15,6 inch IPS Full HD rất lớn của GL552VX-DM070D có một lớp hoàn tất nhám đen triệt tiêu hiện tượng nhức mắt cho trải nghiệm hình ảnh vượt trội. Và với góc nhìn rộng 178 độ, bạn sẽ được thưởng thức những màu sắc hoành tráng sinh động từ cả những vị trí nhìn hẹp nhất.

Chất lượng âm thanh tuyệt hảo



Máy tính xách tay Asus GL552VX-DM070D phát ra những âm thanh trung thực nhất, rõ ràng và mạnh mẽ nhất với công nghệ SonicMaster độc quyền, trong khi tiện ích ROG AudioWizard đi kèm lại cung cấp đến năm chế độ thiết lập sẵn dành riêng cho các dòng game phổ biến – giúp bạn đắm chìm trong thế giới game tuyệt vời.', 2, '24 tháng', 'ip732blk.jpg', 'hHiJ286.jpg');
INSERT INTO product VALUES (2, 'Samsung Galaxy J7 2016 16GB (Vàng) - Hãng phân phối chính thức', 2, 'Giá đặc biệt sinh nhật Lazada - Chỉ 3.590.000 - Từ 21/3 tới 23/3
HĐH Android
Màn hình 5.5'''' Super AMOLED 1280 x 720
Xử lý Octa-Core 1.6GHz
RAM 2GB
Camera chính 13MP', 3590000, 'SKU	SA937ELAA1ABP9VNAMZ-1959787
Thương hiệu sản phẩm tương thích	Samsung
Camera Back	11-15MP
Camera trước	5-6 MP
Màu	Vàng
Cổng kết nối	Micro USB, 3.5mm
CPU Speed	1-2Ghz
Độ phân giải màn hình (pixels)	1280 x 720
Kích thước màn hình	5.5
Loại màn hình	Super AMOLED
Expandable Memory	Yes
Độ phân giải camera (MP)	13.0
Hỗ trợ thẻ nhớ	MicroSD 128GB
Mẫu mã	SSHALOSHOP005Go
Network Connections	2G - GPRS
Số lượng nhân	8
Hệ điều hành	Android
Operating System Version	Lolipop
Phone Features	Expandable Memory
Kích thước sản phẩm (D x R x C cm)	15.2 x 7.6 x 0.8
Product Type	Thanh cảm ứng
Trọng lượng (KG)	0.169
Sản xuất tại	Việt Nam
RAM memory	2GB
Screen Type	AMOLED
Sim Slots	Dual
Bộ nhớ trong (GB)	16
Bộ nhớ trong	16GB
Thời gian bảo hành	12 tháng
Loại hình bảo hành	Bảo hành điện tử
Wireless Connectivity	Cellular (3G/4G)', 'Giới thiệu về Laptop Asus GL552VX-DM070D i7-6700HQ 15.6 inch (Đen) - Hãng phân phối chính thức

Máy tính xách tay ASUS GL552VX-DM070D sở hữu hiệu năng chơi game tối ưu, thiết kế chi tiết, chất lượng hình ảnh hoàn hảo và nâng cấp tức thời. Laptop ROG GL552VX-DM070D luôn sẵn sàng cho hoạt động giải trí.

Hiệu năng ấn tượng cho chơi game và xử lý dữ liệu



Máy tính xách tay ASUS GL552VX-DM070D với cấu hình lên đến bộ vi xử lý Intel® Core™ i7 lõi tứ thế hệ thứ 4 và đồ họa NVIDIA® GeForce® GTX™ chuyên dụng cùng hỗ trợ Microsoft DirectX 12, Laptop được thiết kế dành cho game thủ và cũng rất thích hợp cho các công việc sáng tạo. Trải nghiệm bất cứ game nào hay chạy bất kỳ ứng dụng nào với chất lượng hình ảnh sắc nét hơn và hiệu năng vượt trội vượt trên cả kỳ vọng của bạn.

Máy tính xách tay ASUS GL552VX-DM070D có thiết kế vừa bền bỉ vừa phong cách, lấy cảm hứng từ máy bay tàng hình F-22 và chi tiết Mayan tinh xảo trên một bàn phím đẹp mắt có lắp đèn nền.

Bàn phím tinh chỉnh giúp bạn điều khiển cuộc chơi



Những phím gọn, dễ bấm của ASUS GL552VX-DM070D cho khoảng cách bấm phím 1,8mm hoàn hảo về độ nhạy; nhóm phím WASD được đánh dấu đặc biệt và bộ phím số chuyên dụng cho phép bạn kiểm soát nhanh, toàn diện. Một bàn phím xây dựng nguyên khối vững chắc được chiếu sáng bởi đèn nền ROG màu đỏ giúp dễ nhìn ở mọi hướng và mọi thời điểm.

Khả năng lưu trữ và tốc độ đáng kinh ngạc

Với ổ cứng lên đến 1 TB 7200rpm và ổ lưu trữ thể rắn (SSD) M.2 lên đến 256GB, GL552VX-DM070D luôn có thừa khả năng lưu trữ và tốc độ ấn tượng. Lưu trữ tất cả những nội dung mà bạn cần và thỏa mãn đam mê tốc độ của bạn.



Nâng cấp tức thì một cách dễ dàng

Rất đơn giản để nâng tầm chiếc GL552VX-DM070D của bạn. Tấm nâng cấp có thể được trượt ra để tiếp cận trực tiếp khay nâng cấp bên trong, để bạn có thể lắp thêm ổ SSD M.2, một ổ cứng lớn hơn hoặc thêm bộ nhớ tùy nhu cầu của mình.

Frag siêu nhanh, luôn dẫn đầu

Công nghệ GameFirst III trên laptop GL552VX-DM070D ưu tiên lưu lượng dữ liệu game trên toàn hệ thống mạng của bạn. Trải nghiệm chơi game sẽ được mượt hơn, và hiện tượng lag chỉ còn là dĩ vãng — để chỉ số frag của bạn luôn được đặt lên đầu.

Sự tinh tế của màn hình Full HD, không hề gây nhức mắt



Màn hiển thị 15,6 inch IPS Full HD rất lớn của GL552VX-DM070D có một lớp hoàn tất nhám đen triệt tiêu hiện tượng nhức mắt cho trải nghiệm hình ảnh vượt trội. Và với góc nhìn rộng 178 độ, bạn sẽ được thưởng thức những màu sắc hoành tráng sinh động từ cả những vị trí nhìn hẹp nhất.

Chất lượng âm thanh tuyệt hảo



Máy tính xách tay Asus GL552VX-DM070D phát ra những âm thanh trung thực nhất, rõ ràng và mạnh mẽ nhất với công nghệ SonicMaster độc quyền, trong khi tiện ích ROG AudioWizard đi kèm lại cung cấp đến năm chế độ thiết lập sẵn dành riêng cho các dòng game phổ biến – giúp bạn đắm chìm trong thế giới game tuyệt vời.', 3, '12 tháng', 'ip732blk.jpg', 'v87Nhk.jpg');
INSERT INTO product VALUES (3, 'Samsung Galaxy J5 Prime 16GB (Đen) - Hãng phân phối chính thức', 2, 'Màn hình: 5", HD 720 x 1280
Mặt kính 2.5D, kính cường lực Gorilla Glass 4
Camera sau 13 MP F1.9, Camera trước 5 MP F2.2
CPU: CPU 4 nhân 1.4 GHz, HĐH: Android 6.0
RAM: 2 GB, ROM: 16 GB, hỗ trợ thẻ nhớ 256 GB
Kết nối bluetooth, wifi, 2G,3G,4G, 2sim
Pin 2400 mAH, hỗ trợ Chế Độ Siêu Tiết Kiệm
Thiết kế kim loại đẳng cấp, cảm biến vân tay', 3849000, 'SKU	SA937ELAA2SWITVNAMZ-4818650
Screen Size (inches)	5.0
Mẫu mã	Samsung Galaxy J7 2017 32GB (Đen) - Hãng phân phối chính thức
Bộ nhớ trong	16GB
Thời gian bảo hành	12 tháng
Loại hình bảo hành	Bảo hành điện tử', 'Giới thiệu về Laptop Asus GL552VX-DM070D i7-6700HQ 15.6 inch (Đen) - Hãng phân phối chính thức

Máy tính xách tay ASUS GL552VX-DM070D sở hữu hiệu năng chơi game tối ưu, thiết kế chi tiết, chất lượng hình ảnh hoàn hảo và nâng cấp tức thời. Laptop ROG GL552VX-DM070D luôn sẵn sàng cho hoạt động giải trí.

Hiệu năng ấn tượng cho chơi game và xử lý dữ liệu



Máy tính xách tay ASUS GL552VX-DM070D với cấu hình lên đến bộ vi xử lý Intel® Core™ i7 lõi tứ thế hệ thứ 4 và đồ họa NVIDIA® GeForce® GTX™ chuyên dụng cùng hỗ trợ Microsoft DirectX 12, Laptop được thiết kế dành cho game thủ và cũng rất thích hợp cho các công việc sáng tạo. Trải nghiệm bất cứ game nào hay chạy bất kỳ ứng dụng nào với chất lượng hình ảnh sắc nét hơn và hiệu năng vượt trội vượt trên cả kỳ vọng của bạn.

Máy tính xách tay ASUS GL552VX-DM070D có thiết kế vừa bền bỉ vừa phong cách, lấy cảm hứng từ máy bay tàng hình F-22 và chi tiết Mayan tinh xảo trên một bàn phím đẹp mắt có lắp đèn nền.

Bàn phím tinh chỉnh giúp bạn điều khiển cuộc chơi



Những phím gọn, dễ bấm của ASUS GL552VX-DM070D cho khoảng cách bấm phím 1,8mm hoàn hảo về độ nhạy; nhóm phím WASD được đánh dấu đặc biệt và bộ phím số chuyên dụng cho phép bạn kiểm soát nhanh, toàn diện. Một bàn phím xây dựng nguyên khối vững chắc được chiếu sáng bởi đèn nền ROG màu đỏ giúp dễ nhìn ở mọi hướng và mọi thời điểm.

Khả năng lưu trữ và tốc độ đáng kinh ngạc

Với ổ cứng lên đến 1 TB 7200rpm và ổ lưu trữ thể rắn (SSD) M.2 lên đến 256GB, GL552VX-DM070D luôn có thừa khả năng lưu trữ và tốc độ ấn tượng. Lưu trữ tất cả những nội dung mà bạn cần và thỏa mãn đam mê tốc độ của bạn.



Nâng cấp tức thì một cách dễ dàng

Rất đơn giản để nâng tầm chiếc GL552VX-DM070D của bạn. Tấm nâng cấp có thể được trượt ra để tiếp cận trực tiếp khay nâng cấp bên trong, để bạn có thể lắp thêm ổ SSD M.2, một ổ cứng lớn hơn hoặc thêm bộ nhớ tùy nhu cầu của mình.

Frag siêu nhanh, luôn dẫn đầu

Công nghệ GameFirst III trên laptop GL552VX-DM070D ưu tiên lưu lượng dữ liệu game trên toàn hệ thống mạng của bạn. Trải nghiệm chơi game sẽ được mượt hơn, và hiện tượng lag chỉ còn là dĩ vãng — để chỉ số frag của bạn luôn được đặt lên đầu.

Sự tinh tế của màn hình Full HD, không hề gây nhức mắt



Màn hiển thị 15,6 inch IPS Full HD rất lớn của GL552VX-DM070D có một lớp hoàn tất nhám đen triệt tiêu hiện tượng nhức mắt cho trải nghiệm hình ảnh vượt trội. Và với góc nhìn rộng 178 độ, bạn sẽ được thưởng thức những màu sắc hoành tráng sinh động từ cả những vị trí nhìn hẹp nhất.

Chất lượng âm thanh tuyệt hảo



Máy tính xách tay Asus GL552VX-DM070D phát ra những âm thanh trung thực nhất, rõ ràng và mạnh mẽ nhất với công nghệ SonicMaster độc quyền, trong khi tiện ích ROG AudioWizard đi kèm lại cung cấp đến năm chế độ thiết lập sẵn dành riêng cho các dòng game phổ biến – giúp bạn đắm chìm trong thế giới game tuyệt vời.', 3, '12 tháng', 'ip732blk.jpg', 'nHbs3.jpg');
INSERT INTO product VALUES (4, 'Laptop Asus GL552VX-DM070D i7-6700HQ 15.6 inch (Đen) - Hãng Phân phối chính thức
', 1, 'CPU:	Intel Core i7 6700HQ 2.6GHz up to 3.5GHz 6MB
Ram:	8GB DDR4 2133MHz
Đĩa cứng:	HDD 1TB 7200rpm
Card đồ họa:	NVIDIA GeForce GTX 950M 4GB GDDR5 Intel HD Graphics 530
Màn hình:	15.6 inch Full HD (1920 x 1080 pixels)
Cổng giao tiếp:	USB 2.0 USB 3.0 HDMI LAN
Hệ điều hành:	Free Dos
Pin:	4 Cells 3200 mAh 48 Whrs (3 giờ)
Trọng lượng:	2.59kg', 18000000, 'SKU	HP496ELAA2OZT4VNAMZ-4616598
Kích	thước màn hình	14.0
Hard	Drive Capacity	120GB & Dưới 120GB
Mẫu mã	laptop HP
Processor Type	Quad-core
Thời gian bảo hành	12 tháng
Loại hình bảo hành	Bằng Hóa đơn mua hàng
', 'Giới thiệu về Laptop Asus GL552VX-DM070D i7-6700HQ 15.6 inch (Đen) - Hãng phân phối chính thức

Máy tính xách tay ASUS GL552VX-DM070D sở hữu hiệu năng chơi game tối ưu, thiết kế chi tiết, chất lượng hình ảnh hoàn hảo và nâng cấp tức thời. Laptop ROG GL552VX-DM070D luôn sẵn sàng cho hoạt động giải trí.

Hiệu năng ấn tượng cho chơi game và xử lý dữ liệu



Máy tính xách tay ASUS GL552VX-DM070D với cấu hình lên đến bộ vi xử lý Intel® Core™ i7 lõi tứ thế hệ thứ 4 và đồ họa NVIDIA® GeForce® GTX™ chuyên dụng cùng hỗ trợ Microsoft DirectX 12, Laptop được thiết kế dành cho game thủ và cũng rất thích hợp cho các công việc sáng tạo. Trải nghiệm bất cứ game nào hay chạy bất kỳ ứng dụng nào với chất lượng hình ảnh sắc nét hơn và hiệu năng vượt trội vượt trên cả kỳ vọng của bạn.

Máy tính xách tay ASUS GL552VX-DM070D có thiết kế vừa bền bỉ vừa phong cách, lấy cảm hứng từ máy bay tàng hình F-22 và chi tiết Mayan tinh xảo trên một bàn phím đẹp mắt có lắp đèn nền.

Bàn phím tinh chỉnh giúp bạn điều khiển cuộc chơi



Những phím gọn, dễ bấm của ASUS GL552VX-DM070D cho khoảng cách bấm phím 1,8mm hoàn hảo về độ nhạy; nhóm phím WASD được đánh dấu đặc biệt và bộ phím số chuyên dụng cho phép bạn kiểm soát nhanh, toàn diện. Một bàn phím xây dựng nguyên khối vững chắc được chiếu sáng bởi đèn nền ROG màu đỏ giúp dễ nhìn ở mọi hướng và mọi thời điểm.

Khả năng lưu trữ và tốc độ đáng kinh ngạc

Với ổ cứng lên đến 1 TB 7200rpm và ổ lưu trữ thể rắn (SSD) M.2 lên đến 256GB, GL552VX-DM070D luôn có thừa khả năng lưu trữ và tốc độ ấn tượng. Lưu trữ tất cả những nội dung mà bạn cần và thỏa mãn đam mê tốc độ của bạn.



Nâng cấp tức thì một cách dễ dàng

Rất đơn giản để nâng tầm chiếc GL552VX-DM070D của bạn. Tấm nâng cấp có thể được trượt ra để tiếp cận trực tiếp khay nâng cấp bên trong, để bạn có thể lắp thêm ổ SSD M.2, một ổ cứng lớn hơn hoặc thêm bộ nhớ tùy nhu cầu của mình.

Frag siêu nhanh, luôn dẫn đầu

Công nghệ GameFirst III trên laptop GL552VX-DM070D ưu tiên lưu lượng dữ liệu game trên toàn hệ thống mạng của bạn. Trải nghiệm chơi game sẽ được mượt hơn, và hiện tượng lag chỉ còn là dĩ vãng — để chỉ số frag của bạn luôn được đặt lên đầu.

Sự tinh tế của màn hình Full HD, không hề gây nhức mắt



Màn hiển thị 15,6 inch IPS Full HD rất lớn của GL552VX-DM070D có một lớp hoàn tất nhám đen triệt tiêu hiện tượng nhức mắt cho trải nghiệm hình ảnh vượt trội. Và với góc nhìn rộng 178 độ, bạn sẽ được thưởng thức những màu sắc hoành tráng sinh động từ cả những vị trí nhìn hẹp nhất.

Chất lượng âm thanh tuyệt hảo



Máy tính xách tay Asus GL552VX-DM070D phát ra những âm thanh trung thực nhất, rõ ràng và mạnh mẽ nhất với công nghệ SonicMaster độc quyền, trong khi tiện ích ROG AudioWizard đi kèm lại cung cấp đến năm chế độ thiết lập sẵn dành riêng cho các dòng game phổ biến – giúp bạn đắm chìm trong thế giới game tuyệt vời.', 2, '24 tháng', 'eKd8ns2m.jpg
OLpgTZO.jpg', 'eKd8ns2m.jpg');
INSERT INTO product VALUES (5, 'Apple iPhone 7 32GB (Đen) - Hàng nhập khẩu  ', 1, 'Hệ điều hành	iOS 10
Màn hình	4.7inch LED-backlit IPS LCD 750 x 1334
Bộ xử lý	lõi tứ Apple A10 Fusion 2.23GHz
RAM	2GBGB
Bộ nhớ trong	32GB
Pin	Non-removable Li-Po 1960 mAh', 13673000, 'SKU	HP496ELAA2OZT4VNAMZ-4616598
Kích	thước màn hình	14.0
Hard	Drive Capacity	120GB & Dưới 120GB
Mẫu mã	laptop HP
Processor Type	Quad-core
Thời gian bảo hành	12 tháng
Loại hình bảo hành	Bằng Hóa đơn mua hàng
', 'Giới thiệu về Laptop Asus GL552VX-DM070D i7-6700HQ 15.6 inch (Đen) - Hãng phân phối chính thức

Máy tính xách tay ASUS GL552VX-DM070D sở hữu hiệu năng chơi game tối ưu, thiết kế chi tiết, chất lượng hình ảnh hoàn hảo và nâng cấp tức thời. Laptop ROG GL552VX-DM070D luôn sẵn sàng cho hoạt động giải trí.

Hiệu năng ấn tượng cho chơi game và xử lý dữ liệu



Máy tính xách tay ASUS GL552VX-DM070D với cấu hình lên đến bộ vi xử lý Intel® Core™ i7 lõi tứ thế hệ thứ 4 và đồ họa NVIDIA® GeForce® GTX™ chuyên dụng cùng hỗ trợ Microsoft DirectX 12, Laptop được thiết kế dành cho game thủ và cũng rất thích hợp cho các công việc sáng tạo. Trải nghiệm bất cứ game nào hay chạy bất kỳ ứng dụng nào với chất lượng hình ảnh sắc nét hơn và hiệu năng vượt trội vượt trên cả kỳ vọng của bạn.

Máy tính xách tay ASUS GL552VX-DM070D có thiết kế vừa bền bỉ vừa phong cách, lấy cảm hứng từ máy bay tàng hình F-22 và chi tiết Mayan tinh xảo trên một bàn phím đẹp mắt có lắp đèn nền.

Bàn phím tinh chỉnh giúp bạn điều khiển cuộc chơi



Những phím gọn, dễ bấm của ASUS GL552VX-DM070D cho khoảng cách bấm phím 1,8mm hoàn hảo về độ nhạy; nhóm phím WASD được đánh dấu đặc biệt và bộ phím số chuyên dụng cho phép bạn kiểm soát nhanh, toàn diện. Một bàn phím xây dựng nguyên khối vững chắc được chiếu sáng bởi đèn nền ROG màu đỏ giúp dễ nhìn ở mọi hướng và mọi thời điểm.

Khả năng lưu trữ và tốc độ đáng kinh ngạc

Với ổ cứng lên đến 1 TB 7200rpm và ổ lưu trữ thể rắn (SSD) M.2 lên đến 256GB, GL552VX-DM070D luôn có thừa khả năng lưu trữ và tốc độ ấn tượng. Lưu trữ tất cả những nội dung mà bạn cần và thỏa mãn đam mê tốc độ của bạn.



Nâng cấp tức thì một cách dễ dàng

Rất đơn giản để nâng tầm chiếc GL552VX-DM070D của bạn. Tấm nâng cấp có thể được trượt ra để tiếp cận trực tiếp khay nâng cấp bên trong, để bạn có thể lắp thêm ổ SSD M.2, một ổ cứng lớn hơn hoặc thêm bộ nhớ tùy nhu cầu của mình.

Frag siêu nhanh, luôn dẫn đầu

Công nghệ GameFirst III trên laptop GL552VX-DM070D ưu tiên lưu lượng dữ liệu game trên toàn hệ thống mạng của bạn. Trải nghiệm chơi game sẽ được mượt hơn, và hiện tượng lag chỉ còn là dĩ vãng — để chỉ số frag của bạn luôn được đặt lên đầu.

Sự tinh tế của màn hình Full HD, không hề gây nhức mắt



Màn hiển thị 15,6 inch IPS Full HD rất lớn của GL552VX-DM070D có một lớp hoàn tất nhám đen triệt tiêu hiện tượng nhức mắt cho trải nghiệm hình ảnh vượt trội. Và với góc nhìn rộng 178 độ, bạn sẽ được thưởng thức những màu sắc hoành tráng sinh động từ cả những vị trí nhìn hẹp nhất.

Chất lượng âm thanh tuyệt hảo



Máy tính xách tay Asus GL552VX-DM070D phát ra những âm thanh trung thực nhất, rõ ràng và mạnh mẽ nhất với công nghệ SonicMaster độc quyền, trong khi tiện ích ROG AudioWizard đi kèm lại cung cấp đến năm chế độ thiết lập sẵn dành riêng cho các dòng game phổ biến – giúp bạn đắm chìm trong thế giới game tuyệt vời.', 1, '12', 'ip732blk.jpg', 'iphone7-model-select-201703.jpg');
INSERT INTO product VALUES (6, 'Laptop Asus GL552VX-DM143D 15.6inch (Đen) - Hàng Phân phối chính thức ', 1, 'CPU: Intel Core i5 6300HQ 4*2.3GHz up to 3.2GHz 6Mb
RAM: 8GB DDR4 2133MHz
Đĩa cứng: HDD 1TB 7200rpm
Card đồ họa: NVIDIA GeForce GTX 950M 4GB GDDR5 + Intel HD Graphics 530
Màn hình: 15.6 inch Full HD (1920 x 1080 pixels) LED + Anti-Glare WV', 16478000, 'SKU	HP496ELAA2OZT4VNAMZ-4616598
Kích	thước màn hình	14.0
Hard	Drive Capacity	120GB & Dưới 120GB
Mẫu mã	laptop HP
Processor Type	Quad-core
Thời gian bảo hành	12 tháng
Loại hình bảo hành	Bằng Hóa đơn mua hàng
', 'Giới thiệu về Laptop Asus GL552VX-DM070D i7-6700HQ 15.6 inch (Đen) - Hãng phân phối chính thức

Máy tính xách tay ASUS GL552VX-DM070D sở hữu hiệu năng chơi game tối ưu, thiết kế chi tiết, chất lượng hình ảnh hoàn hảo và nâng cấp tức thời. Laptop ROG GL552VX-DM070D luôn sẵn sàng cho hoạt động giải trí.

Hiệu năng ấn tượng cho chơi game và xử lý dữ liệu



Máy tính xách tay ASUS GL552VX-DM070D với cấu hình lên đến bộ vi xử lý Intel® Core™ i7 lõi tứ thế hệ thứ 4 và đồ họa NVIDIA® GeForce® GTX™ chuyên dụng cùng hỗ trợ Microsoft DirectX 12, Laptop được thiết kế dành cho game thủ và cũng rất thích hợp cho các công việc sáng tạo. Trải nghiệm bất cứ game nào hay chạy bất kỳ ứng dụng nào với chất lượng hình ảnh sắc nét hơn và hiệu năng vượt trội vượt trên cả kỳ vọng của bạn.

Máy tính xách tay ASUS GL552VX-DM070D có thiết kế vừa bền bỉ vừa phong cách, lấy cảm hứng từ máy bay tàng hình F-22 và chi tiết Mayan tinh xảo trên một bàn phím đẹp mắt có lắp đèn nền.

Bàn phím tinh chỉnh giúp bạn điều khiển cuộc chơi



Những phím gọn, dễ bấm của ASUS GL552VX-DM070D cho khoảng cách bấm phím 1,8mm hoàn hảo về độ nhạy; nhóm phím WASD được đánh dấu đặc biệt và bộ phím số chuyên dụng cho phép bạn kiểm soát nhanh, toàn diện. Một bàn phím xây dựng nguyên khối vững chắc được chiếu sáng bởi đèn nền ROG màu đỏ giúp dễ nhìn ở mọi hướng và mọi thời điểm.

Khả năng lưu trữ và tốc độ đáng kinh ngạc

Với ổ cứng lên đến 1 TB 7200rpm và ổ lưu trữ thể rắn (SSD) M.2 lên đến 256GB, GL552VX-DM070D luôn có thừa khả năng lưu trữ và tốc độ ấn tượng. Lưu trữ tất cả những nội dung mà bạn cần và thỏa mãn đam mê tốc độ của bạn.



Nâng cấp tức thì một cách dễ dàng

Rất đơn giản để nâng tầm chiếc GL552VX-DM070D của bạn. Tấm nâng cấp có thể được trượt ra để tiếp cận trực tiếp khay nâng cấp bên trong, để bạn có thể lắp thêm ổ SSD M.2, một ổ cứng lớn hơn hoặc thêm bộ nhớ tùy nhu cầu của mình.

Frag siêu nhanh, luôn dẫn đầu

Công nghệ GameFirst III trên laptop GL552VX-DM070D ưu tiên lưu lượng dữ liệu game trên toàn hệ thống mạng của bạn. Trải nghiệm chơi game sẽ được mượt hơn, và hiện tượng lag chỉ còn là dĩ vãng — để chỉ số frag của bạn luôn được đặt lên đầu.

Sự tinh tế của màn hình Full HD, không hề gây nhức mắt



Màn hiển thị 15,6 inch IPS Full HD rất lớn của GL552VX-DM070D có một lớp hoàn tất nhám đen triệt tiêu hiện tượng nhức mắt cho trải nghiệm hình ảnh vượt trội. Và với góc nhìn rộng 178 độ, bạn sẽ được thưởng thức những màu sắc hoành tráng sinh động từ cả những vị trí nhìn hẹp nhất.

Chất lượng âm thanh tuyệt hảo



Máy tính xách tay Asus GL552VX-DM070D phát ra những âm thanh trung thực nhất, rõ ràng và mạnh mẽ nhất với công nghệ SonicMaster độc quyền, trong khi tiện ích ROG AudioWizard đi kèm lại cung cấp đến năm chế độ thiết lập sẵn dành riêng cho các dòng game phổ biến – giúp bạn đắm chìm trong thế giới game tuyệt vời.', 2, '24 tháng', 'ip732blk.jpg', 'OnsKa3.jpg');
INSERT INTO product VALUES (7, 'Apple iPhone 7 Plus 128GB (Đỏ) - Hàng nhập khẩu  ', 1, 'Giá đặc biệt sinh nhật Lazada - Chỉ 3.590.000 - Từ 21/3 tới 23/3
HĐH Android
Màn hình 5.5'''' Super AMOLED 1280 x 720
Xử lý Octa-Core 1.6GHz
RAM 2GB
Camera chính 13MP', 20380000, 'SKU	AP069ELAA305OUVNAMZ-5213985
Screen Size (inches)	5.0
Mẫu mã	Vinh Phat Mobile (Tp.HCM)-Apple Iphone 7 Plus 128GB
Operating System Version	iOS 7
Bộ nhớ trong	Not Specified
Thời gian bảo hành	12 tháng
Loại hình bảo hành	Bảo hành điện tử', 'Giới thiệu về Laptop Asus GL552VX-DM070D i7-6700HQ 15.6 inch (Đen) - Hãng phân phối chính thức

Máy tính xách tay ASUS GL552VX-DM070D sở hữu hiệu năng chơi game tối ưu, thiết kế chi tiết, chất lượng hình ảnh hoàn hảo và nâng cấp tức thời. Laptop ROG GL552VX-DM070D luôn sẵn sàng cho hoạt động giải trí.

Hiệu năng ấn tượng cho chơi game và xử lý dữ liệu



Máy tính xách tay ASUS GL552VX-DM070D với cấu hình lên đến bộ vi xử lý Intel® Core™ i7 lõi tứ thế hệ thứ 4 và đồ họa NVIDIA® GeForce® GTX™ chuyên dụng cùng hỗ trợ Microsoft DirectX 12, Laptop được thiết kế dành cho game thủ và cũng rất thích hợp cho các công việc sáng tạo. Trải nghiệm bất cứ game nào hay chạy bất kỳ ứng dụng nào với chất lượng hình ảnh sắc nét hơn và hiệu năng vượt trội vượt trên cả kỳ vọng của bạn.

Máy tính xách tay ASUS GL552VX-DM070D có thiết kế vừa bền bỉ vừa phong cách, lấy cảm hứng từ máy bay tàng hình F-22 và chi tiết Mayan tinh xảo trên một bàn phím đẹp mắt có lắp đèn nền.

Bàn phím tinh chỉnh giúp bạn điều khiển cuộc chơi



Những phím gọn, dễ bấm của ASUS GL552VX-DM070D cho khoảng cách bấm phím 1,8mm hoàn hảo về độ nhạy; nhóm phím WASD được đánh dấu đặc biệt và bộ phím số chuyên dụng cho phép bạn kiểm soát nhanh, toàn diện. Một bàn phím xây dựng nguyên khối vững chắc được chiếu sáng bởi đèn nền ROG màu đỏ giúp dễ nhìn ở mọi hướng và mọi thời điểm.

Khả năng lưu trữ và tốc độ đáng kinh ngạc

Với ổ cứng lên đến 1 TB 7200rpm và ổ lưu trữ thể rắn (SSD) M.2 lên đến 256GB, GL552VX-DM070D luôn có thừa khả năng lưu trữ và tốc độ ấn tượng. Lưu trữ tất cả những nội dung mà bạn cần và thỏa mãn đam mê tốc độ của bạn.



Nâng cấp tức thì một cách dễ dàng

Rất đơn giản để nâng tầm chiếc GL552VX-DM070D của bạn. Tấm nâng cấp có thể được trượt ra để tiếp cận trực tiếp khay nâng cấp bên trong, để bạn có thể lắp thêm ổ SSD M.2, một ổ cứng lớn hơn hoặc thêm bộ nhớ tùy nhu cầu của mình.

Frag siêu nhanh, luôn dẫn đầu

Công nghệ GameFirst III trên laptop GL552VX-DM070D ưu tiên lưu lượng dữ liệu game trên toàn hệ thống mạng của bạn. Trải nghiệm chơi game sẽ được mượt hơn, và hiện tượng lag chỉ còn là dĩ vãng — để chỉ số frag của bạn luôn được đặt lên đầu.

Sự tinh tế của màn hình Full HD, không hề gây nhức mắt



Màn hiển thị 15,6 inch IPS Full HD rất lớn của GL552VX-DM070D có một lớp hoàn tất nhám đen triệt tiêu hiện tượng nhức mắt cho trải nghiệm hình ảnh vượt trội. Và với góc nhìn rộng 178 độ, bạn sẽ được thưởng thức những màu sắc hoành tráng sinh động từ cả những vị trí nhìn hẹp nhất.

Chất lượng âm thanh tuyệt hảo



Máy tính xách tay Asus GL552VX-DM070D phát ra những âm thanh trung thực nhất, rõ ràng và mạnh mẽ nhất với công nghệ SonicMaster độc quyền, trong khi tiện ích ROG AudioWizard đi kèm lại cung cấp đến năm chế độ thiết lập sẵn dành riêng cho các dòng game phổ biến – giúp bạn đắm chìm trong thế giới game tuyệt vời.', 1, '12', 'ipRed1.jpg

', 'iphone7-model-select-201703.jpg');
INSERT INTO product VALUES (8, 'Samsung Galaxy A3 2016 16GB (Vàng Hồng) - Hãng phân phối chính thức', 2, 'Giá đặc biệt sinh nhật Lazada - Chỉ 3.590.000 - Từ 21/3 tới 23/3
HĐH Android
Màn hình 5.5'''' Super AMOLED 1280 x 720
Xử lý Octa-Core 1.6GHz
RAM 2GB
Camera chính 13MP', 3590000, 'SKU	HP496ELAA2OZT4VNAMZ-4616598
Kích	thước màn hình	14.0
Hard	Drive Capacity	120GB & Dưới 120GB
Mẫu mã	laptop HP
Processor Type	Quad-core
Thời gian bảo hành	12 tháng
Loại hình bảo hành	Bằng Hóa đơn mua hàng
', 'Giới thiệu về Laptop Asus GL552VX-DM070D i7-6700HQ 15.6 inch (Đen) - Hãng phân phối chính thức

Máy tính xách tay ASUS GL552VX-DM070D sở hữu hiệu năng chơi game tối ưu, thiết kế chi tiết, chất lượng hình ảnh hoàn hảo và nâng cấp tức thời. Laptop ROG GL552VX-DM070D luôn sẵn sàng cho hoạt động giải trí.

Hiệu năng ấn tượng cho chơi game và xử lý dữ liệu



Máy tính xách tay ASUS GL552VX-DM070D với cấu hình lên đến bộ vi xử lý Intel® Core™ i7 lõi tứ thế hệ thứ 4 và đồ họa NVIDIA® GeForce® GTX™ chuyên dụng cùng hỗ trợ Microsoft DirectX 12, Laptop được thiết kế dành cho game thủ và cũng rất thích hợp cho các công việc sáng tạo. Trải nghiệm bất cứ game nào hay chạy bất kỳ ứng dụng nào với chất lượng hình ảnh sắc nét hơn và hiệu năng vượt trội vượt trên cả kỳ vọng của bạn.

Máy tính xách tay ASUS GL552VX-DM070D có thiết kế vừa bền bỉ vừa phong cách, lấy cảm hứng từ máy bay tàng hình F-22 và chi tiết Mayan tinh xảo trên một bàn phím đẹp mắt có lắp đèn nền.

Bàn phím tinh chỉnh giúp bạn điều khiển cuộc chơi



Những phím gọn, dễ bấm của ASUS GL552VX-DM070D cho khoảng cách bấm phím 1,8mm hoàn hảo về độ nhạy; nhóm phím WASD được đánh dấu đặc biệt và bộ phím số chuyên dụng cho phép bạn kiểm soát nhanh, toàn diện. Một bàn phím xây dựng nguyên khối vững chắc được chiếu sáng bởi đèn nền ROG màu đỏ giúp dễ nhìn ở mọi hướng và mọi thời điểm.

Khả năng lưu trữ và tốc độ đáng kinh ngạc

Với ổ cứng lên đến 1 TB 7200rpm và ổ lưu trữ thể rắn (SSD) M.2 lên đến 256GB, GL552VX-DM070D luôn có thừa khả năng lưu trữ và tốc độ ấn tượng. Lưu trữ tất cả những nội dung mà bạn cần và thỏa mãn đam mê tốc độ của bạn.



Nâng cấp tức thì một cách dễ dàng

Rất đơn giản để nâng tầm chiếc GL552VX-DM070D của bạn. Tấm nâng cấp có thể được trượt ra để tiếp cận trực tiếp khay nâng cấp bên trong, để bạn có thể lắp thêm ổ SSD M.2, một ổ cứng lớn hơn hoặc thêm bộ nhớ tùy nhu cầu của mình.

Frag siêu nhanh, luôn dẫn đầu

Công nghệ GameFirst III trên laptop GL552VX-DM070D ưu tiên lưu lượng dữ liệu game trên toàn hệ thống mạng của bạn. Trải nghiệm chơi game sẽ được mượt hơn, và hiện tượng lag chỉ còn là dĩ vãng — để chỉ số frag của bạn luôn được đặt lên đầu.

Sự tinh tế của màn hình Full HD, không hề gây nhức mắt



Màn hiển thị 15,6 inch IPS Full HD rất lớn của GL552VX-DM070D có một lớp hoàn tất nhám đen triệt tiêu hiện tượng nhức mắt cho trải nghiệm hình ảnh vượt trội. Và với góc nhìn rộng 178 độ, bạn sẽ được thưởng thức những màu sắc hoành tráng sinh động từ cả những vị trí nhìn hẹp nhất.

Chất lượng âm thanh tuyệt hảo



Máy tính xách tay Asus GL552VX-DM070D phát ra những âm thanh trung thực nhất, rõ ràng và mạnh mẽ nhất với công nghệ SonicMaster độc quyền, trong khi tiện ích ROG AudioWizard đi kèm lại cung cấp đến năm chế độ thiết lập sẵn dành riêng cho các dòng game phổ biến – giúp bạn đắm chìm trong thế giới game tuyệt vời.', 3, '12 tháng', 'ip732blk.jpg', 'cF34kc.jpg');
INSERT INTO product VALUES (9, 'Laptop HP Folio 9470M core i5 ram 4g ssd 120g 14.0( màu bạc) - Hàng nhập khâu ', 1, 'CPU Intel Core i5 Ivy Bridge Ram
4GB SSD 120GB
Chipset đồ họa
Intel HD Graphics 4000 LCD
≥ 14 inch Thời lượng pin
≥ 4 giờ Chất liệu vỏ
Vỏ kim loại Trọng lượng
≤ 1.8kg Dòng máy
Ultrabook', 8000000, 'SKU	HP496ELAA2OZT4VNAMZ-4616598
Kích	thước màn hình	14.0
Hard	Drive Capacity	120GB & Dưới 120GB
Mẫu mã	laptop HP
Processor Type	Quad-core
Thời gian bảo hành	12 tháng
Loại hình bảo hành	Bằng Hóa đơn mua hàng
', 'Giới thiệu về Laptop Asus GL552VX-DM070D i7-6700HQ 15.6 inch (Đen) - Hãng phân phối chính thức

Máy tính xách tay ASUS GL552VX-DM070D sở hữu hiệu năng chơi game tối ưu, thiết kế chi tiết, chất lượng hình ảnh hoàn hảo và nâng cấp tức thời. Laptop ROG GL552VX-DM070D luôn sẵn sàng cho hoạt động giải trí.

Hiệu năng ấn tượng cho chơi game và xử lý dữ liệu



Máy tính xách tay ASUS GL552VX-DM070D với cấu hình lên đến bộ vi xử lý Intel® Core™ i7 lõi tứ thế hệ thứ 4 và đồ họa NVIDIA® GeForce® GTX™ chuyên dụng cùng hỗ trợ Microsoft DirectX 12, Laptop được thiết kế dành cho game thủ và cũng rất thích hợp cho các công việc sáng tạo. Trải nghiệm bất cứ game nào hay chạy bất kỳ ứng dụng nào với chất lượng hình ảnh sắc nét hơn và hiệu năng vượt trội vượt trên cả kỳ vọng của bạn.

Máy tính xách tay ASUS GL552VX-DM070D có thiết kế vừa bền bỉ vừa phong cách, lấy cảm hứng từ máy bay tàng hình F-22 và chi tiết Mayan tinh xảo trên một bàn phím đẹp mắt có lắp đèn nền.

Bàn phím tinh chỉnh giúp bạn điều khiển cuộc chơi



Những phím gọn, dễ bấm của ASUS GL552VX-DM070D cho khoảng cách bấm phím 1,8mm hoàn hảo về độ nhạy; nhóm phím WASD được đánh dấu đặc biệt và bộ phím số chuyên dụng cho phép bạn kiểm soát nhanh, toàn diện. Một bàn phím xây dựng nguyên khối vững chắc được chiếu sáng bởi đèn nền ROG màu đỏ giúp dễ nhìn ở mọi hướng và mọi thời điểm.

Khả năng lưu trữ và tốc độ đáng kinh ngạc

Với ổ cứng lên đến 1 TB 7200rpm và ổ lưu trữ thể rắn (SSD) M.2 lên đến 256GB, GL552VX-DM070D luôn có thừa khả năng lưu trữ và tốc độ ấn tượng. Lưu trữ tất cả những nội dung mà bạn cần và thỏa mãn đam mê tốc độ của bạn.



Nâng cấp tức thì một cách dễ dàng

Rất đơn giản để nâng tầm chiếc GL552VX-DM070D của bạn. Tấm nâng cấp có thể được trượt ra để tiếp cận trực tiếp khay nâng cấp bên trong, để bạn có thể lắp thêm ổ SSD M.2, một ổ cứng lớn hơn hoặc thêm bộ nhớ tùy nhu cầu của mình.

Frag siêu nhanh, luôn dẫn đầu

Công nghệ GameFirst III trên laptop GL552VX-DM070D ưu tiên lưu lượng dữ liệu game trên toàn hệ thống mạng của bạn. Trải nghiệm chơi game sẽ được mượt hơn, và hiện tượng lag chỉ còn là dĩ vãng — để chỉ số frag của bạn luôn được đặt lên đầu.

Sự tinh tế của màn hình Full HD, không hề gây nhức mắt



Màn hiển thị 15,6 inch IPS Full HD rất lớn của GL552VX-DM070D có một lớp hoàn tất nhám đen triệt tiêu hiện tượng nhức mắt cho trải nghiệm hình ảnh vượt trội. Và với góc nhìn rộng 178 độ, bạn sẽ được thưởng thức những màu sắc hoành tráng sinh động từ cả những vị trí nhìn hẹp nhất.

Chất lượng âm thanh tuyệt hảo



Máy tính xách tay Asus GL552VX-DM070D phát ra những âm thanh trung thực nhất, rõ ràng và mạnh mẽ nhất với công nghệ SonicMaster độc quyền, trong khi tiện ích ROG AudioWizard đi kèm lại cung cấp đến năm chế độ thiết lập sẵn dành riêng cho các dòng game phổ biến – giúp bạn đắm chìm trong thế giới game tuyệt vời.', 1, '12', 'hpf9470.jpg', 'c05116200_1750x1285.jpg');
INSERT INTO product VALUES (10, 'Laptop Asus X441UA-WX017D 14 inch (Trắng)  ', 1, 'Core i3 6100U, upto 2.3 Ghz, 4GB, 500GB, Intel HD520, DVDRW
Màn hình 14.0"
Dos
', 11370000, 'SKU	HP496ELAA2OZT4VNAMZ-4616598
Kích	thước màn hình	14.0
Hard	Drive Capacity	120GB & Dưới 120GB
Mẫu mã	laptop HP
Processor Type	Quad-core
Thời gian bảo hành	12 tháng
Loại hình bảo hành	Bằng Hóa đơn mua hàng
', 'Giới thiệu về Laptop Asus GL552VX-DM070D i7-6700HQ 15.6 inch (Đen) - Hãng phân phối chính thức

Máy tính xách tay ASUS GL552VX-DM070D sở hữu hiệu năng chơi game tối ưu, thiết kế chi tiết, chất lượng hình ảnh hoàn hảo và nâng cấp tức thời. Laptop ROG GL552VX-DM070D luôn sẵn sàng cho hoạt động giải trí.

Hiệu năng ấn tượng cho chơi game và xử lý dữ liệu



Máy tính xách tay ASUS GL552VX-DM070D với cấu hình lên đến bộ vi xử lý Intel® Core™ i7 lõi tứ thế hệ thứ 4 và đồ họa NVIDIA® GeForce® GTX™ chuyên dụng cùng hỗ trợ Microsoft DirectX 12, Laptop được thiết kế dành cho game thủ và cũng rất thích hợp cho các công việc sáng tạo. Trải nghiệm bất cứ game nào hay chạy bất kỳ ứng dụng nào với chất lượng hình ảnh sắc nét hơn và hiệu năng vượt trội vượt trên cả kỳ vọng của bạn.

Máy tính xách tay ASUS GL552VX-DM070D có thiết kế vừa bền bỉ vừa phong cách, lấy cảm hứng từ máy bay tàng hình F-22 và chi tiết Mayan tinh xảo trên một bàn phím đẹp mắt có lắp đèn nền.

Bàn phím tinh chỉnh giúp bạn điều khiển cuộc chơi



Những phím gọn, dễ bấm của ASUS GL552VX-DM070D cho khoảng cách bấm phím 1,8mm hoàn hảo về độ nhạy; nhóm phím WASD được đánh dấu đặc biệt và bộ phím số chuyên dụng cho phép bạn kiểm soát nhanh, toàn diện. Một bàn phím xây dựng nguyên khối vững chắc được chiếu sáng bởi đèn nền ROG màu đỏ giúp dễ nhìn ở mọi hướng và mọi thời điểm.

Khả năng lưu trữ và tốc độ đáng kinh ngạc

Với ổ cứng lên đến 1 TB 7200rpm và ổ lưu trữ thể rắn (SSD) M.2 lên đến 256GB, GL552VX-DM070D luôn có thừa khả năng lưu trữ và tốc độ ấn tượng. Lưu trữ tất cả những nội dung mà bạn cần và thỏa mãn đam mê tốc độ của bạn.



Nâng cấp tức thì một cách dễ dàng

Rất đơn giản để nâng tầm chiếc GL552VX-DM070D của bạn. Tấm nâng cấp có thể được trượt ra để tiếp cận trực tiếp khay nâng cấp bên trong, để bạn có thể lắp thêm ổ SSD M.2, một ổ cứng lớn hơn hoặc thêm bộ nhớ tùy nhu cầu của mình.

Frag siêu nhanh, luôn dẫn đầu

Công nghệ GameFirst III trên laptop GL552VX-DM070D ưu tiên lưu lượng dữ liệu game trên toàn hệ thống mạng của bạn. Trải nghiệm chơi game sẽ được mượt hơn, và hiện tượng lag chỉ còn là dĩ vãng — để chỉ số frag của bạn luôn được đặt lên đầu.

Sự tinh tế của màn hình Full HD, không hề gây nhức mắt



Màn hiển thị 15,6 inch IPS Full HD rất lớn của GL552VX-DM070D có một lớp hoàn tất nhám đen triệt tiêu hiện tượng nhức mắt cho trải nghiệm hình ảnh vượt trội. Và với góc nhìn rộng 178 độ, bạn sẽ được thưởng thức những màu sắc hoành tráng sinh động từ cả những vị trí nhìn hẹp nhất.

Chất lượng âm thanh tuyệt hảo



Máy tính xách tay Asus GL552VX-DM070D phát ra những âm thanh trung thực nhất, rõ ràng và mạnh mẽ nhất với công nghệ SonicMaster độc quyền, trong khi tiện ích ROG AudioWizard đi kèm lại cung cấp đến năm chế độ thiết lập sẵn dành riêng cho các dòng game phổ biến – giúp bạn đắm chìm trong thế giới game tuyệt vời.', 2, '24 tháng', 'ip732blk.jpg', 'pKns2j.jpg');
INSERT INTO product VALUES (11, 'Samsung Galaxy A3 2016 16GB (Vàng) - Hãng phân phối chính thức  ', 2, 'Màn hình 4.7" Super AMOLED
Độ phân giải 720 x 1280 pixels
Kính cường lực Gorilla Glass 4, Mặt kính 2.5D
Khẩu độ f1.9, Camera sau 13 MP, Camera trước 5 MP
OS: Android 5.1 (Lollipop)
Chipset Exynos 7578 4 nhân 64-bit 1.5 GHz
Chip đồ họa (GPU) Mali T720
RAM 1.5 GB, ROM 16 GB, Hỗ trợ thẻ 128 GB
Kết nối 2G,3G, 4G, Wifi, Bluetooth, NFC
2 SIM Nano, SIM 2 chung khe thẻ nhớ
Thiết kế Nguyên khối, Khung kim loại
Dung lượng pin: 2300 mAh', 3590000, 'SKU	HP496ELAA2OZT4VNAMZ-4616598
Kích	thước màn hình	14.0
Hard	Drive Capacity	120GB & Dưới 120GB
Mẫu mã	laptop HP
Processor Type	Quad-core
Thời gian bảo hành	12 tháng
Loại hình bảo hành	Bằng Hóa đơn mua hàng
', 'Giới thiệu về Laptop Asus GL552VX-DM070D i7-6700HQ 15.6 inch (Đen) - Hãng phân phối chính thức

Máy tính xách tay ASUS GL552VX-DM070D sở hữu hiệu năng chơi game tối ưu, thiết kế chi tiết, chất lượng hình ảnh hoàn hảo và nâng cấp tức thời. Laptop ROG GL552VX-DM070D luôn sẵn sàng cho hoạt động giải trí.

Hiệu năng ấn tượng cho chơi game và xử lý dữ liệu



Máy tính xách tay ASUS GL552VX-DM070D với cấu hình lên đến bộ vi xử lý Intel® Core™ i7 lõi tứ thế hệ thứ 4 và đồ họa NVIDIA® GeForce® GTX™ chuyên dụng cùng hỗ trợ Microsoft DirectX 12, Laptop được thiết kế dành cho game thủ và cũng rất thích hợp cho các công việc sáng tạo. Trải nghiệm bất cứ game nào hay chạy bất kỳ ứng dụng nào với chất lượng hình ảnh sắc nét hơn và hiệu năng vượt trội vượt trên cả kỳ vọng của bạn.

Máy tính xách tay ASUS GL552VX-DM070D có thiết kế vừa bền bỉ vừa phong cách, lấy cảm hứng từ máy bay tàng hình F-22 và chi tiết Mayan tinh xảo trên một bàn phím đẹp mắt có lắp đèn nền.

Bàn phím tinh chỉnh giúp bạn điều khiển cuộc chơi



Những phím gọn, dễ bấm của ASUS GL552VX-DM070D cho khoảng cách bấm phím 1,8mm hoàn hảo về độ nhạy; nhóm phím WASD được đánh dấu đặc biệt và bộ phím số chuyên dụng cho phép bạn kiểm soát nhanh, toàn diện. Một bàn phím xây dựng nguyên khối vững chắc được chiếu sáng bởi đèn nền ROG màu đỏ giúp dễ nhìn ở mọi hướng và mọi thời điểm.

Khả năng lưu trữ và tốc độ đáng kinh ngạc

Với ổ cứng lên đến 1 TB 7200rpm và ổ lưu trữ thể rắn (SSD) M.2 lên đến 256GB, GL552VX-DM070D luôn có thừa khả năng lưu trữ và tốc độ ấn tượng. Lưu trữ tất cả những nội dung mà bạn cần và thỏa mãn đam mê tốc độ của bạn.



Nâng cấp tức thì một cách dễ dàng

Rất đơn giản để nâng tầm chiếc GL552VX-DM070D của bạn. Tấm nâng cấp có thể được trượt ra để tiếp cận trực tiếp khay nâng cấp bên trong, để bạn có thể lắp thêm ổ SSD M.2, một ổ cứng lớn hơn hoặc thêm bộ nhớ tùy nhu cầu của mình.

Frag siêu nhanh, luôn dẫn đầu

Công nghệ GameFirst III trên laptop GL552VX-DM070D ưu tiên lưu lượng dữ liệu game trên toàn hệ thống mạng của bạn. Trải nghiệm chơi game sẽ được mượt hơn, và hiện tượng lag chỉ còn là dĩ vãng — để chỉ số frag của bạn luôn được đặt lên đầu.

Sự tinh tế của màn hình Full HD, không hề gây nhức mắt



Màn hiển thị 15,6 inch IPS Full HD rất lớn của GL552VX-DM070D có một lớp hoàn tất nhám đen triệt tiêu hiện tượng nhức mắt cho trải nghiệm hình ảnh vượt trội. Và với góc nhìn rộng 178 độ, bạn sẽ được thưởng thức những màu sắc hoành tráng sinh động từ cả những vị trí nhìn hẹp nhất.

Chất lượng âm thanh tuyệt hảo



Máy tính xách tay Asus GL552VX-DM070D phát ra những âm thanh trung thực nhất, rõ ràng và mạnh mẽ nhất với công nghệ SonicMaster độc quyền, trong khi tiện ích ROG AudioWizard đi kèm lại cung cấp đến năm chế độ thiết lập sẵn dành riêng cho các dòng game phổ biến – giúp bạn đắm chìm trong thế giới game tuyệt vời.', 3, '12 tháng', 'ip732blk.jpg', 'j78Hns.jpg');
INSERT INTO product VALUES (12, 'Asus E200HA-FD0043TS - Hãng Phân phối chính thức', 1, 'Bộ vi xử lý : Intel® Atom™ x5-Z8350 (2M Cache, 1.44 GHz up to 1.92 GHz)
Bộ nhớ RAM : 2 GB
Đồ họa : Intel® HD Graphics 400
Ổ đĩa cứng : 32 GB (eMMC)
Màn hình : 11.6 inch (HD LED 1366 x 768 pixels)
Hệ điều hành : Windows 10 + Office 365(Dùng thử)
Kết nối : LAN, WIFI. BLUETOOTH, USB 2.0, USB 3.0
Pin : 2 Cell
Kích thước màn hình : 11.6 inch
Độ phân giải (W x H) : 1366 x 768 pixels
Công nghệ màn hình : HD
Cảm ứng : Không
Hãng CPU : Intel
Công nghệ CPU : Atom x5
Loại CPU : Z8350
Tốc độ : 1.44 GHz
Bộ nhớ đệm : 2 MB Cache
Tốc độ tối đa : 1.9 GHz
Dung lượng RAM : 2 GB
Nâng cấp RAM : Không
Số lượng khe RAM : 0 (onboard RAM)
', 4990000, 'SKU	HP496ELAA2OZT4VNAMZ-4616598
Kích	thước màn hình	14.0
Hard	Drive Capacity	120GB & Dưới 120GB
Mẫu mã	laptop HP
Processor Type	Quad-core
Thời gian bảo hành	12 tháng
Loại hình bảo hành	Bằng Hóa đơn mua hàng', 'Giới thiệu về Laptop Asus GL552VX-DM070D i7-6700HQ 15.6 inch (Đen) - Hãng phân phối chính thức

Máy tính xách tay ASUS GL552VX-DM070D sở hữu hiệu năng chơi game tối ưu, thiết kế chi tiết, chất lượng hình ảnh hoàn hảo và nâng cấp tức thời. Laptop ROG GL552VX-DM070D luôn sẵn sàng cho hoạt động giải trí.

Hiệu năng ấn tượng cho chơi game và xử lý dữ liệu



Máy tính xách tay ASUS GL552VX-DM070D với cấu hình lên đến bộ vi xử lý Intel® Core™ i7 lõi tứ thế hệ thứ 4 và đồ họa NVIDIA® GeForce® GTX™ chuyên dụng cùng hỗ trợ Microsoft DirectX 12, Laptop được thiết kế dành cho game thủ và cũng rất thích hợp cho các công việc sáng tạo. Trải nghiệm bất cứ game nào hay chạy bất kỳ ứng dụng nào với chất lượng hình ảnh sắc nét hơn và hiệu năng vượt trội vượt trên cả kỳ vọng của bạn.

Máy tính xách tay ASUS GL552VX-DM070D có thiết kế vừa bền bỉ vừa phong cách, lấy cảm hứng từ máy bay tàng hình F-22 và chi tiết Mayan tinh xảo trên một bàn phím đẹp mắt có lắp đèn nền.

Bàn phím tinh chỉnh giúp bạn điều khiển cuộc chơi



Những phím gọn, dễ bấm của ASUS GL552VX-DM070D cho khoảng cách bấm phím 1,8mm hoàn hảo về độ nhạy; nhóm phím WASD được đánh dấu đặc biệt và bộ phím số chuyên dụng cho phép bạn kiểm soát nhanh, toàn diện. Một bàn phím xây dựng nguyên khối vững chắc được chiếu sáng bởi đèn nền ROG màu đỏ giúp dễ nhìn ở mọi hướng và mọi thời điểm.

Khả năng lưu trữ và tốc độ đáng kinh ngạc

Với ổ cứng lên đến 1 TB 7200rpm và ổ lưu trữ thể rắn (SSD) M.2 lên đến 256GB, GL552VX-DM070D luôn có thừa khả năng lưu trữ và tốc độ ấn tượng. Lưu trữ tất cả những nội dung mà bạn cần và thỏa mãn đam mê tốc độ của bạn.



Nâng cấp tức thì một cách dễ dàng

Rất đơn giản để nâng tầm chiếc GL552VX-DM070D của bạn. Tấm nâng cấp có thể được trượt ra để tiếp cận trực tiếp khay nâng cấp bên trong, để bạn có thể lắp thêm ổ SSD M.2, một ổ cứng lớn hơn hoặc thêm bộ nhớ tùy nhu cầu của mình.

Frag siêu nhanh, luôn dẫn đầu

Công nghệ GameFirst III trên laptop GL552VX-DM070D ưu tiên lưu lượng dữ liệu game trên toàn hệ thống mạng của bạn. Trải nghiệm chơi game sẽ được mượt hơn, và hiện tượng lag chỉ còn là dĩ vãng — để chỉ số frag của bạn luôn được đặt lên đầu.

Sự tinh tế của màn hình Full HD, không hề gây nhức mắt



Màn hiển thị 15,6 inch IPS Full HD rất lớn của GL552VX-DM070D có một lớp hoàn tất nhám đen triệt tiêu hiện tượng nhức mắt cho trải nghiệm hình ảnh vượt trội. Và với góc nhìn rộng 178 độ, bạn sẽ được thưởng thức những màu sắc hoành tráng sinh động từ cả những vị trí nhìn hẹp nhất.

Chất lượng âm thanh tuyệt hảo



Máy tính xách tay Asus GL552VX-DM070D phát ra những âm thanh trung thực nhất, rõ ràng và mạnh mẽ nhất với công nghệ SonicMaster độc quyền, trong khi tiện ích ROG AudioWizard đi kèm lại cung cấp đến năm chế độ thiết lập sẵn dành riêng cho các dòng game phổ biến – giúp bạn đắm chìm trong thế giới game tuyệt vời.', 2, '12 tháng', 'ip732blk.jpg', 'hJbsF5.jpg');
INSERT INTO product VALUES (13, 'Laptop ASUS E402SA-WX251D ( Xanh đen) - Hãng phân phối chính thức', 1, 'Giá đặc biệt sinh nhật Lazada - Chỉ 3.590.000 - Từ 21/3 tới 23/3
HĐH Android
Màn hình 5.5'''' Super AMOLED 1280 x 720
Xử lý Octa-Core 1.6GHz
RAM 2GB
Camera chính 13MP', 4850000, 'SKU	HP496ELAA2OZT4VNAMZ-4616598
Kích	thước màn hình	14.0
Hard	Drive Capacity	120GB & Dưới 120GB
Mẫu mã	laptop HP
Processor Type	Quad-core
Thời gian bảo hành	12 tháng
Loại hình bảo hành	Bằng Hóa đơn mua hàng
', 'Giới thiệu về Laptop Asus GL552VX-DM070D i7-6700HQ 15.6 inch (Đen) - Hãng phân phối chính thức

Máy tính xách tay ASUS GL552VX-DM070D sở hữu hiệu năng chơi game tối ưu, thiết kế chi tiết, chất lượng hình ảnh hoàn hảo và nâng cấp tức thời. Laptop ROG GL552VX-DM070D luôn sẵn sàng cho hoạt động giải trí.

Hiệu năng ấn tượng cho chơi game và xử lý dữ liệu



Máy tính xách tay ASUS GL552VX-DM070D với cấu hình lên đến bộ vi xử lý Intel® Core™ i7 lõi tứ thế hệ thứ 4 và đồ họa NVIDIA® GeForce® GTX™ chuyên dụng cùng hỗ trợ Microsoft DirectX 12, Laptop được thiết kế dành cho game thủ và cũng rất thích hợp cho các công việc sáng tạo. Trải nghiệm bất cứ game nào hay chạy bất kỳ ứng dụng nào với chất lượng hình ảnh sắc nét hơn và hiệu năng vượt trội vượt trên cả kỳ vọng của bạn.

Máy tính xách tay ASUS GL552VX-DM070D có thiết kế vừa bền bỉ vừa phong cách, lấy cảm hứng từ máy bay tàng hình F-22 và chi tiết Mayan tinh xảo trên một bàn phím đẹp mắt có lắp đèn nền.

Bàn phím tinh chỉnh giúp bạn điều khiển cuộc chơi



Những phím gọn, dễ bấm của ASUS GL552VX-DM070D cho khoảng cách bấm phím 1,8mm hoàn hảo về độ nhạy; nhóm phím WASD được đánh dấu đặc biệt và bộ phím số chuyên dụng cho phép bạn kiểm soát nhanh, toàn diện. Một bàn phím xây dựng nguyên khối vững chắc được chiếu sáng bởi đèn nền ROG màu đỏ giúp dễ nhìn ở mọi hướng và mọi thời điểm.

Khả năng lưu trữ và tốc độ đáng kinh ngạc

Với ổ cứng lên đến 1 TB 7200rpm và ổ lưu trữ thể rắn (SSD) M.2 lên đến 256GB, GL552VX-DM070D luôn có thừa khả năng lưu trữ và tốc độ ấn tượng. Lưu trữ tất cả những nội dung mà bạn cần và thỏa mãn đam mê tốc độ của bạn.



Nâng cấp tức thì một cách dễ dàng

Rất đơn giản để nâng tầm chiếc GL552VX-DM070D của bạn. Tấm nâng cấp có thể được trượt ra để tiếp cận trực tiếp khay nâng cấp bên trong, để bạn có thể lắp thêm ổ SSD M.2, một ổ cứng lớn hơn hoặc thêm bộ nhớ tùy nhu cầu của mình.

Frag siêu nhanh, luôn dẫn đầu

Công nghệ GameFirst III trên laptop GL552VX-DM070D ưu tiên lưu lượng dữ liệu game trên toàn hệ thống mạng của bạn. Trải nghiệm chơi game sẽ được mượt hơn, và hiện tượng lag chỉ còn là dĩ vãng — để chỉ số frag của bạn luôn được đặt lên đầu.

Sự tinh tế của màn hình Full HD, không hề gây nhức mắt



Màn hiển thị 15,6 inch IPS Full HD rất lớn của GL552VX-DM070D có một lớp hoàn tất nhám đen triệt tiêu hiện tượng nhức mắt cho trải nghiệm hình ảnh vượt trội. Và với góc nhìn rộng 178 độ, bạn sẽ được thưởng thức những màu sắc hoành tráng sinh động từ cả những vị trí nhìn hẹp nhất.

Chất lượng âm thanh tuyệt hảo



Máy tính xách tay Asus GL552VX-DM070D phát ra những âm thanh trung thực nhất, rõ ràng và mạnh mẽ nhất với công nghệ SonicMaster độc quyền, trong khi tiện ích ROG AudioWizard đi kèm lại cung cấp đến năm chế độ thiết lập sẵn dành riêng cho các dòng game phổ biến – giúp bạn đắm chìm trong thế giới game tuyệt vời.', 2, '24 tháng', 'ip732blk.jpg', 'jkhHJF11.jpg');



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


