
-- 1. Dữ liệu cho bảng Users
INSERT INTO users (username, password, firstname, lastname, email, phone, birthday, join_date, status, main_address) VALUES
                                                                                                                         ('nguyenvana', 'password123','Nguyen Van','A', 'vana@gmail.com', '0901234567', '1995-05-20', '2023-10-01','ACTIVE','123 Ly Thuong Kiet, Dist 10'),
                                                                                                                         ('lethib', 'password123','Nguyen Thi','B', 'thib@gmail.com', '0912345678', '1998-08-15', '2023-11-12','ACTIVE','268 Ly Thuong Kiet, Dist 10'),
                                                                                                                         ('tranvanc', 'password123','Nguyen Van','C', 'vanc@gmail.com', '0987654321', '2000-01-10', '2024-01-05','ACTIVE','1234 Ly Thuong Kiet, Dist 10');

INSERT INTO user_addresses (user_id, address_detail) VALUES
                                                         (1, '123 Ly Thuong Kiet, Dist 10'),
                                                         (1, '1231 Ly Thuong Kiet, Dist 10'),
                                                         (1, '1232 Ly Thuong Kiet, Dist 10'),
                                                         (2, '268 Ly Thuong Kiet, Dist 10'),
                                                         (3, '1234 Ly Thuong Kiet, Dist 10');

INSERT INTO categories (category_name) VALUES
                                                     ('Sữa rửa mặt'),
                                                     ('Kem chống nắng'),
                                                     ('Nước tẩy trang'),
                                                     ('Nước hoa'),
                                                     ('Kem dưỡng da');
