
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

-- 3. Dữ liệu cho Categories
INSERT INTO categories (id, category_name) VALUES
                                                     (1, 'Sữa rửa mặt'),
                                                     (2, 'Kem chống nắng'),
                                                     (3, 'Nước tẩy trang'),
                                                     (4, 'Nước hoa'),
                                                     (5, 'Kem dưỡng da')
ON CONFLICT (id) DO NOTHING;

-- 4. Dữ liệu cho Products
INSERT INTO products (id, name, description, image, status) VALUES
(1, 'Sữa Rửa Mặt CeraVe Foaming Facial Cleanser', 'Sữa rửa mặt tạo bọt dành cho da dầu, giúp làm sạch sâu mà không gây khô da.', 'https://res.cloudinary.com/dyzcl7cbe/image/upload/v1740306144/cerave_foaming.jpg', 'ACTIVE'),
(2, 'Kem Chống Nắng La Roche-Posay Anthelios SPF50+', 'Kem chống nắng kiểm soát dầu, bảo vệ da tối ưu khỏi tia UVA/UVB.', 'https://res.cloudinary.com/dyzcl7cbe/image/upload/v1740306144/laroche_posay.jpg', 'ACTIVE')
ON CONFLICT (id) DO NOTHING;

-- 5. Liên kết sản phẩm và danh mục (Many-to-Many)
INSERT INTO product_categories (product_id, category_id) VALUES
(1, 1),
(2, 2)
ON CONFLICT DO NOTHING;

-- 6. Dữ liệu cho ProductVariant
INSERT INTO product_variant (id, product_id, product_variant_name, price, stock_quantity, description, status) VALUES
(1, 1, 'Chai 236ml', 350000, 100, 'Dung tích vừa phải cho sử dụng hàng ngày.', 'ACTIVE'),
(2, 1, 'Chai 473ml', 520000, 50, 'Tiết kiệm hơn với dung tích lớn.', 'ACTIVE'),
(3, 2, 'Tuýp 50ml', 450000, 80, 'Dạng gel khô thoáng, không nhờn rít.', 'ACTIVE')
ON CONFLICT (id) DO NOTHING;
