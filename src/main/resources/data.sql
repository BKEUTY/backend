-- 1. Dữ liệu cho bảng Users
INSERT INTO users (username, password, email, phone, birthday, join_date) VALUES
                                                                              ('nguyenvana', 'password123', 'vana@gmail.com', '0901234567', '1995-05-20', '2023-10-01'),
                                                                              ('lethib', 'password123', 'thib@gmail.com', '0912345678', '1998-08-15', '2023-11-12'),
                                                                              ('tranvanc', 'password123', 'vanc@gmail.com', '0987654321', '2000-01-10', '2024-01-05');

-- 2. Dữ liệu cho bảng Products (Mỹ phẩm)
INSERT INTO products (name, description, price, quantity, image) VALUES
                                                                     ('Sữa Rửa Mặt Cetaphil Gentle Skin Cleanser', 'Sữa rửa mặt dịu nhẹ cho mọi loại da, không gây kích ứng, dung tích 500ml', 350000, 100, 'https://thefaceshop.com.vn/cdn/shop/articles/sua-rua-mat-cetaphil-1.jpg'),
                                                                     ('Nước Tẩy Trang Bioderma Sensibio H2O', 'Nước tẩy trang dành cho da nhạy cảm, làm sạch sâu mà không cần rửa lại', 395000, 85, 'https://cdn.tgdd.vn/Products/Images/2351/231175/bhx/nuoc-tay-trang-bioderma-sensibio-h2o-nap-hong-500ml-202310180956276800.jpg'),
                                                                     ('Sữa Rửa Mặt CeraVe Foaming Facial Cleanser', 'Sữa rửa mặt tạo bọt dành cho da dầu, giúp làm sạch sâu lỗ chân lông', 380000, 120, 'https://media.hcdn.vn/catalog/product/s/u/sua-rua-mat-cerave-giup-lam-sach-sau-cho-da-dau-355ml_1.jpg'),
                                                                     ('Dầu Tẩy Trang Shu Uemura Ultime8', 'Dầu tẩy trang cao cấp giúp làm sạch lớp trang điểm cứng đầu và dưỡng da', 1250000, 30, 'https://shuuemura.com.vn/cdn/shop/files/shuuemura-ultime8-cleansing-oil.jpg'),
                                                                     ('Sữa Rửa Mặt La Roche-Posay Effaclar', 'Gel rửa mặt tạo bọt cho da dầu và da mụn nhạy cảm', 420000, 90, 'https://media.hcdn.vn/catalog/product/f/a/facebook-dynamic-gel-rua-mat-la-roche-posay-danh-cho-da-dau-nhay-cam-400ml-1669865181_img_385x385_647610_fit_center.jpg'),

-- Kem chống nắng
                                                                     ('Kem Chống Nắng La Roche-Posay Anthelios', 'Kem chống nắng kiểm soát dầu, bảo vệ da tối ưu khỏi tia UVA/UVB', 485000, 150, 'https://media.hcdn.vn/catalog/product/k/e/kem-chong-nang-la-roche-posay-anthelios-uvmune-400-oil-control-gel-cream-50ml_1.jpg'),
                                                                     ('Kem Chống Nắng Anessa Perfect UV', 'Sữa chống nắng bảo vệ hoàn hảo với công nghệ Aqua Booster', 550000, 70, 'https://media.hcdn.vn/catalog/product/s/u/sua-chong-nang-anessa-perfect-uv-sunscreen-mild-milk-cho-da-nhay-cam-60ml_1.jpg'),
                                                                     ('Kem Chống Nắng Skin1004 Madagascar Centella', 'Kem chống nắng rau má lành tính, cấp ẩm và bảo vệ da', 320000, 110, 'https://media.hcdn.vn/catalog/product/k/e/kem-chong-nang-skin1004-chiet-xuat-rau-ma-cho-da-nhay-cam-spf50-pa-50ml-1_1.jpg'),
                                                                     ('Kem Chống Nắng Innisfree Intensive Triple-Shield', 'Kem chống nắng đa năng: làm sáng, chống lão hóa và chống tia cực đại', 280000, 65, 'https://media.hcdn.vn/catalog/product/k/e/kem-chong-nang-innisfree-nang-tong-bao-ve-da-50ml_1.jpg'),
                                                                     ('Xịt Chống Nắng Vichy Ideal Soleil', 'Xịt chống nắng không gây nhờn rít, tiện lợi khi đi du lịch', 450000, 45, 'https://media.hcdn.vn/catalog/product/x/i/xit-chong-nang-vichy-bao-ve-da-va-ngan-sam-da-spf-50-200ml-1635398205_img_385x385_647610_fit_center.jpg'),

-- Serum & Đặc trị
                                                                     ('Serum Klairs Freshly Juiced Vitamin Drop', 'Serum Vitamin C giúp làm sáng da, mờ thâm nám hiệu quả', 345000, 55, 'https://media.hcdn.vn/catalog/product/t/i/tinh-chat-dear-klairs-lam-sang-da-mo-tham-35ml_1.jpg'),
                                                                     ('The Ordinary Niacinamide 10% + Zinc 1%', 'Tinh chất giảm mụn, kiểm soát dầu và thu nhỏ lỗ chân lông', 220000, 200, 'https://media.hcdn.vn/catalog/product/t/i/tinh-chat-the-ordinary-niacinamide-10-zinc-1-giam-mun-30ml_1.jpg'),
                                                                     ('Serum Estee Lauder Advanced Night Repair', 'Tinh chất phục hồi da ban đêm huyền thoại, chống lão hóa đỉnh cao', 2100000, 25, 'https://media.hcdn.vn/catalog/product/s/e/serum-estee-lauder-phuc-hoi-da-ban-dem-50ml_1.jpg'),
                                                                     ('Serum B5 La Roche-Posay Hyalu B5', 'Dưỡng chất giúp phục hồi và tái tạo da, cấp ẩm sâu', 850000, 40, 'https://media.hcdn.vn/catalog/product/d/u/duong-chat-la-roche-posay-giup-tai-tao-phuc-hoi-da-30ml-1647413346_img_385x385_647610_fit_center.jpg'),
                                                                     ('Serum Skin1004 Rau Má', 'Tinh chất rau má cô đặc giúp làm dịu da kích ứng và giảm mụn', 310000, 95, 'https://media.hcdn.vn/catalog/product/t/i/tinh-chat-rau-ma-skin1004-lam-diu-da-100ml_1.jpg'),

-- Kem dưỡng ẩm
                                                                     ('Kem Dưỡng Clinique Moisture Surge 100H', 'Kem dưỡng ẩm dạng gel giúp cấp nước tức thì cho da suốt 100 giờ', 1100000, 35, 'https://media.hcdn.vn/catalog/product/k/e/kem-duong-am-clinique-moisture-surge-100h-auto-replenishing-hydrator-50ml_1.jpg'),
                                                                     ('Kem Dưỡng Neutrogena Hydro Boost Water Gel', 'Gel dưỡng ẩm chuyên sâu cho da khô và da dầu thiếu nước', 360000, 130, 'https://media.hcdn.vn/catalog/product/k/e/kem-duong-am-neutrogena-cap-nuoc-cho-da-dau-50g_1.jpg'),
                                                                     ('Kem Dưỡng Kiehl’s Ultra Facial Cream', 'Kem dưỡng ẩm suốt 24 giờ, phù hợp với khí hậu khô lạnh', 850000, 50, 'https://media.hcdn.vn/catalog/product/k/e/kem-duong-am-kiehls-ultra-facial-cream-50ml_1.jpg'),
                                                                     ('Kem Dưỡng Rau Má Skin1004', 'Kem dưỡng làm dịu da, củng cố hàng rào bảo vệ da', 290000, 80, 'https://media.hcdn.vn/catalog/product/k/e/kem-duong-skin1004-rau-ma-lam-diu-da-75ml_1.jpg'),
                                                                     ('Kem Dưỡng Laneige Water Bank Blue HA', 'Kem dưỡng ẩm thế hệ mới giúp phục hồi da hư tổn', 750000, 60, 'https://media.hcdn.vn/catalog/product/k/e/kem-duong-laneige-cap-am-cho-da-thuong-va-da-kho-50ml_1.jpg'),

-- Son môi & Trang điểm
                                                                     ('Son Black Rouge Air Fit Velvet Tint A12', 'Màu đỏ nâu quyến rũ, chất son mịn lì như nhung', 165000, 300, 'https://media.hcdn.vn/catalog/product/s/o/son-kem-li-black-rouge-air-fit-velvet-tint-a12-das-unten-rot-36-g_1.jpg'),
                                                                     ('Son Romand Juicy Lasting Tint #06', 'Son tint bóng màu sung ngọt ngào, giữ màu lâu trôi', 155000, 250, 'https://media.hcdn.vn/catalog/product/s/o/son-tint-li-romand-juicy-lasting-tint-06-figfig-55g_1.jpg'),
                                                                     ('Son MAC Powder Kiss Devoted To Chili', 'Màu đỏ gạch thời thượng, chất son lì mịn mượt', 550000, 100, 'https://media.hcdn.vn/catalog/product/s/o/son-li-mac-powder-kiss-lipstick-923-stay-curious-3g_1.jpg'),
                                                                     ('Phấn Nước Missha Magic Cushion', 'Cushion che phủ tốt, mỏng nhẹ tự nhiên cho da', 240000, 140, 'https://media.hcdn.vn/catalog/product/p/h/phan-nuoc-missha-magic-cushion-cover-lasting-spf50-pa-no-21-15g_1.jpg'),
                                                                     ('Phấn Phủ Bột Innisfree No-Sebum', 'Phấn phủ kiềm dầu vượt trội, giữ lớp nền khô thoáng', 135000, 400, 'https://media.hcdn.vn/catalog/product/p/h/phan-phu-bot-innisfree-kiem-dau-5g_1.jpg'),

-- Mặt nạ & Chăm sóc khác
                                                                     ('Mặt Nạ Đất Sét Kiehl’s Rare Earth', 'Mặt nạ giúp hút sạch dầu thừa và se khít lỗ chân lông', 750000, 40, 'https://media.hcdn.vn/catalog/product/m/a/mat-na-dat-set-kiehls-lam-sach-sau-va-se-khit-lo-chan-long-125ml_1.jpg'),
                                                                     ('Mặt Nạ Ngủ Môi Laneige Lip Sleeping Mask', 'Dưỡng môi mềm mịn, trị thâm môi hiệu quả sau một đêm', 320000, 180, 'https://media.hcdn.vn/catalog/product/m/a/mat-na-ngu-moi-laneige-huong-qua-mong-20g_1.jpg'),
                                                                     ('Mặt Nạ Giấy Innisfree My Real Squeeze', 'Mặt nạ chiết xuất thiên nhiên cấp ẩm tức thì (combo 10 miếng)', 200000, 500, 'https://media.hcdn.vn/catalog/product/c/o/combo-10-mat-na-giấy-innisfree-my-real-squeeze-mask-ex-20ml-x-10_1.jpg'),
                                                                     ('Tẩy Tế Bào Chết Paula’s Choice BHA 2%', 'Dung dịch loại bỏ tế bào chết hóa học, giảm mụn ẩn', 810000, 75, 'https://media.hcdn.vn/catalog/product/d/u/dung-dich-loai-bo-te-bao-chet-paulas-choice-skin-perfecting-2-bha-liquid-exfoliant-118ml_1.jpg'),
                                                                     ('Xịt Khoáng Vichy Mineralizing Thermal Water', 'Xịt khoáng làm dịu da, bảo vệ da trước tác nhân môi trường', 290000, 120, 'https://media.hcdn.vn/catalog/product/x/i/xit-khoang-vichy-lam-diu-da-va-bao-ve-da-150ml-1635398204_img_385x385_647610_fit_center.jpg');

-- 3. Dữ liệu cho bảng Orders (Giả định id user từ 1-3)
INSERT INTO orders (total, payment_method, order_date, address, user_id) VALUES
                                                                                           (830000, 'Cod', '2024-02-01 10:00:00', '268 Lý Thường Kiệt, Quận 10, TP.HCM', 1),
                                                                                           (320000, 'Banking', '2024-02-05 14:30:00', 'Ký túc xá Bách Khoa, Hòa Hải, Đà Nẵng', 2),
                                                                                           (150000, 'Cod', '2024-02-10 09:00:00', '1 Đại Cồ Việt, Hai Bà Trưng, Hà Nội', 3);

-- 4. Dữ liệu cho bảng OrderItems (Chi tiết cho các đơn hàng trên)
-- Order 1: 1 Cetaphil (350k) + 1 La Roche-Posay (480k) = 830k
INSERT INTO order_items (quantity, product_id, order_id) VALUES
                                                             (1, 1, 1),
                                                             (1, 2, 1);

-- Order 2: 1 Serum Klairs (320k)
INSERT INTO order_items (quantity, product_id, order_id) VALUES
    (1, 4, 2);

-- Order 3: 1 Son Black Rouge (150k)
INSERT INTO order_items (quantity, product_id, order_id) VALUES
    (1, 5, 3);

-- 5. Dữ liệu cho bảng CartItems
-- Giả sử đây là các món đồ khách đang bỏ vào giỏ nhưng chưa thanh toán
INSERT INTO cart_items (quantity, product_id,user_id) VALUES
                                                  (2, 3,1), -- 2 chai Bioderma
                                                  (1, 5,1); -- 1 thỏi son