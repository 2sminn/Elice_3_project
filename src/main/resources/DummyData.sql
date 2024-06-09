-- academy 테이블에 데이터 삽입
INSERT INTO academy (academy_name, academy_email, business_number, zip_code, address, address_detail, landline_number, stamp_image_url, created_at, updated_at, user_id)
VALUES
    ('해밀수학', 'info@mathacademy.com', '123-45-67890', 12345, '서울특별시 강남구 테헤란로 123', 'A동 101호', '02-123-4567', 'http://imageurl.com/stamp.png', NOW(), NOW(), 1),
    ('영어학원', 'info@englishacademy.com', '234-56-78901', 23456, '서울특별시 서초구 서초대로 456', 'B동 202호', '02-234-5678', 'http://imageurl.com/stamp.png', NOW(), NOW(), 2);

-- academy_student 테이블에 데이터 삽입
INSERT INTO academy_student (student_name, birth_date, phone_number, email, grade, school_name, created_at, updated_at)
VALUES
    ('John Doe', '2005-05-15', '010-1111-1111', 'john.doe@example.com', '10', 'Springfield High', NOW(), NOW()),
    ('Jane Smith', '2006-06-20', '010-2222-2222', 'jane.smith@example.com', '11', 'Springfield High', NOW(), NOW()),
    ('Alice Johnson', '2004-04-25', '010-3333-3333', 'alice.johnson@example.com', '12', 'Springfield High', NOW(), NOW()),
    ('Bob Brown', '2007-07-30', '010-4444-4444', 'bob.brown@example.com', '9', 'Springfield High', NOW(), NOW()),
    ('Charlie Davis', '2005-03-10', '010-5555-5555', 'charlie.davis@example.com', '10', 'Springfield High', NOW(), NOW()),
    ('David Evans', '2006-08-15', '010-6666-6666', 'david.evans@example.com', '11', 'Springfield High', NOW(), NOW()),
    ('Eva Green', '2004-09-20', '010-7777-7777', 'eva.green@example.com', '12', 'Springfield High', NOW(), NOW()),
    ('Frank Harris', '2007-10-25', '010-8888-8888', 'frank.harris@example.com', '9', 'Springfield High', NOW(), NOW()),
    ('Grace King', '2005-11-30', '010-9999-9999', 'grace.king@example.com', '10', 'Springfield High', NOW(), NOW()),
    ('Henry Lee', '2006-12-05', '010-1010-1010', 'henry.lee@example.com', '11', 'Springfield High', NOW(), NOW()),
    ('Irene Clark', '2005-02-12', '010-1111-2222', 'irene.clark@example.com', '10', 'Springfield High', NOW(), NOW()),
    ('Jack White', '2006-03-23', '010-2222-3333', 'jack.white@example.com', '11', 'Springfield High', NOW(), NOW()),
    ('Kate Adams', '2004-04-17', '010-3333-4444', 'kate.adams@example.com', '12', 'Springfield High', NOW(), NOW()),
    ('Liam Turner', '2007-05-28', '010-4444-5555', 'liam.turner@example.com', '9', 'Springfield High', NOW(), NOW()),
    ('Mia Scott', '2005-06-19', '010-5555-6666', 'mia.scott@example.com', '10', 'Springfield High', NOW(), NOW()),
    ('Noah Morris', '2006-07-10', '010-6666-7777', 'noah.morris@example.com', '11', 'Springfield High', NOW(), NOW()),
    ('Olivia Lewis', '2004-08-31', '010-7777-8888', 'olivia.lewis@example.com', '12', 'Springfield High', NOW(), NOW()),
    ('Paul Walker', '2007-09-22', '010-8888-9999', 'paul.walker@example.com', '9', 'Springfield High', NOW(), NOW()),
    ('Quinn Young', '2005-10-03', '010-9999-0000', 'quinn.young@example.com', '10', 'Springfield High', NOW(), NOW()),
    ('Rachel Harris', '2006-11-14', '010-0000-1111', 'rachel.harris@example.com', '11', 'Springfield High', NOW(), NOW()),
    ('Sam Carter', '2004-12-25', '010-1111-0000', 'sam.carter@example.com', '12', 'Springfield High', NOW(), NOW());

-- bill 테이블에 데이터 삽입
INSERT INTO bill (total_price, due_date, created_at, updated_at, message, status, academy_id, student_id)
VALUES
    (500000, '2024-05-21', NOW(), NOW(), 'Monthly fee', 'BEFORE', 1, 1),
    (500000, '2024-05-21', NOW(), NOW(), 'Monthly fee', 'PAID', 1, 2),
    (500000, '2024-05-21', NOW(), NOW(), 'Monthly fee', 'BEFORE', 1, 3),
    (500000, '2024-05-21', NOW(), NOW(), 'Monthly fee', 'PAID', 1, 4),
    (500000, '2024-05-21', NOW(), NOW(), 'Monthly fee', 'BEFORE', 1, 5),
    (500000, '2024-05-21', NOW(), NOW(), 'Monthly fee', 'PAID', 1, 6),
    (500000, '2024-05-21', NOW(), NOW(), 'Monthly fee', 'BEFORE', 1, 7),
    (500000, '2024-05-21', NOW(), NOW(), 'Monthly fee', 'PAID', 1, 8),
    (500000, '2024-05-21', NOW(), NOW(), 'Monthly fee', 'BEFORE', 1, 9),
    (500000, '2024-05-21', NOW(), NOW(), 'Monthly fee', 'PAID', 1, 10),
    (500000, '2024-05-21', NOW(), NOW(), 'Monthly fee', 'BEFORE', 1, 11),
    (500000, '2024-05-21', NOW(), NOW(), 'Monthly fee', 'PAID', 1, 12),
    (500000, '2024-05-21', NOW(), NOW(), 'Monthly fee', 'BEFORE', 1, 13),
    (500000, '2024-05-21', NOW(), NOW(), 'Monthly fee', 'PAID', 1, 14),
    (500000, '2024-05-21', NOW(), NOW(), 'Monthly fee', 'BEFORE', 1, 15),
    (500000, '2024-05-21', NOW(), NOW(), 'Monthly fee', 'PAID', 1, 16),
    (500000, '2024-05-21', NOW(), NOW(), 'Monthly fee', 'BEFORE', 1, 17),
    (500000, '2024-05-21', NOW(), NOW(), 'Monthly fee', 'PAID', 1, 18),
    (500000, '2024-05-21', NOW(), NOW(), 'Monthly fee', 'BEFORE', 1, 19),
    (500000, '2024-05-21', NOW(), NOW(), 'Monthly fee', 'PAID', 1, 20),
    (500000, '2024-05-21', NOW(), NOW(), 'Monthly fee', 'BEFORE', 1, 21);

-- lecture 테이블에 데이터 삽입
INSERT INTO lecture (academy_id, lecture_name, price, created_at, updated_at, student_id, lecture_status, teacher_name)
VALUES
    (1, 'Math', 3000, NOW(), NOW(), 1, 'OPEN', 'Mr. Kim'),
    (1, 'English', 3000, NOW(), NOW(), 2, 'OPEN', 'Mrs. Lee'),
    (1, 'Science', 3000, NOW(), NOW(), 3, 'OPEN', 'Mr. Park'),
    (1, 'History', 3000, NOW(), NOW(), 4, 'OPEN', 'Mr. Choi'),
    (1, 'Geography', 3000, NOW(), NOW(), 5, 'OPEN', 'Mr. Jung'),
    (1, 'Math', 3000, NOW(), NOW(), 6, 'OPEN', 'Mr. Kim'),
    (1, 'English', 3000, NOW(), NOW(), 7, 'OPEN', 'Mrs. Lee'),
    (1, 'Science', 3000, NOW(), NOW(), 8, 'OPEN', 'Mr. Park'),
    (1, 'History', 3000, NOW(), NOW(), 9, 'OPEN', 'Mr. Choi'),
    (1, 'Geography', 3000, NOW(), NOW(), 10, 'OPEN', 'Mr. Jung'),
    (1, 'Math', 3000, NOW(), NOW(), 11, 'OPEN', 'Mr. Kim'),
    (1, 'English', 3000, NOW(), NOW(), 12, 'OPEN', 'Mrs. Lee'),
    (1, 'Science', 3000, NOW(), NOW(), 13, 'OPEN', 'Mr. Park'),
    (1, 'History', 3000, NOW(), NOW(), 14, 'OPEN', 'Mr. Choi'),
    (1, 'Geography', 3000, NOW(), NOW(), 15, 'OPEN', 'Mr. Jung'),
    (1, 'Math', 3000, NOW(), NOW(), 16, 'OPEN', 'Mr. Kim'),
    (1, 'English', 3000, NOW(), NOW(), 17, 'OPEN', 'Mrs. Lee'),
    (1, 'Science', 3000, NOW(), NOW(), 18, 'OPEN', 'Mr. Park'),
    (1, 'History', 3000, NOW(), NOW(), 19, 'OPEN', 'Mr. Choi'),
    (1, 'Geography', 3000, NOW(), NOW(), 20, 'OPEN', 'Mr. Jung'),
    (1, 'Math', 3000, NOW(), NOW(), 21, 'OPEN', 'Mr. Kim');

-- student_payment_status 테이블에 데이터 삽입
INSERT INTO student_payment_status (bill_id, payment_id, student_id, updated_at)
VALUES
    (1, 1, 1, NOW()),
    (2, 2, 2, NOW()),
    (3, 3, 3, NOW()),
    (4, 4, 4, NOW()),
    (5, 5, 5, NOW()),
    (6, 6, 6, NOW()),
    (7, 7, 7, NOW()),
    (8, 8, 8, NOW()),
    (9, 9, 9, NOW()),
    (10, 10, 10, NOW()),
    (11, 11, 11, NOW()),
    (12, 12, 12, NOW()),
    (13, 13, 13, NOW()),
    (14, 14, 14, NOW()),
    (15, 15, 15, NOW()),
    (16, 16, 16, NOW()),
    (17, 17, 17, NOW()),
    (18, 18, 18, NOW()),
    (19, 19, 19, NOW()),
    (20, 20, 20, NOW()),
    (21, 21, 21, NOW());
